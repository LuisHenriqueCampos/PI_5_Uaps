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
@Table(name = "atribuicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atribuicao.findAll", query = "SELECT a FROM Atribuicao a"),
    @NamedQuery(name = "Atribuicao.findByIdAtribuicao", query = "SELECT a FROM Atribuicao a WHERE a.idAtribuicao = :idAtribuicao"),
    @NamedQuery(name = "Atribuicao.findByDescricao", query = "SELECT a FROM Atribuicao a WHERE a.descricao = :descricao")})
public class Atribuicao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdAtribuicao")
    private Short idAtribuicao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAtribuicao")
    private Collection<Medicoenfermeira> medicoenfermeiraCollection;

    public Atribuicao() {
    }

    public Atribuicao(Short idAtribuicao) {
        this.idAtribuicao = idAtribuicao;
    }

    public Atribuicao(Short idAtribuicao, String descricao) {
        this.idAtribuicao = idAtribuicao;
        this.descricao = descricao;
    }

    public Short getIdAtribuicao() {
        return idAtribuicao;
    }

    public void setIdAtribuicao(Short idAtribuicao) {
        this.idAtribuicao = idAtribuicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Medicoenfermeira> getMedicoenfermeiraCollection() {
        return medicoenfermeiraCollection;
    }

    public void setMedicoenfermeiraCollection(Collection<Medicoenfermeira> medicoenfermeiraCollection) {
        this.medicoenfermeiraCollection = medicoenfermeiraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtribuicao != null ? idAtribuicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atribuicao)) {
            return false;
        }
        Atribuicao other = (Atribuicao) object;
        if ((this.idAtribuicao == null && other.idAtribuicao != null) || (this.idAtribuicao != null && !this.idAtribuicao.equals(other.idAtribuicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Atribuicao[ idAtribuicao=" + idAtribuicao + " ]";
    }
    
}
