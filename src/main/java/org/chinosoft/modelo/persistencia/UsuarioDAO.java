package org.chinosoft.modelo.persistencia;

import org.chinosoft.modelo.Usuario;

import java.util.List;

public interface UsuarioDAO {
    List<Usuario> getUsuarios();

    Usuario logearse(String usuario, String contrasena);

    Usuario nuevoUsuario(Usuario usuario);

    Usuario editarUsuario(String nombreUsuario, Usuario usuario);
}
