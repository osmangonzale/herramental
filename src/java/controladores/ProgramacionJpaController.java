/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Entidad.Programacion;
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
 * @author Prog.Sistemas2
 */
public class ProgramacionJpaController implements Serializable {

    public ProgramacionJpaController() {
        emf = Persistence.createEntityManagerFactory("HerramentalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    //<editor-fold defaultstate="collapsed" desc="Metodos registro No se utilizan">
//    public boolean RegistrarProgramacion(String fecha, String programacion, int id_tipoM, int turno, String usuario_registro) {
//        EntityManager em = getEntityManager();
//        em.getTransaction().begin();
//        try {
//            Query q = em.createNativeQuery("CALL `sp_prg_r_programacion`('" + fecha + "','" + programacion + "','" + id_tipoM + "','" + turno + "','" + usuario_registro + "')");
//            int resultado = q.executeUpdate();
//            em.getTransaction().commit();
//            em.clear();
//            em.close();
//            if (resultado == 1) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public boolean ModificarProgramacion(int id_programacion, String fecha, String programacion, int turno) {
//        EntityManager em = getEntityManager();
//        em.getTransaction().begin();
//        try {
//            Query q = em.createNativeQuery("CALL `sp_prg_m_programacion`('" + id_programacion + "','" + fecha + "','" + programacion + "','" + turno + "')");
//            int resultado = q.executeUpdate();
//            em.getTransaction().commit();
//            em.clear();
//            em.close();
//            if (resultado == 1) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//    }
    //</editor-fold>

    public List ConsultaProgramacion(int tipo_maquina) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_prg_c_programacion`('" + tipo_maquina + "')");
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

    public List ConsultaProgramacionId(int id_programacion) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_prg_c_programacion_id`('" + id_programacion + "')");
            List resultados = q.getResultList();
            em.getTransaction().commit();
            em.clear();
            em.close();
            if (resultados != null) {
                return resultados;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}
