package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "encaminhamento")
public class Encaminhamento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEncaminhamento", nullable = false)
    private Integer idEncaminhamento;

    @NotNull(message = "O campo Data Encaminhamento não pode ser Nulo")
    @Column(name = "DataEncaminhamento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEncaminhamento;
    
    @Lob
    @Size(max = 65535,message = "Máximo 65.535 caracteres")
    @NotNull(message = "O campo Motivo do Encaminhamento não pode ser Nulo")
    @Column(name = "MotivoEncaminhamento")
    private String motivoEncaminhamento;
    
    @NotNull(message = "Selecione o Tipo de Encaminhamento")
    @JoinColumn(name = "IdtipoEncaminhamento", referencedColumnName = "IdtipoEncaminhamento", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tipoencaminhamento idtipoEncaminhamento;
    
    @NotNull(message = "Selecione o Paciente")
    @JoinColumn(name = "IdPessoaPaciente", referencedColumnName = "IdPessoaPaciente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente idPessoaPaciente;
    
    @NotNull(message = "Selecione a Especialidade")
    @JoinColumn(name = "IdEspecialidade", referencedColumnName = "IdEspecialidade")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EspecialidadeEncaminhamento idEspecialidade;
    
    @NotNull(message = "Selecione o Médico / Enfermeira Responsável pelo Encaminhamento")
    @JoinColumn(name = "IdPessoaMedicoEnfermeira", referencedColumnName = "IdPessoaMedicoEnfermeira")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Medicoenfermeira idPessoaMedicoEnfermeira;

    public Encaminhamento() {
    }

    public Encaminhamento(Integer idEncaminhamento) {
        this.idEncaminhamento = idEncaminhamento;
    }

    public Encaminhamento(Integer idEncaminhamento, Date dataEncaminhamento) {
        this.idEncaminhamento = idEncaminhamento;
        this.dataEncaminhamento = dataEncaminhamento;
    }

    public Integer getIdEncaminhamento() {
        return idEncaminhamento;
    }

    public void setIdEncaminhamento(Integer idEncaminhamento) {
        this.idEncaminhamento = idEncaminhamento;
    }

    public Date getDataEncaminhamento() {
        return dataEncaminhamento;
    }

    public void setDataEncaminhamento(Date dataEncaminhamento) {
        this.dataEncaminhamento = dataEncaminhamento;
    }

    public String getMotivoEncaminhamento() {
        return motivoEncaminhamento;
    }

    public void setMotivoEncaminhamento(String motivoEncaminhamento) {
        this.motivoEncaminhamento = motivoEncaminhamento;
    }

    public Tipoencaminhamento getIdtipoEncaminhamento() {
        return idtipoEncaminhamento;
    }

    public void setIdtipoEncaminhamento(Tipoencaminhamento idtipoEncaminhamento) {
        this.idtipoEncaminhamento = idtipoEncaminhamento;
    }

    public Paciente getIdPessoaPaciente() {
        return idPessoaPaciente;
    }

    public void setIdPessoaPaciente(Paciente idPessoaPaciente) {
        this.idPessoaPaciente = idPessoaPaciente;
    }

    public EspecialidadeEncaminhamento getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(EspecialidadeEncaminhamento idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public Medicoenfermeira getIdPessoaMedicoEnfermeira() {
        return idPessoaMedicoEnfermeira;
    }

    public void setIdPessoaMedicoEnfermeira(Medicoenfermeira idPessoaMedicoEnfermeira) {
        this.idPessoaMedicoEnfermeira = idPessoaMedicoEnfermeira;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncaminhamento != null ? idEncaminhamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encaminhamento)) {
            return false;
        }
        Encaminhamento other = (Encaminhamento) object;
        if ((this.idEncaminhamento == null && other.idEncaminhamento != null) || (this.idEncaminhamento != null && !this.idEncaminhamento.equals(other.idEncaminhamento))) {
            return false;
        }
        return true;
    }
}
