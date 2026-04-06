package com.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.idat.model.Consultorio;
import com.idat.service.ConsultorioService;

@RestController
@RequestMapping("/api/consultorios")
public class ConsultorioController {

    @Autowired
    private ConsultorioService service;

    @GetMapping
    public List<Consultorio> listar(){
        return service.listar();
    }

    @PostMapping
    public Consultorio guardar(@RequestBody Consultorio c){
        return service.guardar(c);
    }
}