package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.controller.routing.RouteController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        RouteController.getInstance().init(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
