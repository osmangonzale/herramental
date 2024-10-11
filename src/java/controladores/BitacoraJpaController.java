/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Entidad.Bitacora;
import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Prog.sistemas2
 */
public class BitacoraJpaController implements Serializable {

    public BitacoraJpaController() {
        emf = Persistence.createEntityManagerFactory("HerramentalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List ConsultaBitacorasIdTipo(int id_tipo) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_btc_c_bitacoras_id_tipo`('" + id_tipo + "')");
            List resultados = q.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (!resultados.isEmpty()) {
                return resultados;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List ConsultaBitacoraId(int id_bitacora) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_btc_bitacora_id`('" + id_bitacora + "')");
            List resultados = q.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (!resultados.isEmpty()) {
                return resultados;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public List ConsultaBitacoraIdMaquina(int id_maquina) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_btc_c_bitacora_ult`('" + id_maquina + "')");
            List resultados = q.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (!resultados.isEmpty()) {
                return resultados;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List ConsultaBitacorasMaquinas(int id_tipo, String fecha) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_btc_c_bitacoras`('" + id_tipo + "','" + fecha + "')");
            List resultados = q.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (!resultados.isEmpty()) {
                return resultados;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean RegistrarBitacora(int id_bitacora, String Bitacora, String usuario_registro, int turno) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("Update bitacora b set " + ((turno == 1) ? "b.turno_1" : ((turno == 2) ? "b.turno_2" : ((turno == 3) ? "b.turno_3" : "b.programacion"))) + " = '" + Bitacora + "' " + ((turno == 0)?"where b.id_bitacora = " + id_bitacora + "":","+((turno == 1) ? "b.usuario_t1" : ((turno == 2) ? "b.usuario_t2" : "b.usuario_t3")) + " = '" + usuario_registro + "' where b.id_bitacora = " + id_bitacora + "")+"");
            int resultado = q.executeUpdate();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultado == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean RegistrarProgramacion(int id_bitacora, String programacion) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("Update bitacora b set b.programacion = '" + programacion + "' where b.id_bitacora = " + id_bitacora + "");
            int resultado = q.executeUpdate();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultado == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean RegistroBitacorasMaquinas(int id_maquina, String fecha, String prog) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_btc_r_bitacoras_maquinas`('" + id_maquina + "','" + fecha + "','" + prog + "')");
            int resultado = q.executeUpdate();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultado == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean EstadoBitacoraTurno(int turno) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("Update bitacora b set " + ((turno == 1) ? "b.estado_t1" : ((turno == 2) ? "b.estado_t2" : "b.estado_t3")) + " = 0 where " + ((turno == 1) ? "b.estado_t1 = 1" : ((turno == 2) ? "b.estado_t2 = 1" : "b.estado_t3 = 1 and b.fecha <= DATE_SUB(CURDATE(), INTERVAL 1 day)")) + "");
            int resultado = q.executeUpdate();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultado == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
