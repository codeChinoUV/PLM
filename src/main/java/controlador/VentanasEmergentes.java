package controlador;

import javafx.scene.control.Alert;

public class VentanasEmergentes {

  /**
   * Muestra una ventana que indica que ocurrio un error
   * @param mensaje El mensaje que contendra la ventana
   */
  public static void mostrarVentanaError(String mensaje){
    Alert error = new Alert(Alert.AlertType.ERROR, mensaje);
    error.setHeaderText("Error");
    error.getDialogPane().setPrefHeight(200);
    error.getDialogPane().setPrefWidth(500);
    error.showAndWait();
  }

  /**
   * Muestra una ventana que indica una advertencia
   * @param mensaje El mensaje que contendra la ventana
   */
  public static void mostrarVentanaAlerta(String mensaje){
    Alert alerta = new Alert(Alert.AlertType.WARNING,mensaje);
    alerta.setHeaderText("Alerta");
    alerta.getDialogPane().setPrefHeight(200);
    alerta.getDialogPane().setPrefWidth(500);
    alerta.showAndWait();
  }

  /**
   * Muestra un mensaje indicando exito
   * @param mensaje El mensaje que contendra la ventana
   */
  public static void mostrarVentanaExito(String mensaje){
    Alert exito = new Alert(Alert.AlertType.INFORMATION, mensaje);
    exito.setHeaderText("Exito");
    exito.getDialogPane().setPrefHeight(200);
    exito.getDialogPane().setPrefWidth(500);
    exito.showAndWait();
  }
}

