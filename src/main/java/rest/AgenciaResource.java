package rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.RestResponse;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import persistence.models.Agencia;
import persistence.models.AgenciaHttp;
import services.AgenciaService;

@Path("/agencias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AgenciaResource {

    @Inject
    AgenciaService agenciaService;

    @GET
    @Operation(summary = "Listar agencias ", description = "Retorna uma lista de agencias")
    @APIResponse(responseCode = "200", description = "Agencia", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AgenciaHttp.class, type = SchemaType.ARRAY)) })
    public Response listar() {
        return Response.status(Response.Status.OK).entity(agenciaService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Busca agencia por CNPJ", description = "Retorna uma agencia")
    @APIResponse(responseCode = "200", description = "Agencia", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AgenciaHttp.class)) })
    public Response buscar(int id) {
        return agenciaService.buscarPorId(id);
    }

    @POST
    @Operation(summary = "Cadastrar Agencia", description = "Cadastra e retorna um inteiro")
    @APIResponse(responseCode = "201", description = "Agencia", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AgenciaHttp.class)) })
    public Response cadastrar(Agencia agencia) {
        int check = agenciaService.cadastrar(agencia);
        return Response.status(Response.Status.CREATED).entity(check).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir Agencia", description = "Exclui e retorna um inteiro")
    @APIResponse(responseCode = "200", description = "Agencia", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = AgenciaHttp.class)) })
    public Response excluir(int id) {
        int check = agenciaService.excluir(id);
        return Response.status(Response.Status.ACCEPTED).entity(check).build();
    }

    @PUT
    public RestResponse<Void> alterar(Agencia agencia) {
        this.agenciaService.alterar(agencia);
        return RestResponse.ok();
    }
}
