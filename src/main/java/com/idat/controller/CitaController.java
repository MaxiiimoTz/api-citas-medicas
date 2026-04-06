package com.idat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.idat.model.Cita;
import com.idat.service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    public List<Cita> listar() {
        return service.listar();
    }
    
    @GetMapping("/hoy/count")
    public long contarCita() {
    	return service.contarCitasHoy();
    }
    
    @GetMapping("/pendientes/count")
    public long contarPendientes() {
    	return service.contarPendientes();
    }
    
    @GetMapping("/disponibles")
    public List<Map<String, Object>> disponibles(
        @RequestParam Integer idMedico,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ){
        return service.obtenerHorariosDisponibles(idMedico, fecha);
    }
    
    @GetMapping("/semana")
    public List<Map<String, Object>> citasSemana(){
    	return service.citasSemana();
    }
    
    @GetMapping("/estado")
    public List<Map<String, Object>> estadoCitas(){
    	return service.contarPorEstado();
    }
    
    @GetMapping("/medico/{id}")
    public List<Cita> citasMedico(@PathVariable Integer id) {
        return service.obtenerPorMedico(id);
    }
    
    @GetMapping("/reporte/periodo")
    public List<Map<String, Object>> reporteCitasPorPeriodo(
    		@RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
    		@RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin
    ){
    	return service.reporteCitasPorPeriodo(inicio, fin);
    }
    
    @GetMapping("/reporte/medico")
    public List<Map<String, Object>> reporteCitasPorMedico() {
        return service.reporteCitasPorMedico();
    }
    
    @GetMapping("/paciente/{idUsuario}")
    public List<Cita> citasPaciente(@PathVariable Integer idUsuario) {
        return service.obtenerPorPaciente(idUsuario);
    }

    @PostMapping
    public Cita guardar(@RequestBody Cita cita) {
        return service.guardar(cita);
    }
    
    @PutMapping("/{id}/estado")
    public Cita actualizarEstado(
        @PathVariable Integer id,
        @RequestParam String estado
    ) {
        Cita cita = service.obtenerPorId(id);

        if (cita == null) {
            throw new RuntimeException("CITA_NO_EXISTE");
        }

        cita.setEstado(estado);

        return service.guardar(cita);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}