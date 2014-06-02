package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "atribuicao")
@XmlRootElement
public class Atribuicao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAtribuicao", nullable = false)
    private Short idAtribuicao;

    @NotNull(message = "O campo Atribuição não pode ser Nulo")
    @Size(min = 1, max = 20, message = "Mínimo 1, Máximo 20 caracteres")
    @Column(name = "Descricao", length = 20, nullable = false)
    private String descricao;
    
    @OneToMany(mappedBy = "idAtribuicao", fetch = FetchType.LAZY)
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
