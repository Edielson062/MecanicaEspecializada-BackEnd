package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Marca;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public Marca buscarPorId(Integer id) {
        return marcaRepository.findById(id).orElseThrow(() -> new RuntimeException("Marca não encontrada com o ID: " + id));
    }

    public Marca salvarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    public Marca atualizarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public void deletarMarca(Integer id) {
        marcaRepository.deleteById(id);
    }
}
