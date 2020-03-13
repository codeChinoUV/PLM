package controlador;

import javafx.scene.control.Alert;

public class VentanasEmergentes {

  public static void mostrarVentanaError(String mensaje){
    Alert error = new Alert(Alert.AlertType.ERROR, mensaje);
    error.setHeaderText("Error");
    error.getDialogPane().setPrefHeight(200);
    error.getDialogPane().setPrefWidth(500);
    error.showAndWait();
  }

  public static void mostrarVentanaAlerta(String mensaje){
    Alert alerta = new Alert(Alert.AlertType.WARNING,mensaje);
    alerta.setHeaderText("Alerta");
    alerta.getDialogPane().setPrefHeight(200);
    alerta.getDialogPane().setPrefWidth(500);
    alerta.showAndWait();
  }
}

