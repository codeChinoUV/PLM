package modelo.persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/** ************************************************************************************************
 * principal.Programa:      Sistema de Administracion Papeleria la Montaña
 * Autor:         Jose Miguel Quiroz Benitez
 * Fecha:         04-08-2019
 * Descripci坦n:   Crea una conexion al gestor de base de datos MYSQL
 ************************************************************************************************* */

/**
 *
 * @author chino
 *
 */
public class Conexion {

  private static Connection conexion;
  private static String host = "localhost";
  private static String db = "mydb";
  private static String username = "chino";
  private static String password = "joseMiguel13129899";
  private static String url = "jdbc:mysql://" + host + "/" + db + "?useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City&security?useSSL=false";


  /**
   * El constructor crea una instancia de la clase "com.mysql.cj.jdbc.Driver" y se conecta a traves
   * de la url especificada en el atributo, un usuario que se encuentra en el atributo username y la
   * contrase単a de la base de datos que se encuentra en el atributo password. El constructor crea
   * una "copia" que es asignada al atributo con.
   */
  public static Connection getConexion() {
    if(conexion == null){
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
          conexion = DriverManager.getConnection(url, username, password);
        } catch (SQLException mysqlError) {
          JOptionPane.showMessageDialog(null, "Error de la base de datos");
          mysqlError.printStackTrace();
        }
      } catch (ClassNotFoundException claseNoEncontrada) {
        JOptionPane.showMessageDialog(null, "Error de la base de datos");
        claseNoEncontrada.printStackTrace();
      } catch (Exception excepcionGeneral) {
        JOptionPane.showMessageDialog(null, "Error de la base de datos");
        excepcionGeneral.printStackTrace();
      }
    }
    return conexion;
  }

  /**
   * Cierra la conexion que se genera hacia la base de datos
   */
  public void close() {
    try {
      conexion.close();
    } catch (SQLException e) {
      System.err.println("Error: " + e.getMessage() + "\n" + e.getErrorCode());
    }
  }
}

