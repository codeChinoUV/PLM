package org.chinosoft.modelo.persistencia;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**************************************************************************************************
 * principal.Programa:      Sistema de Administracion Papeleria la Montaña
 * Autor:         Jose Miguel Quiroz Benitez
 * Fecha:         04-08-2019
 * Descripción:   Crea una conexion al gestor de base de datos MYSQL
 ************************************************************************************************* */

/**
 *
 * @author chino
 *
 */
public class Conexion {

  private static Connection conexion;
  private static String host = "localhost";
  private static String db = "PLM";
  private static String username = "PLM";
  private static String password = "hola9011";
  private static String url = "jdbc:mysql://" + host + "/" + db + "?useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City";


  /**
   * El constructor crea una instancia de la clase "com.mysql.cj.jdbc.Driver" y se conecta a traves
   * de la url especificada en el atributo, un usuario que se encuentra en el atributo username y la
   * contrase単a de la base de datos que se encuentra en el atributo password. El constructor crea
   * una "copia" que es asignada al atributo con.
   */
  static Connection getConexion() {
    try {
      if (conexion == null || conexion.isClosed()) {
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          conexion = DriverManager.getConnection(url, username, password);
        } catch (CommunicationsException ex) {
          JOptionPane.showMessageDialog(null, "Ocurrio un error en la base de datos, estamos" +
                  " tratando de resolverlo, intenta nuevamente");
          try {
            String cmd = "service mysql start"; //Comando de apagado en windows
            Runtime.getRuntime().exec(cmd);
            return null;
          } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
          }
        } catch (SQLException mysqlError) {
          JOptionPane.showMessageDialog(null, "Error de la base de datos");
          mysqlError.printStackTrace();
          return null;
        } catch (ClassNotFoundException claseNoEncontrada) {
          JOptionPane.showMessageDialog(null, "Error de la base de datos");
          claseNoEncontrada.printStackTrace();
          return null;
        } catch (Exception excepcionGeneral) {
          JOptionPane.showMessageDialog(null, "Error de la base de datos");
          excepcionGeneral.printStackTrace();
          return null;
        }
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "Algo salio mal contacte al administrador del sistema");
      ex.printStackTrace();
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

