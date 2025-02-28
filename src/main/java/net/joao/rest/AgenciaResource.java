package net.joao.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;

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
import net.joao.persistence.dto.AgenciaNomeRuaDTO;
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
    public List<Agencia> listar() {
        return agenciaService.listar();
    }

    @GET
    @Path("/nome-agencia-rua")
    @Operation(summary = "Listar agencias por nome e rua", description = "Retorna uma lista de agencias")
    public List<AgenciaNomeRuaDTO> listarNomeAgenciaRua() {
        return agenciaService.listarNomeAgenciaRua();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Busca agencia por ID", description = "Retorna uma agencia")
    public Agencia buscar(Integer id) {
        return agenciaService.buscarPorId(id);
    }

    @POST
    @Operation(summary = "Cadastrar Agencia", description = "Cadastra e retorna o ID")
    public Integer cadastrar(Agencia agencia) {
        return agenciaService.cadastrar(agencia);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir Agencia", description = "Exclui e retorna o ID")
    public Integer excluir(Integer id) {
        return agenciaService.excluir(id);
    }

    @PUT
    @Operation(summary = "Alterar Agencia", description = "Edita e etorna a agencia")
    public Agencia alterar(Agencia agencia) {
        if (agencia.getEndereco() == null) {
            agencia.setEndereco(null);
        }
        return this.agenciaService.alterar(agencia);
    }
}
