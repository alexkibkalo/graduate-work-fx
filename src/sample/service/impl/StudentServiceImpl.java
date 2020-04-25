package sample.service.impl;

import sample.service.StudentService;
import sample.utils.db.connection.ConnectionUtil;
import sample.utils.db.constant.QueryConstant;
import sample.utils.db.disconnection.DisconnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
