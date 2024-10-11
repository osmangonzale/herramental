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
 * @author Prog.sistemas2
 */
@Entity
@Table(name = "novedad_personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NovedadPersonal.findAll", query = "SELECT n FROM NovedadPersonal n"),
    @NamedQuery(name = "NovedadPersonal.findByIdNovedad", query = "SELECT n FROM NovedadPersonal n WHERE n.idNovedad = :idNovedad"),
    @NamedQuery(name = "NovedadPersonal.findByFecha", query = "SELECT n FROM NovedadPersonal n WHERE n.fecha = :fecha"),
    @NamedQuery(name = "NovedadPersonal.findByIdtipoM", query = "SELECT n FROM NovedadPersonal n WHERE n.idtipoM = :idtipoM"),
    @NamedQuery(name = "NovedadPersonal.findByFchRegistro", query = "SELECT n FROM NovedadPersonal n WHERE n.fchRegistro = :fchRegistro")})
public class NovedadPersonal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_novedad")
    private Integer idNovedad;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "id_tipoM")
    private Integer idtipoM;
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;
    @Lob
    @Column(name = "usu_registro")
    private String usuRegistro;

    public NovedadPersonal() {
    }

    public NovedadPersonal(Integer idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Integer getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(Integer idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdtipoM() {
        return idtipoM;
    }

    public void setIdtipoM(Integer idtipoM) {
        this.idtipoM = idtipoM;
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
        hash += (idNovedad != null ? idNovedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NovedadPersonal)) {
            return false;
        }
        NovedadPersonal other = (NovedadPersonal) object;
        if ((this.idNovedad == null && other.idNovedad != null) || (this.idNovedad != null && !this.idNovedad.equals(other.idNovedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.NovedadPersonal[ idNovedad=" + idNovedad + " ]";
    }
    
}
