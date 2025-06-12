package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoServicoDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.factory.CalculoValorStrategyFactory;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServicoServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.Servico;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.ServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.strategy.CalculoValorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdemServicoServicoService {

    @Autowired
    private OrdemServicoServicoRepository ordemServicoServicoRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoServico buscarPorId(Integer id) {
        return ordemServicoServicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o ID: " + id));
    }

    public OrdemServicoServico salvarOrdemServicoServico(OrdemServicoServicoDTO dto) {

        // Buscar entidades pelos IDs
        Servico servico = servicoRepository.findById(dto.getServicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com ID: " + dto.getServicoId()));

        OrdemServico ordemServico = ordemServicoRepository.findById(dto.getOrdemServicoId())
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com ID: " + dto.getOrdemServicoId()));

        // Obter valor unitário e calcular valor total
        double valorUnitario = servicoRepository.valorUnitario(servico.getId());

        CalculoValorStrategy strategy = CalculoValorStrategyFactory.getStrategy(CalculoValorStrategyFactory.TipoItem.SERVICO);
        double valorTotal = strategy.calcularValorTotal(dto.getQuantidade(), valorUnitario);

        // Criar e preencher entidade
        OrdemServicoServico ordemServicoServico = new OrdemServicoServico();
        ordemServicoServico.setServico(servico);
        ordemServicoServico.setOrdemServico(ordemServico);
        ordemServicoServico.setQuantidade(dto.getQuantidade());
        ordemServicoServico.setValorTotal(valorTotal);

        return ordemServicoServicoRepository.save(ordemServicoServico);
    }


    public List<OrdemServicoServico> listarOrdemServicoServicos() {
        return ordemServicoServicoRepository.findAll();
    }

    public OrdemServicoServico atualizarOrdemServicoServico(OrdemServicoServico ordemServicoServico) {
        return ordemServicoServicoRepository.save(ordemServicoServico);
    }

    public void deletarOrdemServicoServico(Integer id) {
        ordemServicoServicoRepository.deleteById(id);
    }
}
