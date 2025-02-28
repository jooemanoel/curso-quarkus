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
import net.joao.persistence.models.Agencia;
import net.joao.persistence.models.Endereco;

@ApplicationScoped
public class AgenciaDao {

    @Inject
    EntityManager em;

    public List<Agencia> listar() {
        TypedQuery<Agencia> query = em.createNamedQuery(Agencia.LISTAR, Agencia.class);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (PersistenceException e) {
            throw e;
        }
    }

    public Agencia buscarPorId(Integer id) {
        return em.find(Agencia.class, id);
    }

    public Agencia buscarPorCnpj(String cnpj) {
        try {
            TypedQuery<Agencia> query = em.createNamedQuery(Agencia.CONSULTAR_POR_CNPJ, Agencia.class);
            query.setParameter("cnpj", cnpj);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw e;
        }
    }

    @Transactional
    public Integer incluir(Agencia agencia) {
        em.persist(agencia);
        em.flush();
        return agencia.getId();
    }

    @Transactional
    public void excluir(Integer id) {
        try {
            Agencia agencia = em.find(Agencia.class, id);
            if (agencia != null) {
                em.remove(agencia);
            }
        } catch (IllegalArgumentException e) {
            throw new PersistenceException("Erro ao tentar excluir a agência com ID: " + id, e);
        }
    }

    @Transactional
    public Agencia editar(Agencia agencia) {
        Endereco enderecoReal = buscarPorId(agencia.getId()).getEndereco();
        if (enderecoReal != null) {
            agencia.getEndereco().setId(enderecoReal.getId());
        } else {
            agencia.setEndereco(enderecoReal);
        }
        return em.merge(agencia);
    }
}
