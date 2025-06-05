package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico,Integer> {
}
