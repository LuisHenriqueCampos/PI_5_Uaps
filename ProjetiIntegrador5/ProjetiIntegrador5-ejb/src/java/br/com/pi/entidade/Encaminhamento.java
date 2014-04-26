/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author petrovick
 */
@Entity
@Table(name = "encaminhamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encaminhamento.findAll", query = "SELECT e FROM Encaminhamento e"),
    @NamedQuery(name = "Encaminhamento.findByIdEncaminhamento", query = "SELECT e FROM Encaminhamento e WHERE e.idEncaminhamento = :idEncaminhamento"),
    @NamedQuery(name = "Encaminhamento.findByDataEncaminhamento", query = "SELECT e FROM Encaminhamento e WHERE e.dataEncaminhamento = :dataEncaminhamento")})
public class Encaminhamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdEncaminhamento")
    private Integer idEncaminhamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DataEncaminhamento")
    @Temporal(TemporalType.DATE)
    private Date dataEncaminhamento;
    @Lob
    @Size(max = 65535)
    @Column(name = "MotivoEncaminhamento")
    private String motivoEncaminhamento;
    @JoinColumn(name = "IdtipoEncaminhamento", referencedColumnName = "IdtipoEncaminhamento")
    @ManyToOne(optional = false)
    private Tipoencaminhamento idtipoEncaminhamento;
    @JoinColumn(name = "IdPessoaPaciente", referencedColumnName = "IdPessoaPaciente")
    @ManyToOne(optional = false)
    private Paciente idPessoaPaciente;
    @JoinColumn(name = "IdEspecialidade", referencedColumnName = "IdEspecialidade")
    @ManyToOne(optional = false)
    private EspecialidadeEncaminhamento idEspecialidade;
    @JoinColumn(name = "IdPessoaMedicoEnfermeira", referencedColumnName = "IdPessoaMedicoEnfermeira")
    @ManyToOne(optional = false)
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

    @Override
    public String toString() {
        return "br.com.pi.entidade.Encaminhamento[ idEncaminhamento=" + idEncaminhamento + " ]";
    }
    
}
