package org.proyectoIntegrador.user.service;



import org.proyectoIntegrador.user.entity.Usuario;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final Map<Long, Usuario> usuariosMap = new HashMap<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        if (existeEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        Long nuevoId = contadorId.getAndIncrement();
        usuario.setId(nuevoId);
        usuariosMap.put(nuevoId, usuario);
        return usuario;
    }
    @Override
    public List<Usuario> obtenerTodosUsuarios() {
        return new ArrayList<>(usuariosMap.values());
    }
    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return Optional.ofNullable(usuariosMap.get(id));
    }
    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        if (!existeUsuario(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        if (existeEmail(usuarioActualizado.getEmail()) &&
                !obtenerUsuarioPorId(id).get().getEmail().equals(usuarioActualizado.getEmail())) {
            throw new RuntimeException("El email ya está en uso por otro usuario");
        }

        usuarioActualizado.setId(id);
        usuariosMap.put(id, usuarioActualizado);
        return usuarioActualizado;
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!existeUsuario(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuariosMap.remove(id);
    }

    @Override
    public boolean existeUsuario(Long id) {
        return usuariosMap.containsKey(id);
    }

    @Override
    public boolean existeEmail(String email) {
        return usuariosMap.values().stream()
                .anyMatch(usuario -> usuario.getEmail().equalsIgnoreCase(email));
    }
}
