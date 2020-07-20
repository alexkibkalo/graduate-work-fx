package sample.service.impl;

import sample.service.TaskService;
import sample.service.ValidationService;
import sample.utils.db.connection.ConnectionUtil;
import sample.utils.db.constant.QueryConstant;
import sample.utils.db.disconnection.DisconnectionUtil;

import java.sql.*;

public class ValidationServiceImpl implements ValidationService {

    private final DisconnectionUtil disconnectionUtil = new DisconnectionUtil();
    private final TaskService taskService = new TaskServiceImpl();

    @Override
    public boolean authentication(String login, String password, boolean isTeacher) {
        return isTeacher ? findUserByLoginAndPassword(login, password, QueryConstant.SELECT_TEACHER_BY_LOGIN_AND_PASSWORD)
                : findUserByLoginAndPassword(login, password, QueryConstant.SELECT_USER_BY_LOGIN_AND_PASSWORD);
    }

    @Override
    public boolean isEmptyFields(String login, String password) {
        return login.isEmpty() || password.isEmpty();
    }

    @Override
    public boolean studentGroupAlreadyExist(String groupName) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_STUDENT_GROUP_BY_NAME);
            preparedStatement.setString(1, groupName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }

        return true;
    }

    @Override
    public boolean studentAlreadyExist(String login) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_STUDENT_BY_NAME);
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return true;
    }

    @Override
    public boolean databaseAlreadyExist(String name) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_DATABASE_BY_NAME);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return true;
    }

    @Override
    public boolean moduleAlreadyExist(String name) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_MODULE_BY_NAME);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return true;
    }

    @Override
    public boolean queryAlreadyExist(String name) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_QUERY_BY_NAME);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return true;
    }

    @Override
    public boolean queryValid(String name, Long databaseId) {

        boolean result = true;
        String url = taskService.getDatabaseURLByID(databaseId);

        Connection connection = ConnectionUtil.getConnectionForLaboratoryWork(url, "postgres", "root");
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeQuery(name);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setStatement(statement);
            disconnectionUtil.disconnect();
        }

        return result;
    }

    //////////////// Additional ////////////////

    private boolean findUserByLoginAndPassword(String login, String password, String query) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

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

}
