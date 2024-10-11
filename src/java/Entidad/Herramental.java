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
@Table(name = "herramental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Herramental.findAll", query = "SELECT h FROM Herramental h"),
    @NamedQuery(name = "Herramental.findByIdHerramental", query = "SELECT h FROM Herramental h WHERE h.idHerramental = :idHerramental"),
    @NamedQuery(name = "Herramental.findByIdMaquina", query = "SELECT h FROM Herramental h WHERE h.idMaquina = :idMaquina"),
    @NamedQuery(name = "Herramental.findByIdEstado", query = "SELECT h FROM Herramental h WHERE h.idEstado = :idEstado"),
    @NamedQuery(name = "Herramental.findByNombre", query = "SELECT h FROM Herramental h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "Herramental.findByEstado", query = "SELECT h FROM Herramental h WHERE h.estado = :estado"),
    @NamedQuery(name = "Herramental.findByUsuRegistro", query = "SELECT h FROM Herramental h WHERE h.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "Herramental.findByFchRegistro", query = "SELECT h FROM Herramental h WHERE h.fchRegistro = :fchRegistro")})
public class Herramental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_herramental")
    private Integer idHerramental;
    @Column(name = "id_maquina")
    private Integer idMaquina;
    @Column(name = "id_estado")
    private String idEstado;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "estado")
    private String estado;
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;

    public Herramental() {
    }

    public Herramental(Integer idHerramental) {
        this.idHerramental = idHerramental;
    }

    public Integer getIdHerramental() {
        return idHerramental;
    }

    public void setIdHerramental(Integer idHerramental) {
        this.idHerramental = idHerramental;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
        hash += (idHerramental != null ? idHerramental.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Herramental)) {
            return false;
        }
        Herramental other = (Herramental) object;
        if ((this.idHerramental == null && other.idHerramental != null) || (this.idHerramental != null && !this.idHerramental.equals(other.idHerramental))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Herramental[ idHerramental=" + idHerramental + " ]";
    }
    
}
