package br.com.pi.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author petrovick
 */
@Entity
@Table(name = "agentesaude")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agentesaude.findAll", query = "SELECT a FROM Agentesaude a"),
    @NamedQuery(name = "Agentesaude.findByIdPessoaAgenteSaude", query = "SELECT a FROM Agentesaude a WHERE a.idPessoaAgenteSaude = :idPessoaAgenteSaude"),
    @NamedQuery(name = "Agentesaude.findByMatricula", query = "SELECT a FROM Agentesaude a WHERE a.matricula = :matricula")})
public class Agentesaude implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "IdPessoaAgenteSaude", insertable = false, updatable = false)
    private Integer idPessoaAgenteSaude;
    //@Basic(optional = false)
    //@NotNull
    @Column(name = "matricula")
    private int matricula;
    
/*    @JoinColumn(name = "IdPessoaAgenteSaude", referencedColumnName = "IdPessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
*/
    @OneToOne
    @JoinColumn(name = "IdPessoaAgenteSaude")
    private Pessoa pessoa;
    
    @JoinColumn(name = "IdMicroArea", referencedColumnName = "IdMicroArea")
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
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
