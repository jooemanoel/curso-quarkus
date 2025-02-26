package services;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import persistence.models.Agencia;
import persistence.models.AgenciaHttp;

@RegisterRestClient(configKey = "agencias-api")
@Path("/agencias")
public interface SituacaoCadastralHttpClient {

    @GET
    List<AgenciaHttp> listar();

    @GET
    @Path("/{id}")
    AgenciaHttp buscarPorId(int id);

    @POST
    void cadastrar(Agencia agencia);

    @DELETE
    @Path("/{id}")
    void excluir(int id);

    @PUT
    @Path("/{id}")
    void alterar(int id, Agencia agencia);

    @GET
    List<AgenciaHttp> listarPorCnpj(@QueryParam("cnpj") String cnpj);
}
