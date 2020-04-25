package sample.controller.page;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.service.impl.NotificationServiceImpl;
import sample.controller.routing.RouteController;
import sample.service.TeacherService;
import sample.service.ValidationService;
import sample.service.impl.TeacherServiceImpl;
import sample.service.impl.ValidationServiceImpl;
import sample.utils.ui.DataLoader;

import java.io.IOException;

public class TeacherPageController {

    ////////////////////////////////// Variables ///////////////////////////////////

    @FXML
    private TextField studentGroupName;

    @FXML
    private TextField studentName;

    @FXML
    private TextField studentSurname;

    @FXML
    private TextField studentLogin;

    @FXML
    private TextField studentPassword;

    @FXML
    private ChoiceBox<String> studentGroupNames;

    @FXML
    private TextField moduleName;

    @FXML
    private TextField themeName;

    @FXML
    private ChoiceBox<String> moduleNames;

    @FXML
    private TextField databaseName;

    @FXML
    private TextField connectionToDB;

    @FXML
    private TextArea queryName;

    @FXML
    private TextArea description;

    @FXML
    private ChoiceBox<String> moduleNamesForQuery;

    @FXML
    private ChoiceBox<String> databaseNames;

    @FXML
    private Button logout;

    @FXML
    private Button addDatabase;

    @FXML
    private Button addQuery;

    @FXML
    private Button addGroup;

    @FXML
    private Button addStudent;

    @FXML
    private Button addModule;

    @FXML
    private Button addTheme;

    ///////////////////////////////////// Services /////////////////////////////////////

    private final ValidationService validationService = new ValidationServiceImpl();

    private final TeacherService teacherService = new TeacherServiceImpl();

    private final NotificationServiceImpl notificationService = new NotificationServiceImpl();

    ////////////////////////////////// Initialize block /////////////////////////////////
    @FXML
    public void initialize() {
        DataLoader.loadDataToChoiceBox(moduleNames, teacherService.findAllModules());
        DataLoader.loadDataToChoiceBox(moduleNamesForQuery, teacherService.findAllModules());
        DataLoader.loadDataToChoiceBox(studentGroupNames, teacherService.findAllStudentGroups());
        DataLoader.loadDataToChoiceBox(databaseNames, teacherService.findAllDatabases());

        logout.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    try {
                        RouteController.getInstance().redirect(new Stage(), "../../templates/login.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        addGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    if (validationService.studentGroupAlreadyExist(studentGroupName.getText())) {
                        if (teacherService.addStudentGroup(studentGroupName.getText())) {
                            notificationService.showStudentGroupSuccessfullyCreated();
                            DataLoader.loadDataToChoiceBox(studentGroupNames, teacherService.findAllStudentGroups());
                        } else {
                            notificationService.showSomethingWentWrong();
                        }
                    } else {
                        notificationService.showGroupAlreadyExist();
                    }
                }
        );

        addStudent.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    if (validationService.studentAlreadyExist(studentLogin.getText())) {
                        if (teacherService.addStudent(
                                studentName.getText(),
                                studentSurname.getText(),
                                studentLogin.getText(),
                                studentPassword.getText(),
                                teacherService.findIdByStudentGroupName(
                                        studentGroupNames.getSelectionModel().getSelectedItem()
                                )
                        )) {
                            notificationService.showStudentSuccessfullyCreated();
                        } else {
                            notificationService.showSomethingWentWrong();
                        }
                    } else {
                        notificationService.showStudentAlreadyExist();
                    }
                }
        );

        addModule.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    if (validationService.moduleAlreadyExist(moduleName.getText())) {
                        if (teacherService.addModule(moduleName.getText())) {
                            DataLoader.loadDataToChoiceBox(moduleNames, teacherService.findAllModules());
                            DataLoader.loadDataToChoiceBox(moduleNamesForQuery, teacherService.findAllModules());
                            notificationService.showModuleSuccessfullyCreated();
                        } else {
                            notificationService.showSomethingWentWrong();
                        }
                    } else {
                        notificationService.showModuleAlreadyExist();
                    }
                }
        );

        addTheme.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    if (validationService.themeAlreadyExist(themeName.getText())) {
                        if (teacherService.addTheme(
                                themeName.getText(),
                                teacherService.findIdByModuleName(
                                        moduleNames.getSelectionModel().getSelectedItem()
                                )
                        )) {
                            notificationService.showThemeSuccessfullyCreated();
                        } else {
                            notificationService.showSomethingWentWrong();
                        }
                    } else {
                        notificationService.showThemeAlreadyExist();
                    }
                }
        );

        addDatabase.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    if (validationService.databaseAlreadyExist(databaseName.getText())) {
                        if (teacherService.addDatabase(databaseName.getText(), connectionToDB.getText())) {
                            DataLoader.loadDataToChoiceBox(databaseNames, teacherService.findAllDatabases());
                            notificationService.showDatabaseSuccessfullyCreated();
                        } else {
                            notificationService.showSomethingWentWrong();
                        }
                    } else {
                        notificationService.showDatabaseAlreadyExist();
                    }
                }
        );

        addQuery.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    if (validationService.queryAlreadyExist(queryName.getText())) {
                        if (teacherService.addQuery(
                                queryName.getText(),
                                description.getText(),
                                teacherService.findIdByModuleName(
                                        moduleNamesForQuery.getSelectionModel().getSelectedItem()
                                ),
                                teacherService.findIdByDatabaseName(
                                        databaseNames.getSelectionModel().getSelectedItem()
                                )
                        )) {
                            notificationService.showQuerySuccessfullyCreated();
                        } else {
                            notificationService.showSomethingWentWrong();
                        }
                    } else {
                        notificationService.showQueryAlreadyExist();
                    }
                }
        );
    }
}
