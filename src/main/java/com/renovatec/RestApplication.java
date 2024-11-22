package com.renovatec;

import com.renovatec.config.CorsFilter;
import com.renovatec.controller.ProdutoController;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api") // Define o prefixo das rotas da sua API
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        // Registra o CorsFilter
        resources.add(CorsFilter.class);

        // Registra os controllers
        resources.add(ProdutoController.class);

        return resources;
    }
}
