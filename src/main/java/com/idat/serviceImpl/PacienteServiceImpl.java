package com.idat.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idat.model.Paciente;
import com.idat.model.Usuario;
import com.idat.repository.PacienteRepository;
import com.idat.repository.UsuarioRepository;
import com.idat.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Paciente> listar() {
        return repository.findAll();
    }
    
    @Override
    public long contarPaciente() {
    	return repository.count();
    }

    @Override
    public Paciente obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
    
    @Override
    public Paciente obtenerPorUsuario(Integer idUsuario) {
        return repository.findByUsuarioIdUsuario(idUsuario)
            .orElse(null);
    }
    
    @Override
    public List<Map<String, Object>> reportePacientesPeriodo(
            LocalDateTime inicio,
            LocalDateTime fin) {

        List<Object[]> datos = repository.contarPacientesPorPeriodo(inicio, fin);

        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Object[] fila : datos) {
            resultado.add(Map.of(
                "fecha", fila[0].toString(),
                "total", ((Number) fila[1]).intValue()
            ));
        }

        return resultado;
    }

    @Override
    public Paciente guardar(Paciente paciente) {

        if (paciente.getUsuario() == null) {
            throw new RuntimeException("Usuario es obligatorio");
        }

        Integer idUsuario = paciente.getUsuario().getIdUsuario();

        // obtener usuario real
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        // buscar si ya existe paciente
        Optional<Paciente> existente = repository.findByUsuarioIdUsuario(idUsuario);

        if (existente.isPresent()) {
            paciente.setIdPaciente(existente.get().getIdPaciente());
        }

        paciente.setUsuario(usuario);

        return repository.save(paciente);
    }

    @Override
    public Paciente actualizar(Integer id, Paciente paciente) {

        Paciente p = repository.findById(id).orElse(null);

        if (p == null) return null;

        p.setDni(paciente.getDni());
        p.setDireccion(paciente.getDireccion());
        p.setTelefono(paciente.getTelefono());
        p.setFechaNacimiento(paciente.getFechaNacimiento());
        p.setSexo(paciente.getSexo());

        return repository.save(p);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}