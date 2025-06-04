package com.senai.projetointegrador.mecanicaespecializadabackend.repository;

import com.senai.projetointegrador.mecanicaespecializadabackend.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
