package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.PessoaJuridica;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaJuridicaService {
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public List<PessoaJuridica> listarPessoasJuridicas(){
        return pessoaJuridicaRepository.findAll();
    }

    public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    public void deletarPessoaJuridica(int id) {
        pessoaJuridicaRepository.deleteById(id);
    }

    public PessoaJuridica atualizarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        PessoaJuridica existente = pessoaJuridicaRepository.findByCnpj(pessoaJuridica.getCnpj());
        System.out.println("Pessoa recebida: " + pessoaJuridica.getId() + " / " + pessoaJuridica.getCnpj());
        if (existente != null) {
            System.out.println("Pessoa existente: " + existente.getId() + " / " + existente.getCnpj());
        }
        if (existente != null && !existente.getId().equals(pessoaJuridica.getId())) {
            // Só lança exceção caso o CNPJ já esteja com outro registro
            throw new IllegalStateException("Cnpj já cadastrado");
        }
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }
}
