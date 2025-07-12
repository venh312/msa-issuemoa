package com.issuemoa.subsidy.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(): OpenAPI? {
        // Define the server URL
        val server = Server().url("/")

        val securityScheme = SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .name("Authorization")
            .bearerFormat("JWT")
            .`in`(SecurityScheme.In.HEADER)
            .description("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb25mMzEyQGtha2FvLmNvbSIsIm5hbWUiOiLslYzsiJjsl4bsnYwiLCJpZCI6NCwiYXV0aCI6IklTU1VFTU9BX1VTRVIiLCJleHAiOjE3MDQ5NzkzMjl9.gzXnASGdtoiVQgh57cJqnxREkVGZTFvade8ppCb_yTQKfTMNLashMxD5cZ8FvHPMFTNcC0arvXbQ-vqIxViyvQ")

        val apiKeyScheme = SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .scheme("api-key")
            .name("X-Client-Key")
            .`in`(SecurityScheme.In.HEADER)
            .description("SamQHPleQjbSKeyRvJWElcHJvamVjdCFA")

        val securityRequirement = SecurityRequirement()
            .addList("Authorization")
            .addList("X-Client-Key")

        // Create securityRequirements object
        return OpenAPI()
            .info(Info().title("행안부 보조금24 API").version("1.0"))
            .addServersItem(server)
            .addSecurityItem(securityRequirement)
            .components(Components()
                .addSecuritySchemes("Authorization", securityScheme)
                .addSecuritySchemes("X-Client-Key", apiKeyScheme)
            )
    }
}
