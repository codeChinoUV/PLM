package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.Articulo;
import modelo.Usuario;
import modelo.persistencia.Articulos;
import modelo.persistencia.ArticulosDAO;
import principal.Programa;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AdministrarInventarioController implements Initializable {
  @FXML
  private TableView<Articulo> tProductos;
  @FXML
  private TableColumn<Articulo, Integer> tcCodigo;
  @FXML
  private TableColumn<Articulo, String> tcCodigoBarras;
  @FXML
  private TableColumn<Articulo, String> tcDescripcion;
  @FXML
  private TableColumn<Articulo, Double> tcPrecioPublico;
  @FXML
  private TableColumn<Articulo, Double> tcPrecioMayoreo;
  @FXML
  private TableColumn<Articulo, Integer> tcCantidad;
  @FXML
  private TableColumn<Articulo, String> tcUnidad;
  @FXML
  private TableColumn<Articulo, Integer> tcPzasMayoreo;
  @FXML
  private Label lUsuario;
  @FXML
  private Label lFecha;
  @FXML
  private JFXButton bRegistrarProducto;
  @FXML
  private JFXButton bEditarProducto;
  @FXML
  private JFXTextField tfBuscar;

  private Programa manejadorDeVentanas;

  /**
   * Llena la table view tProductos con los articulos que se le pasen como parametro
   *
   * @param articulos La lista de Articulo con el que se llenara la tabla
   */
  private void llenarTablaArticulos(List<Articulo> articulos) {
    tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    tcCodigoBarras.setCellValueFactory(new PropertyValueFactory<>("codigoBarras"));
    tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    tcPrecioMayoreo.setCellValueFactory(new PropertyValueFactory<>("precioMayoreo"));
    tcPrecioPublico.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
    tcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    tcUnidad.setCellValueFactory(new PropertyValueFactory<>("tipoUnidad"));
    tcPzasMayoreo.setCellValueFactory(new PropertyValueFactory<>("piezasParaMayoreo"));
    ObservableList<Articulo> articulosDisponibles = convertirAArticuloObservableList(articulos);
    tProductos.setItems(articulosDisponibles);
  }

  /**
   * Convierte una lista de Articulo a un ObservableList de Articulo
   *
   * @param listaDeArticulos La List del Articulo que se convertira
   * @return Una ObservableList de Articulo
   */
  private ObservableList<Articulo> convertirAArticuloObservableList(List<Articulo> listaDeArticulos) {
    ObservableList<Articulo> observableListDeArticulos = FXCollections.observableArrayList();
    observableListDeArticulos.addAll(listaDeArticulos);
    return observableListDeArticulos;
  }

  /**
   * Recupera una lista de los Articulo disponibles
   *
   * @return Una List de Articulo
   */
  private List<Articulo> obtenerArticulosDisponibles() {
    ArticulosDAO articulosPersistencia = new Articulos();
    List<Articulo> articulosDisponibles = new ArrayList<>();
    try {
      articulosDisponibles = articulosPersistencia.getArticulosNoEliminados();
    } catch (SQLException | IOException | ClassNotFoundException excepcionSQL) {
      VentanasEmergentes.mostrarVentanaError("Ocurrio un error al recuperar los articulos");
      excepcionSQL.printStackTrace();
    }
    return articulosDisponibles;
  }

  /**
   * Oculta las opciones dependiendo del tipo de usuario que se encuentre logeado
   *
   * @param usuarioLogeado El usuario del cual se obtendra el tipo
   */
  private void mostrarOpcionesParaUsuario(Usuario usuarioLogeado) {
    switch (usuarioLogeado.getTipo()) {
      case Administrador:
      case Dueño:
      case Gerente:
        bEditarProducto.setVisible(true);
        bRegistrarProducto.setVisible(true);
        break;
      case Cajero:
        bRegistrarProducto.setVisible(false);
        bEditarProducto.setVisible(false);
        break;
    }
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
   * Coloca la fecha actual en el label lFecha
   */
  private void colocarFechaActual(){
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    Date date = new Date();
    String fechaActual = sdf.format(date);
    lFecha.setText(fechaActual);
  }

  /**
   * Muestra una ventana con la escena agregar_producto
   */
  public void mostrarVentanaRegistrarProducto(){
    FXMLLoader loader = new FXMLLoader(Programa.class.getResource("/vistas/agregar_producto.fxml"));
    try{
      Pane panelAMostrar = loader.load();
      Scene escenaAMostrar = new Scene(panelAMostrar);
      Stage ventanaAgregarProducto = new Stage();
      ventanaAgregarProducto.setScene(escenaAMostrar);
      ventanaAgregarProducto.setTitle("PLM - Registrar producto");
      ventanaAgregarProducto.showAndWait();
    }catch (IOException excepcionAlCargarVentana){
      VentanasEmergentes.mostrarVentanaError(excepcionAlCargarVentana.getMessage());
      excepcionAlCargarVentana.printStackTrace();
    }

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    llenarTablaArticulos(obtenerArticulosDisponibles());
    colocarFechaActual();
  }

  public void setManejadorDeVentanas(Programa manejadorDeVentanas){
    this.manejadorDeVentanas = manejadorDeVentanas;
    colocarNombreUsuario();
    mostrarOpcionesParaUsuario(manejadorDeVentanas.getUsuarioLogeado());
  }
}
