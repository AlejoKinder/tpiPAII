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
import javax.persistence.ManyToOne;
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
@Table(name = "fojamedicion")
@NamedQueries({
    @NamedQuery(name = "Fojamedicion.findAll", query = "SELECT f FROM Fojamedicion f"),
    @NamedQuery(name = "Fojamedicion.findByVIdFoja", query = "SELECT f FROM Fojamedicion f WHERE f.vIdFoja = :vIdFoja"),
    @NamedQuery(name = "Fojamedicion.findByVFechaEmision", query = "SELECT f FROM Fojamedicion f WHERE f.vFechaEmision = :vFechaEmision")})
public class Fojamedicion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vIdFoja")
    private Integer vIdFoja;
    @Basic(optional = false)
    @Column(name = "vFechaEmision")
    private String vFechaEmision;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vFoja")
    private Certificadopago certificadopago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vIdFoja")
    private List<Renglonfoja> renglonfojaList;
    @JoinColumn(name = "vObra", referencedColumnName = "vIdObra")
    @ManyToOne(optional = false)
    private Obra vObra;

    public Fojamedicion() {
    }

    public Fojamedicion(Integer vIdFoja) {
        this.vIdFoja = vIdFoja;
    }

    public Fojamedicion(Integer vIdFoja, String vFechaEmision, Obra vObra) {
        this.vIdFoja = vIdFoja;
        this.vFechaEmision = vFechaEmision;
        this.vObra = vObra;
    }
    
    public void AgregarRenglon (Renglonfoja vNuevoRenglon) {

        this.renglonfojaList.add(vNuevoRenglon);
    }
    
    public Integer DevolverAvanceAnterior(Item vItem){
    
        for (Renglonfoja a: renglonfojaList){
            if (a.getVIdItem() == vItem){
                return a.getVPorcentajeAcumulado();
            }
        }
        
        return 0;
    }        

    public Integer getVIdFoja() {
        return vIdFoja;
    }

    public void setVIdFoja(Integer vIdFoja) {
        this.vIdFoja = vIdFoja;
    }

    public String getVFechaEmision() {
        return vFechaEmision;
    }

    public void setVFechaEmision(String vFechaEmision) {
        this.vFechaEmision = vFechaEmision;
    }

    public Certificadopago getCertificadopago() {
        return certificadopago;
    }

    public void setCertificadopago(Certificadopago certificadopago) {
        this.certificadopago = certificadopago;
    }

    public List<Renglonfoja> getRenglonfojaList() {
        return renglonfojaList;
    }

    public void setRenglonfojaList(List<Renglonfoja> renglonfojaList) {
        this.renglonfojaList = renglonfojaList;
    }

    public Obra getVObra() {
        return vObra;
    }

    public void setVObra(Obra vObra) {
        this.vObra = vObra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vIdFoja != null ? vIdFoja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fojamedicion)) {
            return false;
        }
        Fojamedicion other = (Fojamedicion) object;
        if ((this.vIdFoja == null && other.vIdFoja != null) || (this.vIdFoja != null && !this.vIdFoja.equals(other.vIdFoja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Fojamedicion[ vIdFoja=" + vIdFoja + " ]";
    }
    
}
