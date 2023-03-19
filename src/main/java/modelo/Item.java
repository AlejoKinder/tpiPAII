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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import metodosIlegales.*;

/**
 *
 * @author alejo
 */
@Entity
@Table(name = "item")
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.findByVIdItem", query = "SELECT i FROM Item i WHERE i.vIdItem = :vIdItem"),
    @NamedQuery(name = "Item.findByVDenominacion", query = "SELECT i FROM Item i WHERE i.vDenominacion = :vDenominacion"),
    @NamedQuery(name = "Item.findByVOrden", query = "SELECT i FROM Item i WHERE i.vOrden = :vOrden"),
    @NamedQuery(name = "Item.findByVIncidencia", query = "SELECT i FROM Item i WHERE i.vIncidencia = :vIncidencia"),
    @NamedQuery(name = "Item.findByVImpuestoFlete", query = "SELECT i FROM Item i WHERE i.vImpuestoFlete = :vImpuestoFlete"),
    @NamedQuery(name = "Item.findByVImpuestoGastos", query = "SELECT i FROM Item i WHERE i.vImpuestoGastos = :vImpuestoGastos"),
    @NamedQuery(name = "Item.findByVImpuestoUtilidad", query = "SELECT i FROM Item i WHERE i.vImpuestoUtilidad = :vImpuestoUtilidad")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vIdItem")
    private Integer vIdItem;
    @Basic(optional = false)
    @Column(name = "vDenominacion")
    private String vDenominacion;
    @Basic(optional = false)
    @Column(name = "vOrden")
    private int vOrden;
    @Basic(optional = false)
    @Column(name = "vIncidencia")
    private int vIncidencia;
    @Basic(optional = false)
    @Column(name = "vImpuestoFlete")
    private double vImpuestoFlete;
    @Basic(optional = false)
    @Column(name = "vImpuestoGastos")
    private double vImpuestoGastos;
    @Basic(optional = false)
    @Column(name = "vImpuestoUtilidad")
    private double vImpuestoUtilidad;
    @JoinColumn(name = "vIdObra", referencedColumnName = "vIdObra")
    @ManyToOne(optional = false)
    private Obra vIdObra;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vIdItem")
    private Renglonfoja renglonfoja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vIdItem")
    private List<Costo> costoList = new ArrayList<Costo>();
    
   

    public Item() {
    }

    public Item(Integer vIdItem) {
        this.vIdItem = vIdItem;
    }
    
    public Item(String vDenominacion, int vOrden, int vIncidencia, double vImpuestoFlete, double vImpuestoGastos, double vImpuestoUtilidad) {
        this.vDenominacion = vDenominacion;
        this.vOrden = vOrden;
        this.vIncidencia = vIncidencia;
        this.vImpuestoFlete = vImpuestoFlete;
        this.vImpuestoGastos = vImpuestoGastos;
        this.vImpuestoUtilidad = vImpuestoUtilidad;
    }

    public Item(Integer vIdItem, String vDenominacion, int vOrden, int vIncidencia, double vImpuestoFlete, double vImpuestoGastos, double vImpuestoUtilidad, Obra obra) {
        this.vIdItem = vIdItem;
        this.vDenominacion = vDenominacion;
        this.vOrden = vOrden;
        this.vIncidencia = vIncidencia;
        this.vImpuestoFlete = vImpuestoFlete;
        this.vImpuestoGastos = vImpuestoGastos;
        this.vImpuestoUtilidad = vImpuestoUtilidad;
        this.vIdObra = obra;
    }
    
    public void AgregarCosto (Costo vNuevoCosto){
        this.costoList.add(vNuevoCosto);
    }
    
    public Costo DevolverCostoVigente () throws Exception{
         Auxiliar vAux = new Auxiliar();
        
        if (!costoList.isEmpty()){
            
            if (costoList.size() != 1){
                
                Integer[] vFechaMayor = new Integer[3];
                
                vFechaMayor[0] = 0;
                vFechaMayor[1] = 0;
                vFechaMayor[2] = 0;
                Costo vCostoVigente = null;
                
                for (Costo a: costoList){
                    
                    Integer[] vFecha = new Integer[3];
                    vFecha = vAux.TransformarFecha(a.getVFechaInicioVigencia());
                    
                    vFechaMayor = vAux.DevolverFechaMayor(vFecha, vFechaMayor);
                    
                    if (vFechaMayor == vFecha){
                        vCostoVigente = a;
                    }
                }
                
                return vCostoVigente;

            }else{
                return costoList.get(0);
            }
        }else{
            throw new Exception ("El item no tiene costos relacionados");
        }
    }

    public Integer getVIdItem() {
        return vIdItem;
    }

    public void setVIdItem(Integer vIdItem) {
        this.vIdItem = vIdItem;
    }

    public String getVDenominacion() {
        return vDenominacion;
    }

    public void setVDenominacion(String vDenominacion) {
        this.vDenominacion = vDenominacion;
    }

    public int getVOrden() {
        return vOrden;
    }

    public void setVOrden(int vOrden) {
        this.vOrden = vOrden;
    }

    public int getVIncidencia() {
        return vIncidencia;
    }

    public void setVIncidencia(int vIncidencia) {
        this.vIncidencia = vIncidencia;
    }

    public double getVImpuestoFlete() {
        return vImpuestoFlete;
    }

    public void setVImpuestoFlete(double vImpuestoFlete) {
        this.vImpuestoFlete = vImpuestoFlete;
    }

    public double getVImpuestoGastos() {
        return vImpuestoGastos;
    }

    public void setVImpuestoGastos(double vImpuestoGastos) {
        this.vImpuestoGastos = vImpuestoGastos;
    }

    public double getVImpuestoUtilidad() {
        return vImpuestoUtilidad;
    }

    public void setVImpuestoUtilidad(double vImpuestoUtilidad) {
        this.vImpuestoUtilidad = vImpuestoUtilidad;
    }

    public Obra getVIdObra() {
        return vIdObra;
    }

    public void setVIdObra(Obra vIdObra) {
        this.vIdObra = vIdObra;
    }

    public Renglonfoja getRenglonfoja() {
        return renglonfoja;
    }

    public void setRenglonfoja(Renglonfoja renglonfoja) {
        this.renglonfoja = renglonfoja;
    }

    public List<Costo> getCostoList() {
        return costoList;
    }

    public void setCostoList(List<Costo> costoList) {
        this.costoList = costoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vIdItem != null ? vIdItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.vIdItem == null && other.vIdItem != null) || (this.vIdItem != null && !this.vIdItem.equals(other.vIdItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Item[ vIdItem=" + vIdItem + " ]";
    }
    
}
