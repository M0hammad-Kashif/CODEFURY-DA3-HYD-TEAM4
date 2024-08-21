package com.hsbc.utilities;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection () throws SQLException, ClassNotFoundException {
        // 1. Loading the Driver: Class.forName("com.mysql.cj.jdbc.Driver")
        //                          or Class.forName(Driver.class.getName());
        Class.forName(Driver.class.getName());

        // 2. Connecting: Connection connection = DriverManager.getConnection(url, username, password)
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsbc24_batch?useSSL=false", "root", "newSQL@24");

        return connection;
    }
}
