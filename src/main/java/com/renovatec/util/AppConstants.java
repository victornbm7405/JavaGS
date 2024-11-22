package com.renovatec.util;

public class AppConstants {
    public static final String BASE_URL = "http://localhost:8080/";
    public static final String DATABASE_PATH = "jdbc:sqlite:C:\\Users\\Modesto\\OneDrive\\Area de Trabalho\\renovatec\\src\\main\\resources\\database\\sqlite-tools-win-x64-3470000/meu_banco.db";

    public static final String ERROR_DATABASE_CONNECTION = "Erro ao conectar ao banco de dados.";
    public static final String ERROR_LISTING_PRODUCTS = "Erro ao listar produtos.";

    private AppConstants() {
        // Construtor privado para evitar instância
    }
}
