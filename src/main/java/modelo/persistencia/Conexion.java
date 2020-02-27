package modelo.persistencia;
import modelo.dataclass.ConfiguracionConexionBD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chino
 *
 */
public class Conexion {

  private static ArchivoDAO gestorDeArchivos;
  private static Connection conexion;

  private Conexion(){
  }

  /**
   * Crea una instancia de la conexion a la base de datos
   */
  public static Connection getConexion() throws IOException,SQLException,ClassNotFoundException {
    gestorDeArchivos = new Archivo();
    if(conexion == null) {
      Class.forName("com.mysql.cj.jdbc.Driver");
      ConfiguracionConexionBD configuracionDeConexion;
      configuracionDeConexion = gestorDeArchivos.getConfiguracionDB();
      conexion = DriverManager.getConnection(configuracionDeConexion.getURl(), configuracionDeConexion.getUsuario(),
              configuracionDeConexion.getContrasena());
    }
    return conexion;
  }


  /**
   * Cierra la conexion que se genera hacia la base de datos
   */
  public void close() {
    if(conexion != null){
      try {
        conexion.close();
      } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage() + "\n" + e.getErrorCode());
      }
    }
  }

}

