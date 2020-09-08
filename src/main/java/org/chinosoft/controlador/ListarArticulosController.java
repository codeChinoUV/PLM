package org.chinosoft.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.chinosoft.App;
import org.chinosoft.modelo.Articulo;
import org.chinosoft.modelo.Usuario;
import org.chinosoft.modelo.persistencia.Articulos;
import org.chinosoft.modelo.persistencia.ArticulosDAO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListarArticulosController implements Initializable {

    private Usuario usuario = null;

    private App ventanaPrincipal = null;

    private List<Articulo> articulos = null;

    private ObservableList<Articulo> articulosTabla = null;

    @FXML
    private Label lUsuario;

    @FXML
    private Label lFecha;

    @FXML
    private TableView<Articulo> tArticulos;

    @FXML
    private TableColumn<Articulo, String> tcCodigo;

    @FXML
    private TableColumn<Articulo, String> tcCodigoBarras;

    @FXML
    private TableColumn<Articulo, String> tcNombre;

    @FXML
    private TableColumn<Articulo, String> tcPrecioPublico;

    @FXML
    private TableColumn<Articulo, String> tcPrecioMayoreo;

    @FXML
    private TableColumn<Articulo, String> tcCantidad;

    @FXML
    private TableColumn<Articulo, String> tcUnidad;

    @FXML
    private TableColumn<Articulo, String> tcPzaMayoreo;

    @FXML
    private JFXTextField tfBuscar;

    @FXML
    private JFXButton btnVerDetalles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        llenarTablaConTodosLosArticulos();
    }

    /**
     * Recupera del DAO los articulos almacenados en la BD
     *
     * @return una lista de articulos recuperados de la BD
     */
    private List<Articulo> recuperarArticulos() {
        ArticulosDAO persistenciaArticlos = new Articulos();
        return persistenciaArticlos.getArticulos();
    }

    /**
     * Recupera del DAO los articulos almacenados en la BD que coinciden con el nombre
     *
     * @param busqueda El nombre que se buscara
     * @return Una lista de articulos que coinciden con el nombre del articulo
     */
    private List<Articulo> recuperarArticulosBusqueda(String busqueda) {
        ArticulosDAO peristenciaArticulos = new Articulos();
        return peristenciaArticulos.buscarArticulos(busqueda);
    }

    /**
     * Copia los articulos contenidos en List a ObservableList, para poder ocuparlos en la tabla
     *
     * @param articulos La lista de articulos a copiar
     * @return La ObservableList con la copia de los articulos
     */
    private ObservableList<Articulo> copiarArticulosDisponiblesParaTabla(List<Articulo> articulos) {
        ObservableList<Articulo> articulosParaTabla = FXCollections.observableArrayList();
        if (articulos != null) {
            articulosParaTabla.addAll(articulos);
            return articulosParaTabla;
        } else {
            return null;
        }
    }

    /**
     * Llena la tabla con los articulos de que hay en la lista de articulosTabla
     */
    private void llenarTabla() {
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tcCodigoBarras.setCellValueFactory(new PropertyValueFactory<>("codigoBarras"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tcPrecioPublico.setCellValueFactory(p -> {
            String precioPublico = "$" + p.getValue().getPrecioVenta();
            return new SimpleStringProperty(precioPublico);
        });

        tcPrecioMayoreo.setCellValueFactory(p -> {
            String precioMayoreo = "$" + p.getValue().getPrecioMayoreo();
            return new SimpleStringProperty(precioMayoreo);
        });

        tcUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        tcPzaMayoreo.setCellValueFactory(new PropertyValueFactory<>("piezasParaMayoreo"));
    }


    /**
     * Muestra todos los articulos en la tabla
     */
    private void llenarTablaConTodosLosArticulos() {
        establecerItemsTabla();
        llenarTabla();
    }

    /**
     * Llena la tabla con los articulos que coinciden con la busqueda, si no hay texto que buscar la llena con todos
     */
    @FXML
    public void llenarTablaConBusqueda() {
        if (tfBuscar != null && !tfBuscar.getText().equals("")) {
            establecerItemsTablaBusqueda(tfBuscar.getText());
            llenarTabla();
        } else {
            llenarTablaConTodosLosArticulos();
        }
    }

    /**
     * Establece los items de la tabla con todos los articulos
     */
    private void establecerItemsTabla() {
        articulos = recuperarArticulos();
        articulosTabla = copiarArticulosDisponiblesParaTabla(articulos);
        tArticulos.setItems(articulosTabla);
    }

    /**
     * Establece los items de la tabla que coinciden con la busqueda
     */
    private void establecerItemsTablaBusqueda(String busqueda) {
        articulos = recuperarArticulosBusqueda(busqueda);
        articulosTabla = copiarArticulosDisponiblesParaTabla(articulos);
        tArticulos.setItems(articulosTabla);
    }

    /**
     * Muestra la ventana de detalles de un articulo
     *
     * @param articulo El articulo a ver los detalles
     * @throws IOException Una excepcion por si no se encuentra el articulo
     */
    private void ventanaDetallesCajero(Articulo articulo) throws IOException {
        Stage ventana = new Stage();
        ventana.setMaximized(false);
        ventana.setResizable(false);
        Image icon = new Image(String.valueOf(App.class.getResource("/img/articulos.png")));
        ventana.getIcons().add(icon);
        ventana.setIconified(true);
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/VerDetallesArticuloCajero.fxml"));
        Pane panelDetalles = loader.load();
        Scene escena = new Scene(panelDetalles);
        ventana.setTitle("Detalles articulo");
        VerDetallesArticuloCajeroController verDetallesArticulo = loader.getController();
        verDetallesArticulo.setArticuloDetalles(articulo);
        verDetallesArticulo.setVentana(ventana);
        ventana.setScene(escena);
        ventana.show();
    }

    /**
     * Muestra la ventana de detalles de un articulo
     *
     * @param articulo El articulo a ver los detalles
     * @throws IOException Una excepcion por si no se encuentra el articulo
     */
    private void ventanaDetallesAdministrador(Articulo articulo) throws IOException {
        Stage ventana = new Stage();
        ventana.setMaximized(false);
        ventana.setResizable(false);
        Image icon = new Image(String.valueOf(App.class.getResource("/img/articulos.png")));
        ventana.getIcons().add(icon);
        ventana.setIconified(true);
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/VerDetallesArticuloAdministrador.fxml"));
        Pane panelDetalles = loader.load();
        Scene escena = new Scene(panelDetalles);
        ventana.setTitle("Detalles articulo");
        VerDetallesArticuloAdministradorController verDetallesArticulo = loader.getController();
        verDetallesArticulo.setArticulo(articulo);
        verDetallesArticulo.setVentana(ventana);
        ventana.setScene(escena);
        ventana.show();
    }

    /**
     * Recupera el articulo seleccionado y muestra la ventana de detalles Articulo
     */
    @FXML
    public void mostrarVentanaDetalles() {
        try {
            Articulo articuloSeleccionado = tArticulos.getSelectionModel().getSelectedItem();
            if (usuario.getTipo().equals("administrador")) {
                ventanaDetallesAdministrador(articuloSeleccionado);
            } else {
                ventanaDetallesCajero(articuloSeleccionado);
            }
        } catch (IOException ex) {
            System.out.println("ListarArticulosController-IOException : mostrarVentanaDetalles ");
            ex.printStackTrace();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        ventanaPrincipal.mostrarInformacionPrincipal(usuario, lUsuario, lFecha);
        this.usuario = usuario;
    }

    public void setVentanaPrincipal(App ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }
}
