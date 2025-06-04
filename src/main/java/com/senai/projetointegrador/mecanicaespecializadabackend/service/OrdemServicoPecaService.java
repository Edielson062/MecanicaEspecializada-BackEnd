package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoPeca;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoPecaRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoPecaService {
    @Autowired
    private OrdemServicoPecaRepository ordemServicoPecaRepository;
    @Autowired
    private PecaRepository pecaRepository;

    public List<OrdemServicoPeca> findAll() {
        return ordemServicoPecaRepository.findAll();
    }

    public OrdemServicoPeca findById(Integer id) {
        return ordemServicoPecaRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public OrdemServicoPeca save(OrdemServicoPeca ordemServicoPeca) {
        int quantidade = pecaRepository.quantidadeDePecas(ordemServicoPeca.getPeca().getId());
        double valorUnitario = pecaRepository.valorUnitario(ordemServicoPeca.getPeca().getId());
        if (quantidade > ordemServicoPeca.getQuantidade()) {
            throw new IllegalStateException("Quantidade de peças indisponivel");
        }else{
            ordemServicoPeca.setValorTotal(ordemServicoPeca.getQuantidade() * valorUnitario);
            ordemServicoPeca = ordemServicoPecaRepository.save(ordemServicoPeca);
        }
        return ordemServicoPeca;
    }

    public OrdemServicoPeca update(OrdemServicoPeca ordemServicoPeca) {
        int quantidade = pecaRepository.quantidadeDePecas(ordemServicoPeca.getPeca().getId());
        double valorUnitario = pecaRepository.valorUnitario(ordemServicoPeca.getPeca().getId());
        if (quantidade > ordemServicoPeca.getQuantidade()) {
            throw new IllegalStateException("Quantidade de peças indisponivel");
        }else{
            ordemServicoPeca.setValorTotal(ordemServicoPeca.getQuantidade() * valorUnitario);
            ordemServicoPeca = ordemServicoPecaRepository.save(ordemServicoPeca);
        }
        return ordemServicoPeca;
    }

    public void delete(Integer id) {
        ordemServicoPecaRepository.deleteById(id);
    }
}
