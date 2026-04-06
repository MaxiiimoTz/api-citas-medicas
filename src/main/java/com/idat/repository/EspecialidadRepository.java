package com.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.idat.model.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
}