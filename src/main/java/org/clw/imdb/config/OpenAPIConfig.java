package org.clw.imdb.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.clw.imdb.common.StringStaticUtil;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OpenAPIConfig {
    @Value("${openapi.devUrl}")
    private String devUrl;

    @Value("${openapi.prodUrl}")
    private String prodUrl;

    @Bean
    public List<GroupedOpenApi> applicationApi() {
        String[] packagesToScan = {"org.clw.imdb.resource.v1", "org.clw.imdb.resource.security"};
        List<GroupedOpenApi> groupedOpenApiList = new ArrayList<GroupedOpenApi>();
        groupedOpenApiList.add(GroupedOpenApi.builder()
                .group("Imdb API Management")
                .pathsToMatch("/api/v1/**")
                .packagesToScan("org.clw.imdb.resource.v1").build());
        groupedOpenApiList.add(GroupedOpenApi.builder()
                .group("Security API Management")
                .pathsToMatch("/api/security/**")
                .packagesToScan("org.clw.imdb.resource.security").build());
        return groupedOpenApiList;
    }

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development Server URL");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Production Server URL");

        Contact contact = new Contact();
        contact.setEmail("info@miladbazrafshan.ir");
        contact.setName("Milad Bazrafshan");
        contact.setUrl("https://miladbazrafshan.ir");
        Info info = new Info()
                .title("Milad IMDB Project Api")
                .version("1.0")
                .contact(contact)
                .description("Milad IMDB Project Api Management").termsOfService("https://miladbazrafshan.ir")
                .license(new License());

        return new OpenAPI()
                .components(new Components());
    }
}
