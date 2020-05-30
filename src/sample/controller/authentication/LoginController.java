package sample.controller.authentication;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.controller.routing.RouteController;
import sample.entity.user.ActiveUser;
import sample.service.PasswordService;
import sample.service.ValidationService;
import sample.service.impl.PasswordServiceImpl;
import sample.service.impl.ValidationServiceImpl;

import java.io.IOException;

public class LoginController {

    ////////////////////////////////// Variables ///////////////////////////////////

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Button buttonTeacher;

    @FXML
    private Label message;

    ///////////////////////////////////// Services /////////////////////////////////////

    private final ValidationService validationService = new ValidationServiceImpl();

    private final PasswordService passwordService = new PasswordServiceImpl();

    ////////////////////////////////// Initialize block /////////////////////////////////

    @FXML
    public void initialize() {
        buttonSignIn.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
                authentication("../../templates/pre-authorization.fxml", false)
        );
        buttonTeacher.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
                authentication("../../templates/teacher.fxml", true)
        );
    }

    /////////////////////////////////// Authentication ///////////////////////////////////

    private void authentication(String path, boolean isTeacher) {
        if (validationService.isEmptyFields(login.getText(), password.getText())) {
            message.setText("All field must be filed!");
        } else {
            if (validationService.authentication(login.getText(), passwordService.hashPassword(password.getText()), isTeacher)) {
                try {
                    ActiveUser.setActiveUserFields(login.getText());
                    RouteController.getInstance().redirect(new Stage(), path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                message.setText("Incorrect login or password!");
            }
        }
    }
}