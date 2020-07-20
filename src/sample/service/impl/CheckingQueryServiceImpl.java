package sample.service.impl;

import sample.entity.task.Task;
import sample.service.CheckingQueryService;
import sample.utils.db.connection.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckingQueryServiceImpl implements CheckingQueryService {

    @Override
    public String checkQuery(String query, String url, Task task) throws SQLException {

        Connection connection = ConnectionUtil.getConnectionForLaboratoryWork(url, "postgres", "root");
        Statement statement;
        ResultSet resultSet;
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, List<String>> result = new HashMap<>();
        List<String> rowContent = new ArrayList<>();

        statement = connection.createStatement();
        statement.executeQuery(query);
        resultSet = statement.getResultSet();

        int countOfColumn = resultSet.getMetaData().getColumnCount();
        int currentColumn;
        int currentRow = 1;

        while (resultSet.next()) {
            currentColumn = 1;
            while (currentColumn <= countOfColumn) {
                rowContent.add(resultSet.getString(currentColumn));
                stringBuilder.append(resultSet.getString(currentColumn)).append(" | ");
                currentColumn++;
            }
            result.put(currentRow, rowContent);
            rowContent = new ArrayList<>();
            stringBuilder.append("\n");
            currentRow++;
        }

        if (validateResult(result, task.getTrueQuery(), url)) {
            task.setStatus("Success!");
        } else {
            task.setStatus("Failed! Try again!");
        }

        disconnectionUtil.setConnection(connection);
        disconnectionUtil.setStatement(statement);
        disconnectionUtil.setResultSet(resultSet);
        disconnectionUtil.disconnect();

        return stringBuilder.toString();
    }

    private boolean validateResult(Map<Integer, List<String>> resultSet, String trueQuery, String url) throws SQLException {
        boolean status = false;
        Map<Integer, List<String>> trueResult = studentService.executeTrueQuery(trueQuery, url);
        if (resultSet != null && trueResult != null) {
            if (resultSet.size() == trueResult.size()) {
                int countRows = resultSet.size();
                while (countRows > 0) {
                    if (compareLists(resultSet.get(countRows), trueResult.get(countRows))) {
                        status = true;
                    } else {
                        status = false;
                        break;
                    }
                    countRows--;
                }
            }
        }
        return status;
    }

    private boolean compareLists(List<String> list1, List<String> list2) {
        boolean status = true;
        if (list1.size() == list2.size()) {
            for (int i = 0; i < list1.size(); i++) {
                if (!list1.get(i).equals(list2.get(i))) {
                    status = false;
                    break;
                }
            }
        } else {
            status = false;
        }
        return status;
    }
}
