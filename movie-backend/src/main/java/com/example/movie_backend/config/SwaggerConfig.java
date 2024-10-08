package com.example.movie_backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setName("Movie Assignment Java Team");
        contact.setUrl("https://www.example.com");
        contact.setEmail("toshaduc@iut-dhaka.edu");

        License mitLicense = new License().name("MIT License").url("https://www.example.com/licenses/mit/");

        Info info = new Info()
                .title("Movie Assignment Java Team")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints to manage Movie-Assignment admin panel and Website ")
                .termsOfService("https://www.example.com/terms")
                .license(mitLicense);

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Authentication");

        Components components = new Components()
                .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme());

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
        // .servers(List.of(devServer, prodServer));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}
