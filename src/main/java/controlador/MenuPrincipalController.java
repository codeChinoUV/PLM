package controlador;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import principal.Programa;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {
  public JFXButton bVentas;
  private Programa manejadorDeVentanas;

  @FXML
  private Label lUsuario;

  @FXML
  private Label lFecha;

  /**
   * Coloca la fecha actual en el label lFecha
   */
  private void colocarFechaActual(){
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    Date date = new Date();
    String fechaActual = sdf.format(date);
    lFecha.setText(fechaActual);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    colocarFechaActual();
  }

  /**
   * Establece el manejadorDeVentanas
   * @param manejadorDeVentanas El manejador de ventanas
   */
  public void setManejadorDeVentanas(Programa manejadorDeVentanas){
    this.manejadorDeVentanas = manejadorDeVentanas;
    colocarNombreUsuario();
  }

  /**
   * Coloca el nombre del usuario logeado en el label lUsuario
   */
  private void colocarNombreUsuario(){
    String nombreUsuario = manejadorDeVentanas.getUsuarioLogeado().getPersona().getNombre() + " " +
        manejadorDeVentanas.getUsuarioLogeado().getPersona().getApellidos();
    lUsuario.setText(nombreUsuario);
  }

  /**
   * Cambia a la escena menu_inventario
   */
  public void mostrarVentanaMenuInventario(){
    FXMLLoader loader = new FXMLLoader(Programa.class.getResource("/vistas/menu_inventario.fxml"));
    try{
      manejadorDeVentanas.setRootPane(loader.load());
      Scene scene = new Scene(manejadorDeVentanas.getRootPane());
      manejadorDeVentanas.getStagePrincipal().setTitle("Papeleria la montaña - Inventario");
      MenuInventarioController controller = loader.getController();
      controller.setManejadorDeVentanas(manejadorDeVentanas);
      manejadorDeVentanas.getStagePrincipal().setScene(scene);
      manejadorDeVentanas.getStagePrincipal().show();
    }catch (IOException excepcionAlLeerCargar){
      VentanasEmergentes.mostrarVentanaError("Error al cargar el menu");
    }

  }



}
