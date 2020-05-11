package sample.service.impl;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.entity.task.Task;
import sample.entity.user.ActiveUser;
import sample.service.TaskService;
import sample.utils.db.connection.ConnectionUtil;
import sample.utils.db.constant.QueryConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskServiceImpl implements TaskService {

    @Override
    public void initializeTasks(List<Task> list, TabPane tasks) {
        int index = 1;
        for (Task task : list) {
            Tab tab = new Tab("Task " + index);

            VBox content = new VBox();

            content.getChildren().add(new Label());

            ///////// Sub Content 1 /////////
            HBox subContent1 = new HBox();

            Label taskDescription = new Label(task.getDescription());
            taskDescription.setFont(Font.font(30));
            taskDescription.setWrapText(true);

            subContent1.getChildren().add(new Label());
            subContent1.getChildren().add(taskDescription);

            subContent1.setSpacing(15);
            /////// end Sub Content 1 ///////

            ///////// Sub Content 2 /////////
            HBox subContent2 = new HBox();

            Label queryLabel = new Label();
            queryLabel.setText("Query  ");

            TextArea queryBody = new TextArea();
            queryBody.setStyle("-fx-max-height: 200; -fx-max-width: 430");

            Label resultLabel = new Label();
            resultLabel.setText("True result");

            TextArea resultBody = new TextArea();
            resultBody.setStyle("-fx-max-height: 200; -fx-max-width: 430");

            Button showResult = new Button();
            showResult.setText("Show result");

            showResult.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                try {
                    Map<Integer, List<String>> resultMap = studentService.executeTrueQuery(
                            task.getTrueQuery(),
                            getDatabaseURLByID(task.getDatabaseId())
                    );
                    resultBody.setText(parseResult(resultMap));
                } catch (SQLException exception) {
                    resultBody.setText(exception.getMessage());
                }
            });

            subContent2.getChildren().add(new Label());
            subContent2.getChildren().add(queryLabel);
            subContent2.getChildren().add(queryBody);
            subContent2.getChildren().add(resultLabel);
            subContent2.getChildren().add(resultBody);
            subContent2.getChildren().add(showResult);

            subContent2.setSpacing(20);
            /////// end Sub Content 2 ///////

            ///////// Sub Content 3 /////////
            HBox subContent3 = new HBox();

            Label trueQueryLabel = new Label();
            trueQueryLabel.setText("Result ");

            TextArea trueResultBody = new TextArea();
            trueResultBody.setStyle("-fx-max-height: 200; -fx-max-width: 432");

            Label status = new Label();
            status.setText(task.getStatus());

            Label countOfTimes = new Label();
            countOfTimes.setText("0");

            subContent3.getChildren().add(new Label());
            subContent3.getChildren().add(trueQueryLabel);
            subContent3.getChildren().add(trueResultBody);
            subContent3.getChildren().add(new Label());
            subContent3.getChildren().add(status);
            subContent3.getChildren().add(new Label("Count of times: "));
            subContent3.getChildren().add(countOfTimes);

            subContent3.setSpacing(20);
            /////// end Sub Content 3 ///////

            ///////// Sub Content 4 /////////
            Label emptySpace = new Label("\t\t");
            HBox subContent4 = new HBox();

            Button check = new Button();
            check.setText("Check query");

            AtomicInteger counter = new AtomicInteger();

            check.setOnMouseClicked((event)->{
                counter.getAndIncrement();
                countOfTimes.setText(String.valueOf(counter));
                try {
                    trueResultBody.setText(
                            checkingQueryService.checkQuery(
                                    queryBody.getText(),
                                    getDatabaseURLByID(task.getDatabaseId()),
                                    task
                            )
                    );
                } catch (SQLException exception) {
                    trueResultBody.setText(exception.getMessage());
                }
                String statusText = task.getStatus();
                if (statusText.equals("Success!")) {
                    tab.setStyle("-fx-background-color: #A6F366");
                    status.setText(statusText);
                    status.setTextFill(Color.web("#4FD944"));
                    setOK(
                            task.getId(),
                            counter.get(),
                            teacherService.findIdByStudentName(ActiveUser.getActiveUser().username)
                    );
                } else {
                    tab.setStyle("-fx-background-color: #F36666");
                    status.setText(statusText);
                    status.setTextFill(Color.web("#F36666"));
                }
            });

            subContent4.getChildren().add(emptySpace);
            subContent4.getChildren().add(check);

            subContent4.setSpacing(20);
            /////// end Sub Content 4 ///////

            content.getChildren().add(subContent1);
            content.getChildren().add(subContent2);
            content.getChildren().add(subContent3);
            content.getChildren().add(subContent4);

            content.setSpacing(20);

            tab.setContent(content);
            tasks.getTabs().add(tab);

            index += 1;
        }
    }

    private String parseResult(Map<Integer, List<String>> resultMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, List<String>> entry : resultMap.entrySet()) {
            for (String column : entry.getValue()) {
                stringBuilder.append(column).append(" | ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String getDatabaseURLByID(Long id) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String url = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_URL_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                url = resultSet.getString("url");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.disconnect();
        }
        return url;
    }

    private void setOK(Long queryId, int countOfTimes, Long studentId) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.CREATE_STATUS_SUCCESS);

            preparedStatement.setLong(1, studentId);
            preparedStatement.setLong(2, countOfTimes);
            preparedStatement.setLong(3, queryId);

            if(existsRowInDB(studentId, queryId)){
                preparedStatement.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.disconnect();
        }
    }

    @Override
    public boolean existsRowInDB(Long studentId, Long queryId) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.EXISTS_BY_STUDENT_ID_AND_QUERY_ID);

            preparedStatement.setLong(1, studentId);
            preparedStatement.setLong(2, queryId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.disconnect();
        }
        return true;
    }
}