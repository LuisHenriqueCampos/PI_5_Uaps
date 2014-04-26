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
@Table(name = "sexo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sexo.findAll", query = "SELECT s FROM Sexo s"),
    @NamedQuery(name = "Sexo.findByIdSexo", query = "SELECT s FROM Sexo s WHERE s.idSexo = :idSexo"),
    @NamedQuery(name = "Sexo.findByDescricao", query = "SELECT s FROM Sexo s WHERE s.descricao = :descricao")})
public class Sexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdSexo")
    private Short idSexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSexo")
    private Collection<Paciente> pacienteCollection;

    public Sexo() {
    }

    public Sexo(Short idSexo) {
        this.idSexo = idSexo;
    }

    public Sexo(Short idSexo, String descricao) {
        this.idSexo = idSexo;
        this.descricao = descricao;
    }

    public Short getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Short idSexo) {
        this.idSexo = idSexo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSexo != null ? idSexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexo)) {
            return false;
        }
        Sexo other = (Sexo) object;
        if ((this.idSexo == null && other.idSexo != null) || (this.idSexo != null && !this.idSexo.equals(other.idSexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Sexo[ idSexo=" + idSexo + " ]";
    }
    
}
