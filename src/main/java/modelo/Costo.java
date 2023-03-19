/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "costo")
@NamedQueries({
    @NamedQuery(name = "Costo.findAll", query = "SELECT c FROM Costo c"),
    @NamedQuery(name = "Costo.findByVIdCosto", query = "SELECT c FROM Costo c WHERE c.vIdCosto = :vIdCosto"),
    @NamedQuery(name = "Costo.findByVMonto", query = "SELECT c FROM Costo c WHERE c.vMonto = :vMonto"),
    @NamedQuery(name = "Costo.findByVFechaInicioVigencia", query = "SELECT c FROM Costo c WHERE c.vFechaInicioVigencia = :vFechaInicioVigencia")})
public class Costo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vIdCosto")
    private Integer vIdCosto;
    @Basic(optional = false)
    @Column(name = "vMonto")
    private double vMonto;
    @Basic(optional = false)
    @Column(name = "vFechaInicioVigencia")
    private String vFechaInicioVigencia;
    @JoinColumn(name = "vIdItem", referencedColumnName = "vIdItem")
    @ManyToOne(optional = false)
    private Item vIdItem;

    public Costo() {
    }
    
    public Costo(Integer vIdCosto) {
        this.vIdCosto = vIdCosto;
    }

    public Costo(Integer vIdCosto, double vMonto, String vFechaInicioVigencia, Item item) {
        this.vIdCosto = vIdCosto;
        this.vMonto = vMonto;
        this.vFechaInicioVigencia = vFechaInicioVigencia;
        this.vIdItem = item;
    }

    public Integer getVIdCosto() {
        return vIdCosto;
    }

    public void setVIdCosto(Integer vIdCosto) {
        this.vIdCosto = vIdCosto;
    }

    public double getVMonto() {
        return vMonto;
    }

    public void setVMonto(double vMonto) {
        this.vMonto = vMonto;
    }

    public String getVFechaInicioVigencia() {
        return vFechaInicioVigencia;
    }

    public void setVFechaInicioVigencia(String vFechaInicioVigencia) {
        this.vFechaInicioVigencia = vFechaInicioVigencia;
    }

    public Item getVIdItem() {
        return vIdItem;
    }

    public void setVIdItem(Item vIdItem) {
        this.vIdItem = vIdItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vIdCosto != null ? vIdCosto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Costo)) {
            return false;
        }
        Costo other = (Costo) object;
        if ((this.vIdCosto == null && other.vIdCosto != null) || (this.vIdCosto != null && !this.vIdCosto.equals(other.vIdCosto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Costo[ vIdCosto=" + vIdCosto + " ]";
    }

    public void add(Costo costo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void remove(Costo costo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
