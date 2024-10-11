/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Prog.Sistemas2
 */
@Entity
@Table(name = "casificacion_defecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasificacionDefecto.findAll", query = "SELECT c FROM CasificacionDefecto c"),
    @NamedQuery(name = "CasificacionDefecto.findByIdClasificacion", query = "SELECT c FROM CasificacionDefecto c WHERE c.idClasificacion = :idClasificacion"),
    @NamedQuery(name = "CasificacionDefecto.findByClasificacion", query = "SELECT c FROM CasificacionDefecto c WHERE c.clasificacion = :clasificacion"),
    @NamedQuery(name = "CasificacionDefecto.findByEstado", query = "SELECT c FROM CasificacionDefecto c WHERE c.estado = :estado"),
    @NamedQuery(name = "CasificacionDefecto.findByFchaRegistro", query = "SELECT c FROM CasificacionDefecto c WHERE c.fchaRegistro = :fchaRegistro"),
    @NamedQuery(name = "CasificacionDefecto.findByUsuRegistro", query = "SELECT c FROM CasificacionDefecto c WHERE c.usuRegistro = :usuRegistro")})
public class CasificacionDefecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clasificacion")
    private Integer idClasificacion;
    @Column(name = "clasificacion")
    private String clasificacion;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fcha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchaRegistro;
    @Column(name = "usu_registro")
    private String usuRegistro;

    public CasificacionDefecto() {
    }

    public CasificacionDefecto(Integer idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public CasificacionDefecto(Integer idClasificacion, int estado, Date fchaRegistro) {
        this.idClasificacion = idClasificacion;
        this.estado = estado;
        this.fchaRegistro = fchaRegistro;
    }

    public Integer getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Integer idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFchaRegistro() {
        return fchaRegistro;
    }

    public void setFchaRegistro(Date fchaRegistro) {
        this.fchaRegistro = fchaRegistro;
    }

    public String getUsuRegistro() {
        return usuRegistro;
    }

    public void setUsuRegistro(String usuRegistro) {
        this.usuRegistro = usuRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClasificacion != null ? idClasificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasificacionDefecto)) {
            return false;
        }
        CasificacionDefecto other = (CasificacionDefecto) object;
        if ((this.idClasificacion == null && other.idClasificacion != null) || (this.idClasificacion != null && !this.idClasificacion.equals(other.idClasificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.CasificacionDefecto[ idClasificacion=" + idClasificacion + " ]";
    }
    
}
