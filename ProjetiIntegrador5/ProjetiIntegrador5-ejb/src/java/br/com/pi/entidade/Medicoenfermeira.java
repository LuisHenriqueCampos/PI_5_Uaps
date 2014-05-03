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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author petrovick
 */
@Entity
@Table(name = "medicoenfermeira")
@XmlRootElement
public class Medicoenfermeira implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "IdPessoaMedicoEnfermeira", insertable = false, updatable = false)
    private Integer idPessoaMedicoEnfermeira;
    
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "Assinatura")
    private String assinatura;
   
    @Column(name = "Registro")
    private int registro;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicoenfermeiraidEnfermeira")
    private Collection<Area> areaCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicoenfermeiraidMedico")
    private Collection<Area> areaCollection1;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPessoaMedicoEnfermeira")
    private Collection<Encaminhamento> encaminhamentoCollection;
    
    @JoinColumn(name = "IdAtribuicao", referencedColumnName = "IdAtribuicao")
    @ManyToOne(optional = false)
    private Atribuicao idAtribuicao;
    
    @JoinColumn(name = "IdPessoaMedicoEnfermeira", referencedColumnName = "IdPessoa")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Pessoaa pessoa;

    public Medicoenfermeira() {
        pessoa = new Pessoaa();
        
    }

    public Integer getIdPessoaMedicoEnfermeira() {
        return idPessoaMedicoEnfermeira;
    }

    public void setIdPessoaMedicoEnfermeira(Integer idPessoaMedicoEnfermeira) {
        this.idPessoaMedicoEnfermeira = idPessoaMedicoEnfermeira;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    @XmlTransient
    public Collection<Area> getAreaCollection() {
        return areaCollection;
    }

    public void setAreaCollection(Collection<Area> areaCollection) {
        this.areaCollection = areaCollection;
    }

    @XmlTransient
    public Collection<Area> getAreaCollection1() {
        return areaCollection1;
    }

    public void setAreaCollection1(Collection<Area> areaCollection1) {
        this.areaCollection1 = areaCollection1;
    }

    @XmlTransient
    public Collection<Encaminhamento> getEncaminhamentoCollection() {
        return encaminhamentoCollection;
    }

    public void setEncaminhamentoCollection(Collection<Encaminhamento> encaminhamentoCollection) {
        this.encaminhamentoCollection = encaminhamentoCollection;
    }

    public Atribuicao getIdAtribuicao() {
        return idAtribuicao;
    }

    public void setIdAtribuicao(Atribuicao idAtribuicao) {
        this.idAtribuicao = idAtribuicao;
    }

    public Pessoaa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoaa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoaMedicoEnfermeira != null ? idPessoaMedicoEnfermeira.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicoenfermeira)) {
            return false;
        }
        Medicoenfermeira other = (Medicoenfermeira) object;
        if ((this.idPessoaMedicoEnfermeira == null && other.idPessoaMedicoEnfermeira != null) || (this.idPessoaMedicoEnfermeira != null && !this.idPessoaMedicoEnfermeira.equals(other.idPessoaMedicoEnfermeira))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Medicoenfermeira[ idPessoaMedicoEnfermeira=" + idPessoaMedicoEnfermeira + " ]";
    }
    
}
