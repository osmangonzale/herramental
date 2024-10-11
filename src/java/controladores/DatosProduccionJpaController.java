/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Entidad.DatosProduccion;
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
public class DatosProduccionJpaController implements Serializable {

    public DatosProduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DatosProduccion datosProduccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(datosProduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatosProduccion datosProduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            datosProduccion = em.merge(datosProduccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = datosProduccion.getIdDatos();
                if (findDatosProduccion(id) == null) {
                    throw new NonexistentEntityException("The datosProduccion with id " + id + " no longer exists.");
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
            DatosProduccion datosProduccion;
            try {
                datosProduccion = em.getReference(DatosProduccion.class, id);
                datosProduccion.getIdDatos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosProduccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(datosProduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatosProduccion> findDatosProduccionEntities() {
        return findDatosProduccionEntities(true, -1, -1);
    }

    public List<DatosProduccion> findDatosProduccionEntities(int maxResults, int firstResult) {
        return findDatosProduccionEntities(false, maxResults, firstResult);
    }

    private List<DatosProduccion> findDatosProduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatosProduccion.class));
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

    public DatosProduccion findDatosProduccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatosProduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosProduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatosProduccion> rt = cq.from(DatosProduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
