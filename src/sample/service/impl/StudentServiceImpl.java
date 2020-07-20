package sample.service.impl;

import sample.entity.task.Task;
import sample.service.StudentService;
import sample.utils.db.connection.ConnectionUtil;
import sample.utils.db.constant.QueryConstant;
import sample.utils.db.disconnection.DisconnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {

    private final DisconnectionUtil disconnectionUtil = new DisconnectionUtil();

    @Override
    public boolean attachStudentToLaboratoryWork(Long studentId, Long moduleId) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.CREATE_NEW_JOIN);

            preparedStatement.setLong(1, studentId);
            preparedStatement.setLong(2, moduleId);
            preparedStatement.setLong(3, 0L);

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
                task.setDatabaseId(resultSet.getLong("database_id"));
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
    public Map<Integer, List<String>> executeTrueQuery(String query, String url) throws SQLException {

        Connection connection = ConnectionUtil.getConnectionForLaboratoryWork(url, "postgres", "root");
        Statement statement;
        ResultSet resultSet;
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
                currentColumn++;
            }
            result.put(currentRow, rowContent);
            rowContent = new ArrayList<>();
            currentRow++;
        }

        disconnectionUtil.setConnection(connection);
        disconnectionUtil.setStatement(statement);
        disconnectionUtil.setResultSet(resultSet);
        disconnectionUtil.disconnect();

        return result;
    }



}
