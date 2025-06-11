package com.senai.projetointegrador.mecanicaespecializadabackend.service;

import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.dto.OrdemServicoResponseDTO;
import com.senai.projetointegrador.mecanicaespecializadabackend.enums.StatusOrdemServico;
import com.senai.projetointegrador.mecanicaespecializadabackend.models.*;
import com.senai.projetointegrador.mecanicaespecializadabackend.repository.*;
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
    @Autowired
    private PecaRepository pecaRepository;

    public List<OrdemServicoResponseDTO> listarOrdensServico() {
        List<OrdemServico> lista = ordemServicoRepository.findAll();
        return lista.stream()
                .map(this::converterEntidadeParaDtoResponse)
                .collect(Collectors.toList());
    }

    public OrdemServicoDTO buscarPorId(Integer id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada com o ID: " + id));
        return converterEntidadeParaDto(ordemServico);
    }
    public OrdemServico fromDTO(OrdemServicoDTO dto) {
        OrdemServico ordemServico = new OrdemServico();

        if (dto.getId() != null) {
            ordemServico.setId(dto.getId());
        }

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

        if (dto.getVeiculo() != null) {
            Veiculo veiculo = new Veiculo();
            veiculo.setId(dto.getVeiculo().getId());
            ordemServico.setVeiculo(veiculo);
        }

        ordemServico.setDataAbertura(dto.getDataAbertura());
        ordemServico.setDataFechamento(dto.getDataFechamento());

        ordemServico.setStatus(dto.getStatus());

        ordemServico.setObservacoes(dto.getObservacoes());
        ordemServico.setValorTotal(dto.getValorTotal());

        return ordemServico;
    }


    public OrdemServicoDTO salvarOrdemServico(OrdemServicoDTO dto) {
        OrdemServico ordemServico = fromDTO(dto);

        if (ordemServico.getCliente() != null && ordemServico.getCliente().getId() != null) {
            ordemServico.setCliente(
                    clienteRepository.findById(ordemServico.getCliente().getId())
                            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + ordemServico.getCliente().getId()))
            );
        }

        if (ordemServico.getStatus() == null) {
            ordemServico.setStatus(StatusOrdemServico.EM_ABERTO);
        }

        List<Double> valoresOSS = ordemServicoServicoRepository.valoresOss(ordemServico.getId());
        Double valorTotalOSS = valoresOSS.stream().mapToDouble(Double::doubleValue).sum();
        List<Double> valoresOSP = ordemServicoPecaRepository.valoresOsp(ordemServico.getId());
        Double valorTotalOSP = valoresOSP.stream().mapToDouble(Double::doubleValue).sum();

        ordemServico.setValorTotal(valorTotalOSS + valorTotalOSP);

        OrdemServico ordemServicoSalva = ordemServicoRepository.save(ordemServico);

        return converterEntidadeParaDto(ordemServicoSalva);
    }

    public OrdemServicoDTO atualizarOrdemServico(OrdemServicoDTO dto) {
        OrdemServico ordemServicoExistente = ordemServicoRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada para atualização"));

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

        Double valorTotal = calcularValorTotal(ordemServicoExistente.getId());
        ordemServicoExistente.setValorTotal(valorTotal);
        ordemServicoExistente = ordemServicoRepository.save(ordemServicoExistente);

        return converterEntidadeParaDto(ordemServicoExistente);
    }

    public OrdemServicoDTO pagarOrdemServico(int id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço com ID " + id + " não encontrada."));

        ordemServico.setStatus(StatusOrdemServico.PAGA);
        ordemServico.setDataFechamento(LocalDate.now());

        ordemServico = ordemServicoRepository.save(ordemServico);

        return converterEntidadeParaDto(ordemServico);
    }

    public OrdemServicoDTO cancelarOrdemServico(int id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço com ID " + id + " não encontrada."));

        if (ordemServico.getStatus() == StatusOrdemServico.CANCELADA) {
            throw new IllegalStateException("A ordem de serviço já está cancelada.");
        }

        List<OrdemServicoPeca> pecas = ordemServicoPecaRepository.findByOrdemServicoId(id);
        for (OrdemServicoPeca osp : pecas) {
            pecaRepository.reporEstoque(osp.getPeca().getId(), osp.getQuantidade());
        }

        ordemServico.setStatus(StatusOrdemServico.CANCELADA);
        ordemServico.setDataFechamento(LocalDate.now());
        ordemServico = ordemServicoRepository.save(ordemServico);

        return converterEntidadeParaDto(ordemServico);
    }

    public OrdemServicoDTO reabrirOrdemServico(int id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço com ID " + id + " não encontrada."));

        ordemServico.setStatus(StatusOrdemServico.EM_ABERTO);

        ordemServico = ordemServicoRepository.save(ordemServico);

        return converterEntidadeParaDto(ordemServico);
    }

    private Double calcularValorTotal(Integer ordemServicoId) {
        List<Double> valoresOSS = ordemServicoServicoRepository.valoresOss(ordemServicoId);
        List<Double> valoresOSP = ordemServicoPecaRepository.valoresOsp(ordemServicoId);

        double totalOSS = valoresOSS.stream().mapToDouble(Double::doubleValue).sum();
        double totalOSP = valoresOSP.stream().mapToDouble(Double::doubleValue).sum();

        return totalOSS + totalOSP;
    }


    private OrdemServicoDTO converterEntidadeParaDto(OrdemServico ordemServico) {

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
    private OrdemServicoResponseDTO converterEntidadeParaDtoResponse(OrdemServico ordemServico) {

        Double valorTotalAtualizado = calcularValorTotal(ordemServico.getId());
        ordemServico.setValorTotal(valorTotalAtualizado);

        OrdemServicoResponseDTO dto = new OrdemServicoResponseDTO();
        dto.setId(ordemServico.getId());
        dto.setStatus(ordemServico.getStatus());
        dto.setObservacoes(ordemServico.getObservacoes());
        dto.setValorTotal(ordemServico.getValorTotal());

        if (ordemServico.getCliente() != null) {

            dto.setNome(ordemServico.getCliente().getNome());
        }
        String marca = String.valueOf(ordemServico.getVeiculo().getModelo().getMarca().getNome());
        String modelo = String.valueOf(ordemServico.getVeiculo().getModelo().getNome());
        String placa = ordemServico.getVeiculo().getPlaca();
        if (ordemServico.getVeiculo() != null) {
            dto.setVeiculo(marca + " - " + modelo + " - " + placa);
        }

        return dto;
    }
}
