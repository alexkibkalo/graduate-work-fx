package sample.service.impl;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.entity.task.Task;
import sample.service.StudentService;
import sample.utils.db.connection.ConnectionUtil;
import sample.utils.db.constant.QueryConstant;
import sample.utils.db.disconnection.DisconnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final DisconnectionUtil disconnectionUtil = new DisconnectionUtil();

    @Override
    public boolean attachStudentToLaboratoryWork(Long studentId, Long moduleId, Long visibleQuery) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.CREATE_NEW_JOIN);

            preparedStatement.setLong(1, studentId);
            preparedStatement.setLong(2, moduleId);
            preparedStatement.setLong(3, visibleQuery);
            preparedStatement.setLong(4, 0L);

            preparedStatement.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.disconnect();
        }
        return false;
    }

    @Override
    public boolean hasStudentLaboratoryWork(Long userId, Long labId) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_JOIN_BY_USER_ID_AND_LAB_ID);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, labId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return false;
    }

    @Override
    public void finishLab(Long userId, Long labId) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.FINISH_LAB_WORK);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, labId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
    }

    @Override
    public List<Task> findAllQueriesByLabId(Long labId) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Task> tasks = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_ALL_QUERIES_BY_LAB_ID);
            preparedStatement.setLong(1, labId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setDescription(resultSet.getString("description"));
                task.setTrueQuery(resultSet.getString("name"));
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return tasks;
    }


    @Override
    public String checkQuery(String query) {
        return null;
    }

    @Override
    public List<String> executeTrueQuery(String query) throws SQLException {

        Connection connection = ConnectionUtil.getConnection();
        Statement statement;
        ResultSet resultSet;
        List<String> rows = new ArrayList<>();

        statement = connection.createStatement();
        statement.executeQuery(query);
        resultSet = statement.getResultSet();

        while (resultSet.next()) {
            rows.add(resultSet.getString("id"));
        }

        disconnectionUtil.setConnection(connection);
        disconnectionUtil.setStatement(statement);
        disconnectionUtil.setResultSet(resultSet);
        disconnectionUtil.disconnect();

        return rows;
    }

    @Override
    public void initializeTasks(List<Task> list, TabPane tasks) {
        int index = 1;
        for (Task task : list) {
            Tab tab = new Tab("Task " + index);

            VBox content = new VBox();

            content.getChildren().add(new Label());
            content.getChildren().add(subContent1(task.getDescription()));
            content.getChildren().add(subContent2(task.getTrueQuery()));
            content.getChildren().add(subContent3());
            content.getChildren().add(subContent4());

            content.setSpacing(20);

            tab.setContent(content);
            tasks.getTabs().add(tab);

            index += 1;
        }
    }

    public HBox subContent1(String description) {
        HBox subContent = new HBox();

        Label taskDescription = new Label(description);
        taskDescription.setFont(Font.font(30));
        taskDescription.setWrapText(true);

        subContent.getChildren().add(new Label());
        subContent.getChildren().add(taskDescription);

        subContent.setSpacing(15);

        return subContent;
    }

    public HBox subContent2(String trueResult) {
        HBox subContent = new HBox();

        Label queryLabel = new Label();
        queryLabel.setText("Query  ");

        TextArea queryBody = new TextArea();
        queryBody.setStyle("-fx-max-height: 200; -fx-max-width: 430");

        Label resultLabel = new Label();
        resultLabel.setText("True result");

        TextArea resultBody = new TextArea();
        resultBody.setStyle("-fx-max-height: 200; -fx-max-width: 430");

        Button check = new Button();
        check.setText("Show result");

        check.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                for (String name : executeTrueQuery(trueResult)) {
                    stringBuilder.append(name).append("\n");
                }
                resultBody.setText(stringBuilder.toString());
            } catch (SQLException exception) {
                resultBody.setText(exception.getMessage());
            }
        });

        subContent.getChildren().add(new Label());
        subContent.getChildren().add(queryLabel);
        subContent.getChildren().add(queryBody);
        subContent.getChildren().add(resultLabel);
        subContent.getChildren().add(resultBody);
        subContent.getChildren().add(check);

        subContent.setSpacing(20);

        return subContent;
    }

    public HBox subContent3() {
        HBox subContent = new HBox();

        Label trueQueryLabel = new Label();
        trueQueryLabel.setText("Result ");

        TextArea trueResultBody = new TextArea();
        trueResultBody.setStyle("-fx-max-height: 200; -fx-max-width: 432");

        subContent.getChildren().add(new Label());
        subContent.getChildren().add(trueQueryLabel);
        subContent.getChildren().add(trueResultBody);

        subContent.setSpacing(20);

        return subContent;
    }

    public HBox subContent4() {
        Label emptySpace = new Label("\t\t");
        HBox subContent = new HBox();

        Button showResult = new Button();
        showResult.setText("Check query");

        subContent.getChildren().add(emptySpace);
        subContent.getChildren().add(showResult);

        subContent.setSpacing(20);

        return subContent;
    }
}
