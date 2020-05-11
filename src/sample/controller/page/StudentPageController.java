package sample.controller.page;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.controller.routing.RouteController;
import sample.entity.module.ActiveModule;
import sample.entity.task.Task;
import sample.entity.user.ActiveUser;
import sample.service.NotificationService;
import sample.service.StudentService;
import sample.service.TaskService;
import sample.service.TeacherService;
import sample.service.impl.NotificationServiceImpl;
import sample.service.impl.StudentServiceImpl;
import sample.service.impl.TaskServiceImpl;
import sample.service.impl.TeacherServiceImpl;

import java.io.IOException;
import java.util.List;

public class StudentPageController {

    ////////////////////////////////// Variables ///////////////////////////////////

    @FXML
    private Button logout;

    @FXML
    private Button finishLab;

    @FXML
    private TabPane tasks;

    @FXML
    private Label nickname;

    ///////////////////////////////////// State variables /////////////////////////////////////

    private final ActiveUser activeUser = ActiveUser.getActiveUser();

    private final ActiveModule activeModule = ActiveModule.getActiveModule();

    ///////////////////////////////////// Services /////////////////////////////////////

    private final StudentService studentService = new StudentServiceImpl();

    private final TaskService taskService = new TaskServiceImpl();

    private final TeacherService teacherService = new TeacherServiceImpl();

    private final NotificationService notificationService = new NotificationServiceImpl();

    ////////////////////////////////// Initialize block /////////////////////////////////

    @FXML
    public void initialize() {
        nickname.setText("Welcome, " + ActiveUser.getActiveUser().username);
        nickname.setTextFill(Color.web("#ffffff"));
        taskService.initializeTasks(
                studentService.findAllQueriesByLabId(
                        teacherService.findIdByModuleName(activeModule.moduleName)
                ),
                tasks
        );

        logout.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    try {
                        activeUser.username = null;
                        activeModule.moduleName = null;
                        RouteController.getInstance().redirect(new Stage(), "../../templates/login.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        finishLab.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Long userId = teacherService.findIdByStudentName(activeUser.username);
            Long moduleId = teacherService.findIdByModuleName(activeModule.moduleName);
            List<Task> allQueriesByLabId = studentService.findAllQueriesByLabId(
                    teacherService.findIdByModuleName(activeModule.moduleName)
            );
            boolean flag = true;
            for (Task task : allQueriesByLabId) {
                if(taskService.existsRowInDB(userId, task.getId())){
                    flag = false;
                }
            }
            if(flag) {
                studentService.finishLab(userId, moduleId);
                try {
                    activeModule.moduleName = null;
                    RouteController.getInstance().redirect(new Stage(), "../../templates/pre-authorization.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                notificationService.showFinishLabNotAllow();
            }
        });
    }
}
