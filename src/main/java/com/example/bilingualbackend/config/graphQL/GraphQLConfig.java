package com.example.bilingualbackend.config.graphQL;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import com.example.bilingualbackend.config.FirebaseAuthentication;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public FirebaseAuthentication firebaseAuthentication() {
        return new FirebaseAuthentication();
    }

    @Bean
    public GraphQLSchema graphQLSchema(FirebaseAuthentication firebaseAuthentication) {
        return SchemaParser.newParser()
                .resolvers((GraphQLResolver<?>) firebaseAuthentication)
                .file("classpath:scheme.graphql")
                .build()
                .makeExecutableSchema();
    }

    @Bean
    public GraphQL graphQL(GraphQLSchema graphQLSchema) {
        return GraphQL.newGraphQL(graphQLSchema).build();
    }
}