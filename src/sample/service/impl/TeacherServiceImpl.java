package sample.service.impl;

import sample.service.TeacherService;
import sample.utils.db.connection.ConnectionUtil;
import sample.utils.db.constant.QueryConstant;
import sample.utils.db.disconnection.DisconnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final DisconnectionUtil disconnectionUtil = new DisconnectionUtil();

    @Override
    public boolean addStudentGroup(String name) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.CREATE_NEW_STUDENT_GROUP);
            preparedStatement.setString(1, name);
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
    public boolean addStudent(String name, String surname, String login, String password, Long groupId) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.CREATE_NEW_STUDENT);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, password);
            preparedStatement.setLong(5, 0L);
            preparedStatement.setLong(6, groupId);

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
    public boolean addModule(String name) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.CREATE_NEW_MODULE);
            preparedStatement.setString(1, name);
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
    public boolean addTheme(String name, Long moduleId) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.CREATE_NEW_THEME);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, moduleId);
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
    public boolean addDatabase(String name, String connectionURL) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.CREATE_NEW_DATABASE);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, connectionURL);
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
    public boolean addQuery(String name, String description, Long moduleId, Long databaseId) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.CREATE_NEW_QUERY);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setLong(3, moduleId);
            preparedStatement.setLong(4, databaseId);

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
    public boolean studentGroupExistByName(String name) {
        // TODO
        return false;
    }

    @Override
    public boolean studentExistByLogin(String login) {
        // TODO
        return false;
    }

    @Override
    public boolean moduleExistByName(String name) {
        // TODO
        return false;
    }

    @Override
    public boolean themeExistByName(String name) {
        // TODO
        return false;
    }

    @Override
    public boolean databaseExistByName(String name) {
        // TODO
        return false;
    }

    @Override
    public boolean queryExistBy(String name) {
        // TODO
        return false;
    }

    @Override
    public List<String> findAllStudentGroups() {

        Connection connection = ConnectionUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<String> studentGroups = new ArrayList<>();

        try {
            statement = connection.createStatement();
            statement.executeQuery(QueryConstant.SELECT_ALL_STUDENT_GROUPS);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                studentGroups.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setStatement(statement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }

        return studentGroups;
    }

    @Override
    public List<String> findAllModules() {

        Connection connection = ConnectionUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<String> modules = new ArrayList<>();

        try {
            statement = connection.createStatement();
            statement.executeQuery(QueryConstant.SELECT_ALL_MODULES);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                modules.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setStatement(statement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }

        return modules;
    }

    @Override
    public List<String> findAllNotFinishedModules(Long userId) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> modules = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_ALL_NOT_FINISHED_MODULES);
            preparedStatement.setLong(1, userId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                modules.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return modules;
    }

    @Override
    public Long findIdByStudentGroupName(String name) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_STUDENT_GROUP_BY_NAME);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getLong("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return 0L;
    }

    @Override
    public Long findIdByModuleName(String name) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_MODULE_BY_NAME);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getLong("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return 0L;
    }

    @Override
    public Long findIdByThemeName(String name) {
        // TODO
        return null;
    }

    @Override
    public List<String> findAllThemes() {

        Connection connection = ConnectionUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<String> themes = new ArrayList<>();

        try {
            statement = connection.createStatement();
            statement.executeQuery(QueryConstant.SELECT_ALL_THEMES);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                themes.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setStatement(statement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }

        return themes;
    }

    @Override
    public List<String> findAllDatabases() {

        Connection connection = ConnectionUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<String> databases = new ArrayList<>();

        try {
            statement = connection.createStatement();
            statement.executeQuery(QueryConstant.SELECT_ALL_DATABASES);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                databases.add(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setStatement(statement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }

        return databases;
    }

    @Override
    public Long findIdByDatabaseName(String name) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_DATABASE_BY_NAME);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getLong("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return 0L;
    }

    @Override
    public Long findIdByStudentName(String name) {

        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryConstant.SELECT_STUDENT_BY_NAME);
            preparedStatement.setString(1, name);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getLong("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnectionUtil.setConnection(connection);
            disconnectionUtil.setPreparedStatement(preparedStatement);
            disconnectionUtil.setResultSet(resultSet);
            disconnectionUtil.disconnect();
        }
        return 0L;
    }
}
