package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Articulo;
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
  private TableColumn<Articulo,Integer> tcCodigo;
  @FXML
  private TableColumn<Articulo,String> tcCodigoBarras;
  @FXML
  private TableColumn<Articulo,String> tcDescripcion;
  @FXML
  private TableColumn<Articulo,Double> tcPrecioPublico;
  @FXML
  private TableColumn<Articulo,Double> tcPrecioMayoreo;
  @FXML
  private TableColumn<Articulo,Integer> tcCantidad;
  @FXML
  private TableColumn<Articulo,String> tcUnidad;
  @FXML
  private TableColumn<Articulo,Integer> tcPzasMayoreo;
  @FXML
  private Label lUsuario;
  @FXML
  private Label lFecha;

  private Programa manejadorDeVentanas;

  private void llenarTablaArticulos(List<Articulo> articulos){
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
   * @param listaDeArticulos La List del Articulo que se convertira
   * @return Una ObservableList de Articulo
   */
  private ObservableList<Articulo> convertirAArticuloObservableList(List<Articulo> listaDeArticulos){
    ObservableList<Articulo> observableListDeArticulos = FXCollections.observableArrayList();
    observableListDeArticulos.addAll(listaDeArticulos);
    return observableListDeArticulos;
  }

  /**
   * Recupera una lista de los Articulo disponibles
   * @return Una List de Articulo
   */
  private List<Articulo> obtenerArticulosDisponibles(){
    ArticulosDAO articulosPersistencia = new Articulos();
    List<Articulo> articulosDisponibles = new ArrayList<>();
    try{
      articulosDisponibles = articulosPersistencia.getArticulosNoEliminados();
    }catch (SQLException | IOException | ClassNotFoundException excepcionSQL){
      VentanasEmergentes.mostrarVentanaError("Ocurrio un error al recuperar los articulos");
      excepcionSQL.printStackTrace();
    }
    return articulosDisponibles;
  }


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    llenarTablaArticulos(obtenerArticulosDisponibles());
    colocarFechaActual();
  }

  public void setManejadorDeVentanas(Programa manejadorDeVentanas){
    this.manejadorDeVentanas = manejadorDeVentanas;
    colocarNombreUsuario();
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
}
