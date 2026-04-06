package com.idat.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.model.Consultorio;
import com.idat.repository.ConsultorioRepository;
import com.idat.service.ConsultorioService;

@Service
public class ConsultorioServiceImpl implements ConsultorioService {

    @Autowired
    private ConsultorioRepository repository;

    @Override
    public List<Consultorio> listar() {
        return repository.findAll();
    }

    @Override
    public Consultorio guardar(Consultorio c) {
        return repository.save(c);
    }
}