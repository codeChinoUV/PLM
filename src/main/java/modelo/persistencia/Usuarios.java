package modelo.persistencia;
import controlador.util.Encriptacion;
import modelo.Persona;
import modelo.Usuario;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Usuarios implements UsuarioDAO{

  /***
     * Crea un objeto de tipo Usuario en base a los resultados de una consulta
     * @param resultadosDeConsulta ResultSet donde se encuentra el usuario recuperado
     * @return Un Usuario que se creo en base al RelsultSet
     * @throws SQLException Una excepcion SQLException por si ocurre una excepcion al momento de realizar la consulta
     */
  private Usuario recuperarUsuarioDeResultado(ResultSet resultadosDeConsulta) throws SQLException, IOException,
      ClassNotFoundException {
      Usuario usuarioRecuperado = null;
      if (resultadosDeConsulta != null){
      String nombreUsuario = resultadosDeConsulta.getString("usuario");
      String tipo = resultadosDeConsulta.getString("tipo_usuario");
      boolean activo = resultadosDeConsulta.getBoolean("activo");
      Persona persona = new Persona();
      persona.setId(resultadosDeConsulta.getInt("persona_id_persona"));
      persona = getPersona(persona);
      if(persona != null){
        usuarioRecuperado = new Usuario(nombreUsuario,tipo,persona,activo);
      }
    }
      return usuarioRecuperado;
  }

    /***
     * Recupera todos los Usuario registrados
     * @return Una List<Usuario> con todos los usuarios registrados
     * @throws SQLException Una excepcion que indica que hubo un error al ejecutar la consulta SQL
     * @throws IOException Una excepcion que indica que no se pudo abrir el documento de configuracion de la base de datos
     * @throws ClassNotFoundException Una excepcion que indica que no se encuentra la libreria para realizar la conexión
     * a la base de datos
     */
  public List<Usuario> getUsuarios() throws SQLException, IOException, ClassNotFoundException {
    Connection conexion = Conexion.getConexion();
    List<Usuario> usuarios = new ArrayList<>();
    String CONSULTA_TODOS_USUARIOS = "SELECT * FROM usuario;";
    PreparedStatement consulta = conexion.prepareStatement(CONSULTA_TODOS_USUARIOS);
      ResultSet resultados = consulta.executeQuery();
      while (resultados.next()){
        usuarios.add(recuperarUsuarioDeResultado(resultados));
      }
      conexion.close();
      return usuarios;
  }

  /***
   * Busca a un usuario en base a su nombre de usuario y conytraseña, si el usuario existe lo regresa, si no regresa null
   * @param usuario El nombre de usuario
   * @param contrasena La contraseña correspondiente al nombre de usuario en texto plano
   * @return El Usuario correspondiente al usuario y contraseña o null si el usuario no existe
   * @throws SQLException Una Excepcion que indica que ocurrio un error al ejecutar la consulta SQL
   * @throws IOException Una excepcion que indica que ocurrio un error al leer el archivo de configuracion
   * @throws ClassNotFoundException Una excepcion que indica que ocurrio un error al buscar la libreria que permite la
   * conexión con la base de datos
   */
  public Usuario logearse(String usuario, String contrasena) throws SQLException, IOException, ClassNotFoundException {
    Usuario usuarioRecuperado = null;
    Connection conexion = Conexion.getConexion();
    String CONSULTA_LOGEO = "SELECT * FROM usuario WHERE usuario = ? and contrasena = ? ;";
    PreparedStatement consulta = conexion.prepareStatement(CONSULTA_LOGEO);
    consulta.setString(1,usuario);
    consulta.setString(2, Encriptacion.toSHA256(contrasena));
    ResultSet resultados;
    resultados = consulta.executeQuery();
    while (resultados.next()){
      usuarioRecuperado = recuperarUsuarioDeResultado(resultados);
    }
    conexion.close();
    return usuarioRecuperado;
  }

  /**
   * Registra un nuevo usuario en la base de datos
   * @param usuario El nuevo usuario que se registrara
   * @return El Usuario que se registrara
   * @throws SQLException Una Excepcion que indica que ocurrio un error al momento de ejecutar la consulta
   * @throws IOException Una Excepcion que indica que ocurrio un error al momento de leer el archivo de configuracion
   * @throws ClassNotFoundException Una Excepcion que indica que ocurrio un error al momento de buscar la libreria
   * que permite la conexión a la base de datos
   */
  public Usuario registrarUsuario(Usuario usuario) throws SQLException, IOException, ClassNotFoundException{
    Usuario usuarioARegistrar = null;
    Connection conexion = Conexion.getConexion();
    usuario.setPersona(registrarPersona(usuario.getPersona(), conexion));
    String REGISTRO_USUARIO = "INSERT INTO usuario(usuario,contrasena,tipo_usuario,activo,persona_id_persona)" +
        " VALUES(?,?,?,?,?);";
    PreparedStatement consulta = conexion.prepareStatement(REGISTRO_USUARIO);
    consulta.setString(1,usuario.getUsuario());
    consulta.setString(2,Encriptacion.toSHA256(usuario.getContrasena()));
    consulta.setString(3,usuario.getTipo());
    consulta.setBoolean(4,usuario.isActivo());
    consulta.setInt(5, usuario.getPersona().getId());
    if(usuario.getPersona() != null){
        consulta.executeQuery();
      String CONSULTA_TERMINAR_TRANSACCION = "COMMIT";
      consulta.execute(CONSULTA_TERMINAR_TRANSACCION);
        conexion.close();
        usuarioARegistrar = usuario;
    }
    return usuarioARegistrar;
  }

  public Usuario editarUsuario(String nombreUsuario, Usuario usuario){
    return null;
  }

  /**
   * Recupera la informacion de una Persona en base a un ResultSet
   * @param resultadosDeConsulta El ResultSet que contiene la información de la Persona
   * @return Una Persona con la información proporcionada por el ResultSet
   * @throws SQLException Una Excepcion que indica que ocurrio un error al momento de ejecutar la consulta SQL
   */
  private Persona recuperarPersonaDeResultado(ResultSet resultadosDeConsulta) throws SQLException{
    Persona persona = null;
    if (resultadosDeConsulta != null){
      int id = resultadosDeConsulta.getInt("id_persona");
      String nombre = resultadosDeConsulta.getString("nombre");
      String apellidos = resultadosDeConsulta.getString("apellidos");
      String direccion = resultadosDeConsulta.getString("direccion");
      String telefono = resultadosDeConsulta.getString("telefono");
      persona = new Persona(id,nombre,apellidos,direccion,telefono);
    }
    return persona;
  }

  /**
   * Recupera la información de una persona con base en su id
   * @param persona La persona de la cual se recuperara la informacion
   * @return La información de la persona o null si no existe
   * @throws SQLException Una Excepcion que indica que ocurrio un error al moemento de ejecutar la consulta
   * @throws IOException Una Excepcion que indica que ocurrio un error al momento de leer el archivo de configuracion
   * @throws ClassNotFoundException Una Excepcion que indica que ocurrio un error al momento de buscar la libreria
   * que permite la conexion con la base de datos
   */
  private Persona getPersona(Persona persona) throws SQLException, IOException, ClassNotFoundException{
    Persona personaRecuperada = null;
    if (persona != null && persona.getId() > 0){
      Connection conexion = Conexion.getConexion();
      String CONSULTA_PERSONA_ESPECIFICA = "SELECT * FROM persona WHERE id_persona = ? ;";
      PreparedStatement consulta = conexion.prepareStatement(CONSULTA_PERSONA_ESPECIFICA);
      consulta.setInt(1,persona.getId());
      ResultSet resultados = consulta.executeQuery();
      while (resultados.next()) {
        personaRecuperada = recuperarPersonaDeResultado(resultados);
      }
    }
    return personaRecuperada;
  }

  /**
   *
   * @param persona La persona que se registrara
   * @param conexion La conexion abierta a la base de datos
   * @return La Persona con su id en la base de datos
   * @throws SQLException Una Excepcion que indica que ocurrio un error al momento de ejecutar la consulta
   */
  private Persona registrarPersona(Persona persona, Connection conexion) throws SQLException{
      if(persona != null){
        String REGISTRO_PERSONA = "INSERT INTO persona(nombre,apellidos,direccion, telefono) VALUES(?,?,?,?);";
        PreparedStatement consulta = conexion.prepareStatement(REGISTRO_PERSONA);
        consulta.setString(1, persona.getNombre());
        consulta.setString(2, persona.getApellidos());
        consulta.setString(3, persona.getDireccion());
        consulta.setString(4, persona.getTelefono());
        String CONSULTA_INICIA_TRANSACCION = "BEGIN;";
        consulta.execute(CONSULTA_INICIA_TRANSACCION);
        consulta.executeQuery();
        String CONSULTA_ULTIMO_ID_PERSONA = "SELECT MAX(id_persona) as 'ultima' from personas;";
        ResultSet resultados = consulta.executeQuery(CONSULTA_ULTIMO_ID_PERSONA);
        int id =-1;
        while (resultados.next()){
          id = resultados.getInt("ultima");
        }
        if(id > 0 ) {
            persona.setId(id);
        }
      }
      return persona;
    }

  /**
   * Verifica si el nombre de usuario aun no se encuentra registrado
   * @param nombreUsuario El nombre de usuario a verificar
   * @return Verdadero si el usuario ya existe o falso si no
   * @throws SQLException Una Excepcion que indica que ocurrio un error al momento de ejcutar la consulta
   * @throws IOException Una Excepcion que indica que ocurrio un error al momento de leer el archivo de configuracion
   * @throws ClassNotFoundException Una Excepcion que indica que no se encuentra la libreria que permite la conexion
   * a la base de datos
   */
    public boolean existeElUsuario(String nombreUsuario) throws SQLException, IOException, ClassNotFoundException {
      boolean existeElUsuario = true;
      Connection conexion = Conexion.getConexion();
      String CONSULTA_CONTAR_USUARIOS_CON_MISMO_NOMBREUSUARIO = "SELECT count(usuario) " +
          "LIKE 'cantidad_usuarios' FROM usuario WHERE usuario = ?;";
      PreparedStatement consulta = conexion.prepareStatement(CONSULTA_CONTAR_USUARIOS_CON_MISMO_NOMBREUSUARIO);
      consulta.setString(1,nombreUsuario);
      ResultSet resultados = consulta.executeQuery();
      int cantidadUsuarios = 2;
      while (resultados.next()){
        cantidadUsuarios = resultados.getInt("cantidad_usuarios");
      }
      conexion.close();
      if(cantidadUsuarios == 0){
        existeElUsuario = false;
      }
      return existeElUsuario;
    }

  }

