package com.idat.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idat.model.Rol;
import com.idat.repository.RolRepository;
import com.idat.service.RolService;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository repository;

    @Override
    public List<Rol> listar() {
        return repository.findAll();
    }

    @Override
    public Rol obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Rol guardar(Rol rol) {
        return repository.save(rol);
    }

    @Override
    public Rol actualizar(Integer id, Rol rol) {
        rol.setIdRol(id);
        return repository.save(rol);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}