package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
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

@Entity
@Table(name = "especialidadeencaminhamento")
public class EspecialidadeEncaminhamento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEspecialidade", nullable = false)
    private Integer idEspecialidade;

    @Column(name = "descricao", nullable = false)
    @NotNull(message = "O campo Especialidade não pode ser Nulo")
    @Size(min = 1, max = 45, message = "Mínimo 1, Máximo 45 caracteres")
    private String descricao;
    
    @OneToMany(mappedBy = "idEspecialidade", fetch = FetchType.LAZY)
    private Collection<Encaminhamento> encaminhamentoCollection;

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<Encaminhamento> getEncaminhamentoCollection() {
        return encaminhamentoCollection;
    }

    public void setEncaminhamentoCollection(Collection<Encaminhamento> encaminhamentoCollection) {
        this.encaminhamentoCollection = encaminhamentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.idEspecialidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EspecialidadeEncaminhamento other = (EspecialidadeEncaminhamento) obj;
        if (!Objects.equals(this.idEspecialidade, other.idEspecialidade)) {
            return false;
        }
        return true;
    }   
   

}
