package org.proyectoIntegrador.user.service;

import org.proyectoIntegrador.user.entity.Usuario;
import org.proyectoIntegrador.user.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        if (existeEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya esta registrado");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(String id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario actualizarUsuario(String id, Usuario usuarioActualizado) {
        if (!existeUsuario(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }

        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            if (!usuario.getEmail().equals(usuarioActualizado.getEmail()) &&
                    existeEmail(usuarioActualizado.getEmail())) {
                throw new RuntimeException("El email ya esta en uso por otro usuario");
            }

            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setEdad(usuarioActualizado.getEdad());

            return usuarioRepository.save(usuario);
        }

        throw new RuntimeException("Usuario no encontrado");
    }

    @Override
    public void eliminarUsuario(String id) {
        if (!existeUsuario(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existeUsuario(String id) {
        return usuarioRepository.existsById(id);
    }

    @Override
    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}