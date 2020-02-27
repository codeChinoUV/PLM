package modelo.persistencia;

import modelo.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
  List<Usuario> getUsuarios() throws SQLException, IOException, ClassNotFoundException;
  Usuario logearse(String usuario, String contrasena) throws SQLException, IOException, ClassNotFoundException;
  Usuario registrarUsuario(Usuario usuario) throws SQLException, IOException, ClassNotFoundException;
  Usuario editarUsuario(String nombreUsuario, Usuario usuario);
  boolean existeElUsuario(String nombreUsuario) throws SQLException, IOException, ClassNotFoundException;
}
