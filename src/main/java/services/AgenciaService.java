package services;

import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import exception.ErrosSistema.AgenciaInativaException;
import exception.ErrosSistema.AgenciaNaoEncontradaException;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import persistence.dao.AgenciaDao;
import persistence.models.Agencia;
import persistence.models.AgenciaHttp;
import persistence.models.SituacaoCadastral;

@ApplicationScoped
public class AgenciaService {
    private final String linha = "\n====================================================================================================\n";

    @Inject
    @RestClient
    SituacaoCadastralHttpClient httpClient;

    @Inject
    AgenciaDao dao;

    public List<Agencia> listar() {
        return dao.listar();
    }

    public Response buscarPorId(Integer id) {
        Log.info(linha + "Buscar por ID: " + id + linha);
        try {
            AgenciaHttp agenciaHttp = httpClient.buscarPorId(id);
            return Response.status(Response.Status.OK).entity(agenciaHttp).build();
        } catch (ClientWebApplicationException e) {
            Log.info(linha + e.getMessage() + linha);
            return Response.status(Response.Status.NOT_FOUND).entity("{}").build();
        }
    }

    public AgenciaHttp buscarPorCNPJ(String cnpj) {
        Log.info(linha + "Buscar por CNPJ: " + cnpj + linha);
        List<AgenciaHttp> agencias = httpClient.listar();
        try {
            AgenciaHttp agenciaHttp = agencias.stream().filter(x -> x.getCnpj().equals(cnpj))
                    .toList().getFirst();
            return agenciaHttp;
        } catch (NoSuchElementException e) {
            Log.info(linha + "Não Encontrado." + linha);
            return null;
        }
    }

    public int cadastrar(Agencia agencia) {
        List<AgenciaHttp> resultado = httpClient.listarPorCnpj(agencia.getCnpj());
        if (resultado.size() == 0) {
            Log.info(linha + "Agencia nao encontrada: " + agencia.getId() + linha);
            throw new AgenciaNaoEncontradaException(agencia.getCnpj());
        }
        if (resultado.getFirst().getSituacaoCadastral().equals(SituacaoCadastral.INATIVO)) {
            Log.info(linha + "Agencia inativa: " + agencia.getId() + linha);
            throw new AgenciaInativaException(agencia.getCnpj());
        }
        agencia.setId(null);
        agencia.getEndereco().setId(null);
        dao.incluir(agencia);
        return 1;
    }

    public int excluir(Integer id) {
        Log.info(linha + "Excluir: " + id + linha);
        dao.excluir(id);
        return id;
    }

    public void alterar(Agencia agencia) {
        Log.info(linha + "Alterar: " + agencia.getId() + linha);
        dao.editar(agencia);
    }
}
