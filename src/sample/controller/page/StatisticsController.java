package sample.controller.page;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.controller.routing.RouteController;
import sample.service.StatisticService;
import sample.service.impl.StatisticServiceImpl;

import java.io.IOException;

public class StatisticsController {

    ////////////////////////////////// Variables ///////////////////////////////////

    @FXML
    private TabPane groups;

    @FXML
    private Button close;

    ///////////////////////////////////// Services /////////////////////////////////////

    private final StatisticService statisticService = new StatisticServiceImpl();

    ////////////////////////////////// Initialize block /////////////////////////////////

    @FXML
    public void initialize() {
        statisticService.initializeGroups(groups);

        close.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                RouteController.getInstance().redirect(new Stage(), "../../templates/teacher.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
