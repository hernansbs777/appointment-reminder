/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hernanrg.appointmentreminder.entities;

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
 * @author Hernan
 */
@Entity
@Table(name = "turno_recordatorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recordatorio.findAll", query = "SELECT r FROM Recordatorio r"),
    @NamedQuery(name = "Recordatorio.findByIdrecordatorio", query = "SELECT r FROM Recordatorio r WHERE r.idrecordatorio = :idrecordatorio"),
    @NamedQuery(name = "Recordatorio.findByEnviado", query = "SELECT r FROM Recordatorio r WHERE r.enviado = :enviado"),
    @NamedQuery(name = "Recordatorio.findByEmail", query = "SELECT r FROM Recordatorio r WHERE r.email = :email"),
    @NamedQuery(name = "Recordatorio.findByFechaEnvio", query = "SELECT r FROM Recordatorio r WHERE r.fechaEnvio = :fechaEnvio"),
    @NamedQuery(name = "Recordatorio.findByCuerpo", query = "SELECT r FROM Recordatorio r WHERE r.cuerpo = :cuerpo"),
    @NamedQuery(name = "Recordatorio.findByIdturno", query = "SELECT r FROM Recordatorio r WHERE r.idturno = :idturno")})
public class Recordatorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrecordatorio")
    private Integer idrecordatorio;
    @Basic(optional = false)
    @Column(name = "enviado")
    private int enviado;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    @Basic(optional = false)
    @Column(name = "cuerpo")
    private String cuerpo;
    @Basic(optional = false)
    @Column(name = "idturno")
    private int idturno;

    public Recordatorio() {
    }

    public Recordatorio(Integer idrecordatorio) {
        this.idrecordatorio = idrecordatorio;
    }

    public Recordatorio(Integer idrecordatorio, int enviado, String email, String cuerpo, int idturno) {
        this.idrecordatorio = idrecordatorio;
        this.enviado = enviado;
        this.email = email;
        this.cuerpo = cuerpo;
        this.idturno = idturno;
    }

    public Integer getIdrecordatorio() {
        return idrecordatorio;
    }

    public void setIdrecordatorio(Integer idrecordatorio) {
        this.idrecordatorio = idrecordatorio;
    }

    public int getEnviado() {
        return enviado;
    }

    public void setEnviado(int enviado) {
        this.enviado = enviado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getIdturno() {
        return idturno;
    }

    public void setIdturno(int idturno) {
        this.idturno = idturno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecordatorio != null ? idrecordatorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recordatorio)) {
            return false;
        }
        Recordatorio other = (Recordatorio) object;
        if ((this.idrecordatorio == null && other.idrecordatorio != null) || (this.idrecordatorio != null && !this.idrecordatorio.equals(other.idrecordatorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hernanrg.appointmentreminder.entities.Recordatorio[ idrecordatorio=" + idrecordatorio + " ]";
    }
    
}
