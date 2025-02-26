package net.joao.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
@RequestScoped
public class HelloResource {
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

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        return Response.status(Response.Status.OK).entity("Teste do hello").build();
    }
}
