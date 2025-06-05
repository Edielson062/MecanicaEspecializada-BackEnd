package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.PessoaFisica;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaFisicaService {
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public List<PessoaFisica> listarPessoasFisicas(){
        return pessoaFisicaRepository.findAll();
    }

    public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {
        String cpf = pessoaFisicaRepository.cpf(pessoaFisica.getCpf());
        if(cpf != null) {
            throw new IllegalStateException("Cpf já cadastrado");
        }else {
            pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
        }
        return pessoaFisica;
    }

    public PessoaFisica atualizarPessoaFisica(PessoaFisica pessoaFisica) {
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    public void deletarPessoaFisica(int id) {
        pessoaFisicaRepository.deleteById(id);
    }
}
