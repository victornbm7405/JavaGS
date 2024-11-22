package com.renovatec.config;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        // Adiciona os cabeçalhos para permitir CORS
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000"); // Permite requisições do front
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Métodos permitidos
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization"); // Cabeçalhos permitidos
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true"); // Permite cookies e credenciais (se necessário)
    }
}
