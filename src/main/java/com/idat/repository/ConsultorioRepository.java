package com.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.idat.model.Consultorio;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Integer> {
}