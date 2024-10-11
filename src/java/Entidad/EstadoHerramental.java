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
@Table(name = "estado_herramental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoHerramental.findAll", query = "SELECT e FROM EstadoHerramental e"),
    @NamedQuery(name = "EstadoHerramental.findByIdEstado", query = "SELECT e FROM EstadoHerramental e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "EstadoHerramental.findByNombre", query = "SELECT e FROM EstadoHerramental e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EstadoHerramental.findByEstado", query = "SELECT e FROM EstadoHerramental e WHERE e.estado = :estado"),
    @NamedQuery(name = "EstadoHerramental.findByUsuRegistro", query = "SELECT e FROM EstadoHerramental e WHERE e.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "EstadoHerramental.findByFchRegistro", query = "SELECT e FROM EstadoHerramental e WHERE e.fchRegistro = :fchRegistro")})
public class EstadoHerramental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado")
    private Integer idEstado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;

    public EstadoHerramental() {
    }

    public EstadoHerramental(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
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
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoHerramental)) {
            return false;
        }
        EstadoHerramental other = (EstadoHerramental) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.EstadoHerramental[ idEstado=" + idEstado + " ]";
    }
    
}
