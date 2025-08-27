package org.proyectoIntegrador.user.service;

import org.proyectoIntegrador.user.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);
    List<Usuario> obtenerTodosUsuarios();
    Optional<Usuario> obtenerUsuarioPorId(String id);
    Usuario actualizarUsuario(String id, Usuario usuario);
    void eliminarUsuario(String id);
    boolean existeUsuario(String id);
    boolean existeEmail(String email);
    Optional<Usuario> obtenerUsuarioPorEmail(String email);
}
