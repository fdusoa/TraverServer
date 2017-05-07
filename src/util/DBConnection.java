package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zihao on 2017/5/3.
 */
public class DBConnection {
    private static Connection connection;
    private static String url = "jdbc:mysql://120.76.125.35:3306/traveler";
    private static String user = "root";
    private static String password = "123456";

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver") ;
            connection = DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Cannot load driver");
            e.printStackTrace() ;
        }
        if (connection == null)
            System.out.println("Null connection");
    }

    public static Connection getConnection() {
        return connection;
    }
}
