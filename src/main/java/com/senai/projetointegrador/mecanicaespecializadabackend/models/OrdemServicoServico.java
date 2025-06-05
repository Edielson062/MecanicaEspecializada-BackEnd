package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import jakarta.persistence.*;

@Entity
@Table(name="ordemServicoServico")
public class OrdemServicoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Servico servico;

    @ManyToOne
    private OrdemServico ordemServico;

    @Column
    private int quantidade;

    @Column
    private Double valorTotal;


    public OrdemServicoServico() {
    }

    public OrdemServicoServico(Integer id, Servico servico, OrdemServico ordemServico, int quantidade, Double valorTotal) {
        this.id = id;
        this.servico = servico;
        this.ordemServico = ordemServico;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }
}
