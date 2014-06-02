package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "familia")
@XmlRootElement
public class Familia implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFamilia", nullable = false)
    private Integer idFamilia;
    
    @NotNull(message = "O campo Descrição não pode ser Nulo")
    @Size(min = 1, max = 10, message = "Mínimo 1, Máximo 10 caracteres")
    @Column(name = "Descricao", nullable = false, length = 10)
    private String descricao;
    
    @NotNull(message = "O campo Número da Casa não pode ser Nulo")
    @Column(name = "NumeroCasa", nullable = false)
    private Integer numeroCasa;
    
    @OneToMany( mappedBy = "idFamilia", fetch = FetchType.LAZY)
    private Collection<Paciente> pacienteCollection;
    
    @NotNull(message = "Selecione um Endereço")
    @JoinColumn(name = "IdEndereco", referencedColumnName = "IdEndereco", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Endereco idEndereco;

    public Familia() {
    }

    public Familia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public Familia(Integer idFamilia, String descricao) {
        this.idFamilia = idFamilia;
        this.descricao = descricao;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(Integer numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        this.idEndereco = idEndereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamilia != null ? idFamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familia)) {
            return false;
        }
        Familia other = (Familia) object;
        if ((this.idFamilia == null && other.idFamilia != null) || (this.idFamilia != null && !this.idFamilia.equals(other.idFamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Familia[ idFamilia=" + idFamilia + " ]";
    }
    
}
