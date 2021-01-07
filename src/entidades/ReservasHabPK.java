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
public class ReservasHabPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "FECHA_IN")
    @Temporal(TemporalType.DATE)
    private Date fechaIn;
    @Basic(optional = false)
    @Column(name = "FECHA_OUT")
    @Temporal(TemporalType.DATE)
    private Date fechaOut;
    @Basic(optional = false)
    @Column(name = "DNI_CLIENTE")
    private String dniCliente;

    public ReservasHabPK() {
    }

    public ReservasHabPK(Date fechaIn, Date fechaOut, String dniCliente) {
        this.fechaIn = fechaIn;
        this.fechaOut = fechaOut;
        this.dniCliente = dniCliente;
    }

    public Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    public Date getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(Date fechaOut) {
        this.fechaOut = fechaOut;
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
        hash += (fechaIn != null ? fechaIn.hashCode() : 0);
        hash += (fechaOut != null ? fechaOut.hashCode() : 0);
        hash += (dniCliente != null ? dniCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservasHabPK)) {
            return false;
        }
        ReservasHabPK other = (ReservasHabPK) object;
        if ((this.fechaIn == null && other.fechaIn != null) || (this.fechaIn != null && !this.fechaIn.equals(other.fechaIn))) {
            return false;
        }
        if ((this.fechaOut == null && other.fechaOut != null) || (this.fechaOut != null && !this.fechaOut.equals(other.fechaOut))) {
            return false;
        }
        if ((this.dniCliente == null && other.dniCliente != null) || (this.dniCliente != null && !this.dniCliente.equals(other.dniCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ReservasHabPK[ fechaIn=" + fechaIn + ", fechaOut=" + fechaOut + ", dniCliente=" + dniCliente + " ]";
    }
    
}
