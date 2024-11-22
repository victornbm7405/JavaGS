package com.renovatec;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetProdutosTest {
    public static void main(String[] args) {
        try {
            // URL do endpoint que queremos testar
            String url = "http://localhost:8080/produtos";

            // Criar uma conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            // Configurar o cabeçalho
            connection.setRequestProperty("Accept", "application/json");

            // Obter o código de resposta
            int responseCode = connection.getResponseCode();
            System.out.println("Código de resposta: " + responseCode);

            // Ler a resposta do servidor, se for bem-sucedida
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // Exibir o JSON retornado
                System.out.println("Resposta JSON:");
                System.out.println(response.toString());
            } else {
                System.out.println("Erro ao conectar: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
