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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "postosaude")
@XmlRootElement
public class Postosaude implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPostoSaude", nullable = false)
    private Short idPostoSaude;
    
    @NotNull(message = "O campo Nome Posto de Saúde não pode ser Nulo")
    @Size(min = 5, max = 100, message = "Mínimo 5, Máximo 100 caracteres")
    @Column(name = "NomePosto", nullable = false, length = 100)
    private String nomePosto;
    
    @OneToMany(mappedBy = "idPostoSaude", fetch = FetchType.LAZY)
    private Collection<Area> areaCollection;

    public Postosaude() {
    }

    public Postosaude(Short idPostoSaude) {
        this.idPostoSaude = idPostoSaude;
    }

    public Postosaude(Short idPostoSaude, String nomePosto) {
        this.idPostoSaude = idPostoSaude;
        this.nomePosto = nomePosto;
    }

    public Short getIdPostoSaude() {
        return idPostoSaude;
    }

    public void setIdPostoSaude(Short idPostoSaude) {
        this.idPostoSaude = idPostoSaude;
    }

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    @XmlTransient
    public Collection<Area> getAreaCollection() {
        return areaCollection;
    }

    public void setAreaCollection(Collection<Area> areaCollection) {
        this.areaCollection = areaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPostoSaude != null ? idPostoSaude.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postosaude)) {
            return false;
        }
        Postosaude other = (Postosaude) object;
        if ((this.idPostoSaude == null && other.idPostoSaude != null) || (this.idPostoSaude != null && !this.idPostoSaude.equals(other.idPostoSaude))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Postosaude[ idPostoSaude=" + idPostoSaude + " ]";
    }
    
}
