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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alejo
 */
@Entity
@Table(name = "renglonfoja")
@NamedQueries({
    @NamedQuery(name = "Renglonfoja.findAll", query = "SELECT r FROM Renglonfoja r"),
    @NamedQuery(name = "Renglonfoja.findByVIdRenglon", query = "SELECT r FROM Renglonfoja r WHERE r.vIdRenglon = :vIdRenglon"),
    @NamedQuery(name = "Renglonfoja.findByVPorcentajeAnterior", query = "SELECT r FROM Renglonfoja r WHERE r.vPorcentajeAnterior = :vPorcentajeAnterior"),
    @NamedQuery(name = "Renglonfoja.findByVPorcentajeActual", query = "SELECT r FROM Renglonfoja r WHERE r.vPorcentajeActual = :vPorcentajeActual"),
    @NamedQuery(name = "Renglonfoja.findByVPorcentajeAcumulado", query = "SELECT r FROM Renglonfoja r WHERE r.vPorcentajeAcumulado = :vPorcentajeAcumulado")})
public class Renglonfoja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vIdRenglon")
    private Integer vIdRenglon;
    @Basic(optional = false)
    @Column(name = "vPorcentajeAnterior")
    private int vPorcentajeAnterior;
    @Basic(optional = false)
    @Column(name = "vPorcentajeActual")
    private int vPorcentajeActual;
    @Basic(optional = false)
    @Column(name = "vPorcentajeAcumulado")
    private int vPorcentajeAcumulado;
    @JoinColumn(name = "vIdItem", referencedColumnName = "vIdItem")
    @OneToOne(optional = false)
    private Item vIdItem;
    @JoinColumn(name = "vIdFoja", referencedColumnName = "vIdFoja")
    @ManyToOne(optional = false)
    private Fojamedicion vIdFoja;

    public Renglonfoja() {
    }

    public Renglonfoja(Integer vIdRenglon) {
        this.vIdRenglon = vIdRenglon;
    }

    public Renglonfoja(Integer vIdRenglon, Integer vPorcentajeAnterior, Item vItem) {
        this.vIdRenglon = vIdRenglon;
        this.vPorcentajeAnterior = vPorcentajeAnterior;
        this.vPorcentajeActual = 0;
        this.vPorcentajeAcumulado = vPorcentajeAnterior;
        this.vIdItem = vItem;
    }
    
    public void DefinirPorcentajeActual(Integer vPorcentajeActual) throws Exception {
        if ((vPorcentajeActual + this.vPorcentajeAnterior)<=100){
            this.vPorcentajeActual = vPorcentajeActual;
            this.vPorcentajeAcumulado = vPorcentajeActual + this.vPorcentajeAnterior;
        }else{
            throw new Exception ("La suma de porcentajes da un valor mayor al 100%");
        }
    }

    public Integer getVIdRenglon() {
        return vIdRenglon;
    }

    public void setVIdRenglon(Integer vIdRenglon) {
        this.vIdRenglon = vIdRenglon;
    }

    public int getVPorcentajeAnterior() {
        return vPorcentajeAnterior;
    }

    public void setVPorcentajeAnterior(int vPorcentajeAnterior) {
        this.vPorcentajeAnterior = vPorcentajeAnterior;
    }

    public int getVPorcentajeActual() {
        return vPorcentajeActual;
    }

    public void setVPorcentajeActual(int vPorcentajeActual) {
        this.vPorcentajeActual = vPorcentajeActual;
    }

    public int getVPorcentajeAcumulado() {
        return vPorcentajeAcumulado;
    }

    public void setVPorcentajeAcumulado(int vPorcentajeAcumulado) {
        this.vPorcentajeAcumulado = vPorcentajeAcumulado;
    }

    public Item getVIdItem() {
        return vIdItem;
    }

    public void setVIdItem(Item vIdItem) {
        this.vIdItem = vIdItem;
    }

    public Fojamedicion getVIdFoja() {
        return vIdFoja;
    }

    public void setVIdFoja(Fojamedicion vIdFoja) {
        this.vIdFoja = vIdFoja;
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
        if (!(object instanceof Renglonfoja)) {
            return false;
        }
        Renglonfoja other = (Renglonfoja) object;
        if ((this.vIdRenglon == null && other.vIdRenglon != null) || (this.vIdRenglon != null && !this.vIdRenglon.equals(other.vIdRenglon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Renglonfoja[ vIdRenglon=" + vIdRenglon + " ]";
    }
    
}
