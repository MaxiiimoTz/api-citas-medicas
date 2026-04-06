package com.idat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.model.Especialidad;
import com.idat.repository.EspecialidadRepository;
import com.idat.service.EspecialidadService;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    private EspecialidadRepository repository;

    @Override
    public List<Especialidad> listar() {
        return repository.findAll();
    }

    @Override
    public Especialidad guardar(Especialidad especialidad) {
        return repository.save(especialidad);
    }
    
    @Override
    public Especialidad actualizar(Integer id, Especialidad especialidad) {
    	especialidad.setIdEspecialidad(id);
        return repository.save(especialidad);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}