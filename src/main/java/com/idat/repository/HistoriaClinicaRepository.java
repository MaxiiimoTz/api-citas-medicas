package com.idat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.idat.model.HistoriaClinica;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Integer> {
}