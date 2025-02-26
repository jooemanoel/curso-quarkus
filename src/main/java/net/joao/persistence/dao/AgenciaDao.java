package net.joao.persistence.dao;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import net.joao.persistence.models.Agencia;

@ApplicationScoped
public class AgenciaDao implements PanacheRepository<Agencia> {

    @Inject
    EntityManager em;

    public List<Agencia> listar() {
        return listAll();
    }

    public Agencia buscarPorId(Long id) {
        return findById(id);
    }

    public Agencia buscarPorCnpj(String cnpj) {
        return find("cnpj", cnpj).firstResult();
    }

    @Transactional
    public void incluir(Agencia agencia) {
        persist(agencia);
    }

    @Transactional
    public void excluir(Long id) {
        Agencia agencia = findById(id);
        if (agencia != null) {
            delete(agencia);
        }
    }

    @Transactional
    public int editar(Agencia agencia) {
        return update("nome = ?1, razaoSocial = ?2, cnpj = ?3 where id = ?4", agencia.getNome(),
                agencia.getRazaoSocial(), agencia.getCnpj(), agencia.getId());
    }
}
