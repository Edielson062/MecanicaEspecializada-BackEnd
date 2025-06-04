package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Peca;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PecaService {

    @Autowired
    private PecaRepository pecaRepository;

    public List<Peca> findAll() {
        return pecaRepository.findAll();
    }

    public Peca findById(int id) {
        return this.pecaRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public Peca save(Peca peca) {
        String codigoPeca = pecaRepository.codigoPeca(peca.getCodigo());
        if (codigoPeca != null) {
            throw new IllegalStateException("Codigo da peça já cadastrado");
        }else{
            peca = this.pecaRepository.save(peca);
        }
        return peca;
    }

    public Peca update(Peca peca) {
        return this.pecaRepository.save(peca);
    }

    public void delete(int id) {
        pecaRepository.deleteById(id);
    }
}
