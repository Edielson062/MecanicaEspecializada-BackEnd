package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import com.senai.projetointegrador.mecanicaespecializadabackend.enums.StatusOrdemServico;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ordemServico")
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Veiculo veiculo;

    @Column
    private LocalDate dataAbertura;

    @Column
    private LocalDate dataFechamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrdemServico status = StatusOrdemServico.EM_ABERTO;;

    @Column
    private String observacoes;

    @Column
    private Double valorTotal;

    public OrdemServico() {
    }

    public OrdemServico(int id, LocalDate dataAbertura, LocalDate dataFechamento, StatusOrdemServico status, String observacoes, Double valorTotal) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.status = status;
        this.observacoes = observacoes;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
