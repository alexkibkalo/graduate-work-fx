package sample.entity.stage;

import javafx.stage.Stage;

public class ActiveStage {

    private static Stage activeStage;

    private ActiveStage(){}

    public static Stage getActiveStage(){
        return activeStage;
    }

    public static void setActiveStage(Stage stage){
        activeStage = stage;
    }
}
