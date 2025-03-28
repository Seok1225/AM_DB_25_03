package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul\";";
        String userName = "root";
        String password = "";

        Connection connection = DriverManager.getConnection(url, userName, password);

    }
}
