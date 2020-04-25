package sample.controller.page;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.controller.routing.RouteController;
import sample.entity.module.ActiveModule;
import sample.entity.student.ActiveUser;
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
    private TextArea query;

    @FXML
    private TextArea resultSet;

    @FXML
    private Label taskDescription;

    @FXML
    private TextArea trueResultSet;

    @FXML
    private Button check;

    @FXML
    private Button showTrueResult;

    @FXML
    private Button finishLab;

    @FXML
    private Pagination pagination;

    ///////////////////////////////////// State variables /////////////////////////////////////

    private final ActiveUser activeUser = ActiveUser.getActiveUser();

    private final ActiveModule activeModule = ActiveModule.getActiveModule();

    ///////////////////////////////////// Services /////////////////////////////////////

    private final StudentService studentService = new StudentServiceImpl();

    private final TeacherService teacherService = new TeacherServiceImpl();

    ////////////////////////////////// Initialize block /////////////////////////////////

    @FXML
    public void initialize() {

        logout.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    try {
                        RouteController.getInstance().redirect(new Stage(), "../../templates/login.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        finishLab.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            studentService.finishLab(
                    teacherService.findIdByStudentName(activeUser.username),
                    teacherService.findIdByModuleName(activeModule.moduleName)
            );
        });
    }
}
