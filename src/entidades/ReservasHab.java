/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rafa
 */
@Entity
@Table(name = "RESERVAS_HAB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservasHab.findAll", query = "SELECT r FROM ReservasHab r")
    , @NamedQuery(name = "ReservasHab.findByFechaIn", query = "SELECT r FROM ReservasHab r WHERE r.reservasHabPK.fechaIn = :fechaIn")
    , @NamedQuery(name = "ReservasHab.findByFechaOut", query = "SELECT r FROM ReservasHab r WHERE r.reservasHabPK.fechaOut = :fechaOut")
    , @NamedQuery(name = "ReservasHab.findByNHab", query = "SELECT r FROM ReservasHab r WHERE r.nHab = :nHab")
    , @NamedQuery(name = "ReservasHab.findByTipo", query = "SELECT r FROM ReservasHab r WHERE r.tipo = :tipo")
    , @NamedQuery(name = "ReservasHab.findByFumador", query = "SELECT r FROM ReservasHab r WHERE r.fumador = :fumador")
    , @NamedQuery(name = "ReservasHab.findByRegimen", query = "SELECT r FROM ReservasHab r WHERE r.regimen = :regimen")
    , @NamedQuery(name = "ReservasHab.findByDniCliente", query = "SELECT r FROM ReservasHab r WHERE r.reservasHabPK.dniCliente = :dniCliente")
    , @NamedQuery(name = "ReservasHab.findByReservasHabPK", query = "SELECT r FROM ReservasHab r WHERE r.reservasHabPK.dniCliente = :dniCliente and r.reservasHabPK.fechaIn = :fechaIn and r.reservasHabPK.fechaOut = :fechaOut")})
public class ReservasHab implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservasHabPK reservasHabPK;
    @Column(name = "N_HAB")
    private Integer nHab;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "FUMADOR")
    private Character fumador;
    @Column(name = "REGIMEN")
    private Character regimen;
    @JoinColumn(name = "DNI_CLIENTE", referencedColumnName = "DNI", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public ReservasHab() {
    }

    public ReservasHab(ReservasHabPK reservasHabPK) {
        this.reservasHabPK = reservasHabPK;
    }

    public ReservasHab(Date fechaIn, Date fechaOut, String dniCliente) {
        this.reservasHabPK = new ReservasHabPK(fechaIn, fechaOut, dniCliente);
    }

    public ReservasHabPK getReservasHabPK() {
        return reservasHabPK;
    }

    public void setReservasHabPK(ReservasHabPK reservasHabPK) {
        this.reservasHabPK = reservasHabPK;
    }

    public Integer getNHab() {
        return nHab;
    }

    public void setNHab(Integer nHab) {
        this.nHab = nHab;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Character getFumador() {
        return fumador;
    }

    public void setFumador(Character fumador) {
        this.fumador = fumador;
    }

    public Character getRegimen() {
        return regimen;
    }

    public void setRegimen(Character regimen) {
        this.regimen = regimen;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservasHabPK != null ? reservasHabPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservasHab)) {
            return false;
        }
        ReservasHab other = (ReservasHab) object;
        if ((this.reservasHabPK == null && other.reservasHabPK != null) || (this.reservasHabPK != null && !this.reservasHabPK.equals(other.reservasHabPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ReservasHab[ reservasHabPK=" + reservasHabPK + " ]";
    }
    
}
