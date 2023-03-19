/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alejo
 */
@Entity
@Table(name = "rengloncertificado")
@NamedQueries({
    @NamedQuery(name = "Rengloncertificado.findAll", query = "SELECT r FROM Rengloncertificado r"),
    @NamedQuery(name = "Rengloncertificado.findByVIdRenglon", query = "SELECT r FROM Rengloncertificado r WHERE r.vIdRenglon = :vIdRenglon"),
    @NamedQuery(name = "Rengloncertificado.findByVCostoActual", query = "SELECT r FROM Rengloncertificado r WHERE r.vCostoActual = :vCostoActual"),
    @NamedQuery(name = "Rengloncertificado.findByVAvance", query = "SELECT r FROM Rengloncertificado r WHERE r.vAvance = :vAvance"),
    @NamedQuery(name = "Rengloncertificado.findByVCostoAPagar", query = "SELECT r FROM Rengloncertificado r WHERE r.vCostoAPagar = :vCostoAPagar")})
public class Rengloncertificado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "vIdRenglon")
    private Integer vIdRenglon;
    @Basic(optional = false)
    @Column(name = "vCostoActual")
    private double vCostoActual;
    @Basic(optional = false)
    @Column(name = "vAvance")
    private int vAvance;
    @Basic(optional = false)
    @Column(name = "vCostoAPagar")
    private double vCostoAPagar;
    @JoinColumn(name = "vIdCertificado", referencedColumnName = "vIdCertificado")
    @ManyToOne(optional = false)
    private Certificadopago vIdCertificado;

    public Rengloncertificado() {
    }

    public Rengloncertificado(Integer vIdRenglon) {
        this.vIdRenglon = vIdRenglon;
    }

    public Rengloncertificado(Integer vIdRenglon, Double vCostoActual, Integer vAvance) {
        this.vIdRenglon = vIdRenglon;
        this.vCostoActual = vCostoActual;
        this.vAvance = vAvance;
        this.vCostoAPagar = (vCostoActual * vAvance)/100;
    }

    public Integer getVIdRenglon() {
        return vIdRenglon;
    }

    public void setVIdRenglon(Integer vIdRenglon) {
        this.vIdRenglon = vIdRenglon;
    }

    public double getVCostoActual() {
        return vCostoActual;
    }

    public void setVCostoActual(double vCostoActual) {
        this.vCostoActual = vCostoActual;
    }

    public int getVAvance() {
        return vAvance;
    }

    public void setVAvance(int vAvance) {
        this.vAvance = vAvance;
    }

    public double getVCostoAPagar() {
        return vCostoAPagar;
    }

    public void setVCostoAPagar(double vCostoAPagar) {
        this.vCostoAPagar = vCostoAPagar;
    }

    public Certificadopago getVIdCertificado() {
        return vIdCertificado;
    }

    public void setVIdCertificado(Certificadopago vIdCertificado) {
        this.vIdCertificado = vIdCertificado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vIdRenglon != null ? vIdRenglon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rengloncertificado)) {
            return false;
        }
        Rengloncertificado other = (Rengloncertificado) object;
        if ((this.vIdRenglon == null && other.vIdRenglon != null) || (this.vIdRenglon != null && !this.vIdRenglon.equals(other.vIdRenglon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Rengloncertificado[ vIdRenglon=" + vIdRenglon + " ]";
    }
    
}
