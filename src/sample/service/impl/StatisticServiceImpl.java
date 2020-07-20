package sample.service.impl;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import sample.entity.statistic.Statistic;
import sample.service.StatisticService;
import sample.utils.db.connection.ConnectionUtil;
import sample.utils.db.constant.QueryConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticServiceImpl implements StatisticService {

    @Override
    public void initializeGroups(TabPane tabPane) {
        List<String> groups = teacherService.findAllStudentGroups();
        Collections.sort(groups);

        for (String groupName : groups) {
            Tab tab = new Tab();
            tab.setText(groupName);

            VBox content = new VBox();

            content.getChildren().add(new Label());
            content.getChildren().add(
                    initTable(
                            population().stream()
                                    .filter(statistic -> statistic.getGroupName().equals(groupName))
                                    .collect(Collectors.toList())
                    )
            );

            tab.setContent(content);
            tabPane.getTabs().add(tab);
        }
    }

    public TableView<Statistic> initTable(List<Statistic> statistics) {
        TableView<Statistic> table = new TableView<>();
        table.setPrefWidth(350);
        table.setPrefHeight(800);

        TableColumn<Statistic, Long> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(30);
        table.getColumns().add(idColumn);

        TableColumn<Statistic, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(130);
        table.getColumns().add(nameColumn);

        TableColumn<Statistic, String> surnameColumn = new TableColumn<>("surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameColumn.setPrefWidth(130);
        table.getColumns().add(surnameColumn);

        TableColumn<Statistic, String> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setPrefWidth(230);
        table.getColumns().add(emailColumn);

        TableColumn<Statistic, Double> statusColumn = new TableColumn<>("status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setPrefWidth(70);
        table.getColumns().add(statusColumn);

        TableColumn<Statistic, Integer> countTimesColumn = new TableColumn<>("countTimes");
        countTimesColumn.setCellValueFactory(new PropertyValueFactory<>("countTimes"));
        countTimesColumn.setPrefWidth(100);
        table.getColumns().add(countTimesColumn);

        TableColumn<Statistic, String> labToExecuteColumn = new TableColumn<>("labToExecute");
        labToExecuteColumn.setCellValueFactory(new PropertyValueFactory<>("labToExecute"));
        labToExecuteColumn.setPrefWidth(485);
        table.getColumns().add(labToExecuteColumn);

        customiseFactory(statusColumn);
        table.getItems().addAll(statistics);

        return table;
    }

    public List<Statistic> population() {

        Connection connection = ConnectionUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<Statistic> statistics = new ArrayList<>();
        Statistic statistic;

        try {
            statement = connection.createStatement();
            statement.executeQuery(QueryConstant.POPULATION_STATISTICS);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Long userId = resultSet.getLong("id");
                statistic = new Statistic(
                        userId,
                        resultSet.getString("sname"),
                        resultSet.getString("surname"),
                        resultSet.getString("login"),
                        resultSet.getDouble("status"),
                        resultSet.getString("sgname")
                );
                statistic.setCountTimes(getCountOfTimesByUser(userId));
                statistic.setLabToExecute(getLabToExecute(userId));
                statistics.add(statistic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setStatement(statement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }

        return statistics;
    }

    private String getLabToExecute(Long userId) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        StringBuilder stringBuilder = new StringBuilder();
        String labs = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_ALL_NOT_FINISHED_MODULES);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String row = resultSet.getString("name");
                labs = row.substring(row.length() - 2);
                stringBuilder.append(labs).append(", ");
            }

            if(stringBuilder.length() > 0){
                labs = stringBuilder.substring(0, stringBuilder.length() - 2);
            } else {
                labs = "none";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.disconnect();
        }
        return labs;
    }

    public int getCountOfTimesByUser(Long userId) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        int times = 0;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.GET_COUNT_OF_TIMES);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                times = resultSet.getInt("common_count");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.disconnect();
        }
        return times;
    }

    private void customiseFactory(TableColumn<Statistic, Double> tableColumn) {
        tableColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);

                setText(empty ? "" : getItem().toString());
                setGraphic(null);

                TableRow<Statistic> currentRow = getTableRow();

                if (!isEmpty()) {
                    if (item < 50.0) {
                        currentRow.setStyle("-fx-background-color:#d56d6d");
                    } else if (item > 49.0 && item < 80) {
                        currentRow.setStyle("-fx-background-color:#ffe045");
                    } else {
                        currentRow.setStyle("-fx-background-color:#5bbf5b");
                    }
                }
            }
        });
    }
}

