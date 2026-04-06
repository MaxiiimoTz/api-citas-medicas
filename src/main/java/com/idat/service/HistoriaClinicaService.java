package com.idat.service;

import java.util.List;
import com.idat.model.HistoriaClinica;

public interface HistoriaClinicaService {

    List<HistoriaClinica> listar();
    HistoriaClinica guardar(HistoriaClinica historia);
    void eliminar(Integer id);
}