package com.idat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.model.HorarioMedico;
import com.idat.repository.HorarioMedicoRepository;
import com.idat.service.HorarioMedicoService;

@Service
public class HorarioMedicoServiceImpl implements HorarioMedicoService {

    @Autowired
    private HorarioMedicoRepository repository;

    @Override
    public List<HorarioMedico> listar() {
        return repository.findAll();
    }

    @Override
    public HorarioMedico guardar(HorarioMedico nuevo) {

        List<HorarioMedico> existentes =
            repository.findByMedicoAndDiaSemana(
                nuevo.getMedico().getIdMedico(),
                nuevo.getDiaSemana()
            );

        for (HorarioMedico h : existentes) {

            if (
                nuevo.getHoraInicio().isBefore(h.getHoraFin()) &&
                nuevo.getHoraFin().isAfter(h.getHoraInicio())
            ) {
                throw new RuntimeException("HORARIO_CRUZADO");
            }
        }

        return repository.save(nuevo);
    }
}