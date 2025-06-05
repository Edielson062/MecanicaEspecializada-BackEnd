package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ordemServico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Veiculo veiculo;

    @Column
    private LocalDate dataAbertura;

    @Column
    private LocalDate dataFechamento;

    @Column
    private String status;

    @Column
    private String descricao;

    public OrdemServico() {
    }

    public OrdemServico(Integer id, Cliente cliente, Veiculo veiculo, LocalDate dataAbertura, LocalDate dataFechamento, String status, String descricao) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.status = status;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
