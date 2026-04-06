package com.idat.service;

import java.util.List;
import com.idat.model.HorarioMedico;

public interface HorarioMedicoService {

    List<HorarioMedico> listar();
    HorarioMedico guardar(HorarioMedico horario);
}