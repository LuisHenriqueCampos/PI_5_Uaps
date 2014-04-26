/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author petrovick
 */
@Entity
@Table(name = "postosaude")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postosaude.findAll", query = "SELECT p FROM Postosaude p"),
    @NamedQuery(name = "Postosaude.findByIdPostoSaude", query = "SELECT p FROM Postosaude p WHERE p.idPostoSaude = :idPostoSaude"),
    @NamedQuery(name = "Postosaude.findByNomePosto", query = "SELECT p FROM Postosaude p WHERE p.nomePosto = :nomePosto")})
public class Postosaude implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPostoSaude")
    private Short idPostoSaude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NomePosto")
    private String nomePosto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPostoSaude")
    private Collection<Area> areaCollection;

    public Postosaude() {
    }

    public Postosaude(Short idPostoSaude) {
        this.idPostoSaude = idPostoSaude;
    }

    public Postosaude(Short idPostoSaude, String nomePosto) {
        this.idPostoSaude = idPostoSaude;
        this.nomePosto = nomePosto;
    }

    public Short getIdPostoSaude() {
        return idPostoSaude;
    }

    public void setIdPostoSaude(Short idPostoSaude) {
        this.idPostoSaude = idPostoSaude;
    }

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    @XmlTransient
    public Collection<Area> getAreaCollection() {
        return areaCollection;
    }

    public void setAreaCollection(Collection<Area> areaCollection) {
        this.areaCollection = areaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPostoSaude != null ? idPostoSaude.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postosaude)) {
            return false;
        }
        Postosaude other = (Postosaude) object;
        if ((this.idPostoSaude == null && other.idPostoSaude != null) || (this.idPostoSaude != null && !this.idPostoSaude.equals(other.idPostoSaude))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Postosaude[ idPostoSaude=" + idPostoSaude + " ]";
    }
    
}
