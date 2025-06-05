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

    public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    public List<PessoaFisica> listarPessoasFisicas(){
        return pessoaFisicaRepository.findAll();
    }

    public PessoaFisica atualizarPessoaFisica(PessoaFisica pessoaFisica) {
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    public void deletarPessoaFisica(Integer id) {
        pessoaFisicaRepository.deleteById(id);
    }
}
