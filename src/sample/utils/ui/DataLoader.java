package sample.utils.ui;

import javafx.scene.control.ChoiceBox;

import java.util.List;

public class DataLoader {

    public static void loadDataToChoiceBox(ChoiceBox<String> box, List<String> data){
        if(box != null && data != null && !data.isEmpty()) {
            box.getItems().clear();
            box.getItems().addAll(data);
        }
    }
}
