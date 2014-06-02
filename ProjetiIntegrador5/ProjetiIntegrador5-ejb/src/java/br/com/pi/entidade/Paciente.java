package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "paciente")
@XmlRootElement
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "IdPessoaPaciente",insertable = false, updatable = false)
    private Integer idPessoaPaciente;

    @NotNull(message = "O campo Data de Nascimento não pode ser Nulo")
    @Column(name = "DataNascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @NotNull(message = "O campo Nome da Mãe não pode ser Nulo")
    @Size(min = 1,max = 80, message = "Mínimo 1, Máximo 80 caracteres")
    @Column(name = "NomeMae", nullable = false)
    private String nomeMae;
    
    @Size(max = 80, message = "Máximo 80 caracteres")
    @Column(name = "NomePai",nullable = true)
    private String nomePai;

    @NotNull(message = "O campo CNS não pode ser Nulo")
    @Column(name = "Cns", nullable = false)
    private String cns;
    
    @Column(name = "Telefone", nullable = true)
    private String telefone;
    
    @OneToMany(mappedBy = "idPessoaPaciente")
    private Collection<Encaminhamento> encaminhamentoCollection;
    
    @JoinColumn(name = "IdPessoaPaciente", referencedColumnName = "IdPessoa")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Pessoaa pessoa;
    
    @NotNull(message = "O campo Sexo não pode ser Nulo")
    @JoinColumn(name = "IdSexo", referencedColumnName = "IdSexo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sexo idSexo;
    
    @NotNull(message = "O campo Família não pode ser Nulo")
    @JoinColumn(name = "IdFamilia", referencedColumnName = "IdFamilia", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Familia idFamilia;

    public Paciente() {
    }

    public Paciente(Integer idPessoaPaciente) {
        this.idPessoaPaciente = idPessoaPaciente;
    }

    public Paciente(Integer idPessoaPaciente, Date dataNascimento, String nomeMae, String cns) {
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

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
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
