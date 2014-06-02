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
@Table(name = "tipoencaminhamento")
public class Tipoencaminhamento implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdtipoEncaminhamento")
    private Short idtipoEncaminhamento;
    
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Descricao")
    private String descricao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipoEncaminhamento")
    private Collection<Encaminhamento> encaminhamentoCollection;

    public Tipoencaminhamento() {
    }

    public Tipoencaminhamento(Short idtipoEncaminhamento) {
        this.idtipoEncaminhamento = idtipoEncaminhamento;
    }

    public Tipoencaminhamento(Short idtipoEncaminhamento, String descricao) {
        this.idtipoEncaminhamento = idtipoEncaminhamento;
        this.descricao = descricao;
    }

    public Short getIdtipoEncaminhamento() {
        return idtipoEncaminhamento;
    }

    public void setIdtipoEncaminhamento(Short idtipoEncaminhamento) {
        this.idtipoEncaminhamento = idtipoEncaminhamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Encaminhamento> getEncaminhamentoCollection() {
        return encaminhamentoCollection;
    }

    public void setEncaminhamentoCollection(Collection<Encaminhamento> encaminhamentoCollection) {
        this.encaminhamentoCollection = encaminhamentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoEncaminhamento != null ? idtipoEncaminhamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoencaminhamento)) {
            return false;
        }
        Tipoencaminhamento other = (Tipoencaminhamento) object;
        if ((this.idtipoEncaminhamento == null && other.idtipoEncaminhamento != null) || (this.idtipoEncaminhamento != null && !this.idtipoEncaminhamento.equals(other.idtipoEncaminhamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Tipoencaminhamento[ idtipoEncaminhamento=" + idtipoEncaminhamento + " ]";
    }
    
}
