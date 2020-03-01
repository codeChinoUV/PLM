package controlador;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import modelo.Usuario;
import modelo.persistencia.UsuarioDAO;
import modelo.persistencia.Usuarios;
import principal.Programa;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IniciarSesionController implements Initializable {

  private Programa ventanaPrincipal = null;

  @FXML
  private JFXTextField tfUsuario;

  @FXML
  private JFXPasswordField tfcontrasena;

  @FXML
  private Label lMensaje;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  /**
   * Cambia el color de un texto
   *
   * @param etiqueta La etiqueta al cual se le cambiara el color
   * @param color El color que tendra el texto
   */
  private void cambiarTextoColor(Label etiqueta, String color) {
    etiqueta.setTextFill(Color.web(color));
  }

  /**
   * Verifica si los parametros no son nulos y si contienen texto, coloca el un mensaje si los datos
   * no son validos
   * @param username El nombre de usuario a verificar
   * @param contrasena La contrasena a verificar
   * @return Verdadero si los datos son validos y falso si no.
   */
  private boolean verificarCamposLlenos(String username, String contrasena){
    if((username != null && !username.equals("")) && (contrasena != null && !contrasena.equals(""))){
      return true;
    }else{
      lMensaje.setText("¡Debe de llenar ambos campos!");
      cambiarTextoColor(lMensaje, "#FF0000");
      return false;
    }
  }

  /**
   * Obtiene el usuario que coincida con las credenciales usuario y contrasena
   * @param usuario El usuario con el que se logeara
   * @param contrasena La contrasena de el usuario
   * @return Un objeto de tipo usuario al que le pertenecen las credenciales o null si no se encuentra las credenciasles
   */
  private Usuario logearse(String usuario, String contrasena){
    Usuario usuarioLogeado = null;
    UsuarioDAO persistenciaUsuario = new Usuarios();
    try{
      usuarioLogeado = persistenciaUsuario.logearse(usuario,contrasena);
    }catch (SSLException excepcionSSL){
      System.out.println(excepcionSSL.getMessage());
    }catch (SQLException | ClassNotFoundException | IOException excepcionConsulta){
      VentanasEmergentes.mostrarVentanaError(excepcionConsulta.getMessage());
      excepcionConsulta.printStackTrace();
    }
    return usuarioLogeado;
  }

  /**
   * Verifica si el inicio de sesión es valido para acceder al sistema
   */
  @FXML
  public void iniciarSesion(){
    String username = tfUsuario.getText();
    String contrasena = tfcontrasena.getText();
    if(verificarCamposLlenos(username,contrasena)){
      Usuario usuarioLogeo = logearse(username,contrasena);
      if(usuarioLogeo != null){
        lMensaje.setText("¡Logeado exitosamente!");
        ventanaPrincipal.setUsuarioLogeado(usuarioLogeo);
        cambiarTextoColor(lMensaje,"#009900");
        cambiarAVentanaMenuPrincipal();
      }else{
        lMensaje.setText("¡Credenciales invalidas!");
        cambiarTextoColor(lMensaje,"#FF0000");
      }
    }
  }

  /**
   * Cambia la escena actual por la menu_principal
   */
  private void cambiarAVentanaMenuPrincipal(){
    if(ventanaPrincipal != null){
      try{
        ventanaPrincipal.cambiarAVentanaMenuPrincipal();
      }catch (IOException excepcionAlLeerArchivo){
        VentanasEmergentes.mostrarVentanaError("Algo salio mal al iniciar sesión");
        excepcionAlLeerArchivo.printStackTrace();
      }
    }else{
      VentanasEmergentes.mostrarVentanaError("Esta vacio el menu principal");
    }
  }

  public void setVentanaPrincipal(Programa ventanaPrincipal) {
    this.ventanaPrincipal = ventanaPrincipal;
  }
}

