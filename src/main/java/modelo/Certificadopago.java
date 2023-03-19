/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alejo
 */
@Entity
@Table(name = "certificadopago")
@NamedQueries({
    @NamedQuery(name = "Certificadopago.findAll", query = "SELECT c FROM Certificadopago c"),
    @NamedQuery(name = "Certificadopago.findByVIdCertificado", query = "SELECT c FROM Certificadopago c WHERE c.vIdCertificado = :vIdCertificado"),
    @NamedQuery(name = "Certificadopago.findByVCostoTotal", query = "SELECT c FROM Certificadopago c WHERE c.vCostoTotal = :vCostoTotal"),
    @NamedQuery(name = "Certificadopago.findByVFechaEmision", query = "SELECT c FROM Certificadopago c WHERE c.vFechaEmision = :vFechaEmision"),
    @NamedQuery(name = "Certificadopago.findByVPagado", query = "SELECT c FROM Certificadopago c WHERE c.vPagado = :vPagado")})
public class Certificadopago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vIdCertificado")
    private Integer vIdCertificado;
    @Basic(optional = false)
    @Column(name = "vCostoTotal")
    private double vCostoTotal;
    @Basic(optional = false)
    @Column(name = "vFechaEmision")
    private String vFechaEmision;
    @Basic(optional = false)
    @Column(name = "vPagado")
    private boolean vPagado;
    @JoinColumn(name = "vFoja", referencedColumnName = "vIdFoja")
    @OneToOne(optional = false)
    private Fojamedicion vFoja;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vIdCertificado")
    private List<Rengloncertificado> rengloncertificadoList;

    public Certificadopago() {
    }

    public Certificadopago(Integer vIdCertificado) {
        this.vIdCertificado = vIdCertificado;
    }

    public Certificadopago(Integer vIdCertificado, String vFechaEmision, /*Obra vObra,*/ Fojamedicion vFoja) {
        this.vIdCertificado = vIdCertificado;
        this.vCostoTotal = (double)0;
        this.vFechaEmision = vFechaEmision;
        this.vPagado = false;
        //CREO QUE NO IRIA ESTO.
        //this.vObra = vObra;
        this.vFoja = vFoja;
    }
    
    public void CrearRenglones() throws Exception{
    
        Integer vPos = 0;
        for (Renglonfoja a: this.vFoja.getRenglonfojaList()){
            Rengloncertificado vNuevoRenglon = new Rengloncertificado(this.rengloncertificadoList.size()+1, this.vFoja.getVObra().getItemList().get(vPos).DevolverCostoVigente().getVMonto(), a.getVPorcentajeActual());
            
            this.rengloncertificadoList.add(vNuevoRenglon);
            vPos = vPos + 1;
        }
        
        this.vCostoTotal = this.CalcularMontoTotal();
        
    }
    
    public Double CalcularMontoTotal (){
    
        Double vMonto = (double)0;
        for(Rengloncertificado a: this.rengloncertificadoList){
            
            System.out.println("Valor agregado: " + a.getVCostoAPagar());
            vMonto = vMonto + a.getVCostoAPagar();
        }
        
        return vMonto;
    }

    public Integer getVIdCertificado() {
        return vIdCertificado;
    }

    public void setVIdCertificado(Integer vIdCertificado) {
        this.vIdCertificado = vIdCertificado;
    }

    public double getVCostoTotal() {
        return vCostoTotal;
    }

    public void setVCostoTotal(double vCostoTotal) {
        this.vCostoTotal = vCostoTotal;
    }

    public String getVFechaEmision() {
        return vFechaEmision;
    }

    public void setVFechaEmision(String vFechaEmision) {
        this.vFechaEmision = vFechaEmision;
    }

    public boolean getVPagado() {
        return vPagado;
    }

    public void setVPagado(boolean vPagado) {
        this.vPagado = vPagado;
    }

    public Fojamedicion getVFoja() {
        return vFoja;
    }

    public void setVFoja(Fojamedicion vFoja) {
        this.vFoja = vFoja;
    }
    
    public void Pagar(){
        this.vPagado = true;
    }

    public List<Rengloncertificado> getRengloncertificadoList() {
        return rengloncertificadoList;
    }

    public void setRengloncertificadoList(List<Rengloncertificado> rengloncertificadoList) {
        this.rengloncertificadoList = rengloncertificadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vIdCertificado != null ? vIdCertificado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificadopago)) {
            return false;
        }
        Certificadopago other = (Certificadopago) object;
        if ((this.vIdCertificado == null && other.vIdCertificado != null) || (this.vIdCertificado != null && !this.vIdCertificado.equals(other.vIdCertificado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Certificadopago[ vIdCertificado=" + vIdCertificado + " ]";
    }
    
}
