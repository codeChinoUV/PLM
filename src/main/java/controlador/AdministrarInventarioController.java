package controlador;

import javafx.fxml.Initializable;
import principal.Programa;

import java.net.URL;
import java.util.ResourceBundle;

public class AdministrarInventarioController implements Initializable {
  private Programa manejadorDeVentanas;
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void setManejadorDeVentanas(Programa manejadorDeVentanas){
    this.manejadorDeVentanas = manejadorDeVentanas;
  }
}
