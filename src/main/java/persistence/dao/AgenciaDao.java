package persistence.dao;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import persistence.models.Agencia;

@ApplicationScoped
public class AgenciaDao implements PanacheRepository<Agencia> {

    @Inject
    EntityManager em;

    public List<Agencia> listar() {
        return listAll();
    }

    @Transactional
    public void incluir(Agencia agencia) {
        persist(agencia);
    }

    @Transactional
    public void excluir(Integer id) {
        Agencia agencia = findById((long) id);
        if (agencia != null) {
            delete(agencia);
        }
    }

    public Agencia buscarPorId(Integer id) {
        return findById((long) id);
    }

    @Transactional
    public void editar(Agencia agencia) {
        update("nome = ?1, razaoSocial = ?2, cnpj = ?3 where id = ?4", agencia.getNome(),
                agencia.getRazaoSocial(), agencia.getCnpj(), agencia.getId());
    }
}
