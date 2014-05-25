/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pi.service;

import br.com.pi.entidade.Paciente;
import java.util.List;

/**
 *
 * @author petrovick
 */
public interface IPacienteService extends ICrudGeneric<Paciente>
{
    public String salvar(Paciente entity);
    public String excluir(Paciente Idobj);    
    public Paciente obter(Integer IdObj);
    public List<Paciente> listar();
    public List<Paciente> listarRel(String pesq);
}
