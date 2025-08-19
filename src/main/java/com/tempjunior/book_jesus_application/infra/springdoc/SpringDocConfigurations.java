package com.tempjunior.book_jesus_application.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Api de gestão de livros - Criada para estudos e treinos")
                        .version("1.0")
                        .description("Documentação completa da API - Acessibilidade e mostrar minha habilidades com Api Rest")
                        .contact(new Contact().name("Junior Oliveira").email("junior_zelito17@hotmail.com").url("https://www.linkedin.com/in/junior-oliveira-it-mt/"))
                );
    }


}
