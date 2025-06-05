package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.enums.StatusOrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.*;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.ClienteRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoPecaRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoRepository;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.OrdemServicoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private OrdemServicoServicoRepository ordemServicoServicoRepository;
    @Autowired
    private OrdemServicoPecaRepository ordemServicoPecaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    // --- LISTAR TODAS ORDEM SERVICO - retorna lista de DTOs ---
    public List<OrdemServicoDTO> listarOrdensServico() {
        List<OrdemServico> lista = ordemServicoRepository.findAll();
        return lista.stream()
                .map(this::converterEntidadeParaDto)
                .collect(Collectors.toList());
    }

    // --- BUSCAR POR ID (DTO) ---
    public OrdemServicoDTO buscarPorId(Integer id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com o ID: " + id));
        return converterEntidadeParaDto(ordemServico);
    }
    public OrdemServico fromDTO(OrdemServicoDTO dto) {
        OrdemServico ordemServico = new OrdemServico();

        // Se precisar setar o id (em caso de atualização)
        if (dto.getId() != null) {
            ordemServico.setId(dto.getId());
        }

        // Cliente
        Cliente cliente;
        if (dto.getCliente() != null) {
            if ("juridica".equalsIgnoreCase(dto.getCliente().getTipo())) {
                cliente = new PessoaJuridica();
            } else {
                cliente = new PessoaFisica();
            }
            cliente.setId(dto.getCliente().getId());
            ordemServico.setCliente(cliente);
        }

        // Veículo
        if (dto.getVeiculo() != null) {
            Veiculo veiculo = new Veiculo();
            veiculo.setId(dto.getVeiculo().getId());
            ordemServico.setVeiculo(veiculo);
        }

        // Datas
        ordemServico.setDataAbertura(dto.getDataAbertura());
        ordemServico.setDataFechamento(dto.getDataFechamento());

        // Status
        ordemServico.setStatus(dto.getStatus());

        // Observações e valor total
        ordemServico.setObservacoes(dto.getObservacoes());
        ordemServico.setValorTotal(dto.getValorTotal());

        return ordemServico;
    }


    // --- SALVAR ORDEM SERVIÇO (recebe DTO, retorna DTO) ---
    public OrdemServicoDTO salvarOrdemServico(OrdemServicoDTO dto) {
        OrdemServico ordemServico = fromDTO(dto);

        // Carregar cliente completo do banco, se id existir
        if (ordemServico.getCliente() != null && ordemServico.getCliente().getId() != null) {
            ordemServico.setCliente(
                    clienteRepository.findById(ordemServico.getCliente().getId())
                            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + ordemServico.getCliente().getId()))
            );
        }

        if (ordemServico.getStatus() == null) {
            ordemServico.setStatus(StatusOrdemServico.EM_ABERTO);
        }

        // calcular valor total (conforme seu código atual)
        List<Double> valoresOSS = ordemServicoServicoRepository.valoresOss(ordemServico.getId());
        Double valorTotalOSS = valoresOSS.stream().mapToDouble(Double::doubleValue).sum();
        List<Double> valoresOSP = ordemServicoPecaRepository.valoresOsp(ordemServico.getId());
        Double valorTotalOSP = valoresOSP.stream().mapToDouble(Double::doubleValue).sum();

        ordemServico.setValorTotal(valorTotalOSS + valorTotalOSP);

        OrdemServico ordemServicoSalva = ordemServicoRepository.save(ordemServico);

        // Converte para DTO antes de retornar
        return converterEntidadeParaDto(ordemServicoSalva);
    }


    // --- ATUALIZAR ORDEM SERVIÇO (DTO) ---
    public OrdemServicoDTO atualizarOrdemServico(OrdemServicoDTO dto) {
        OrdemServico ordemServicoExistente = ordemServicoRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada para atualização"));

        // Atualiza dados da entidade com dados do DTO (exceto id que já existe)
        ordemServicoExistente.setDataAbertura(dto.getDataAbertura());
        ordemServicoExistente.setDataFechamento(dto.getDataFechamento());
        ordemServicoExistente.setStatus(dto.getStatus());
        ordemServicoExistente.setObservacoes(dto.getObservacoes());

        if (dto.getCliente() != null && dto.getCliente().getId() != null) {
            ordemServicoExistente.setCliente(
                    clienteRepository.findById(dto.getCliente().getId())
                            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + dto.getCliente().getId()))
            );
        }

        ordemServicoExistente = ordemServicoRepository.save(ordemServicoExistente);

        // Recalcula valor total
        Double valorTotal = calcularValorTotal(ordemServicoExistente.getId());
        ordemServicoExistente.setValorTotal(valorTotal);
        ordemServicoExistente = ordemServicoRepository.save(ordemServicoExistente);

        return converterEntidadeParaDto(ordemServicoExistente);
    }

    // --- MÉTODO PARA PAGAR ORDEM ---
    public OrdemServicoDTO pagarOrdemServico(int id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço com ID " + id + " não encontrada."));

        ordemServico.setStatus(StatusOrdemServico.PAGA);
        ordemServico.setDataFechamento(LocalDate.now());

        ordemServico = ordemServicoRepository.save(ordemServico);

        return converterEntidadeParaDto(ordemServico);
    }

    // --- MÉTODO PARA CANCELAR ORDEM ---
    public OrdemServicoDTO cancelarOrdemServico(int id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço com ID " + id + " não encontrada."));

        ordemServico.setStatus(StatusOrdemServico.CANCELADA);
        ordemServico.setDataFechamento(LocalDate.now());

        ordemServico = ordemServicoRepository.save(ordemServico);

        return converterEntidadeParaDto(ordemServico);
    }

    // --- DELETAR ORDEM ---
    public void deletarOrdemServico(Integer id) {
        ordemServicoRepository.deleteById(id);
    }

    // -----------------------
    // MÉTODOS AUXILIARES
    // -----------------------

    private Double calcularValorTotal(Integer ordemServicoId) {
        List<Double> valoresOSS = ordemServicoServicoRepository.valoresOss(ordemServicoId);
        List<Double> valoresOSP = ordemServicoPecaRepository.valoresOsp(ordemServicoId);

        double totalOSS = valoresOSS.stream().mapToDouble(Double::doubleValue).sum();
        double totalOSP = valoresOSP.stream().mapToDouble(Double::doubleValue).sum();

        return totalOSS + totalOSP;
    }


    // CONVERTER ENTIDADE -> DTO
    private OrdemServicoDTO converterEntidadeParaDto(OrdemServico ordemServico) {
        // Recalcula o valor total
        Double valorTotalAtualizado = calcularValorTotal(ordemServico.getId());
        ordemServico.setValorTotal(valorTotalAtualizado);

        OrdemServicoDTO dto = new OrdemServicoDTO();
        dto.setId(ordemServico.getId());
        dto.setDataAbertura(ordemServico.getDataAbertura());
        dto.setDataFechamento(ordemServico.getDataFechamento());
        dto.setStatus(ordemServico.getStatus());
        dto.setObservacoes(ordemServico.getObservacoes());
        dto.setValorTotal(ordemServico.getValorTotal());

        if (ordemServico.getCliente() != null) {
            OrdemServicoDTO.ClienteDTO clienteDto = new OrdemServicoDTO.ClienteDTO();
            clienteDto.setId(ordemServico.getCliente().getId());
            dto.setCliente(clienteDto);
        }

        if (ordemServico.getVeiculo() != null) {
            OrdemServicoDTO.VeiculoDTO veiculoDto = new OrdemServicoDTO.VeiculoDTO();
            veiculoDto.setId(ordemServico.getVeiculo().getId());
            dto.setVeiculo(veiculoDto);
        }

        return dto;
    }
}
