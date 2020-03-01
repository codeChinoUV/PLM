package controlador;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class VentanasEmergentes {
  Scene scene = new Scene(new Group(), 100, 100);

  public static void mostrarVentanaError(String mensaje){

    Alert alert = new Alert(Alert.AlertType.ERROR, mensaje);

    // clicking X also means no
    alert.showAndWait();
  }
}

