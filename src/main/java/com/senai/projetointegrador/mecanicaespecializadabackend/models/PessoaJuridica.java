package com.senai.projetointegrador.mecanicaespecializadabackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PessoaJuridica extends  Cliente {

    @Column(name = "cnpj", nullable = false, unique = true, length = 18)
    private String cnpj;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Integer id, String nome, String email, String telefone, String cnpj) {
        super(id, nome, email, telefone);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
