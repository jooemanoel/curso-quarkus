package net.joao.utils;

import net.joao.domain.AgenciaHttp;
import net.joao.domain.SituacaoCadastral;
import net.joao.persistence.models.Agencia;
import net.joao.persistence.models.Endereco;

public class AgenciaFixture {
    public static Agencia criarAgencia(Integer id) {
        Endereco endereco = new Endereco(1, "qwer", "qwert", "qwerty", 1);
        return new Agencia(id, "asdf", "asdfg", "123", endereco);
    }

    public static AgenciaHttp criarAgenciaHttp(Integer id) {
        return new AgenciaHttp(id, "asdf", "asdfg", "123", SituacaoCadastral.ATIVO);
    }
}
