package com.intuit.gaming_service.config;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

  @Value("${spring.swagger-ui.path}")
  private String swaggerBasePath;

  @Bean
  public Docket api() {
    final Docket docket =
        new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any()).build();

    if (!swaggerBasePath.isEmpty()) {
      docket.pathMapping(swaggerBasePath);
    }
    return docket;
  }

  private ApiKey apiKey() {
    return new ApiKey("Bearer", "Authorization", "header");
  }
}
