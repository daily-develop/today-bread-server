package com.github.org.todaybread.todaybread.config.graphql;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.GraphQLScalarType;
import java.util.Arrays;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.graphql.server.WebGraphQlHandler;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurerUpload() {

        GraphQLScalarType uploadScalar = GraphQLScalarType.newScalar()
            .name("Upload")
            .coercing(new UploadCoercing())
            .build();

        return wiringBuilder -> wiringBuilder.scalar(uploadScalar);
    }

    @Bean
    @Order(1)
    public RouterFunction<ServerResponse> graphQlMultipartRouterFunction(
        GraphQlProperties properties,
        WebGraphQlHandler webGraphQlHandler,
        ObjectMapper objectMapper
    ) {
        String path = properties.getPath();
        RouterFunctions.Builder builder = RouterFunctions.route();
        GraphqlMultipartHandler graphqlMultipartHandler = new GraphqlMultipartHandler(
            webGraphQlHandler,
            objectMapper
        );

        builder = builder.POST(
            path,
            RequestPredicates.contentType(MULTIPART_FORM_DATA)
                .and(RequestPredicates.accept(
                        Arrays.asList(MediaType.APPLICATION_GRAPHQL, MediaType.APPLICATION_JSON)
                            .toArray(MediaType[]::new)
                    )
                ),
            graphqlMultipartHandler::handleRequest
        );

        return builder.build();
    }
}
