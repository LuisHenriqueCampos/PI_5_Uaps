package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author petrovick
 */
@Entity
@Table(name = "bairro")
@XmlRootElement
public class Bairro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdBairro", nullable = false)
    private Integer idBairro;
    
    @NotNull(message = "O campo Bairro não pode ser Nulo")
    @Size(min = 1, max = 50, message = "Mínimo 1, Máximo 50 caracteres")
    @Column(name = "Bairro")
    private String bairro;
    
    @OneToMany(mappedBy = "idBairro", fetch = FetchType.LAZY)
    private Collection<Endereco> enderecoCollection;

    public Bairro() {
    }

    public Bairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public Bairro(Integer idBairro, String bairro) {
        this.idBairro = idBairro;
        this.bairro = bairro;
    }

    public Integer getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @XmlTransient
    public Collection<Endereco> getEnderecoCollection() {
        return enderecoCollection;
    }

    public void setEnderecoCollection(Collection<Endereco> enderecoCollection) {
        this.enderecoCollection = enderecoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBairro != null ? idBairro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bairro)) {
            return false;
        }
        Bairro other = (Bairro) object;
        if ((this.idBairro == null && other.idBairro != null) || (this.idBairro != null && !this.idBairro.equals(other.idBairro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Bairro[ idBairro=" + idBairro + " ]";
    }
    
}
