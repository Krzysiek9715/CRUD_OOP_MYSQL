package com.example;
import com.example.entity.User;

import java.sql.*;


public class DbUtil {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/workshop2";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "coderslab";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
    }
}
