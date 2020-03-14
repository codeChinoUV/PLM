package modelo.persistencia;

import controlador.observable.IObservable;
import controlador.observable.IObserver;
import modelo.Articulo;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Articulos implements ArticulosDAO, IObservable {

  private ArrayList<IObserver> observers;

  public Articulos(){
    observers = new ArrayList<>();
  }

  /**
   * Recupera la información de un Articulo a partir de la información de un ResultSet
   * @param resultadosDeConsulta El ResultSet que contiene los resultados de la consulta
   * @return Un objeto Articulo con la información del ResultSet
   * @throws SQLException Una excepcion que se produce al no encontrarse los campos solicitados
   */
  private Articulo recuperarArticulo(ResultSet resultadosDeConsulta) throws SQLException {
    Articulo articulo = null;
    if(resultadosDeConsulta != null){
      int codigo = resultadosDeConsulta.getInt("codigo");
      String codigoBarras= resultadosDeConsulta.getString("codigo_barras");
      String nombre = resultadosDeConsulta.getString("nombre");
      int cantidad = resultadosDeConsulta.getInt("cantidad");
      int piezasMayoreo = resultadosDeConsulta.getInt("piezas_mayoreo");
      double gananciaMayoreo = resultadosDeConsulta.getDouble("ganancia_mayoreo");
      double gananciaPublico = resultadosDeConsulta.getDouble("ganancia");
      double precioVenta = resultadosDeConsulta.getDouble("precio_venta");
      double precioMayoreo = resultadosDeConsulta.getDouble("precio_mayoreo");
      double precioCompra = resultadosDeConsulta.getDouble("precio_compra");
      boolean activo = resultadosDeConsulta.getBoolean("estado_borrado");
      String tipoUnidad = resultadosDeConsulta.getString("tipo_unidad");
      articulo = new Articulo(codigo, codigoBarras, nombre, cantidad, piezasMayoreo, gananciaMayoreo,
          gananciaPublico, precioVenta, precioMayoreo, precioCompra, activo, tipoUnidad);
    }
    return articulo;
  }

  /**
   * Recupera todos los articulos disponible
   * @return Una List de Articulos con todos los articulos disponibles
   * @throws SQLException Una excepcion que indica que ocurrio un error al ejecutar la consulta en la base de datos
   * @throws IOException Una excepcion que indica que ocurrio un error al leer el archivo de configuracion de la bd
   * @throws ClassNotFoundException Una excepcion que indica que no se encontro la libreria que permite la conexion con
   * la bd
   */
  public List<Articulo> getArticulosNoEliminados() throws SQLException, IOException, ClassNotFoundException {
    String CONSULTA_TODOS_LOS_ARTICULOS = "SELECT * FROM articulo WHERE estado_borrado=false";
    List<Articulo> articulos = new ArrayList<>();
    Connection conexion = Conexion.getConexion();
    PreparedStatement consulta = conexion.prepareStatement(CONSULTA_TODOS_LOS_ARTICULOS);
    ResultSet resultados = consulta.executeQuery();
    while (resultados.next()){
      articulos.add(recuperarArticulo(resultados));
    }
    conexion.close();
    return articulos;
  }

  /**
   *
   * @param descripcion El descripcion por el cual se buscara el articulo
   * @return Una lista de articulos que coincida con el descripcion de busqueda
   * @throws SQLException Una excepcion que indica que ocurrio un error al ejecutar la consulta
   * @throws IOException Una excepcion que indica que ocurrio un error al leer el archivo de configuracion de la BD
   * @throws ClassNotFoundException una excepcion que indica que ocurrio un error al buscar la libreria que permite la
   * conexion a la BD
   */
  public List<Articulo> buscarArticulos(String descripcion) throws SQLException, IOException, ClassNotFoundException{
    String CONSULTA_BUSCAR_ARTICULOS = "SELECT * FROM articulo WHERE estado_borrado = false AND  nombre LIKE ? ;";
    List<Articulo> articulos = new ArrayList<>();
    Connection conexion = Conexion.getConexion();
    PreparedStatement consulta = conexion.prepareStatement(CONSULTA_BUSCAR_ARTICULOS);
    consulta.setString(1,descripcion);
    ResultSet resultados;
    resultados = consulta.executeQuery();
    while (resultados.next()){
      articulos.add(recuperarArticulo(resultados));
    }
    conexion.close();
    return articulos;
  }

  /**
   * Registra un nuevo articulo en la base de datos
   * @param articulo El articulo a registrar
   * @return Verdadero si el articulo se registro correctamente o falso si no
   * @throws SQLException Una excepcion que indica que ocurrio un error al realizar la consulta
   * @throws IOException Una excepcion que indica que ocurrio un error al leer el archivo de configuración
   * @throws ClassNotFoundException Una excepcion que indica que no se encontro la libreria de conexion a la BD
   */
  public int nuevoArticulo(Articulo articulo) throws SQLException, IOException, ClassNotFoundException{
    int seRegistro = 0;
    if (articulo != null) {
      Connection conexion = Conexion.getConexion();
      PreparedStatement consulta = prepararInsertarNuevoArticulo(articulo, conexion);
      seRegistro = consulta.executeUpdate();
    }
    notificar();
    return seRegistro;
  }

  /**
   * Prepara la consulta para registrar un producto
   * @param articuloARegistrar El Articulo que se registrara
   * @param conexion La conexion con la cual se realizara la consulta
   * @return Un PreparedStatement con la consulta
   * @throws SQLException Una excepcion que indica que ocurrio un error al preparar la consulta
   */
  private PreparedStatement prepararInsertarNuevoArticulo(Articulo articuloARegistrar, Connection conexion)
      throws SQLException{
    String AGREGAR_ARTICULO = "INSERT INTO articulo(codigo_barras, nombre, cantidad, piezas_mayoreo, " +
        "ganancia_mayoreo, ganancia, precio_venta, precio_mayoreo, precio_compra, estado_borrado, tipo_unidad) " +
        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    return prepararConsultaArticulo(articuloARegistrar, conexion, AGREGAR_ARTICULO);
  }

  /**
   * Edita el articulo que tenga el codigo
   * @param codigo El codigo del articulo que se va a editar
   * @param articulo La información del articulo que se le asignara
   * @return La cantidad de registros que se modificaron
   * @throws SQLException Una excepcion que indica que ocurrio un error al ejecutar la consulta
   * @throws IOException Una excepcion que indica que ocurrio un error al leer el archivo de configuración
   * @throws ClassNotFoundException Una excepcion que indica que no se encontro la libreria de conexion a la BD
   */
  public int editarArticulo(int codigo, Articulo articulo) throws SQLException, IOException, ClassNotFoundException{
    int seEdito = 0;
    if (codigo < 0 && articulo != null){
      Connection conexion = Conexion.getConexion();
      PreparedStatement consulta = prepararEditarArticulo(codigo, articulo, conexion);
      seEdito = consulta.executeUpdate();

    }
    notificar();
    return seEdito;
  }

  /**
   * Prepara la consulta para editar el articulo con el codigo
   * @param articulo El Articulo que se editara
   * @param codigo El codigo del articulo a editar
   * @param conexion La conexion con la cual se realizara la consulta
   * @return Un PreparedStatement con la consulta
   * @throws SQLException Una excepcion que indica que ocurrio un error al preparar la consulta
   */
  private PreparedStatement prepararEditarArticulo(int codigo ,Articulo articulo, Connection conexion)
      throws SQLException{
    String EDITAR_ARTICULO = "UPDATE articulo SET codigo_barras= ?, nombre= ?, cantidad= ?, piezas_mayoreo= ?, " +
        "ganancia_mayoreo= ?, ganancia= ?, precio_venta= ?, precio_mayoreo= ?, precio_compra= ?, estado_borrado= ?," +
        " tipo_unidad= ? WHERE codigo= ?;";
    prepararConsultaArticulo(articulo, conexion, EDITAR_ARTICULO);
    PreparedStatement consulta = prepararConsultaArticulo(articulo, conexion, EDITAR_ARTICULO);
    consulta.setInt(12,codigo);
    return consulta;
  }

  /**
   * Prepara una consulta general sobre un articulo
   * @param articulo El articulo de donde se obtendran los datos
   * @param conexion La conexion en donde se prepara el statement
   * @param CONSULTA La consulta que se preparara
   * @return La consulta preparada
   * @throws SQLException Una excepcion que indica que ocurrio un error preparando la consulta
   */
  private PreparedStatement prepararConsultaArticulo(Articulo articulo, Connection conexion, String CONSULTA) throws SQLException {
    PreparedStatement consulta = conexion.prepareStatement(CONSULTA);
    if(!articulo.getCodigoBarras().equals("") ){
      consulta.setString(1,articulo.getCodigoBarras());
    }else{
      consulta.setString(1,null);
    }
    consulta.setString(2,articulo.getNombre());
    consulta.setInt(3,articulo.getCantidad());
    consulta.setInt(4,articulo.getPiezasParaMayoreo());
    consulta.setDouble(5,articulo.getGananciaMayoreo());
    consulta.setDouble(6,articulo.getGanancia());
    consulta.setDouble(7,articulo.getPrecioVenta());
    consulta.setDouble(8,articulo.getPrecioMayoreo());
    consulta.setDouble(9,articulo.getPrecioCompra());
    consulta.setBoolean(10,articulo.isEstadoBorrado());
    consulta.setString(11,articulo.getTipoUnidad());
    return consulta;
  }

  /**
   * Verifica si el codigo de barras que se encuentra ya se encuentra registrado
   * @param codigoBarras El codigo de barras que se verificara si se encuentra registrado
   * @return Verdadero si el codigo de barras ya se encuentra registrado en un producto o falso si no
   * @throws SQLException Una excepcion que indica que ocurrio un error al ejecutar la consulta
   * @throws IOException Una excepcion que indica que ocurrio un error al leer el archivo de configuracion en la BD
   * @throws ClassNotFoundException Una excepcion que indica que no se encontro la libreria que permite la conexion a
   * la BD
   */
  public boolean existeElCodigoDeBarras(String codigoBarras) throws SQLException, IOException, ClassNotFoundException{
    String CONSULTA_BUSCAR_CODIGO_BARRAS = "SELECT count(codigo_barras) as 'cantidad_codigos_barras' FROM articulo " +
        "WHERE codigo_barras = ? ;";
    Connection conexion = Conexion.getConexion();
    PreparedStatement consulta = conexion.prepareStatement(CONSULTA_BUSCAR_CODIGO_BARRAS);
    consulta.setString(1,codigoBarras);
    ResultSet resultados = consulta.executeQuery();
    resultados.last();
    int cantidadRepetidos = resultados.getInt("cantidad_codigos_barras");
    conexion.close();
    return (cantidadRepetidos > 0);
  }

  /**
   * Recupera el ultimo codigo insertado en un articulo
   * @return El ultimo codigo insertado
   * @throws SQLException Una excepcion que indica que ocurrio un error al ejecutar la consulta
   * @throws IOException Una excepcion que indica que ocurrio un error al leer el archivo de configuración de la BD
   * @throws ClassNotFoundException Una excepcion que indica que ocurrio un error al cargar la libreria de conexion a la
   * BD
   */
  public int recuperarELUltimoIdInsertado() throws SQLException, IOException, ClassNotFoundException{
    String CONSULTA_ULTIMO_CODIGO = "SELECT MAX(codigo) as 'ultimo_id' from articulo;";
    Connection conexion = Conexion.getConexion();
    PreparedStatement consulta = conexion.prepareStatement(CONSULTA_ULTIMO_CODIGO);
    ResultSet resultados = consulta.executeQuery();
    resultados.last();
    int ultimoCodigo = resultados.getInt("ultimo_id");
    conexion.close();
    return ultimoCodigo;
  }

  /**
   * Agrega un observador a la lista de observados
   * @param observer objeto que implementa la interfaz IObserver.
   */
  @Override
  public void agregarObserver(IObserver observer) {
    observers.add(observer);
  }

  /**
   * Elimina un odservador de la lista de observados
   * @param observer objeto que implementa la interfaz IObserver.
   */
  @Override
  public void eliminarObserver(IObserver observer) {
    observers.remove(observer);
  }

  /**
   * Notifica los observados de que ocurrio un cambio
   */
  private void notificar(){
    for(IObserver observado : observers){
      observado.update();
    }
  }

}
