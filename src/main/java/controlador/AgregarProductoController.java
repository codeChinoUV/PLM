package controlador;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Paint;
import modelo.Articulo;
import modelo.persistencia.Articulos;
import modelo.persistencia.ArticulosDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import static controlador.util.VerificacionDeTexto.*;

public class AgregarProductoController implements Initializable {
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

  /**
   * Crea un objeto de tipo Articulo a partir de la informacion de los campos de la escena
   * @return Un objeto Articulo
   */
  private Articulo recuperarArticuloDeInformacionDeCampos(){
    Articulo articulo = new Articulo();
    articulo.setCodigoBarras(tfCodigoBarras.getText());
    articulo.setNombre(tfDescripcion.getText());
    articulo.setPrecioVenta(Double.parseDouble(tfPrecioVenta.getText()));
    articulo.setPrecioMayoreo(Double.parseDouble(tfPrecioMayoreo.getText()));
    articulo.setPrecioCompra(Double.parseDouble(tfPrecioCompra.getText()));
    articulo.setCantidad(Integer.parseInt(tfCantidad.getText()));
    articulo.setGanancia(Double.parseDouble(tfPorcentajeGanancia.getText()));
    articulo.setGananciaMayoreo(Double.parseDouble(tfPorcentajeGananciaMayoreo.getText()));
    articulo.setTipoUnidad(tfTipoUnidad.getText());
    articulo.setPiezasParaMayoreo(Integer.parseInt(tfPzasMayoreo.getText()));
    return articulo;
  }

  /**
   * Calcula el precio porcentaje de ganancia con base en el precio de compra y en el precio de venta
   * @param precioCompra El precio de compra del articulo
   * @param precioVenta El precio de venta del articulo
   * @return El porcentaje de ganancia
   */
  private double calcularPorcentajeGanancia(double precioCompra, double precioVenta){
    return ((precioVenta * 100)/precioCompra)-100;
  }

  /**
   * Coloca el porcentaje de ganancia del articulo
   */
  public void colocarPorcentajeGanancia(){
    colocarPorcentajes(tfPrecioVenta, tfPorcentajeGanancia);
  }

  /**
   * Calcula el porcentaje de ganancia a partir de la informacion de los JFXTextField
   * @param tfPrecioVenta El JFXTextField que contiene el precio de venta
   * @param tfPorcentajeGanancia El JFXTextField que contiene el porcentaje de ganancia
   */
  private void colocarPorcentajes(JFXTextField tfPrecioVenta, JFXTextField tfPorcentajeGanancia) {
    if(esUnNumeroConDecminasValido(tfPrecioCompra.getText())
        && esUnNumeroConDecminasValido(tfPrecioVenta.getText())){
      double precioCompra = Double.parseDouble(tfPrecioCompra.getText());
      double precioVenta = Double.parseDouble(tfPrecioVenta.getText());
      double porcentajeGanancia = calcularPorcentajeGanancia(precioCompra,precioVenta);
      DecimalFormat porcentajeDeDosDecimas = new DecimalFormat("#.##");
      tfPorcentajeGanancia.setText(porcentajeDeDosDecimas.format(porcentajeGanancia));
    }
  }

  /**
   * Coloca el precio de venta a partir del porcentaje calculado
   */
  public void colocarPrecioVenta(){
    colocarPrecio(tfPorcentajeGanancia, tfPrecioVenta);
  }

  /**
   * Coloca el precio de mayoreo
   */
  public void colocarPrecioMayoreo(){
    colocarPrecio(tfPorcentajeGananciaMayoreo, tfPrecioMayoreo);
  }

  /**
   * Coloca el precio de mayoreo
   * @param tfPorcentajeGanancia El JFXTextField del porcentaje de ganancia
   * @param tfPrecioMayoreo El JFXTextField del precio de mayoreo
   */
  private void colocarPrecio(JFXTextField tfPorcentajeGanancia, JFXTextField tfPrecioMayoreo) {
    if(esUnNumeroConDecminasValido(tfPrecioCompra.getText()) &&
        esUnNumeroConDecminasValido(tfPorcentajeGanancia.getText())){
      double precioCompra = Double.parseDouble(tfPrecioCompra.getText());
      double porcentajeGanancia = Double.parseDouble(tfPorcentajeGanancia.getText());
      double precioMayoreo = calcularPrecioAPartirDePorcentaje(precioCompra,porcentajeGanancia);
      DecimalFormat precioConDosDecimas = new DecimalFormat("#.##");
      tfPrecioMayoreo.setText(precioConDosDecimas.format(precioMayoreo));
    }
  }

