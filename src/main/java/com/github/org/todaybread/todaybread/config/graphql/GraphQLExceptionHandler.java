package com.github.org.todaybread.todaybread.config.graphql;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphQLException;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Slf4j
@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof GraphQLException) {
            log.error("[GraphQL Error] {}", ex.getMessage());

            return GraphqlErrorBuilder.newError()
                .message(ex.getMessage())
                .errorType(ErrorType.DataFetchingException)
                .build();
        } else if (ex instanceof BindException) {
            log.error("[Bind Error] {}", ex.getMessage());

            return GraphqlErrorBuilder.newError()
                .message(ex.getMessage())
                .errorType(ErrorType.ValidationError)
                .build();
        } else {
            return super.resolveToSingleError(ex, env);
        }
    }
}
