package com.mealmate.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig() {
        return new OpenAPI().info(
                new Info()
                        .title("MealMate Backend API")
                        .description("A Food Delivery Application by Avaneesh Chopdekar")
                        .version("1.0.0")
        )
                .servers(
                        List.of(
                                new Server().url("http://localhost:8080")
                                        .description("Local Development Server"),
                                new Server().url("https://mealmate-backend.onrender.com")
                                        .description("Production Server")
                        )
                ) // TODO: Change production server url after deployment
                .tags(
                        List.of(
                                new Tag().name("Authentication API"),
                                new Tag().name("User API"),
                                new Tag().name("Consumer API"),
                                new Tag().name("Admin API"),
                                new Tag().name("Restaurant API"),
                                new Tag().name("Rider API"),
                                new Tag().name("Menu Item API"),
                                new Tag().name("Order API"),
                                new Tag().name("Order Item API"),
                                new Tag().name("Payment API"),
                                new Tag().name("profile-controller")
                        )
                );

    }
}
