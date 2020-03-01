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

public class MenuInventarioController implements Initializable {
  @FXML
  private JFXButton bRegresar;
  @FXML
  private JFXButton bVentas;
  @FXML
  private Label lUsuario;
  @FXML
  private Label lFecha;

  private Programa manejadorDeVentanas;



  /**
   * Coloca el nombre del usuario logeado en el label lUsuario
   */
  private void colocarNombreUsuario(){
    String nombreUsuario = manejadorDeVentanas.getUsuarioLogeado().getPersona().getNombre() + " " +
        manejadorDeVentanas.getUsuarioLogeado().getPersona().getApellidos();
    lUsuario.setText(nombreUsuario);
  }

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

  public void setManejadorDeVentanas(Programa manejadorDeVentanas){
    this.manejadorDeVentanas = manejadorDeVentanas;
    colocarNombreUsuario();
  }

  /**
   * Cambia la escena actual a la menu_principal
   */
  public void regresarAVentanaPrincipal(){
    try{
      manejadorDeVentanas.cambiarAVentanaMenuPrincipal();
    }catch (IOException excepcionAlCargarLaEscena){
      VentanasEmergentes.mostrarVentanaError("Error al regresar a la ventana principal");
    }
  }

  /**
   * Cambia a la escena menu_inventario
   */
  public void mostrarVentanaAdministrarInventario(){
    FXMLLoader loader = new FXMLLoader(Programa.class.getResource("/vistas/administrar_inventario.fxml"));
    try{
      manejadorDeVentanas.setRootPane(loader.load());
      Scene scene = new Scene(manejadorDeVentanas.getRootPane());
      manejadorDeVentanas.getStagePrincipal().setTitle("Papeleria la montaña - Administrar inventario");
      AdministrarInventarioController controller = loader.getController();
      controller.setManejadorDeVentanas(manejadorDeVentanas);
      manejadorDeVentanas.getStagePrincipal().setScene(scene);
      manejadorDeVentanas.getStagePrincipal().show();
    }catch (IOException excepcionAlLeerCargar){
      VentanasEmergentes.mostrarVentanaError("Error al cargar la ventana");
    }

  }
}
