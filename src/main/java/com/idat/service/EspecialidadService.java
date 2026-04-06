package com.idat.service;

import java.util.List;
import com.idat.model.Especialidad;

public interface EspecialidadService {

    List<Especialidad> listar();
    Especialidad guardar(Especialidad especialidad);
    Especialidad actualizar(Integer id, Especialidad especialidad);
    void eliminar(Integer id);
}