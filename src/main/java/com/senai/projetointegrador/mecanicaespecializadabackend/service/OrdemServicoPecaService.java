package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.factory.CalculoValorStrategyFactory;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoPeca;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoPecaRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.PecaRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.strategy.CalculoValorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoPecaService {

    @Autowired
    private OrdemServicoPecaRepository ordemServicoPecaRepository;
    @Autowired
    private PecaRepository pecaRepository;

    public OrdemServicoPeca buscarPorId(Integer id) {
        return ordemServicoPecaRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public OrdemServicoPeca salvarOrdemServicoPeca(OrdemServicoPeca ordemServicoPeca) {
        double valorUnitario = pecaRepository.valorUnitario(ordemServicoPeca.getPeca().getId());

        CalculoValorStrategy strategy = CalculoValorStrategyFactory.getStrategy(CalculoValorStrategyFactory.TipoItem.PECA);
        double valorTotal = strategy.calcularValorTotal(ordemServicoPeca.getQuantidade(), valorUnitario);
        ordemServicoPeca.setValorTotal(valorTotal);

        int atualizou = pecaRepository.reduzirEstoque(ordemServicoPeca.getPeca().getId(), ordemServicoPeca.getQuantidade());
        if (atualizou == 0) {
            throw new IllegalStateException("Não foi possível atualizar o estoque. Verifique a disponibilidade.");
        }

        return ordemServicoPecaRepository.save(ordemServicoPeca);
    }

    public List<OrdemServicoPeca> listarOrdemServicoPecas() {
        return ordemServicoPecaRepository.findAll();
    }

    public OrdemServicoPeca atualizarOrdemServicoPeca(OrdemServicoPeca ordemServicoPeca) {
        OrdemServicoPeca antiga = ordemServicoPecaRepository.findById(ordemServicoPeca.getId())
                .orElseThrow(() -> new IllegalStateException("Ordem de serviço de peça não encontrada"));

        int quantidadeAntiga = antiga.getQuantidade();
        int quantidadeNova = ordemServicoPeca.getQuantidade();
        int diferenca = quantidadeNova - quantidadeAntiga;

        double valorUnitario = pecaRepository.valorUnitario(ordemServicoPeca.getPeca().getId());
        ordemServicoPeca.setValorTotal(quantidadeNova * valorUnitario);

        if (diferenca > 0) {
            int atualizou = pecaRepository.reduzirEstoque(ordemServicoPeca.getPeca().getId(), diferenca);
            if (atualizou == 0) {
                throw new IllegalStateException("Estoque insuficiente para aumentar a quantidade");
            }
        } else if (diferenca < 0) {
            pecaRepository.reporEstoque(ordemServicoPeca.getPeca().getId(), -diferenca);
        }

        return ordemServicoPecaRepository.save(ordemServicoPeca);
    }


    public void deletarOrdemServicoPeca(Integer id) {
        ordemServicoPecaRepository.deleteById(id);
    }
}