  /**
   * Regresa el precio del producto a partir del precio de compra y el porcentaje
   * @param precioCompra El precio de compra que se utilizara para calcular el precio
   * @param porcentaje El porcentaje que se utilizara para calcular el precio
   * @return El precio con el porcentaje
   */
  public double calcularPrecioAPartirDePorcentaje(double precioCompra, double porcentaje){
    return (precioCompra * (porcentaje/100)) + precioCompra;
  }

  /**
   * Coloca el porcentaje de ganancia de precio de mayoreo del articulo
   */
  public void colocarPorcentajeGananciaMayoreo(){
    colocarPorcentajes(tfPrecioMayoreo, tfPorcentajeGananciaMayoreo);
  }

  /**
   * Coloca el color del campo en rojo para indicar que no es valido
   * @param campoInvalido El campo invalido que se coloreara
   */
  private void colorearCampoInvalido(JFXTextField campoInvalido){
    final String colorAColocar = "red";
    campoInvalido.setUnFocusColor(Paint.valueOf(colorAColocar));
  }

  /**
   * Coloca el colot del campo en el original
   * @param campoAColorear El campo que se coloreara
   */
  private void colorearCampoNormal(JFXTextField campoAColorear){
    final String colorAColocar = "#fbb748";
    campoAColorear.setUnFocusColor(Paint.valueOf(colorAColocar));
  }

  /**
   * Coloca del color normal a todos los campos del formulario
   */
  private void colorearTodosLosCamposANormal(){
    colorearCampoNormal(tfCodigoBarras);
    colorearCampoNormal(tfDescripcion);
    colorearCampoNormal(tfPrecioCompra);
    colorearCampoNormal(tfPrecioVenta);
    colorearCampoNormal(tfPrecioMayoreo);
    colorearCampoNormal(tfCantidad);
    colorearCampoNormal(tfPorcentajeGanancia);
    colorearCampoNormal(tfPorcentajeGananciaMayoreo);
    colorearCampoNormal(tfPzasMayoreo);
    colorearCampoNormal(tfTipoUnidad);
  }


  /**
   * Verifica que la información de cada campo se valida, si no lo es colorea de rojo el campo
   * @return Verdero su todos los campos son validos o falso si no
   */
  private boolean losCamposSonValidos(){
    boolean losCamposSonValidos = true;
    if(!esUnCodigoDeBarrasDeArticuloValido(tfCodigoBarras.getText())){
      losCamposSonValidos = false;
      colorearCampoInvalido(tfCodigoBarras);
    }
    if(!esUnaDescripcionDeArticuloValida(tfDescripcion.getText())){
      losCamposSonValidos = false;
      colorearCampoInvalido(tfDescripcion);
    }
    if(!esUnNumeroConDecminasValido(tfPrecioCompra.getText())){
      losCamposSonValidos = false;
      colorearCampoInvalido(tfPrecioCompra);
    }
    if(!esUnNumeroConDecminasValido(tfPrecioVenta.getText())){
      losCamposSonValidos = false;
      colorearCampoInvalido(tfPrecioVenta);
    }
    if(!esUnNumeroConDecminasValido(tfPrecioMayoreo.getText())){
      losCamposSonValidos = false;
      colorearCampoInvalido(tfPrecioMayoreo);
    }
    if(!esUnNumeroValido(tfCantidad.getText())){
      losCamposSonValidos = false;
      colorearCampoInvalido(tfCantidad);
    }
    if(!esUnNumeroConDecminasValido(tfPorcentajeGanancia.getText())){
      losCamposSonValidos = false;
      colorearCampoInvalido(tfPorcentajeGanancia);
    }
    if(!esUnNumeroConDecminasValido(tfPorcentajeGananciaMayoreo.getText())){
      losCamposSonValidos = false;
      colorearCampoInvalido(tfPorcentajeGananciaMayoreo);
    }
    if(!esUnaUnidadDeArticuloValida(tfTipoUnidad.getText())){
      losCamposSonValidos = false;
      colorearCampoInvalido(tfTipoUnidad);
    }
    if(!esUnNumeroValido(tfPzasMayoreo.getText())){
      losCamposSonValidos = false;
      colorearCampoInvalido(tfPzasMayoreo);
    }
    return losCamposSonValidos;
  }

  /**
   * Guarda un articulo con base de lo que se encuentra en los campos
   */
  public void agregarArticulo(){
    colorearTodosLosCamposANormal();
    if(losCamposSonValidos()){
      Articulo articuloARegistrar = recuperarArticuloDeInformacionDeCampos();
      ArticulosDAO persistenciaArticulos = new Articulos();
      try{
        persistenciaArticulos.nuevoArticulo(articuloARegistrar);
      }catch (SQLException | IOException | ClassNotFoundException excepcionAlRegistrar){
        VentanasEmergentes.mostrarVentanaError("Sucedio un error al guardar el articulo");
      }
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}
