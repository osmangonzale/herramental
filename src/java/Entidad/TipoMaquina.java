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
@Table(name = "tipo_maquina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMaquina.findAll", query = "SELECT t FROM TipoMaquina t"),
    @NamedQuery(name = "TipoMaquina.findByIdTipoMaquina", query = "SELECT t FROM TipoMaquina t WHERE t.idTipoMaquina = :idTipoMaquina"),
    @NamedQuery(name = "TipoMaquina.findByTipo", query = "SELECT t FROM TipoMaquina t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TipoMaquina.findByEstado", query = "SELECT t FROM TipoMaquina t WHERE t.estado = :estado"),
    @NamedQuery(name = "TipoMaquina.findByUsuRegistro", query = "SELECT t FROM TipoMaquina t WHERE t.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "TipoMaquina.findByFchRegistro", query = "SELECT t FROM TipoMaquina t WHERE t.fchRegistro = :fchRegistro")})
public class TipoMaquina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_maquina")
    private Integer idTipoMaquina;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;

    public TipoMaquina() {
    }

    public TipoMaquina(Integer idTipoMaquina) {
        this.idTipoMaquina = idTipoMaquina;
    }

    public Integer getIdTipoMaquina() {
        return idTipoMaquina;
    }

    public void setIdTipoMaquina(Integer idTipoMaquina) {
        this.idTipoMaquina = idTipoMaquina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        hash += (idTipoMaquina != null ? idTipoMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMaquina)) {
            return false;
        }
        TipoMaquina other = (TipoMaquina) object;
        if ((this.idTipoMaquina == null && other.idTipoMaquina != null) || (this.idTipoMaquina != null && !this.idTipoMaquina.equals(other.idTipoMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.TipoMaquina[ idTipoMaquina=" + idTipoMaquina + " ]";
    }
    
}
