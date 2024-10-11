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
@Table(name = "familia_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FamiliaProducto.findAll", query = "SELECT f FROM FamiliaProducto f"),
    @NamedQuery(name = "FamiliaProducto.findByIdFamiliaProducto", query = "SELECT f FROM FamiliaProducto f WHERE f.idFamiliaProducto = :idFamiliaProducto"),
    @NamedQuery(name = "FamiliaProducto.findByNombre", query = "SELECT f FROM FamiliaProducto f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "FamiliaProducto.findByEstado", query = "SELECT f FROM FamiliaProducto f WHERE f.estado = :estado"),
    @NamedQuery(name = "FamiliaProducto.findByFchRegistro", query = "SELECT f FROM FamiliaProducto f WHERE f.fchRegistro = :fchRegistro"),
    @NamedQuery(name = "FamiliaProducto.findByUsuResgistro", query = "SELECT f FROM FamiliaProducto f WHERE f.usuResgistro = :usuResgistro")})
public class FamiliaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_familia_producto")
    private Integer idFamiliaProducto;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;
    @Basic(optional = false)
    @Column(name = "usu_resgistro")
    private String usuResgistro;

    public FamiliaProducto() {
    }

    public FamiliaProducto(Integer idFamiliaProducto) {
        this.idFamiliaProducto = idFamiliaProducto;
    }

    public FamiliaProducto(Integer idFamiliaProducto, String nombre, int estado, Date fchRegistro, String usuResgistro) {
        this.idFamiliaProducto = idFamiliaProducto;
        this.nombre = nombre;
        this.estado = estado;
        this.fchRegistro = fchRegistro;
        this.usuResgistro = usuResgistro;
    }

    public Integer getIdFamiliaProducto() {
        return idFamiliaProducto;
    }

    public void setIdFamiliaProducto(Integer idFamiliaProducto) {
        this.idFamiliaProducto = idFamiliaProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getUsuResgistro() {
        return usuResgistro;
    }

    public void setUsuResgistro(String usuResgistro) {
        this.usuResgistro = usuResgistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamiliaProducto != null ? idFamiliaProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamiliaProducto)) {
            return false;
        }
        FamiliaProducto other = (FamiliaProducto) object;
        if ((this.idFamiliaProducto == null && other.idFamiliaProducto != null) || (this.idFamiliaProducto != null && !this.idFamiliaProducto.equals(other.idFamiliaProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.FamiliaProducto[ idFamiliaProducto=" + idFamiliaProducto + " ]";
    }
    
}
