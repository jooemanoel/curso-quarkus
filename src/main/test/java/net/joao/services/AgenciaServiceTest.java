package net.joao.services;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import net.joao.exception.ErrosSistema.AgenciaNaoEncontradaException;
import net.joao.persistence.dao.AgenciaDao;
import net.joao.persistence.models.Endereco;

@QuarkusTest
public class AgenciaServiceTest {
    @InjectMock
    private AgenciaDao dao;

    @InjectMock
    @RestClient
    private SituacaoCadastralHttpClient httpClient;

    @Inject
    private AgenciaService agenciaService;

    @Test
    public void deveNaoCadastrarQuandoClientRetornarNull() {
        Agencia agencia = criarAgencia();
        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj("123")).thenReturn(null);
        Assertions.assertThrows(AgenciaNaoEncontradaException.class, () -> agenciaService.cadastrar(agencia));
        Mockito.verify(dao, Mockito.never()).persist(agencia);
    }

    private Agencia criarAgencia() {
        Endereco endereco = new Endereco(1, "", "", "", 1);
        return new Agencia(1, "", "", "", endereco);
    }
}
