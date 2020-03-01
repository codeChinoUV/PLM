package modelo.persistencia;

import modelo.Articulo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Articulos implements ArticulosDAO {

  private Articulo recuperarArticulo(ResultSet resultadosDeConsulta) throws SQLException {
    Articulo articulo = null;
    if(resultadosDeConsulta != null){
      int codigo = resultadosDeConsulta.getInt("codigo");
      String codigoBarras= resultadosDeConsulta.getString("codigoDeBarras");
      String nombre = resultadosDeConsulta.getString("nombre");
      int cantidad = resultadosDeConsulta.getInt("cantidad");
      int piezasMayoreo = resultadosDeConsulta.getInt("piezasParaMayoreo");
      double gananciaMayoreo = resultadosDeConsulta.getDouble("gananciaMayoreo");
      double gananciaPublico = resultadosDeConsulta.getDouble("gananciaPublico");
      double precioVenta = resultadosDeConsulta.getDouble("precioVenta");
      double precioMayoreo = resultadosDeConsulta.getDouble("precioMayoreo");
      double precioCompra = resultadosDeConsulta.getDouble("precioCompra");
      boolean activo = resultadosDeConsulta.getBoolean("estado");
      articulo = new Articulo(codigo, codigoBarras, nombre, cantidad, piezasMayoreo, gananciaMayoreo,
          gananciaPublico, precioVenta, precioMayoreo, precioCompra, activo);
    }
    return articulo;
  }

  public List<Articulo> getArticulos() throws SQLException, IOException, ClassNotFoundException {
    List<Articulo> articulos = new ArrayList<>();
    Connection conexion = Conexion.getConexion();
    Statement consulta;
    ResultSet resultados;
    try{
      consulta = conexion.createStatement();
      String CONSULTA_TODOS_LOS_ARTICULOS = "SELECT * FROM articulo";
      resultados = consulta.executeQuery(CONSULTA_TODOS_LOS_ARTICULOS);
      while (resultados.next()){
        articulos.add(recuperarArticulo(resultados));
      }
      conexion.close();
    }catch (SQLException ex){
      ex.printStackTrace();
      System.out.println("Articulos-SQLException: getArticulos");
      return null;
    }catch (NullPointerException ex){
      ex.printStackTrace();
      System.out.println("Articulos-NullPointerException: getArticulos");
      return null;
    }
    return articulos;
  }

  public List<Articulo> buscarArticulos(String nombre) throws SQLException, IOException, ClassNotFoundException{
    List<Articulo> articulos = new ArrayList<>();
    Connection conexion = Conexion.getConexion();
    Statement consulta;
    ResultSet resultados;
    String CONSULTA_BUSCAR_ARTICULOS = "SELECT * FROM articulo WHERE nombre LIKE ";
    String query = CONSULTA_BUSCAR_ARTICULOS + "''" + nombre + "';'";
    try{
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados.next()){
        articulos.add(recuperarArticulo(resultados));
      }
      conexion.close();
    }catch (SQLException ex){
      ex.printStackTrace();
      System.out.println("Articulos-SQLException: buscarArticulos");
      return null;
    }catch (NullPointerException ex){
      ex.printStackTrace();
      System.out.println("Articulos-NullPointerException: buscarArticulos");
      return null;
    }
    return articulos;
  }

  public boolean nuevoArticulo(Articulo articulo) throws SQLException, IOException, ClassNotFoundException{
    if (articulo != null) {
      String AGREGAR_ARTICULO = "INSERT INTO articulo(codigo_barras, nombre, cantidad, piezas_mayoreo, " +
          "ganancia_mayoreo, ganancia, precio_venta, precio_mayoreo, precio_compra, estado_borrado) VALUES(";
      String query = AGREGAR_ARTICULO + "'" + articulo.getCodigoBarras() + "','" + articulo.getNombre() + "',"
          + articulo.getCantidad() + "," + articulo.getPiezasParaMayoreo() + "," + articulo.getGananciaMayoreo()
          + "," + articulo.getGanancia() + "," + articulo.getPrecioVenta() + "," + articulo.getPrecioMayoreo()
          + "," + articulo.getPrecioCompra() + "," + articulo.isEstadoBorrado() + ");";
      Connection conexion = Conexion.getConexion();
      Statement consulta;
      try {
        consulta = conexion.createStatement();
        return consulta.execute(query);
      } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("Articulos-SQLException: nuevoArticulo");
        return false;
      }
    } else {
      return false;
    }
  }

  public boolean editarArticulo(int codigo, Articulo articulo) throws SQLException, IOException, ClassNotFoundException{
    if (codigo < 0 && articulo != null){
      String EDITAR_ARTICULO = "UPDATE articulo SET codigoDeBarras=";
      String query =  EDITAR_ARTICULO + "'"+ articulo.getCodigoBarras() + "', nombre='" + articulo.getNombre()
          + "', cantidad=" + articulo.getCantidad() + ", piezas_mayoreo=" + articulo.getPiezasParaMayoreo()
          + ", ganancia_mayoreo=" + articulo.getGananciaMayoreo() + ", ganancia=" + articulo.getGanancia()
          + ", precio_venta=" + articulo.getPrecioVenta() + ", precio_mayoreo=" + articulo.getPrecioMayoreo()
          + ", precio_compra=" + articulo.getPrecioCompra() + ", estado_borrado=" + articulo.isEstadoBorrado() + " WHERE codigo="
          + codigo + ";";
      Connection conexion = Conexion.getConexion();
      Statement consulta;
      try{
        consulta = conexion.createStatement();
        return consulta.execute(query);
      }catch (SQLException ex){
        System.out.println("Articulos-SQLException: editarArticulo");
        ex.printStackTrace();
        return false;
      }
    }else{
      return false;
    }
  }

}
