package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.Articulo;
import modelo.persistencia.Articulos;
import modelo.persistencia.ArticulosDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class VerDetallesArticuloAdministradorController implements Initializable {

    private Articulo articulo = null;

    private Stage ventana = null;

    @FXML
    private JFXTextField tfCodigo;

    @FXML
    private JFXTextField tfCodigoBarras;

    @FXML
    private JFXTextField tfNombre;

    @FXML
    private JFXTextField tfCantidad;

    @FXML
    private JFXTextField tfPrecioPublico;

    @FXML
    private JFXTextField tfPrecioMayoreo;

    @FXML
    private JFXTextField tfPzasMayoreo;

    @FXML
    private JFXButton btnCerrar;

    @FXML
    private JFXTextField tfUnidad;

    @FXML
    private JFXButton btnLimpiar;

    @FXML
    private JFXTextField tfGananciaMayoreo;

    @FXML
    private JFXTextField tfPrecioCompra;

    @FXML
    private JFXTextField tfGananciaPublico;

    public Articulo getArticulo() {
        return articulo;
    }

    void setArticulo(Articulo articulo) {
        this.articulo = articulo;
        llenarCamposArticulo(articulo);
    }

    public Stage getVentana() {
        return ventana;
    }

    void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    /**
     * Recupera el texto ingresado en el tf codigo, recupera al articulo con ese codigo y lo muestra
     */
    @FXML
    void buscarProductoCodigo(KeyEvent event) {
        ArticulosDAO persistenciaArticulos = new Articulos();
        if (tfCodigo != null && !tfCodigo.getText().equals("")) {
            try {
                int codigo = Integer.parseInt(tfCodigo.getText());
                Articulo articulo = persistenciaArticulos.recuperarArticulo(codigo);
                llenarCamposArticulo(articulo);
            } catch (NumberFormatException ex) {
                tfCodigo.setText("");
            }
        }
    }

    /**
     * Recupera el texto ingresado en el tf codigo de barras, recupera al articulo con ese codigo de barras y lo
     * muestra
     */
    @FXML
    void buscarProductoCodigoBarras(KeyEvent event) {
        ArticulosDAO persistenciaArticulos = new Articulos();
        if (!tfCodigoBarras.getText().equals("")) {
            Articulo articuloRecuperado = persistenciaArticulos.recuperarArticulo(tfCodigoBarras.getText());
            llenarCamposArticulo(articuloRecuperado);
        }
    }

    /**
     * Cierra la ventana actual
     */
    @FXML
    void cerrarVentana(ActionEvent event) {
        if (ventana != null) {
            ventana.close();
        }
    }

    /**
     * Limpia los textfield
     */
    @FXML
    void limpiarCampos(ActionEvent event) {
        tfCodigo.setText("");
        tfCodigoBarras.setText("");
        tfPrecioMayoreo.setText("");
        tfCantidad.setText("");
        tfNombre.setText("");
        tfPrecioPublico.setText("");
        tfPzasMayoreo.setText("");
        tfUnidad.setText("");
        tfGananciaPublico.setText("");
        tfGananciaMayoreo.setText("");
        tfPrecioCompra.setText("");
    }

    /**
     * Llena los textfield con la informacion del articulo
     *
     * @param articulo El articulo del cual se llenaran los campos
     */
    private void llenarCamposArticulo(Articulo articulo) {
        if (articulo != null) {
            tfCodigo.setText(Integer.toString(articulo.getCodigo()));
            tfCodigoBarras.setText(articulo.getCodigoBarras());
            tfNombre.setText(articulo.getNombre());
            tfUnidad.setText(articulo.getUnidad());
            tfCantidad.setText(Integer.toString(articulo.getCantidad()));
            tfPzasMayoreo.setText(Integer.toString(articulo.getPiezasParaMayoreo()));
            tfPrecioPublico.setText("$" + articulo.getPrecioVenta());
            tfPrecioMayoreo.setText("$" + articulo.getPrecioMayoreo());
            tfGananciaMayoreo.setText("%" + articulo.getGananciaMayoreo());
            System.out.println(articulo.getGananciaMayoreo());
            tfGananciaPublico.setText("%" + articulo.getGananciaPublico());
            tfPrecioCompra.setText("$" + articulo.getPrecioCompra());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}