package sample.controller.page;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.controller.routing.RouteController;
import sample.entity.module.ActiveModule;
import sample.entity.user.ActiveUser;
import sample.service.StudentService;
import sample.service.TeacherService;
import sample.service.impl.StudentServiceImpl;
import sample.service.impl.TeacherServiceImpl;

import java.io.IOException;

public class StudentPageController {

    ////////////////////////////////// Variables ///////////////////////////////////

    @FXML
    private Button logout;

    @FXML
    private Button finishLab;

    @FXML
    private TabPane tasks;

    private TextArea query;

    private TextArea result;

    private TextArea trueResult;

    private Button checkQuery;

    private Button showTrueResult;

    ///////////////////////////////////// State variables /////////////////////////////////////

    private final ActiveUser activeUser = ActiveUser.getActiveUser();

    private final ActiveModule activeModule = ActiveModule.getActiveModule();

    ///////////////////////////////////// Services /////////////////////////////////////

    private final StudentService studentService = new StudentServiceImpl();

    private final TeacherService teacherService = new TeacherServiceImpl();

    ////////////////////////////////// Initialize block /////////////////////////////////

    @FXML
    public void initialize() {

        studentService.initializeTasks(
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

        finishLab.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> studentService.finishLab(
                teacherService.findIdByStudentName(activeUser.username),
                teacherService.findIdByModuleName(activeModule.moduleName)
        ));
    }
}
