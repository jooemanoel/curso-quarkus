package net.joao.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import net.joao.persistence.models.Endereco;

@ApplicationScoped
public class EnderecoDao {

    @Inject
    EntityManager em;

    public List<Endereco> listar() {
        TypedQuery<Endereco> query = em.createNamedQuery(Endereco.LISTAR, Endereco.class);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw e;
        }
    }

    public Endereco buscarPorId(Integer id) {
        return em.find(Endereco.class, id);
    }

    @Transactional
    public Integer incluir(Endereco endereco) {
        em.persist(endereco);
        em.flush();
        return endereco.getId();
    }

    @Transactional
    public void excluir(Integer id) {
        try {
            Endereco endereco = em.find(Endereco.class, id);
            if (endereco != null) {
                em.remove(endereco);
            }
        } catch (IllegalArgumentException e) {
            throw new PersistenceException("Erro ao tentar excluir o endereço com ID: " + id, e);
        }
    }

    @Transactional
    public Endereco editar(Endereco endereco) {
        return em.merge(endereco);
    }
}
