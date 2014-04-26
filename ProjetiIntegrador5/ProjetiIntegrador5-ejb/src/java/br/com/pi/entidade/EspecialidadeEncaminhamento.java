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
@Table(name = "EspecialidadeEncaminhamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EspecialidadeEncaminhamento.findAll", query = "SELECT e FROM EspecialidadeEncaminhamento e"),
    @NamedQuery(name = "EspecialidadeEncaminhamento.findByIdEspecialidade", query = "SELECT e FROM EspecialidadeEncaminhamento e WHERE e.idEspecialidade = :idEspecialidade"),
    @NamedQuery(name = "EspecialidadeEncaminhamento.findByDescricao", query = "SELECT e FROM EspecialidadeEncaminhamento e WHERE e.descricao = :descricao")})
public class EspecialidadeEncaminhamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdEspecialidade")
    private Integer idEspecialidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEspecialidade")
    private Collection<Encaminhamento> encaminhamentoCollection;

    public EspecialidadeEncaminhamento() {
    }

    public EspecialidadeEncaminhamento(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public EspecialidadeEncaminhamento(Integer idEspecialidade, String descricao) {
        this.idEspecialidade = idEspecialidade;
        this.descricao = descricao;
    }

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
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
        hash += (idEspecialidade != null ? idEspecialidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspecialidadeEncaminhamento)) {
            return false;
        }
        EspecialidadeEncaminhamento other = (EspecialidadeEncaminhamento) object;
        if ((this.idEspecialidade == null && other.idEspecialidade != null) || (this.idEspecialidade != null && !this.idEspecialidade.equals(other.idEspecialidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.EspecialidadeEncaminhamento[ idEspecialidade=" + idEspecialidade + " ]";
    }
    
}
