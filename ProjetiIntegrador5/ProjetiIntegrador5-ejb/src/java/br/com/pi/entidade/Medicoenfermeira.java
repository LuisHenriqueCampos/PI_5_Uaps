package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "medicoenfermeira")
@XmlRootElement
public class Medicoenfermeira implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "IdPessoaMedicoEnfermeira", insertable = false, updatable = false)
    private Integer idPessoaMedicoEnfermeira;
    
    @NotNull(message = "O campo Assinatura não pode ser Nulo")
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "Assinatura", nullable = false)
    private String assinatura;
   
    @Size(min = 6, max = 8, message = "Mínimo 6, Máximo 8 caracteres")
    @NotNull(message = "O campo Registro não pode ser Nulo")
    @Column(name = "Registro", nullable = false)
    private String registro;
   
    @OneToMany(mappedBy = "medicoenfermeiraidEnfermeira")
    private Collection<Area> areaCollection;
    
    @OneToMany(mappedBy = "medicoenfermeiraidMedico")
    private Collection<Area> areaCollection1;
    
    @OneToMany(mappedBy = "idPessoaMedicoEnfermeira")
    private Collection<Encaminhamento> encaminhamentoCollection;
    
    @NotNull(message = "O campo Atribuição não pode ser Nulo")
    @JoinColumn(name = "IdAtribuicao", referencedColumnName = "IdAtribuicao")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
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

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
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
