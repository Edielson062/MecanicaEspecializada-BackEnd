package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ordemServicoPeca")
public class OrdemServicoPeca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Peca peca;

    @ManyToOne
    private OrdemServico ordemServico;

    @Column
    private int quantidade;

    @Column
    private double valorTotal;

    public OrdemServicoPeca() {
    }

    public OrdemServicoPeca(Integer id, Peca peca, OrdemServico ordemServico, int quantidade, double valorTotal) {
        this.id = id;
        this.peca = peca;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
}
