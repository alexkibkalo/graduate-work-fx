package sample.controller.page;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.controller.routing.RouteController;

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



    ///////////////////////////////////// Services /////////////////////////////////////


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

        showTrueResult.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

                }
        );
    }
}
