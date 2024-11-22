package com.renovatec;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {
    public static void main(String[] args) {
        // Configuração da URI base do servidor
        String baseUri = "http://localhost:8080/";

        // Configuração dos recursos e handlers do servidor
        ResourceConfig config = new ResourceConfig()
                .packages("com.renovatec.controller") // Pacote onde estão os controllers/endpoints
                .register(com.renovatec.config.GsonMessageBodyHandler.class); // Registro do Gson

        // Criação do servidor Grizzly
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), config);

        System.out.println("Servidor rodando em: " + baseUri);
        System.out.println("Pressione Ctrl+C para encerrar...");

        // Inicia o servidor
        try {
            server.start();
            Thread.currentThread().join(); // Mantém o servidor rodando
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}
