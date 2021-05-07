package com.future.restoapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "jwtAuth", // can be set to anything
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @Info(title = "Resto API", version = "v1"),
        security = @SecurityRequirement(name = "jwtAuth") // references the name defined in the line 3
)
public class OpenApiConfiguration {
//    @Bean
//    public GroupedOpenApi clientOpenApi() {
//        String paths[] = {UrlBasePath.CURRENT_CLIENT + "/**"};
//        return GroupedOpenApi.builder()
//                .group("client")
//                .pathsToMatch(paths)
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi adminOpenApi() {
//        //        String packagesToscan[] = {"test.org.springdoc.api.app68.api.user"};
//        String paths[] = {UrlBasePath.CURRENT_CLIENT + "/**"};
//        return GroupedOpenApi.builder()
//                .group("admin")
//                .pathsToMatch(paths)
//                .build();
//    }
}