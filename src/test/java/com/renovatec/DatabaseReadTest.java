package com.renovatec;

import com.renovatec.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseReadTest {
    public static void main(String[] args) {
        String query = "SELECT * FROM Produto";
        try (Connection connection = DatabaseConfig.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Dados da tabela Produto:");
            while (rs.next()) {
                System.out.printf("ID: %d, Nome: %s, Tipo: %s, Consumo: %.2f, Custo: %.2f%n",
                        rs.getInt("id"), rs.getString("nome"), rs.getString("tipo"),
                        rs.getDouble("consumoEnergetico"), rs.getDouble("custoMensal"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao acessar o banco de dados:");
            e.printStackTrace();
        }
    }
}
