package com.renovatec.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {

    public static Connection getConnection() throws Exception {
        String url = "jdbc:sqlite:src/main/resources/database/sqlite-tools-win-x64-3470000/meu_banco.db";
        return DriverManager.getConnection(url);
    }

}
