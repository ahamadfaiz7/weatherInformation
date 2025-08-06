package com.weatherInformation.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger configuration to document and visualize API endpoints using OpenAPI 3.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI weatherOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Weather Information API")
                        .description("Fetch current weather info for New Zealand cities")
                        .version("v1.0"));
    }
}
