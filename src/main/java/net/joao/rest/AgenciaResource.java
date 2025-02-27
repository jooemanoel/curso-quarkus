package net.joao.rest;

import java.util.List;

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
import net.joao.persistence.models.Agencia;
import net.joao.services.AgenciaService;

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
            @Content(mediaType = "application/json", schema = @Schema(implementation = Agencia.class, type = SchemaType.ARRAY)) })
    public RestResponse<List<Agencia>> listar() {
        return RestResponse.ok(agenciaService.listar());
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Busca agencia por ID", description = "Retorna uma agencia")
    @APIResponse(responseCode = "200", description = "Agencia", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Agencia.class)) })
    public RestResponse<Agencia> buscar(Integer id) {
        return RestResponse.ok(agenciaService.buscarPorId(id));
    }

    @POST
    @Operation(summary = "Cadastrar Agencia", description = "Cadastra e retorna um inteiro")
    @APIResponse(responseCode = "201", description = "Agencia", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Agencia.class)) })
    public RestResponse<Integer> cadastrar(Agencia agencia) {
        return RestResponse.accepted(agenciaService.cadastrar(agencia));
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir Agencia", description = "Exclui e retorna um inteiro")
    @APIResponse(responseCode = "200", description = "Agencia", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Agencia.class)) })
    public RestResponse<Integer> excluir(Integer id) {
        return RestResponse.accepted(agenciaService.excluir(id));
    }

    @PUT
    public RestResponse<Integer> alterar(Agencia agencia) {
        return RestResponse.accepted(this.agenciaService.alterar(agencia));
    }
}
