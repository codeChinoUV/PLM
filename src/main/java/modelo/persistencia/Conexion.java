package modelo.persistencia;
import modelo.dataclass.ConfiguracionConexionBD;

import javax.net.ssl.SSLException;
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

  private static Connection conexion;
  private static ConfiguracionConexionBD configuracionConexionBD;

  private Conexion(){
  }

  /**
   * Crea una instancia de la conexion a la base de datos
   */
  public static Connection getConexion() throws IOException,SQLException,ClassNotFoundException {
    if(configuracionConexionBD == null){
      ArchivoDAO gestorDeArchivos = new Archivo();
      configuracionConexionBD = gestorDeArchivos.getConfiguracionDB();
    }
    if(conexion == null || conexion.isClosed()) {
      Class.forName("com.mysql.cj.jdbc.Driver");
      ConfiguracionConexionBD configuracionDeConexion;

      conexion = DriverManager.getConnection(configuracionConexionBD.getURl(), configuracionConexionBD.getUsuario(),
          configuracionConexionBD.getContrasena());
    }
    return conexion;
  }


  /**
   * Cierra la conexion que se genera hacia la base de datos
   */
  public void close() {
    if(conexion != null){
      try {
        if(!conexion.isClosed()){
          conexion.close();
        }
      } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage() + "\n" + e.getErrorCode());
      }
    }
  }

}

