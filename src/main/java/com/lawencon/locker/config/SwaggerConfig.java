package com.lawencon.locker.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class SwaggerConfig {
    private static final String GROUP_NAME = "locker-api";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group(GROUP_NAME)
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI dialogDesignerOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Locker Rest API")
                        .description("Rest API for Book Locker")
                        .version("1.0"));
    }
}

