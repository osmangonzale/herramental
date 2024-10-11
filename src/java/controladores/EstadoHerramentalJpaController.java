/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Entidad.EstadoHerramental;
import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Prog.Sistemas2
 */
public class EstadoHerramentalJpaController implements Serializable {

    public EstadoHerramentalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoHerramental estadoHerramental) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadoHerramental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoHerramental estadoHerramental) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadoHerramental = em.merge(estadoHerramental);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadoHerramental.getIdEstado();
                if (findEstadoHerramental(id) == null) {
                    throw new NonexistentEntityException("The estadoHerramental with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoHerramental estadoHerramental;
            try {
                estadoHerramental = em.getReference(EstadoHerramental.class, id);
                estadoHerramental.getIdEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoHerramental with id " + id + " no longer exists.", enfe);
            }
            em.remove(estadoHerramental);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoHerramental> findEstadoHerramentalEntities() {
        return findEstadoHerramentalEntities(true, -1, -1);
    }

    public List<EstadoHerramental> findEstadoHerramentalEntities(int maxResults, int firstResult) {
        return findEstadoHerramentalEntities(false, maxResults, firstResult);
    }

    private List<EstadoHerramental> findEstadoHerramentalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoHerramental.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EstadoHerramental findEstadoHerramental(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoHerramental.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoHerramentalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoHerramental> rt = cq.from(EstadoHerramental.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
