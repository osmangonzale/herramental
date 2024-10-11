/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MovimientoInyectoraJpaController implements Serializable {

    public MovimientoInyectoraJpaController() {
        emf = Persistence.createEntityManagerFactory("HerramentalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List ConsultaMovimientos() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_tmv_c_tipo_movimientos`()");
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

    public List ConsultaMovimientosId(int id_tipoM) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_tmv_c_tipo_movimiento_id`('" + id_tipoM + "')");
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

    public List ConsultaMovimientosIdMaquina(int id_Maquina) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_mvm_c_movimientos_id_maquina`('" + id_Maquina + "')");
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
    
    public List ConsultaUltMovimientoIdMaquina(int id_Maquina) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_mvm_c_ult_movimiento_idM`('" + id_Maquina + "')");
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

    public List ConsultaMovimientosIdMovimiento(int id_Movimiento) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_mvm_c_movimientos_id_movimiento`('" + id_Movimiento + "')");
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

    public boolean RegistroMovimiento(int id_Maquina, int id_herramental, int id_tipoM, String fecha, String producto, String lote, int cavidad, int cavidadD, String cavidades, String descripcion, String usuario_registro, String descripcion_cavidades_deshabilitadas,String usuario_firma) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_mvm_r_movimiento`('" + id_Maquina + "','" + id_herramental + "','" + id_tipoM + "','" + fecha + "','" + producto + "','" + lote + "','" + cavidad + "','" + cavidadD + "','" + cavidades + "','" + descripcion + "','" + usuario_registro + "','" + descripcion_cavidades_deshabilitadas + "','" + usuario_firma + "')");
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

    public boolean RegistroMovimientoHerramental(int id_Maquina, int id_herramental, int id_tipoM, String fecha, String producto, String lote, int cavidad, int cavidadD, String cavidades, String usuario_registro, String descripcion_cavidades_deshabilitadas) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_mvm_r_movimiento_herramental`('" + id_Maquina + "','" + id_herramental + "','" + id_tipoM + "','" + fecha + "','" + producto + "','" + lote + "','" + cavidad + "','" + cavidadD + "','" + cavidades + "','" + usuario_registro + "','" + descripcion_cavidades_deshabilitadas + "')");
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

    public boolean ModificarMovimiento(int id_Movimiento, int id_herramental, int id_tipoM, String fecha, String producto, String lote, int cavidad, int cavidadD, String cavidades, String descripcion, String usuarioR, String descripcion_cavidades_deshabilitadas) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_mvm_m_movimiento`('" + id_Movimiento + "','" + id_herramental + "','" + id_tipoM + "','" + fecha + "','" + producto + "','" + lote + "','" + cavidad + "','" + cavidadD + "','" + cavidades + "','" + descripcion + "','" + usuarioR + "','" + descripcion_cavidades_deshabilitadas + "')");
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

    public boolean ModificarMovimientoDescripcion(int id_Movimiento, String descripcion) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query q = em.createNativeQuery("CALL `sp_mvm_m_descripcion`('" + id_Movimiento + "','" + descripcion + "')");
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
