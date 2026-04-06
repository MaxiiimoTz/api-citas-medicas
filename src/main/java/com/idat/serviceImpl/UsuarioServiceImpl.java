package com.idat.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.model.Paciente;
import com.idat.model.Usuario;
import com.idat.repository.PacienteRepository;
import com.idat.repository.UsuarioRepository;
import com.idat.service.EmailService;
import com.idat.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Usuario guardar(Usuario usuario) {

        if (repository.findByEmail(usuario.getEmail()) != null) {
            throw new RuntimeException("EMAIL_DUPLICADO");
        }

        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            String email = usuario.getEmail();
            String base = email.substring(0, email.indexOf("@"));
            usuario.setPassword(base + "123");
            usuario.setPasswordTemporal(true);
        }

        return repository.save(usuario);
    }
    
    @Override
    public Usuario cambiarEstado(Integer id, Boolean estado) {
        Usuario usuario = repository.findById(id).orElse(null);
        if (usuario == null) return null;

        usuario.setEstado(estado);
        return repository.save(usuario);
    }
    
    @Override
    public Usuario cambiarPassword(Integer idUsuario, String nuevaPassword) {
    	Usuario usuario = repository.findById(idUsuario).orElse(null);
    	
    	if(usuario == null) {
    		throw new RuntimeException("USUARIO_NO_EXISTE");
    	}
    	
    	usuario.setPassword(nuevaPassword);
    	usuario.setPasswordTemporal(false);
    	
    	return repository.save(usuario);
    }

    @Override
    public Usuario actualizar(Integer id, Usuario usuario) {

        Usuario existente = repository.findById(id).orElse(null);

        if (existente == null) {
            throw new RuntimeException("USUARIO_NO_EXISTE");
        }

        Usuario conMismoEmail = repository.findByEmail(usuario.getEmail());

        if (conMismoEmail != null && !conMismoEmail.getIdUsuario().equals(id)) {
            throw new RuntimeException("EMAIL_DUPLICADO");
        }

        existente.setNombres(usuario.getNombres());
        existente.setApellidos(usuario.getApellidos());
        existente.setEmail(usuario.getEmail());
        existente.setRol(usuario.getRol());

        return repository.save(existente);
    }
    
    @Override
    public Map<String, String> enviarCredenciales(Integer idUsuario) {

        Usuario usuario = repository.findById(idUsuario).orElse(null);

        if (usuario == null) {
            throw new RuntimeException("USUARIO_NO_EXISTE");
        }

        emailService.enviarCredenciales(
            usuario.getEmail(),
            usuario.getEmail(),
            usuario.getPassword()
        );

        return Map.of(
            "mensaje", "Credenciales enviadas correctamente"
        );
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}