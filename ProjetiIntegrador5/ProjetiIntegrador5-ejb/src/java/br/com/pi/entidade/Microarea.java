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
@Table(name = "microarea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Microarea.findAll", query = "SELECT m FROM Microarea m"),
    @NamedQuery(name = "Microarea.findByIdMicroArea", query = "SELECT m FROM Microarea m WHERE m.idMicroArea = :idMicroArea"),
    @NamedQuery(name = "Microarea.findByDescricao", query = "SELECT m FROM Microarea m WHERE m.descricao = :descricao")})
public class Microarea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdMicroArea")
    private Integer idMicroArea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMicroArea")
    private Collection<Endereco> enderecoCollection;
    @JoinColumn(name = "IdArea", referencedColumnName = "IdArea")
    @ManyToOne(optional = false)
    private Area idArea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMicroArea")
    private Collection<Agentesaude> agentesaudeCollection;

    public Microarea() {
    }

    public Microarea(Integer idMicroArea) {
        this.idMicroArea = idMicroArea;
    }

    public Microarea(Integer idMicroArea, String descricao) {
        this.idMicroArea = idMicroArea;
        this.descricao = descricao;
    }

    public Integer getIdMicroArea() {
        return idMicroArea;
    }

    public void setIdMicroArea(Integer idMicroArea) {
        this.idMicroArea = idMicroArea;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Endereco> getEnderecoCollection() {
        return enderecoCollection;
    }

    public void setEnderecoCollection(Collection<Endereco> enderecoCollection) {
        this.enderecoCollection = enderecoCollection;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    @XmlTransient
    public Collection<Agentesaude> getAgentesaudeCollection() {
        return agentesaudeCollection;
    }

    public void setAgentesaudeCollection(Collection<Agentesaude> agentesaudeCollection) {
        this.agentesaudeCollection = agentesaudeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMicroArea != null ? idMicroArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Microarea)) {
            return false;
        }
        Microarea other = (Microarea) object;
        if ((this.idMicroArea == null && other.idMicroArea != null) || (this.idMicroArea != null && !this.idMicroArea.equals(other.idMicroArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Microarea[ idMicroArea=" + idMicroArea + " ]";
    }
    
}
