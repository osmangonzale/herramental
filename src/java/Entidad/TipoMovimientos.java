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
@Table(name = "tipo_movimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMovimientos.findAll", query = "SELECT t FROM TipoMovimientos t"),
    @NamedQuery(name = "TipoMovimientos.findByIdTipo", query = "SELECT t FROM TipoMovimientos t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "TipoMovimientos.findByNombre", query = "SELECT t FROM TipoMovimientos t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoMovimientos.findByEstado", query = "SELECT t FROM TipoMovimientos t WHERE t.estado = :estado"),
    @NamedQuery(name = "TipoMovimientos.findByUsuRegistro", query = "SELECT t FROM TipoMovimientos t WHERE t.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "TipoMovimientos.findByFchRegistro", query = "SELECT t FROM TipoMovimientos t WHERE t.fchRegistro = :fchRegistro")})
public class TipoMovimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;

    public TipoMovimientos() {
    }

    public TipoMovimientos(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
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
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMovimientos)) {
            return false;
        }
        TipoMovimientos other = (TipoMovimientos) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.TipoMovimientos[ idTipo=" + idTipo + " ]";
    }
    
}
