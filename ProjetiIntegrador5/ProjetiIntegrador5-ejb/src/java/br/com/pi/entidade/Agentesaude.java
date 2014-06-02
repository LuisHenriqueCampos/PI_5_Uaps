package br.com.pi.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "agentesaude")
@XmlRootElement
public class Agentesaude implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "IdPessoaAgenteSaude", insertable = false, updatable = false)
    private Integer idPessoaAgenteSaude;

    @NotNull(message = "O campo Matrícula não pode ser Nulo")
    @Column(name = "matricula", nullable = false)
    private int matricula;
    
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "IdPessoaAgenteSaude", referencedColumnName = "IdPessoa")
    private Pessoaa pessoa;
    
    @Size(min = 1 ,message = "Selecione Micro-Área")
    @JoinColumn(name = "IdMicroArea", referencedColumnName = "IdMicroArea", nullable = false)
    @ManyToOne(optional = false)
    private Microarea idMicroArea;

    public Agentesaude() {
    }

    public Agentesaude(Integer idPessoaAgenteSaude) {
        this.idPessoaAgenteSaude = idPessoaAgenteSaude;
    }

    public Agentesaude(Integer idPessoaAgenteSaude, int matricula) {
        this.idPessoaAgenteSaude = idPessoaAgenteSaude;
        this.matricula = matricula;
    }

    public Integer getIdPessoaAgenteSaude() {
        return idPessoaAgenteSaude;
    }

    public void setIdPessoaAgenteSaude(Integer idPessoaAgenteSaude) {
        this.idPessoaAgenteSaude = idPessoaAgenteSaude;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Pessoaa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoaa pessoa) {
        this.pessoa = pessoa;
    }

    public Microarea getIdMicroArea() {
        return idMicroArea;
    }

    public void setIdMicroArea(Microarea idMicroArea) {
        this.idMicroArea = idMicroArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoaAgenteSaude != null ? idPessoaAgenteSaude.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agentesaude)) {
            return false;
        }
        Agentesaude other = (Agentesaude) object;
        if ((this.idPessoaAgenteSaude == null && other.idPessoaAgenteSaude != null) || (this.idPessoaAgenteSaude != null && !this.idPessoaAgenteSaude.equals(other.idPessoaAgenteSaude))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Agentesaude[ idPessoaAgenteSaude=" + idPessoaAgenteSaude + " ]";
    }
    
}
