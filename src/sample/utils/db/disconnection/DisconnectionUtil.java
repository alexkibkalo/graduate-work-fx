package sample.utils.db.disconnection;

import java.sql.*;

public class DisconnectionUtil {

    private Connection connection;

    private Statement statement;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void disconnect() {
        try {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
