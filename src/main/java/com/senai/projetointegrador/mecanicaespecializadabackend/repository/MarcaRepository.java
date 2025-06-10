package com.senai.projetointegrador.mecanicaespecializadabackend.repository;


import com.senai.projetointegrador.mecanicaespecializadabackend.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca,Integer> {
}
