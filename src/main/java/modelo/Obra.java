/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alejo
 */
@Entity
@Table(name = "obra")
@NamedQueries({
    @NamedQuery(name = "Obra.findAll", query = "SELECT o FROM Obra o"),
    @NamedQuery(name = "Obra.findByVIdObra", query = "SELECT o FROM Obra o WHERE o.vIdObra = :vIdObra"),
    @NamedQuery(name = "Obra.findByVDenominacion", query = "SELECT o FROM Obra o WHERE o.vDenominacion = :vDenominacion"),
    @NamedQuery(name = "Obra.findByVLocalidad", query = "SELECT o FROM Obra o WHERE o.vLocalidad = :vLocalidad"),
    @NamedQuery(name = "Obra.findByVCantidadViviendas", query = "SELECT o FROM Obra o WHERE o.vCantidadViviendas = :vCantidadViviendas"),
    @NamedQuery(name = "Obra.findByVFechaInicio", query = "SELECT o FROM Obra o WHERE o.vFechaInicio = :vFechaInicio"),
    @NamedQuery(name = "Obra.findByVPlazo", query = "SELECT o FROM Obra o WHERE o.vPlazo = :vPlazo")})
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vIdObra")
    private Integer vIdObra;
    @Basic(optional = false)
    @Column(name = "vDenominacion")
    private String vDenominacion;
    @Basic(optional = false)
    @Column(name = "vLocalidad")
    private String vLocalidad;
    @Basic(optional = false)
    @Column(name = "vCantidadViviendas")
    private int vCantidadViviendas;
    @Basic(optional = false)
    @Column(name = "vFechaInicio")
    private String vFechaInicio;
    @Basic(optional = false)
    @Column(name = "vPlazo")
    private int vPlazo;
    @JoinColumn(name = "vFinanciacion", referencedColumnName = "vIdFinanciacion")
    @ManyToOne(optional = false)
    private Financiacion vFinanciacion;
    @JoinColumn(name = "vEmpresa", referencedColumnName = "vCuit")
    @ManyToOne(optional = false)
    private Empresa vEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vIdObra")
    private List<Item> itemList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vObra")
    private List<Fojamedicion> fojamedicionList;

    public Obra() {
    }

    public Obra(Integer vIdObra) {
        this.vIdObra = vIdObra;
    }

    public Obra(Integer vIdObra, String vDenominacion, String vLocalidad, Integer vCantidadViviendas, String vFechaInicio, Integer vPlazo, Empresa vEmpresa, Financiacion vFinanciacion) {
        this.vIdObra = vIdObra;
        this.vDenominacion = vDenominacion;
        this.vLocalidad = vLocalidad;
        this.vCantidadViviendas = vCantidadViviendas;
        this.vFechaInicio = vFechaInicio;
        this.vPlazo = vPlazo;
        this.vEmpresa = vEmpresa;
        this.vFinanciacion = vFinanciacion;
    }
    
    public void AgregarItem (Item vNuevoItem){
        this.itemList.add(vNuevoItem);
    }
    
    public Item BuscarItemId (Integer vIdItem)throws Exception{
        for (Item a: this.itemList){
            if (vIdItem.equals(a.getVIdItem())){
                return a;
            }
        }
        return null;
    }
    
    public Integer getVIdObra() {
        return vIdObra;
    }

    public void setVIdObra(Integer vIdObra) {
        this.vIdObra = vIdObra;
    }

    public String getVDenominacion() {
        return vDenominacion;
    }

    public void setVDenominacion(String vDenominacion) {
        this.vDenominacion = vDenominacion;
    }

    public String getVLocalidad() {
        return vLocalidad;
    }

    public void setVLocalidad(String vLocalidad) {
        this.vLocalidad = vLocalidad;
    }

    public int getVCantidadViviendas() {
        return vCantidadViviendas;
    }

    public void setVCantidadViviendas(int vCantidadViviendas) {
        this.vCantidadViviendas = vCantidadViviendas;
    }

    public String getVFechaInicio() {
        return vFechaInicio;
    }

    public void setVFechaInicio(String vFechaInicio) {
        this.vFechaInicio = vFechaInicio;
    }

    public int getVPlazo() {
        return vPlazo;
    }

    public void setVPlazo(int vPlazo) {
        this.vPlazo = vPlazo;
    }

    public Financiacion getVFinanciacion() {
        return vFinanciacion;
    }

    public void setVFinanciacion(Financiacion vFinanciacion) {
        this.vFinanciacion = vFinanciacion;
    }

    public Empresa getVEmpresa() {
        return vEmpresa;
    }

    public void setVEmpresa(Empresa vEmpresa) {
        this.vEmpresa = vEmpresa;
    }

    public List<Item> getItemList() {
        return itemList;
    }
    
    public ArrayList<Item> getItemArrayList(){
    
        List<Item> itemList1 = getItemList();
        return new ArrayList<>(itemList1);
    }
            

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Fojamedicion> getFojamedicionList() {
        return fojamedicionList;
    }

    public void setFojamedicionList(List<Fojamedicion> fojamedicionList) {
        this.fojamedicionList = fojamedicionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vIdObra != null ? vIdObra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obra)) {
            return false;
        }
        Obra other = (Obra) object;
        if ((this.vIdObra == null && other.vIdObra != null) || (this.vIdObra != null && !this.vIdObra.equals(other.vIdObra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Obra[ vIdObra=" + vIdObra + " ]";
    }

    public Object BuscarItemId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
