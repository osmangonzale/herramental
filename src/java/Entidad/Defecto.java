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
@Table(name = "defecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Defecto.findAll", query = "SELECT d FROM Defecto d"),
    @NamedQuery(name = "Defecto.findByIdDefecto", query = "SELECT d FROM Defecto d WHERE d.idDefecto = :idDefecto"),
    @NamedQuery(name = "Defecto.findByDefecto", query = "SELECT d FROM Defecto d WHERE d.defecto = :defecto"),
    @NamedQuery(name = "Defecto.findByEstado", query = "SELECT d FROM Defecto d WHERE d.estado = :estado"),
    @NamedQuery(name = "Defecto.findByFchRegistro", query = "SELECT d FROM Defecto d WHERE d.fchRegistro = :fchRegistro"),
    @NamedQuery(name = "Defecto.findByUsuRegistro", query = "SELECT d FROM Defecto d WHERE d.usuRegistro = :usuRegistro")})
public class Defecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_defecto")
    private Integer idDefecto;
    @Basic(optional = false)
    @Column(name = "defecto")
    private String defecto;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;
    @Basic(optional = false)
    @Column(name = "usu_registro")
    private String usuRegistro;

    public Defecto() {
    }

    public Defecto(Integer idDefecto) {
        this.idDefecto = idDefecto;
    }

    public Defecto(Integer idDefecto, String defecto, int estado, Date fchRegistro, String usuRegistro) {
        this.idDefecto = idDefecto;
        this.defecto = defecto;
        this.estado = estado;
        this.fchRegistro = fchRegistro;
        this.usuRegistro = usuRegistro;
    }

    public Integer getIdDefecto() {
        return idDefecto;
    }

    public void setIdDefecto(Integer idDefecto) {
        this.idDefecto = idDefecto;
    }

    public String getDefecto() {
        return defecto;
    }

    public void setDefecto(String defecto) {
        this.defecto = defecto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFchRegistro() {
        return fchRegistro;
    }

    public void setFchRegistro(Date fchRegistro) {
        this.fchRegistro = fchRegistro;
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
        hash += (idDefecto != null ? idDefecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Defecto)) {
            return false;
        }
        Defecto other = (Defecto) object;
        if ((this.idDefecto == null && other.idDefecto != null) || (this.idDefecto != null && !this.idDefecto.equals(other.idDefecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Defecto[ idDefecto=" + idDefecto + " ]";
    }
    
}
