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

public class StudentPageController {

    ////////////////////////////////// Variables ///////////////////////////////////

    @FXML
    private Button logout;

    @FXML
    private TabPane tasks;

    @FXML
    private Label nickname;

    @FXML
    private Button finishLab;

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
        Long moduleId = teacherService.findIdByModuleName(activeModule.moduleName);
        Long studentId = teacherService.findIdByStudentName(activeUser.username);

        nickname.setText("Welcome, " + ActiveUser.getActiveUser().username);
        nickname.setTextFill(Color.web("#ffffff"));
        Long visibleQueries = teacherService.findCountVisibleQueriesByModuleId(
                teacherService.findIdByModuleName(activeModule.moduleName)
        );

        taskService.initializeTasks(
                studentService.findAllQueriesByLabId(moduleId),
                tasks,
                visibleQueries
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
            int numberExecutedQueriesForModule = studentService.getNumberExecutedQueriesForModule(moduleId);
            int numberVisibleQueriesForModule = studentService.getNumberVisibleQueriesForModule(moduleId);

            if(numberVisibleQueriesForModule == numberExecutedQueriesForModule){
                try {
                    studentService.finishLab(studentId, moduleId);
                    RouteController.getInstance().redirect(new Stage(), "../../pre-authorization.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                notificationService.showFinishLabNotAllow();
            }
        });
    }
}
