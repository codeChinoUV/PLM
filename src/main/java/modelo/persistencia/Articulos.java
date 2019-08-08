package modelo.persistencia;

import modelo.Articulo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Articulos implements ArticulosDAO {

  private final String CONSULTA_TODOS_LOS_ARTICULOS = "SELECT * FROM articulos";
  private final String CONSULTA_BUSCAR_ARTICULOS = "SELECT * FROM articulos WHERE nombre LIKE ";
  private final String AGREGAR_ARTICULO = "INSERT INTO articulo(codigoDeBarras, nombre, cantidad, piezasParaMayoreo, " +
      "gananciaMayoreo, gananciaPublico, precioVenta, precioMayoreo, precioCompra, estado) VALUES(";
  private final String EDITAR_ARTICULO = "UPDATE articulos SET codigoDeBarras=";

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

  public List<Articulo> getArticulos(){
    List<Articulo> articulos = new ArrayList<>();
    Connection conexion = Conexion.getConexion();
    Statement consulta = null;
    ResultSet resultados = null;
    try{
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(CONSULTA_TODOS_LOS_ARTICULOS);
      while (resultados.next() && resultados!= null){
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

  public List<Articulo> buscarArticulos(String nombre){
    List<Articulo> articulos = new ArrayList<>();
    Connection conexion = Conexion.getConexion();
    Statement consulta = null;
    ResultSet resultados = null;
    String query = CONSULTA_BUSCAR_ARTICULOS + "''" + nombre + "';'";
    try{
      consulta = conexion.createStatement();
      resultados = consulta.executeQuery(query);
      while (resultados.next() && resultados != null){
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

  public boolean nuevoArticulo(Articulo articulo) {
    if (articulo != null) {
      String query = AGREGAR_ARTICULO + "'" + articulo.getCodigoBarras() + "','" + articulo.getNombre() + "',"
          + articulo.getCantidad() + "," + articulo.getPiezasParaMayoreo() + "," + articulo.getGananciaMayoreo()
          + "," + articulo.getGananciaPublico() + "," + articulo.getPrecioVenta() + "," + articulo.getPrecioMayoreo()
          + "," + articulo.getPrecioCompra() + "," + articulo.isEstado() + ");";
      Connection conexion = Conexion.getConexion();
      Statement consulta = null;
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

  public boolean editarArticulo(int codigo, Articulo articulo){
    if (codigo < 0 && articulo != null){
      String query =  EDITAR_ARTICULO + "'"+ articulo.getCodigoBarras() + "', nombre='" + articulo.getNombre()
          + "', cantidad=" + articulo.getCantidad() + ", piezasParaMayoreo=" + articulo.getPiezasParaMayoreo()
          + ", gananciaMayoreo=" + articulo.getGananciaMayoreo() + ", gananciaPublico=" + articulo.getGananciaPublico()
          + ", precioVenta=" + articulo.getPrecioVenta() + ", precioMayoreo=" + articulo.getPrecioMayoreo()
          + ", precioCompra=" + articulo.getPrecioCompra() + ", estado=" + articulo.isEstado() + " WHERE codigo="
          + codigo + ";";
      Connection conexion = Conexion.getConexion();
      Statement consulta = null;
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
