package com.senai.projetointegrador.mecanicaespecializadabackend.dto;


public class RelatorioServicosPorMecanicoDTO {
    private String nomeMecanico;
    private Long quantidadeServicos;

    public String getNomeMecanico() {
        return nomeMecanico;
    }

    public void setNomeMecanico(String nomeMecanico) {
        this.nomeMecanico = nomeMecanico;
    }

    public Long getQuantidadeServicos() {
        return quantidadeServicos;
    }

    public void setQuantidadeServicos(Long quantidadeServicos) {
        this.quantidadeServicos = quantidadeServicos;
    }
}