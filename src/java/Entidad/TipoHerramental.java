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
@Table(name = "tipo_herramental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoHerramental.findAll", query = "SELECT t FROM TipoHerramental t"),
    @NamedQuery(name = "TipoHerramental.findByIdTipoHerramental", query = "SELECT t FROM TipoHerramental t WHERE t.idTipoHerramental = :idTipoHerramental"),
    @NamedQuery(name = "TipoHerramental.findByNombre", query = "SELECT t FROM TipoHerramental t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoHerramental.findByEstado", query = "SELECT t FROM TipoHerramental t WHERE t.estado = :estado"),
    @NamedQuery(name = "TipoHerramental.findByUsuRegistro", query = "SELECT t FROM TipoHerramental t WHERE t.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "TipoHerramental.findByFchRegistro", query = "SELECT t FROM TipoHerramental t WHERE t.fchRegistro = :fchRegistro")})
public class TipoHerramental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_herramental")
    private Integer idTipoHerramental;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;

    public TipoHerramental() {
    }

    public TipoHerramental(Integer idTipoHerramental) {
        this.idTipoHerramental = idTipoHerramental;
    }

    public Integer getIdTipoHerramental() {
        return idTipoHerramental;
    }

    public void setIdTipoHerramental(Integer idTipoHerramental) {
        this.idTipoHerramental = idTipoHerramental;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getUsuRegistro() {
        return usuRegistro;
    }

    public void setUsuRegistro(String usuRegistro) {
        this.usuRegistro = usuRegistro;
    }

    public Date getFchRegistro() {
        return fchRegistro;
    }

    public void setFchRegistro(Date fchRegistro) {
        this.fchRegistro = fchRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoHerramental != null ? idTipoHerramental.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoHerramental)) {
            return false;
        }
        TipoHerramental other = (TipoHerramental) object;
        if ((this.idTipoHerramental == null && other.idTipoHerramental != null) || (this.idTipoHerramental != null && !this.idTipoHerramental.equals(other.idTipoHerramental))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.TipoHerramental[ idTipoHerramental=" + idTipoHerramental + " ]";
    }
    
}
