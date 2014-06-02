package br.com.pi.entidade;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "pessoa")
@XmlRootElement
public class Pessoaa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPessoa",nullable = false)
    private Integer idPessoa;
    
    @Size(min = 1, max = 50,message = "Mínimo 1, Máximo 50 caracteres.")
    @NotNull(message = "Não pode ser Nulo.")
    @Column(name = "Nome")
    private String nome;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Agentesaude agentesaude;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Medicoenfermeira medicoenfermeira;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Paciente paciente;

    public Pessoaa() {
    }

    public Pessoaa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Pessoaa(Integer idPessoa, String nome) {
        this.idPessoa = idPessoa;
        this.nome = nome;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Agentesaude getAgentesaude() {
        return agentesaude;
    }

    public void setAgentesaude(Agentesaude agentesaude) {
        this.agentesaude = agentesaude;
    }

    public Medicoenfermeira getMedicoenfermeira() {
        return medicoenfermeira;
    }

    public void setMedicoenfermeira(Medicoenfermeira medicoenfermeira) {
        this.medicoenfermeira = medicoenfermeira;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoa != null ? idPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoaa)) {
            return false;
        }
        Pessoaa other = (Pessoaa) object;
        if ((this.idPessoa == null && other.idPessoa != null) || (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Pessoa[ idPessoa=" + idPessoa + " ]";
    }
    
}
