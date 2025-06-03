package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PessoaFisica extends Cliente {

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;
    private LocalDate dataNasc;

    public PessoaFisica() {
    }

    public PessoaFisica(int id, String nome, String email, String telefone, String cpf, LocalDate dataNasc) {
        super(id, nome, email, telefone);
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
}