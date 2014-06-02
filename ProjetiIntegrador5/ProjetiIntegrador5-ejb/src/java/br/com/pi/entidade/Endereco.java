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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "endereco")
@XmlRootElement
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEndereco", nullable = false)
    private Integer idEndereco;
    
    @NotNull(message = "O campo Rua não pode ser Nulo")
    @Size(min = 1, max = 100, message = "Mínimo 1, Máximo 100 caracteres")
    @Column(name = "Rua", nullable = false, length = 100)
    private String rua;
    
    @NotNull(message = "O campo Cep não pode ser Nulo")
    @Column(name = "Cep", nullable = false)
    private String cep;
    
    @NotNull(message = "Selecione o Bairro")
    @JoinColumn(name = "IdBairro", referencedColumnName = "IdBairro", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bairro idBairro;
    
    @NotNull(message = "Selecione Micro-Área")
    @JoinColumn(name = "IdMicroArea", referencedColumnName = "IdMicroArea", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Microarea idMicroArea;
    
    @OneToMany(mappedBy = "idEndereco", fetch = FetchType.LAZY)
    private Collection<Familia> familiaCollection;

    public Endereco() {
    }

    public Endereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Endereco(Integer idEndereco, String rua, String cep) {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.cep = cep;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Bairro getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Bairro idBairro) {
        this.idBairro = idBairro;
    }

    public Microarea getIdMicroArea() {
        return idMicroArea;
    }

    public void setIdMicroArea(Microarea idMicroArea) {
        this.idMicroArea = idMicroArea;
    }

    @XmlTransient
    public Collection<Familia> getFamiliaCollection() {
        return familiaCollection;
    }

    public void setFamiliaCollection(Collection<Familia> familiaCollection) {
        this.familiaCollection = familiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEndereco != null ? idEndereco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.idEndereco == null && other.idEndereco != null) || (this.idEndereco != null && !this.idEndereco.equals(other.idEndereco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Endereco[ idEndereco=" + idEndereco + " ]";
    }
    
}
