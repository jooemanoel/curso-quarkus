package net.joao.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import net.joao.domain.AgenciaHttp;
import net.joao.exception.ErrosSistema.AgenciaNaoEncontradaException;
import net.joao.persistence.dao.AgenciaDao;
import net.joao.persistence.models.Agencia;
import net.joao.utils.AgenciaFixture;

@QuarkusTest
public class AgenciaServiceTest {
    @InjectMock
    private AgenciaDao dao;

    @Inject
    @InjectMock
    @RestClient
    private AgenciaHttpClient httpClient;

    @Inject
    private AgenciaService agenciaService;

    @Test
    public void deveNaoCadastrarQuandoClientRetornarNull() {
        Agencia agencia = AgenciaFixture.criarAgencia(1);
        Mockito.when(httpClient.listarPorCnpj("123")).thenReturn(new ArrayList<>());
        Assertions.assertThrows(AgenciaNaoEncontradaException.class, () -> agenciaService.cadastrar(agencia));
        Mockito.verify(dao, Mockito.never()).incluir(agencia);
    }

    @Test
    public void deveNaoCadastrarQuandoClientRetornarSituacaoCadastralAtiva() {
        Agencia agencia = AgenciaFixture.criarAgencia(1);
        List<AgenciaHttp> mockList = new ArrayList<>();
        mockList.add(AgenciaFixture.criarAgenciaHttp(1));
        Mockito.when(httpClient.listarPorCnpj("123")).thenReturn(mockList);
        agenciaService.cadastrar(agencia);
        Mockito.verify(dao).incluir(agencia);
    }
}
