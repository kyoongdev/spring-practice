package kyoongdev.kyoongdevspring.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI openApi() {
    SecurityScheme securityScheme = new SecurityScheme().type(Type.HTTP).scheme("bearer")
        .bearerFormat("JWT").in(SecurityScheme.In.HEADER).name("Authorization");

    SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

    return new OpenAPI().components(
            new Components().addSecuritySchemes("bearerAuth", securityScheme))
        .security(Arrays.asList(securityRequirement))
        .info(new Info()
            .title("Kyoongdev API")
            .description("Flight Kyoongdev Spring boot practice")
            .version("v1.0")
        );
  }
}