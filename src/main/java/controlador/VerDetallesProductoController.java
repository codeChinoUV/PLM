package controlador;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import modelo.Articulo;
import modelo.enums.EnumTipoUsuario;

import java.net.URL;
import java.util.ResourceBundle;

public class VerDetallesProductoController implements Initializable {
  @FXML
  private JFXTextField tfCodigo;
  @FXML
  private JFXTextField tfCodigoBarras;
  @FXML
  private JFXTextField tfDescripcion;
  @FXML
  private JFXTextField tfPrecioVenta;
  @FXML
  private JFXTextField tfPrecioMayoreo;
  @FXML
  private JFXTextField tfPrecioCompra;
  @FXML
  private JFXTextField tfCantidad;
  @FXML
  private JFXTextField tfPorcentajeGanancia;
  @FXML
  private JFXTextField tfPorcentajeGananciaMayoreo;
  @FXML
  private JFXTextField tfPzasMayoreo;
  @FXML
  private JFXTextField tfTipoUnidad;

  private Stage miVentana;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  private void llenarCampos(Articulo articulo){
    tfCodigo.setText(Integer.toString(articulo.getCodigo()));
    tfCodigoBarras.setText(articulo.getCodigoBarras());
    tfDescripcion.setText(articulo.getNombre());
    tfPrecioCompra.setText(Double.toString(articulo.getPrecioCompra()));
    tfPrecioVenta.setText(Double.toString(articulo.getPrecioVenta()));
    tfPrecioMayoreo.setText(Double.toString(articulo.getPrecioMayoreo()));
    tfPorcentajeGanancia.setText(Double.toString(articulo.getGanancia()));
    tfPorcentajeGananciaMayoreo.setText(Double.toString(articulo.getGananciaMayoreo()));
    tfCantidad.setText(Integer.toString(articulo.getCantidad()));
    tfPzasMayoreo.setText(Integer.toString(articulo.getPiezasParaMayoreo()));
    tfTipoUnidad.setText(articulo.getTipoUnidad());
  }

  /**
   * Oculta o muestra los campos del formulario, dependiendo del tipo de usuario
   * @param tipoDeUsuario El tipo de usuario
   */
  public void mostrarCamposParaTipoDeUsuario(EnumTipoUsuario tipoDeUsuario){
    switch (tipoDeUsuario){
      case Dueño:
      case Administrador:
        tfPorcentajeGanancia.setVisible(true);
        tfPorcentajeGananciaMayoreo.setVisible(true);
        tfPrecioCompra.setVisible(true);
        break;
      case Cajero:
      case Gerente:
        tfPorcentajeGananciaMayoreo.setVisible(false);
        tfPorcentajeGanancia.setVisible(false);
        tfPrecioCompra.setVisible(false);
    }
  }

  public void setArticuloAMostrar(Articulo articuloAMostrar){
    llenarCampos(articuloAMostrar);
  }

  public void setMiVentana(Stage miVentana){
    this.miVentana = miVentana;
  }

  public void cerrarVentana(){
    miVentana.close();
  }
}
