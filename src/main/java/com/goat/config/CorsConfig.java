package com.goat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // configura Spring
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // aplica CORS a todas las rutas que empiecen por /api/
                        .allowedOrigins("http://localhost:3000", "http://localhost:5173", "http://localhost:5174") // url front vite/npm
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // verbos HTTP acepta
                        .allowedHeaders("*"); // acepta todas las cabeceras
            }
        };
    }
}
