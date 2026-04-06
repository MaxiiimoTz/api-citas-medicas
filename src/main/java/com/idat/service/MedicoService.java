package com.idat.service;

import java.util.List;
import com.idat.model.Medico;

public interface MedicoService {

    List<Medico> listar();
    List<Medico> obtenerPorEspecialidad(Integer idEspecialidad);
    Medico obtenerPorUsuario(Integer idUsuario);
    Medico guardar(Medico medico);
    void eliminar(Integer id);
    long contarActivos();
}