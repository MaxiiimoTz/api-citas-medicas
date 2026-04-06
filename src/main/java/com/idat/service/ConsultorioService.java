package com.idat.service;

import java.util.List;
import com.idat.model.Consultorio;

public interface ConsultorioService {
    List<Consultorio> listar();
    Consultorio guardar(Consultorio c);
}