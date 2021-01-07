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
@Table(name = "RESERVAS_SALON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservasSalon.findAll", query = "SELECT r FROM ReservasSalon r")
    , @NamedQuery(name = "ReservasSalon.findByTipoEvento", query = "SELECT r FROM ReservasSalon r WHERE r.tipoEvento = :tipoEvento")
    , @NamedQuery(name = "ReservasSalon.findByNumPersonas", query = "SELECT r FROM ReservasSalon r WHERE r.numPersonas = :numPersonas")
    , @NamedQuery(name = "ReservasSalon.findByTipoCocina", query = "SELECT r FROM ReservasSalon r WHERE r.tipoCocina = :tipoCocina")
    , @NamedQuery(name = "ReservasSalon.findByNumHabitaciones", query = "SELECT r FROM ReservasSalon r WHERE r.numHabitaciones = :numHabitaciones")
    , @NamedQuery(name = "ReservasSalon.findByFecha", query = "SELECT r FROM ReservasSalon r WHERE r.reservasSalonPK.fecha = :fecha")
    , @NamedQuery(name = "ReservasSalon.findByNumDias", query = "SELECT r FROM ReservasSalon r WHERE r.numDias = :numDias")
    , @NamedQuery(name = "ReservasSalon.findByDniCliente", query = "SELECT r FROM ReservasSalon r WHERE r.reservasSalonPK.dniCliente = :dniCliente")
    , @NamedQuery(name = "ReservasSalon.findByReservasSalonPK", query = "SELECT r FROM ReservasSalon r WHERE r.reservasSalonPK.dniCliente = :dni and r.reservasSalonPK.fecha = :fecha")})
public class ReservasSalon implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservasSalonPK reservasSalonPK;
    @Column(name = "TIPO_EVENTO")
    private Character tipoEvento;
    @Column(name = "NUM_PERSONAS")
    private Integer numPersonas;
    @Column(name = "TIPO_COCINA")
    private Character tipoCocina;
    @Column(name = "NUM_HABITACIONES")
    private Integer numHabitaciones;
    @Column(name = "NUM_DIAS")
    private Integer numDias;
    @JoinColumn(name = "DNI_CLIENTE", referencedColumnName = "DNI", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public ReservasSalon() {
    }

    public ReservasSalon(ReservasSalonPK reservasSalonPK) {
        this.reservasSalonPK = reservasSalonPK;
    }

    public ReservasSalon(Date fecha, String dniCliente) {
        this.reservasSalonPK = new ReservasSalonPK(fecha, dniCliente);
    }

    public ReservasSalonPK getReservasSalonPK() {
        return reservasSalonPK;
    }

    public void setReservasSalonPK(ReservasSalonPK reservasSalonPK) {
        this.reservasSalonPK = reservasSalonPK;
    }

    public Character getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(Character tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Integer getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(Integer numPersonas) {
        this.numPersonas = numPersonas;
    }

    public Character getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(Character tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public Integer getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(Integer numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public Integer getNumDias() {
        return numDias;
    }

    public void setNumDias(Integer numDias) {
        this.numDias = numDias;
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
        hash += (reservasSalonPK != null ? reservasSalonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservasSalon)) {
            return false;
        }
        ReservasSalon other = (ReservasSalon) object;
        if ((this.reservasSalonPK == null && other.reservasSalonPK != null) || (this.reservasSalonPK != null && !this.reservasSalonPK.equals(other.reservasSalonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ReservasSalon[ reservasSalonPK=" + reservasSalonPK + " ]";
    }

}
