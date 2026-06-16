package com.queueflow.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI queueFlowOpenApi() {
        return new OpenAPI().info(new Info()
    .title("QueueFlow API")
    .description(
    "API to manage queue in the public or private services")
    .version("1.0.0")
    .contact(
    new Contact()
        .name("Project Owner")
        .email("ernestdossa.9@gmail.com")
    )
    );
    }
}