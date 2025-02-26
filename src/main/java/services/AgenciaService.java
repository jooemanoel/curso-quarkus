package services;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import exception.ErrosSistema.AgenciaInativaException;
import exception.ErrosSistema.AgenciaNaoEncontradaException;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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

    public Agencia buscarPorId(Integer id) {
        Log.info(linha + "Buscar por ID: " + id + linha);
        Agencia agencia = dao.buscarPorId(id);
        if (agencia == null)
            throw new AgenciaNaoEncontradaException(id.toString());
        return agencia;
    }

    public Agencia buscarPorCNPJ(String cnpj) {
        Log.info(linha + "Buscar por CNPJ: " + cnpj + linha);
        return dao.buscarPorCnpj(cnpj);
    }

    public Integer cadastrar(Agencia agencia) {
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
        return 1;
    }

    public int alterar(Agencia agencia) {
        Log.info(linha + "Alterar: " + agencia.getId() + linha);
        return dao.editar(agencia);
    }
}
