package com.idat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.model.HistoriaClinica;
import com.idat.repository.HistoriaClinicaRepository;
import com.idat.service.HistoriaClinicaService;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    @Autowired
    private HistoriaClinicaRepository repository;

    @Override
    public List<HistoriaClinica> listar() {
        return repository.findAll();
    }

    @Override
    public HistoriaClinica guardar(HistoriaClinica historia) {
        return repository.save(historia);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}