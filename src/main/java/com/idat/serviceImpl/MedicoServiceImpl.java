package com.idat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.model.Medico;
import com.idat.repository.MedicoRepository;
import com.idat.service.MedicoService;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Override
    public List<Medico> listar() {
        return repository.findAll();
    }
    
    @Override
    public List<Medico> obtenerPorEspecialidad(Integer idEspecialidad) {
        return repository.findByEspecialidadIdEspecialidad(idEspecialidad);
    }
    
    @Override
    public Medico obtenerPorUsuario(Integer idUsuario) {
        return repository.findAll()
            .stream()
            .filter(m -> m.getUsuario().getIdUsuario().equals(idUsuario))
            .findFirst()
            .orElse(null);
    }

    @Override
    public Medico guardar(Medico medico) {
        return repository.save(medico);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
    
    @Override
    public long contarActivos() {
    	return repository.countByEstado(true);
    }
}