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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alejo
 */
@Entity
@Table(name = "financiacion")
@NamedQueries({
    @NamedQuery(name = "Financiacion.findAll", query = "SELECT f FROM Financiacion f"),
    @NamedQuery(name = "Financiacion.findByVIdFinanciacion", query = "SELECT f FROM Financiacion f WHERE f.vIdFinanciacion = :vIdFinanciacion"),
    @NamedQuery(name = "Financiacion.findByVDescripcion", query = "SELECT f FROM Financiacion f WHERE f.vDescripcion = :vDescripcion")})
public class Financiacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "vIdFinanciacion")
    private Integer vIdFinanciacion;
    @Basic(optional = false)
    @Column(name = "vDescripcion")
    private String vDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vFinanciacion")
    private List<Obra> obraList;

    public Financiacion() {
    }

    public Financiacion(Integer vIdFinanciacion) {
        this.vIdFinanciacion = vIdFinanciacion;
    }

    public Financiacion(Integer vIdFinanciacion, String vDescripcion) {
        this.vIdFinanciacion = vIdFinanciacion;
        this.vDescripcion = vDescripcion;
    }

    public Integer getVIdFinanciacion() {
        return vIdFinanciacion;
    }

    public void setVIdFinanciacion(Integer vIdFinanciacion) {
        this.vIdFinanciacion = vIdFinanciacion;
    }

    public String getVDescripcion() {
        return vDescripcion;
    }

    public void setVDescripcion(String vDescripcion) {
        this.vDescripcion = vDescripcion;
    }

    public List<Obra> getObraList() {
        return obraList;
    }

    public void setObraList(List<Obra> obraList) {
        this.obraList = obraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vIdFinanciacion != null ? vIdFinanciacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Financiacion)) {
            return false;
        }
        Financiacion other = (Financiacion) object;
        if ((this.vIdFinanciacion == null && other.vIdFinanciacion != null) || (this.vIdFinanciacion != null && !this.vIdFinanciacion.equals(other.vIdFinanciacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Financiacion[ vIdFinanciacion=" + vIdFinanciacion + " ]";
    }
    
}
