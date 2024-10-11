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
@Table(name = "datos_produccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosProduccion.findAll", query = "SELECT d FROM DatosProduccion d"),
    @NamedQuery(name = "DatosProduccion.findByIdDatos", query = "SELECT d FROM DatosProduccion d WHERE d.idDatos = :idDatos"),
    @NamedQuery(name = "DatosProduccion.findByOrden", query = "SELECT d FROM DatosProduccion d WHERE d.orden = :orden"),
    @NamedQuery(name = "DatosProduccion.findByProducto", query = "SELECT d FROM DatosProduccion d WHERE d.producto = :producto"),
    @NamedQuery(name = "DatosProduccion.findByLote", query = "SELECT d FROM DatosProduccion d WHERE d.lote = :lote"),
    @NamedQuery(name = "DatosProduccion.findByCantidad", query = "SELECT d FROM DatosProduccion d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DatosProduccion.findByEstado", query = "SELECT d FROM DatosProduccion d WHERE d.estado = :estado"),
    @NamedQuery(name = "DatosProduccion.findByUsuRegistro", query = "SELECT d FROM DatosProduccion d WHERE d.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "DatosProduccion.findByFchRegistro", query = "SELECT d FROM DatosProduccion d WHERE d.fchRegistro = :fchRegistro")})
public class DatosProduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_datos")
    private Integer idDatos;
    @Column(name = "orden")
    private String orden;
    @Column(name = "producto")
    private String producto;
    @Column(name = "lote")
    private String lote;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;

    public DatosProduccion() {
    }

    public DatosProduccion(Integer idDatos) {
        this.idDatos = idDatos;
    }

    public Integer getIdDatos() {
        return idDatos;
    }

    public void setIdDatos(Integer idDatos) {
        this.idDatos = idDatos;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idDatos != null ? idDatos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosProduccion)) {
            return false;
        }
        DatosProduccion other = (DatosProduccion) object;
        if ((this.idDatos == null && other.idDatos != null) || (this.idDatos != null && !this.idDatos.equals(other.idDatos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.DatosProduccion[ idDatos=" + idDatos + " ]";
    }
    
}
