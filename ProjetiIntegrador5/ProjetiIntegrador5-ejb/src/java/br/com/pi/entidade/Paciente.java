/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author petrovick
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByIdPessoaPaciente", query = "SELECT p FROM Paciente p WHERE p.idPessoaPaciente = :idPessoaPaciente"),
    @NamedQuery(name = "Paciente.findByDataNascimento", query = "SELECT p FROM Paciente p WHERE p.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "Paciente.findByNomeMae", query = "SELECT p FROM Paciente p WHERE p.nomeMae = :nomeMae"),
    @NamedQuery(name = "Paciente.findByNomePai", query = "SELECT p FROM Paciente p WHERE p.nomePai = :nomePai"),
    @NamedQuery(name = "Paciente.findByCns", query = "SELECT p FROM Paciente p WHERE p.cns = :cns"),
    @NamedQuery(name = "Paciente.findByTelefone", query = "SELECT p FROM Paciente p WHERE p.telefone = :telefone")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
    @Column(name = "IdPessoaPaciente", insertable = false, updatable = false)
    private Integer idPessoaPaciente;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "DataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NomeMae")
    private String nomeMae;
    @Size(max = 80)
    @Column(name = "NomePai")
    private String nomePai;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "Cns")
    private long cns;
    @Size(max = 11)
    @Column(name = "Telefone")
    private String telefone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPessoaPaciente")
    private Collection<Encaminhamento> encaminhamentoCollection;
    
    @JoinColumn(name = "IdPessoaPaciente", referencedColumnName = "IdPessoa")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Pessoaa pessoa;
    @JoinColumn(name = "IdSexo", referencedColumnName = "IdSexo")
    @ManyToOne(optional = false)
    private Sexo idSexo;
    @JoinColumn(name = "IdFamilia", referencedColumnName = "IdFamilia")
    @ManyToOne(optional = false)
    private Familia idFamilia;

    public Paciente() {
    }

    public Paciente(Integer idPessoaPaciente) {
        this.idPessoaPaciente = idPessoaPaciente;
    }

    public Paciente(Integer idPessoaPaciente, Date dataNascimento, String nomeMae, long cns) {
        this.idPessoaPaciente = idPessoaPaciente;
        this.dataNascimento = dataNascimento;
        this.nomeMae = nomeMae;
        this.cns = cns;
    }

    public Integer getIdPessoaPaciente() {
        return idPessoaPaciente;
    }

    public void setIdPessoaPaciente(Integer idPessoaPaciente) {
        this.idPessoaPaciente = idPessoaPaciente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public long getCns() {
        return cns;
    }

    public void setCns(long cns) {
        this.cns = cns;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @XmlTransient
    public Collection<Encaminhamento> getEncaminhamentoCollection() {
        return encaminhamentoCollection;
    }

    public void setEncaminhamentoCollection(Collection<Encaminhamento> encaminhamentoCollection) {
        this.encaminhamentoCollection = encaminhamentoCollection;
    }

    public Pessoaa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoaa pessoa) {
        this.pessoa = pessoa;
    }

    public Sexo getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Sexo idSexo) {
        this.idSexo = idSexo;
    }

    public Familia getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Familia idFamilia) {
        this.idFamilia = idFamilia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoaPaciente != null ? idPessoaPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idPessoaPaciente == null && other.idPessoaPaciente != null) || (this.idPessoaPaciente != null && !this.idPessoaPaciente.equals(other.idPessoaPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Paciente[ idPessoaPaciente=" + idPessoaPaciente + " ]";
    }
    
}
