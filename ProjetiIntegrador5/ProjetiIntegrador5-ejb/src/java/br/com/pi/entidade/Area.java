package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "area")
@XmlRootElement
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdArea",nullable = false)
    private Short idArea;
    
    @NotNull(message = "O campo Área não pode ser Nulo")
    @Size(min = 1, max = 10,message = "Mínimo 1, Máximo 10 caracteres")
    @Column(name = "Descricao", nullable = false)
    private String descricao;
    
    @NotNull(message = "Selecione o Posto de Saúde")
    @JoinColumn(name = "IdPostoSaude", referencedColumnName = "IdPostoSaude", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Postosaude idPostoSaude;
    
    @NotNull(message = "Selecione Enfermeira")
    @JoinColumn(name = "medicoenfermeira_idEnfermeira", referencedColumnName = "IdPessoaMedicoEnfermeira", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Medicoenfermeira medicoenfermeiraidEnfermeira;
    
    @NotNull(message = "Selecione Médico")
    @JoinColumn(name = "medicoenfermeira_idMedico", referencedColumnName = "IdPessoaMedicoEnfermeira", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Medicoenfermeira medicoenfermeiraidMedico;
    
    @OneToMany(mappedBy = "idArea", fetch = FetchType.LAZY)
    private Collection<Microarea> microareaCollection;

    public Area() {
    }

    public Area(Short idArea) {
        this.idArea = idArea;
    }

    public Area(Short idArea, String descricao) {
        this.idArea = idArea;
        this.descricao = descricao;
    }

    public Short getIdArea() {
        return idArea;
    }

    public void setIdArea(Short idArea) {
        this.idArea = idArea;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Postosaude getIdPostoSaude() {
        return idPostoSaude;
    }

    public void setIdPostoSaude(Postosaude idPostoSaude) {
        this.idPostoSaude = idPostoSaude;
    }

    public Medicoenfermeira getMedicoenfermeiraidEnfermeira() {
        return medicoenfermeiraidEnfermeira;
    }

    public void setMedicoenfermeiraidEnfermeira(Medicoenfermeira medicoenfermeiraidEnfermeira) {
        this.medicoenfermeiraidEnfermeira = medicoenfermeiraidEnfermeira;
    }

    public Medicoenfermeira getMedicoenfermeiraidMedico() {
        return medicoenfermeiraidMedico;
    }

    public void setMedicoenfermeiraidMedico(Medicoenfermeira medicoenfermeiraidMedico) {
        this.medicoenfermeiraidMedico = medicoenfermeiraidMedico;
    }

    @XmlTransient
    public Collection<Microarea> getMicroareaCollection() {
        return microareaCollection;
    }

    public void setMicroareaCollection(Collection<Microarea> microareaCollection) {
        this.microareaCollection = microareaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pi.entidade.Area[ idArea=" + idArea + " ]";
    }
    
}
