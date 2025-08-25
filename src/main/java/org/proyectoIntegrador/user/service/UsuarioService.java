package org.proyectoIntegrador.user.service;

import org.proyectoIntegrador.user.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> obtenerTodosUsuarios();
    Optional<Usuario> obtenerUsuarioPorId(Long id);
    Usuario actualizarUsuario(Long id, Usuario usuario);
    void eliminarUsuario(Long id);
    boolean existeUsuario(Long id);
    boolean existeEmail(String email);
}
