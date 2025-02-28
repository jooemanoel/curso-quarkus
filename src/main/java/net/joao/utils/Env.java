package net.joao.utils;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Env {
    @ConfigProperty(name = "quarkus.datasource.db-kind")
    String dbKind;
    @ConfigProperty(name = "quarkus.datasource.username")
    String username;
    @ConfigProperty(name = "quarkus.datasource.password")
    String password;
    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
    String jdbcUrl;
    @ConfigProperty(name = "quarkus.hibernate-orm.database.generation")
    String generation;
    @ConfigProperty(name = "quarkus.rest-client.agencias-api.url")
    String quarkusRestClientAgenciasApiUrl;
}
