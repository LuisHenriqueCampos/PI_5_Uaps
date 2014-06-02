package br.com.pi.model;

public class GraficoTipoEncaminhamentoModel {
    
    private String nomeTipo;
    private int quantidade;
    
    public GraficoTipoEncaminhamentoModel(String nome, int quantidade){
        this.nomeTipo = nome;
        this.quantidade = quantidade;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

   
}
