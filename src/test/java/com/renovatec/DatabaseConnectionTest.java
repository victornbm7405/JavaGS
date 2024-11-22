package com.renovatec;

import com.renovatec.config.DatabaseConfig;
import com.renovatec.util.AppConstants;

import java.sql.Connection;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            if (connection != null) {
                System.out.println("Conexão com o banco SQLite foi bem-sucedida!");
            }
        } catch (Exception e) {
            System.out.println(AppConstants.ERROR_DATABASE_CONNECTION); // Usando constante
            e.printStackTrace();
        }
    }
}
