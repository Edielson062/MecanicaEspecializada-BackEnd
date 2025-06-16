package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Modelo;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    public Modelo buscarPorId(Integer id) {
        return modeloRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public Modelo salvarModelo(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public List<Modelo> listarModelos() {
        return modeloRepository.findAll();
    }

    public Modelo atualizarModelo(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public void deletarModelo(Integer id) {
        modeloRepository.deleteById(id);
    }
}
