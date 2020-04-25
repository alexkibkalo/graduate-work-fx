package sample.controller.routing;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.entity.stage.ActiveStage;

import java.io.IOException;

public class RouteController {

    private RouteController() { }

    private static RouteController routeController = new RouteController();

    public static RouteController getInstance() {
        return routeController;
    }

    private void destroy(Stage stage){
        stage.close();
    }

    public void init(Stage stage) throws IOException {
        ActiveStage.setActiveStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource("../../templates/login.fxml"));
        stage.setTitle("SQL Teacher |ONLINE|");
        stage.setScene(new Scene(root, 1200, 750));
        stage.setResizable(false);
        stage.show();
    }

    public void redirect(Stage stage, String path) throws IOException {
        destroy(ActiveStage.getActiveStage());
        ActiveStage.setActiveStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource(path));
        stage.setTitle("SQL Teacher |ONLINE|");
        stage.setScene(new Scene(root, 1200, 750));
        stage.setResizable(false);
        stage.show();
    }

}
