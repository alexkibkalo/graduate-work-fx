package sample.controller.page;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.entity.module.ActiveModule;
import sample.service.impl.NotificationServiceImpl;
import sample.controller.routing.RouteController;
import sample.entity.user.ActiveUser;
import sample.service.StudentService;
import sample.service.TeacherService;
import sample.service.impl.StudentServiceImpl;
import sample.service.impl.TeacherServiceImpl;
import sample.utils.ui.DataLoader;

import java.io.IOException;

public class SelectModuleController {

    ////////////////////////////////// Variables ///////////////////////////////////

    @FXML
    private ChoiceBox<String> modules;

    @FXML
    private TextField visibleQueries;

    @FXML
    private Button send;

    @FXML
    private Button cancel;

    ///////////////////////////////////// State variables /////////////////////////////////////

    private final ActiveUser activeUser = ActiveUser.getActiveUser();

    private final ActiveModule activeModule = ActiveModule.getActiveModule();

    ///////////////////////////////////// Services /////////////////////////////////////

    private final StudentService studentService = new StudentServiceImpl();

    private final TeacherService teacherService = new TeacherServiceImpl();

    private final NotificationServiceImpl notificationService = new NotificationServiceImpl();

    ////////////////////////////////// Initialize block /////////////////////////////////

    @FXML
    public void initialize() {
        visibleQueries.setText("10");
        DataLoader.loadDataToChoiceBox(
                modules,
                teacherService.findAllNotFinishedModules(
                        teacherService.findIdByStudentName(activeUser.username)
                )
        );

        send.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                if (!studentService.hasStudentLaboratoryWork(
                        teacherService.findIdByStudentName(activeUser.username),
                        teacherService.findIdByModuleName(modules.getSelectionModel().getSelectedItem())
                )) {
                    if (studentService.attachStudentToLaboratoryWork(
                            teacherService.findIdByStudentName(activeUser.username),
                            teacherService.findIdByModuleName(modules.getSelectionModel().getSelectedItem()),
                            Long.parseLong(visibleQueries.getText())
                    )) {
                        ActiveModule.setActiveModuleFields(modules.getSelectionModel().getSelectedItem());
                        RouteController.getInstance().redirect(new Stage(), "../../templates/student.fxml");
                    } else {
                        notificationService.showSomethingWentWrong();
                    }
                } else {
                    ActiveModule.setActiveModuleFields(modules.getSelectionModel().getSelectedItem());
                    RouteController.getInstance().redirect(new Stage(), "../../templates/student.fxml");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                activeUser.username = null;
                activeModule.moduleName = null;
                RouteController.getInstance().redirect(new Stage(), "../../templates/login.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
