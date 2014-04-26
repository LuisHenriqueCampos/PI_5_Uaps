package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "Bairro.findAll", query = "SELECT b FROM Bairro b"),
    @NamedQuery(name = "Bairro.findByIdBairro", query = "SELECT b FROM Bairro b WHERE b.idBairro = :idBairro"),
    @NamedQuery(name = "Bairro.findByBairro", query = "SELECT b FROM Bairro b WHERE b.bairro = :bairro")})
public class Bairro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  //  @Basic(optional = false)
    @Column(name = "IdBairro")
    private Integer idBairro;
    
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 50)
    @Column(name = "Bairro")
    private String bairro;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBairro")
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
