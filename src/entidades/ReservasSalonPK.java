/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Rafa
 */
@Embeddable
public class ReservasSalonPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "DNI_CLIENTE")
    private String dniCliente;

    public ReservasSalonPK() {
    }

    public ReservasSalonPK(Date fecha, String dniCliente) {
        this.fecha = fecha;
        this.dniCliente = dniCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (dniCliente != null ? dniCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservasSalonPK)) {
            return false;
        }
        ReservasSalonPK other = (ReservasSalonPK) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.dniCliente == null && other.dniCliente != null) || (this.dniCliente != null && !this.dniCliente.equals(other.dniCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ReservasSalonPK[ fecha=" + fecha + ", dniCliente=" + dniCliente + " ]";
    }
    
}
