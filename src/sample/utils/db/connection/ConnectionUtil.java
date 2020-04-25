package sample.utils.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionUtil {

    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/graduate_work", "postgres", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnectionForLaboratoryWork(String url, String username, String password){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
