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
@Table(name = "empresa")
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
    @NamedQuery(name = "Empresa.findByVCuit", query = "SELECT e FROM Empresa e WHERE e.vCuit = :vCuit"),
    @NamedQuery(name = "Empresa.findByVNombre", query = "SELECT e FROM Empresa e WHERE e.vNombre = :vNombre"),
    @NamedQuery(name = "Empresa.findByVDireccion", query = "SELECT e FROM Empresa e WHERE e.vDireccion = :vDireccion"),
    @NamedQuery(name = "Empresa.findByVRepresentanteLegal", query = "SELECT e FROM Empresa e WHERE e.vRepresentanteLegal = :vRepresentanteLegal"),
    @NamedQuery(name = "Empresa.findByVRepresentanteTecnico", query = "SELECT e FROM Empresa e WHERE e.vRepresentanteTecnico = :vRepresentanteTecnico")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "vCuit")
    private Integer vCuit;
    @Basic(optional = false)
    @Column(name = "vNombre")
    private String vNombre;
    @Basic(optional = false)
    @Column(name = "vDireccion")
    private String vDireccion;
    @Basic(optional = false)
    @Column(name = "vRepresentanteLegal")
    private String vRepresentanteLegal;
    @Basic(optional = false)
    @Column(name = "vRepresentanteTecnico")
    private String vRepresentanteTecnico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vEmpresa")
    private List<Obra> obraList;

    public Empresa() {
    }

    public Empresa(Integer vCuit) {
        this.vCuit = vCuit;
    }

    public Empresa(Integer vCuit, String vNombre, String vDireccion, String vRepresentanteLegal, String vRepresentanteTecnico) {
        this.vCuit = vCuit;
        this.vNombre = vNombre;
        this.vDireccion = vDireccion;
        this.vRepresentanteLegal = vRepresentanteLegal;
        this.vRepresentanteTecnico = vRepresentanteTecnico;
    }

    public Integer getVCuit() {
        return vCuit;
    }

    public void setVCuit(Integer vCuit) {
        this.vCuit = vCuit;
    }

    public String getVNombre() {
        return vNombre;
    }

    public void setVNombre(String vNombre) {
        this.vNombre = vNombre;
    }

    public String getVDireccion() {
        return vDireccion;
    }

    public void setVDireccion(String vDireccion) {
        this.vDireccion = vDireccion;
    }

    public String getVRepresentanteLegal() {
        return vRepresentanteLegal;
    }

    public void setVRepresentanteLegal(String vRepresentanteLegal) {
        this.vRepresentanteLegal = vRepresentanteLegal;
    }

    public String getVRepresentanteTecnico() {
        return vRepresentanteTecnico;
    }

    public void setVRepresentanteTecnico(String vRepresentanteTecnico) {
        this.vRepresentanteTecnico = vRepresentanteTecnico;
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
        hash += (vCuit != null ? vCuit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.vCuit == null && other.vCuit != null) || (this.vCuit != null && !this.vCuit.equals(other.vCuit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Empresa[ vCuit=" + vCuit + " ]";
    }
    
}
