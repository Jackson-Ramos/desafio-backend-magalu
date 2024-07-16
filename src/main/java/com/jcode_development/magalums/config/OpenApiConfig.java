package com.jcode_development.magalums.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Magalums API")
                        .version("V1")
                        .description("Api responsible for scheduling and sending notification")
                        .termsOfService("jcode_development.com.br")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("jcode_development.com.br")
                        )
                );
    }
}
