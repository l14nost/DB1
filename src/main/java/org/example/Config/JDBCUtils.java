package org.example.Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    public static Connection getConnection() {
        Connection connection = null;
        String dbUrl;
        String userName;
        String dbPassw;
        FileInputStream fis;
        Properties properties = new Properties();


        try {

            fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
            dbUrl = properties.getProperty("dbhost");
            userName = properties.getProperty("username");
            dbPassw = properties.getProperty("pass");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(dbUrl, userName, dbPassw);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            if(connection!= null){
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//    }
        return connection;
    }

}
