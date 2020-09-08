package org.chinosoft.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import java.net.URL;
import java.util.ResourceBundle;
import org.chinosoft.App;
import org.chinosoft.modelo.Articulo;
import org.chinosoft.modelo.Usuario;
import org.chinosoft.modelo.persistencia.Articulos;
import org.chinosoft.modelo.persistencia.ArticulosDAO;

import javax.swing.*;

public class AgregarArticuloController implements Initializable {

    App ventanaPrincipal;

    Usuario usuarioLogeado;

    @FXML
    private Label lUsuario;

    @FXML
    private Label lFecha;

    @FXML
    private JFXTextField tfCantidad;

    @FXML
    private JFXTextField tfCodigo;

    @FXML
    private JFXTextField tfCodigoBarras;

    @FXML
    private JFXTextField tfNombre;

    @FXML
    private JFXTextField tfUnidad;

    @FXML
    private JFXTextField tfPrecioCompra;

    @FXML
    private JFXTextField tfPrecioVenta;

    @FXML
    private JFXTextField tfPrecioMayoreo;

    @FXML
    private JFXTextField tfPzasMayoreo;

    @FXML
    private JFXTextField tfGananciaPublico;

    @FXML
    private JFXTextField tfGananciaMayoreo;

    /**
     * Valida que la cadena recibida solo sea una cadena de numeros
     * @param codigoBarras La cadena a validar
     * @return Verdadero si la cadena es valida o falso si no
     */
    private boolean validarCodigoBarras(String codigoBarras){
        char[] numerosPermitidos = {'1','2','3','4','5','6','7','8','9','0'};
        if(!codigoBarras.equals("")){
            for(char caracter : codigoBarras.toCharArray()){
                boolean bandera = false;
                for (char numeroPermitido: numerosPermitidos){
                    if(!bandera){
                        if(numeroPermitido == caracter){
                            bandera = true;
                        }
                    }
                }
                if (!bandera){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Valida si la cadena recibida sea una cadena contenga caracteres de tipo double
     * @param textoValidar La cadeba a verificar
     * @return Verdaro si la cadena es valida o falso si no
     */
    private boolean validarDouble(String textoValidar){
        try{
            Double.parseDouble(textoValidar);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    /**
     * Valida si la cadena recibida se puede convertir a entero
     * @param textoValidar El texto a validar
     * @return Verdadero si el texto se puede convertir a entero
     */
    private boolean validarInt(String textoValidar){
        try{
            Integer.parseInt(textoValidar);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }

  /**
   * Valida que la cadena no este vacia
   * @param textoValidar El texto a validar
   * @return Verdadero si la cadena no es vacia, falso en lo contrario
   */
  private boolean validarTextoNoVacio(String textoValidar){
      return !textoValidar.equals("");
    }

  /**
   * Recupera los datos del articulo de los textfield y crea un objeto del tipo articulo
   * @return El articulo creado
   */
  private Articulo recuperarArticuloDeDatos(){
      int codigo = Integer.parseInt(tfCodigo.getText());
      String nombre = tfNombre.getText();
      String codigoBarras = tfCodigoBarras.getText();
      int cantidad = Integer.parseInt(tfCantidad.getText());
      double precioCompra = Double.parseDouble(tfPrecioCompra.getText());
      double precioVenta = Double.parseDouble(tfPrecioVenta.getText());
      double precioMayoreo = Double.parseDouble(tfPrecioMayoreo.getText());
      double gananciaVenta = Double.parseDouble(tfGananciaPublico.getText());
      double gananciaMayoreo = Double.parseDouble(tfGananciaPublico.getText());
      String unidad = tfUnidad.getText();
      int pzasMayoreo = Integer.parseInt(tfPzasMayoreo.getText());
      return new Articulo(codigo, codigoBarras, nombre, cantidad, pzasMayoreo, gananciaMayoreo, gananciaVenta,
          precioVenta, precioMayoreo, precioCompra, true, unidad);
    }

  /**
   * Cambia el color del bordel del JFXTextField que se le pase como parametro
   * @param textField El TextField al que se le cambiara el color
   * @param color El color que se le asignara al TextField
   */
    private void cambiarBordeDeTextField(JFXTextField textField, String color){
      Paint colorSinEnfocar = Paint.valueOf(color);
      textField.setUnFocusColor(colorSinEnfocar);
    }

  /**
   * Verifica que los datos sean del tipo adecuado al que deberian ser
   * @param codigo El codigo del articulo
   * @param codigoBarras El codigo de barras del articulo
   * @param nombre El nombre del articulo
   * @param cantidad La cantidad del articulo
   * @param pzasMayoreo Las piezas para aplicar mayoreo del articulo
   * @param gananciaMayoreo La ganancia de mayoreo del articulo
   * @param gananciaVenta La ganancia de venta del articulo
   * @param precioVenta El precio de venta del articulo
   * @param precioMayoreo El precio de mayoreo del articulo
   * @param precioCompra El precio de compra del articulo
   * @param unidad El tipo de unidad de compra del articulo
   * @return Verdadero si todos los parametros pertenecen a su tipo de dato o falso si no
   */
    private boolean verificarDatosCorrectos(String codigo, String codigoBarras, String nombre, String cantidad,
                                            String pzasMayoreo, String gananciaMayoreo, String gananciaVenta,
                                            String precioVenta, String precioMayoreo, String precioCompra,
                                            String unidad){
      if(validarInt(codigo) && validarCodigoBarras(codigoBarras) && validarTextoNoVacio(nombre) && validarInt(cantidad)
          && validarInt(pzasMayoreo) && validarDouble(gananciaMayoreo) && validarDouble(gananciaVenta)
          && validarDouble(precioVenta) && validarDouble(precioMayoreo) && validarDouble(precioCompra)
          && validarTextoNoVacio(unidad)){
        return true;
      }else{
            if(validarInt(codigo)){
                cambiarBordeDeTextField(tfCodigo, "#00b300");
            }else{
                cambiarBordeDeTextField(tfCodigo, "#ff0000");
            }
            if(validarCodigoBarras(codigoBarras)){
                cambiarBordeDeTextField(tfCodigoBarras, "#00b300");
            }else{
                cambiarBordeDeTextField(tfCodigoBarras, "#ff0000");
            }
            if(validarTextoNoVacio(nombre)){
                cambiarBordeDeTextField(tfNombre, "#00b300");
            }else{
                cambiarBordeDeTextField(tfNombre, "#ff0000");
            }
            if(validarInt(cantidad)){
                cambiarBordeDeTextField(tfCantidad, "#00b300");
            }else{
                cambiarBordeDeTextField(tfCantidad, "#ff0000");
            }
            if(validarInt(pzasMayoreo)){
                cambiarBordeDeTextField(tfPzasMayoreo, "#00b300");
            }else{
                cambiarBordeDeTextField(tfPzasMayoreo, "#ff0000");
            }
            if(validarDouble(gananciaMayoreo)){
                cambiarBordeDeTextField(tfGananciaMayoreo, "#00b300");
            }else{
                cambiarBordeDeTextField(tfGananciaMayoreo, "#ff0000");
            }
            if(validarDouble(gananciaVenta)){
                cambiarBordeDeTextField(tfGananciaPublico, "#00b300");
            }else{
                cambiarBordeDeTextField(tfGananciaPublico, "#ff0000");
            }
            if(validarDouble(precioVenta)){
                cambiarBordeDeTextField(tfPrecioVenta, "#00b300");
            }else{
                cambiarBordeDeTextField(tfPrecioVenta, "#ff0000");
            }
            if(validarDouble(precioCompra)){
                cambiarBordeDeTextField(tfPrecioCompra, "#00b300");
            }else{
                cambiarBordeDeTextField(tfPrecioCompra, "#ff0000");
            }
            if(validarDouble(precioMayoreo)){
                cambiarBordeDeTextField(tfPrecioMayoreo, "#00b300");
            }else{
                cambiarBordeDeTextField(tfPrecioMayoreo, "#ff0000");
            }
            if(validarTextoNoVacio(unidad)){
                cambiarBordeDeTextField(tfUnidad, "#00b300");
            }else{
                cambiarBordeDeTextField(tfUnidad, "#ff0000");
            }
        return false;
      }
    }

    /**
     * Guarda en la base de datos un nuevo articulo con la información de los campos
     */
    @FXML
    public void guardarArticulo(){
        String codigo = tfCodigo.getText();
        String codigoBarras = tfCodigoBarras.getText();
        String nombre = tfNombre.getText();
        String cantidad = tfCantidad.getText();
        String precioCompra = tfPrecioCompra.getText();
        String precioVenta = tfPrecioVenta.getText();
        String precioMayoreo = tfPrecioMayoreo.getText();
        String gananciaPublico = tfGananciaPublico.getText();
        String gananciaMayoreo = tfGananciaMayoreo.getText();
        String pzasMayoreo = tfPzasMayoreo.getText();
        String unidad = tfUnidad.getText();
        if(verificarDatosCorrectos(codigo,codigoBarras,nombre,cantidad,pzasMayoreo,gananciaMayoreo,gananciaPublico,
                precioVenta, precioMayoreo, precioCompra, unidad)){
            Articulo articuloGuardar = recuperarArticuloDeDatos();
            ArticulosDAO persistenciaArticulos = new Articulos();
            int seGuardo = persistenciaArticulos.nuevoArticulo(articuloGuardar);
            if(seGuardo > 0){
                JOptionPane.showMessageDialog(null, "El articulo se guardo correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar el articulo");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Calcula el precio de venta a partir del precio de compra y el porcentaje de ganancia publico
     */
    public void colocarPrecioPublico(){
        calcularPrecios(tfGananciaPublico, tfPrecioVenta);
    }

    /**
     * Coloca el precio de mayoreo a partir del precio de compra y el porcentaje de ganancia de mayoreo
     */
    public void colocarPrecioMayoreo(){
        calcularPrecios(tfGananciaMayoreo, tfPrecioMayoreo);
    }

    /**
     * Calcula el precio de venta a partir del precio de compra y de un porcentaje de ganancia
     * @param porcentajeGanancia el campo donde contiene el porcentaje de ganancia
     * @param campoAColocarPrecio el campo en donde se coloca el precio
     */
    private void calcularPrecios(JFXTextField porcentajeGanancia, JFXTextField campoAColocarPrecio) {
        if(validarDouble(tfPrecioCompra.getText()) && validarDouble(porcentajeGanancia.getText())){
            double precioCompra = Double.parseDouble(tfPrecioCompra.getText());
            double porcentajeGananciaMayoreo = Double.parseDouble(porcentajeGanancia.getText());
            double precioMayoreo = (precioCompra  * (porcentajeGananciaMayoreo / 100)) + precioCompra;
            campoAColocarPrecio.setText(String.valueOf(precioMayoreo));
        }else{
            campoAColocarPrecio.setText("0");
        }
    }

    /**
     * Colca el porcentaje de ganancia del precio publico a partir del precio de compra y el precio de venta
     */
    public void colocarGananciaPublico(){
        calcularPorcentajeDeGanancia(tfPrecioVenta, tfGananciaPublico);
    }

    /**
     * Coloca el porcentaje de ganancia del precio mayoreo a partir del precio de compra y el precio de mayoreo
     */
    public void colocarGananciaMayoreo(){
        calcularPorcentajeDeGanancia(tfPrecioMayoreo, tfGananciaMayoreo);
    }

    /**
     * Calcula el porcentaje de ganancia a partir del precio y del precioCompra
     * @param campoDelPrecio el campor que contiene el precio
     * @param campoAColocarPorcentaje el campo en donde se colocara el porcentaje
     */
    private void calcularPorcentajeDeGanancia(JFXTextField campoDelPrecio, JFXTextField campoAColocarPorcentaje){
        if(validarDouble(tfPrecioCompra.getText()) && validarDouble(campoDelPrecio.getText())){
            double precioCompra = Double.parseDouble(tfPrecioCompra.getText());
            double precio = Double.parseDouble(campoDelPrecio.getText());
            double porcentajeGanancia = ((precio - precioCompra) / precioCompra) * 100;
            campoAColocarPorcentaje.setText(String.valueOf(porcentajeGanancia));
        }else{
            campoAColocarPorcentaje.setText("0");
        }
    }

    public App getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public void setVentanaPrincipal(App ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }
}
