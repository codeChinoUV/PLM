package org.chinosoft.modelo.persistencia;

import org.chinosoft.modelo.Persona;
import org.chinosoft.modelo.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Usuarios implements UsuarioDAO{
  private final String CONSULTA_PERSONA_ESPECIFICA = "SELECT * FROM personas WHERE idPersona = ";
  private final String CONSULTA_TODOS_USUARIOS = "SELECT * FROM usuarios;";
  private final String CONSULTA_LOGEO = "SELECT * FROM usuarios WHERE usuario=";
  private final String CONSULTA_INICIA_TRANSACCION = "BEGIN;";
  private final String CONSULTA_NUEVA_PERSONA = "INSERT INTO personas(nombre,apellidos,direccion, telefono) VALUES(";
  private final String CONSULTA_NUEVO_USUARIO = "INSERT INTO usuarios(usuario,contrasena,tipo,activo,idPersona) VALUES(";
  private final String CONSULTA_ULTIMO_ID_PERSONA = "SELECT MAX(idPersona) as 'ultima' from personas;";
  private final String CONSULTA_TERMINAR_TRANSACCION = "COMMIT";

  private Usuario recuperarUsuarioDeResultado(ResultSet resultadosDeConsulta) throws SQLException {
    if (resultadosDeConsulta != null){
      String nombreUsuario = resultadosDeConsulta.getString("usuario");
      String tipo = resultadosDeConsulta.getString("tipo");
      boolean activo = resultadosDeConsulta.getBoolean("activo");
      Persona persona = new Persona();
      persona.setId(resultadosDeConsulta.getInt("idPersona"));
      persona = getPersona(persona);
      if(persona != null){
        Usuario usuario = new Usuario(nombreUsuario,tipo,persona,activo);
        return usuario;
      }else{
        return null;
      }
    }else{
      return null;
    }
  }

  public List<Usuario> getUsuarios() {
    List<Usuario> usuarios = new ArrayList<>();
    Connection conexion = Conexion.getConexion();
    Statement consulta = null;
    ResultSet resultados = null;
    try{
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(CONSULTA_TODOS_USUARIOS);
      while (resultados.next() && resultados != null){
        usuarios.add(recuperarUsuarioDeResultado(resultados));
      }
      conexion.close();
      return usuarios;
    }catch (SQLException ex){
      ex.printStackTrace();
      System.out.println("Usuarios-SQLException: getUsuarios");
      return null;
    }
  }

  public Usuario logearse(String usuario, String contrasena) {
    String query = CONSULTA_LOGEO + "'" + usuario + "' and contrasena='" + contrasena + "';";
    Usuario usuarioRecuperado = null;
    if (!usuario.equals("") && !contrasena.equals("")){
      Connection conexion = Conexion.getConexion();
      Statement consulta = null;
      ResultSet resultados = null;
      try{
        consulta = conexion.createStatement();
        resultados = consulta.executeQuery(query);
        while (resultados.next() && resultados != null){
          usuarioRecuperado = recuperarUsuarioDeResultado(resultados);
        }
        conexion.close();
      }catch (SQLException ex){
        ex.printStackTrace();
        System.out.println("Usuarios-SQLExcepcion: logearse");
      }
      return usuarioRecuperado;
    }else{
      return null;
    }
  }

  public Usuario nuevoUsuario(Usuario usuario) {
    Connection conexion = Conexion.getConexion();
    Statement consulta = null;
    usuario.setPersona(nuevaPersona(usuario.getPersona(), conexion));
    if(usuario.getPersona() != null){
      String query = CONSULTA_NUEVO_USUARIO + "'" + usuario.getUsuario() + "','" + usuario.getContrasena()
          + "','" + usuario.getTipo() + "'," + usuario.isActivo() + "," + usuario.getPersona().getId() + ");";
      try{
        consulta = conexion.createStatement();
        consulta.execute(query);
        consulta.execute(CONSULTA_TERMINAR_TRANSACCION);
        conexion.close();
        return usuario;
      }catch (SQLException ex){
        ex.printStackTrace();
        System.out.println("Usuarios-SQLException: nuevoUsuario");
        return null;
      }
    }else{
      try {
        conexion.close();
        return null;
      }catch (SQLException ex){
        ex.printStackTrace();
        System.out.println("Usuarios-SQLException: nuevoUsuario");
        return null;
      }
    }
  }

  public Usuario editarUsuario(String nombreUsuario, Usuario usuario){
    return null;
  }

  private Persona recuperarPersonaDeResultado(ResultSet resultadosDeConsulta) throws SQLException{
    if (resultadosDeConsulta != null){
      int id = resultadosDeConsulta.getInt("idPersona");
      String nombre = resultadosDeConsulta.getString("nombre");
      String apellidos = resultadosDeConsulta.getString("apellidos");
      String direccion = resultadosDeConsulta.getString("direccion");
      String telefono = resultadosDeConsulta.getString("telefono");
      Persona persona = new Persona(id,nombre,apellidos,direccion,telefono);
      return persona;
    }else{
      return null;
    }
  }

  private Persona getPersona(Persona persona){
    if (persona != null && persona.getId() > 0){
      Persona personaRecuperada = null;
      String query = CONSULTA_PERSONA_ESPECIFICA + persona.getId() + ";";
      Connection conexion = Conexion.getConexion();
      Statement consulta = null;
      ResultSet resultados = null;
      try{
        consulta = conexion.createStatement();
        resultados = consulta.executeQuery(query);
        while (resultados.next() && resultados != null) {
          personaRecuperada = recuperarPersonaDeResultado(resultados);
        }
        return personaRecuperada;
      }catch (SQLException ex){
        ex.printStackTrace();
        System.out.println("Usuarios-SQLException: getPersona");
        return null;
      }
    }else{
      return null;
    }
  }

  private Persona nuevaPersona(Persona persona, Connection conexion){
    if(persona != null){
      String query = CONSULTA_NUEVA_PERSONA + "'" + persona.getNombre() + "','" + persona.getApellidos() + "','"
          + persona.getDireccion() + "','" + persona.getTelefono() +"');";
      Statement consulta = null;
      ResultSet resultados = null;
      try{
        consulta = conexion.createStatement();
        consulta.execute(CONSULTA_INICIA_TRANSACCION);
        consulta.execute(query);
        resultados = consulta.executeQuery(CONSULTA_ULTIMO_ID_PERSONA);
        int id =-1;
        while (resultados.next() && resultados != null){
          id = resultados.getInt("ultima");
        }
        if(id > 0 ){
          persona.setId(id);
          return persona;
        }else{
          return null;
        }
      }catch (SQLException ex){
        System.out.println("Usuarios-SQLException: nuevaPersona");
        ex.printStackTrace();
        return null;
      }
    }else{
      return null;
    }
  }
}
