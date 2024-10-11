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
 * @author Prog.sistemas2
 */
@Entity
@Table(name = "informe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Informe.findAll", query = "SELECT i FROM Informe i"),
    @NamedQuery(name = "Informe.findByIdInforme", query = "SELECT i FROM Informe i WHERE i.idInforme = :idInforme"),
    @NamedQuery(name = "Informe.findByCantidad", query = "SELECT i FROM Informe i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "Informe.findByNum", query = "SELECT i FROM Informe i WHERE i.num = :num"),
    @NamedQuery(name = "Informe.findByDimensiones", query = "SELECT i FROM Informe i WHERE i.dimensiones = :dimensiones"),
    @NamedQuery(name = "Informe.findByMaterial", query = "SELECT i FROM Informe i WHERE i.material = :material"),
    @NamedQuery(name = "Informe.findByFchInicio", query = "SELECT i FROM Informe i WHERE i.fchInicio = :fchInicio"),
    @NamedQuery(name = "Informe.findByFchFin", query = "SELECT i FROM Informe i WHERE i.fchFin = :fchFin"),
    @NamedQuery(name = "Informe.findByDuracion", query = "SELECT i FROM Informe i WHERE i.duracion = :duracion"),
    @NamedQuery(name = "Informe.findByUsuRegistro", query = "SELECT i FROM Informe i WHERE i.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "Informe.findByFchRegistro", query = "SELECT i FROM Informe i WHERE i.fchRegistro = :fchRegistro")})
public class Informe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_informe")
    private Integer idInforme;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "num")
    private Integer num;
    @Column(name = "dimensiones")
    private String dimensiones;
    @Column(name = "material")
    private String material;
    @Column(name = "fch_inicio")
    @Temporal(TemporalType.DATE)
    private Date fchInicio;
    @Column(name = "fch_fin")
    @Temporal(TemporalType.DATE)
    private Date fchFin;
    @Column(name = "duracion")
    private Integer duracion;
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Basic(optional = false)
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;

    public Informe() {
    }

    public Informe(Integer idInforme) {
        this.idInforme = idInforme;
    }

    public Informe(Integer idInforme, Date fchRegistro) {
        this.idInforme = idInforme;
        this.fchRegistro = fchRegistro;
    }

    public Integer getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(Integer idInforme) {
        this.idInforme = idInforme;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Date getFchInicio() {
        return fchInicio;
    }

    public void setFchInicio(Date fchInicio) {
        this.fchInicio = fchInicio;
    }

    public Date getFchFin() {
        return fchFin;
    }

    public void setFchFin(Date fchFin) {
        this.fchFin = fchFin;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
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
        hash += (idInforme != null ? idInforme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informe)) {
            return false;
        }
        Informe other = (Informe) object;
        if ((this.idInforme == null && other.idInforme != null) || (this.idInforme != null && !this.idInforme.equals(other.idInforme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Informe[ idInforme=" + idInforme + " ]";
    }
    
}
