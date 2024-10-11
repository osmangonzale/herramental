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
@Table(name = "bitacora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b"),
    @NamedQuery(name = "Bitacora.findByIdBitacora", query = "SELECT b FROM Bitacora b WHERE b.idBitacora = :idBitacora"),
    @NamedQuery(name = "Bitacora.findByIdMaquina", query = "SELECT b FROM Bitacora b WHERE b.idMaquina = :idMaquina"),
    @NamedQuery(name = "Bitacora.findByFecha", query = "SELECT b FROM Bitacora b WHERE b.fecha = :fecha"),
    @NamedQuery(name = "Bitacora.findByEstadoT1", query = "SELECT b FROM Bitacora b WHERE b.estadoT1 = :estadoT1"),
    @NamedQuery(name = "Bitacora.findByEstadoT2", query = "SELECT b FROM Bitacora b WHERE b.estadoT2 = :estadoT2"),
    @NamedQuery(name = "Bitacora.findByEstadoT3", query = "SELECT b FROM Bitacora b WHERE b.estadoT3 = :estadoT3"),
    @NamedQuery(name = "Bitacora.findByFchRegistro", query = "SELECT b FROM Bitacora b WHERE b.fchRegistro = :fchRegistro")})
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacora")
    private Integer idBitacora;
    @Column(name = "id_maquina")
    private Integer idMaquina;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Lob
    @Column(name = "turno_1")
    private String turno1;
    @Lob
    @Column(name = "usuario_t1")
    private String usuarioT1;
    @Basic(optional = false)
    @Column(name = "estado_t1")
    private int estadoT1;
    @Lob
    @Column(name = "turno_2")
    private String turno2;
    @Lob
    @Column(name = "usuario_t2")
    private String usuarioT2;
    @Column(name = "estado_t2")
    private Integer estadoT2;
    @Lob
    @Column(name = "turno_3")
    private String turno3;
    @Lob
    @Column(name = "usuario_t3")
    private String usuarioT3;
    @Column(name = "estado_t3")
    private Integer estadoT3;
    @Lob
    @Column(name = "programacion")
    private String programacion;
    @Basic(optional = false)
    @Column(name = "fch_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fchRegistro;

    public Bitacora() {
    }

    public Bitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Bitacora(Integer idBitacora, int estadoT1, Date fchRegistro) {
        this.idBitacora = idBitacora;
        this.estadoT1 = estadoT1;
        this.fchRegistro = fchRegistro;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTurno1() {
        return turno1;
    }

    public void setTurno1(String turno1) {
        this.turno1 = turno1;
    }

    public String getUsuarioT1() {
        return usuarioT1;
    }

    public void setUsuarioT1(String usuarioT1) {
        this.usuarioT1 = usuarioT1;
    }

    public int getEstadoT1() {
        return estadoT1;
    }

    public void setEstadoT1(int estadoT1) {
        this.estadoT1 = estadoT1;
    }

    public String getTurno2() {
        return turno2;
    }

    public void setTurno2(String turno2) {
        this.turno2 = turno2;
    }

    public String getUsuarioT2() {
        return usuarioT2;
    }

    public void setUsuarioT2(String usuarioT2) {
        this.usuarioT2 = usuarioT2;
    }

    public Integer getEstadoT2() {
        return estadoT2;
    }

    public void setEstadoT2(Integer estadoT2) {
        this.estadoT2 = estadoT2;
    }

    public String getTurno3() {
        return turno3;
    }

    public void setTurno3(String turno3) {
        this.turno3 = turno3;
    }

    public String getUsuarioT3() {
        return usuarioT3;
    }

    public void setUsuarioT3(String usuarioT3) {
        this.usuarioT3 = usuarioT3;
    }

    public Integer getEstadoT3() {
        return estadoT3;
    }

    public void setEstadoT3(Integer estadoT3) {
        this.estadoT3 = estadoT3;
    }

    public String getProgramacion() {
        return programacion;
    }

    public void setProgramacion(String programacion) {
        this.programacion = programacion;
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
        hash += (idBitacora != null ? idBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        if ((this.idBitacora == null && other.idBitacora != null) || (this.idBitacora != null && !this.idBitacora.equals(other.idBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Bitacora[ idBitacora=" + idBitacora + " ]";
    }
    
}
