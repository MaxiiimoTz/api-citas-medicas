package com.idat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.idat.model.Paciente;

public interface PacienteService {

    List<Paciente> listar();
    long contarPaciente();
    List<Map<String, Object>> reportePacientesPeriodo( LocalDateTime inicio, LocalDateTime fin);
    Paciente obtenerPorId(Integer id);
    Paciente obtenerPorUsuario(Integer idUsuario);
    Paciente guardar(Paciente paciente);
    Paciente actualizar(Integer id, Paciente paciente);
    void eliminar(Integer id);
}