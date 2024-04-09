package com.thuctaptotnghiem.thuctaptotnghiep.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("OGANI REST API DOCUMENT")
                        .contact(new Contact().name("PVH").email("pvhau2002@gmail.com"))
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0")
                                .url("http://springdoc.org")));
    }

}
