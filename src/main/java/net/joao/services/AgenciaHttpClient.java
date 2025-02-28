package net.joao.services;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import net.joao.domain.AgenciaHttp;
import net.joao.persistence.models.Agencia;

@RegisterRestClient(configKey = "agencias-api")
@Path("/agencias")
public interface AgenciaHttpClient {

    @GET
    List<AgenciaHttp> listar();

    @GET
    @Path("/{id}")
    AgenciaHttp buscarPorId(Integer id);

    @POST
    void cadastrar(Agencia agencia);

    @DELETE
    @Path("/{id}")
    void excluir(Integer id);

    @PUT
    @Path("/{id}")
    void alterar(Integer id, Agencia agencia);

    @GET
    List<AgenciaHttp> listarPorCnpj(@QueryParam("cnpj") String cnpj);
}
