package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import modelo.Articulo;
import modelo.persistencia.Articulos;
import modelo.persistencia.ArticulosDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class VerDetallesArticuloCajeroController implements Initializable {
    private Articulo articuloDetalles = null;

    private Stage ventana = null;

    @FXML
    private JFXTextField tfUnidad;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    /**
     * Cierra la ventana actual
     */
    @FXML
    public void cerrarVentana() {
        if (ventana != null) {
            ventana.close();
        }
    }

    public Articulo getArticuloDetalles() {
        return articuloDetalles;
    }

    void setArticuloDetalles(Articulo articuloDetalles) {
        this.articuloDetalles = articuloDetalles;
        llenarCamposArticulo(articuloDetalles);
    }

    public Stage getVentana() {
        return ventana;
    }

    void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    /**
     * Recupera el texto ingresado en el tf codigo de barras, recupera al articulo con ese codigo de barras y lo
     * muestra
     */
    @FXML
    public void buscarProductoCodigoBarras() {
        ArticulosDAO persistenciaArticulos = new Articulos();
        if (!tfCodigoBarras.getText().equals("")) {
            Articulo articuloRecuperado = persistenciaArticulos.recuperarArticulo(tfCodigoBarras.getText());
            llenarCamposArticulo(articuloRecuperado);
        }
    }

    /**
     * Limpia los textfield
     */
    @FXML
    public void limpiarCampos() {
        tfCodigo.setText("");
        tfCodigoBarras.setText("");
        tfPrecioMayoreo.setText("");
        tfCantidad.setText("");
        tfNombre.setText("");
        tfPrecioPublico.setText("");
        tfPzasMayoreo.setText("");
        tfUnidad.setText("");
    }

    /**
     * Recupera el texto ingresado en el tf codigo, recupera al articulo con ese codigo y lo muestra
     */
    @FXML
    public void buscarProductoCodigo() {
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
        }
    }
}
