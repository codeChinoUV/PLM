package org.chinosoft.modelo.persistencia;


import org.chinosoft.modelo.Articulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Articulos implements ArticulosDAO {

    private final String CONSULTA_TODOS_LOS_ARTICULOS = "SELECT * FROM articulos";
    private final String CONSULTA_BUSCAR_ARTICULOS = "SELECT * FROM articulos WHERE nombre RLIKE ";
    private final String AGREGAR_ARTICULO = "INSERT INTO articulos(codigoDeBarras, nombre, cantidad, piezasParaMayoreo, " +
            "gananciaMayoreo, gananciaPublico, precioVenta, precioMayoreo, precioCompra, estado, unidad) VALUES(";
    private final String EDITAR_ARTICULO = "UPDATE articulos SET codigoDeBarras=";
    private final String CONSULTA_RECUPERAR_ARTICULO_POR_CODIGO = "SELECT * FROM articulos WHERE codigo =";
    private final String CONSULTA_RECUPERAR_ARTICULO_POR_BARRAS = "SELECT * FROM articulos WHERE codigoDeBarras = ";
    private final String CONSULTA_MODIFICAR_CANTIDAD_ARTICULO = "UPDATE articulos SET cantidad = ";
    private final String CONSULTA_ULTIMO_ID_ARTICULO = "SELECT MAX(codigo) as 'ultima' from articulos;";

    /**
     * Recupera un articulo realizando una consulta
     *
     * @param query La consulta que se realizara
     * @return El articulo recuperado
     * @throws SQLException Si ocurre un error al ejecutar la consulta
     */
    private Articulo recuperarArticuloDeConsulta(String query) throws SQLException {
        Articulo articulo = null;
        Connection conexion = Conexion.getConexion();
        if (conexion != null) {
            Statement consulta = conexion.createStatement();
            ResultSet resultados = consulta.executeQuery(query);
            while (resultados != null && resultados.next()) {
                articulo = recuperarArticuloDeResultados(resultados);
            }
            conexion.close();
        } else {
            return null;
        }
        return articulo;
    }

    /**
     * Crea un objeto de tipo Articulo a partir de un Resulset
     *
     * @param resultadosDeConsulta El Resulset del cual se tomara la consulta
     * @return El objeto Articulo creado a partir del Resulset
     * @throws SQLException Una excepcion por si ocurre un error SQL
     */
    private Articulo recuperarArticuloDeResultados(ResultSet resultadosDeConsulta) throws SQLException {
        Articulo articulo = null;
        if (resultadosDeConsulta != null) {
            int codigo = resultadosDeConsulta.getInt("codigo");
            String codigoBarras = resultadosDeConsulta.getString("codigoDeBarras");
            String nombre = resultadosDeConsulta.getString("nombre");
            String unidad = resultadosDeConsulta.getString("unidad");
            int cantidad = resultadosDeConsulta.getInt("cantidad");
            int piezasMayoreo = resultadosDeConsulta.getInt("piezasParaMayoreo");
            double gananciaMayoreo = resultadosDeConsulta.getDouble("gananciaMayoreo");
            double gananciaPublico = resultadosDeConsulta.getDouble("gananciaPublico");
            double precioVenta = resultadosDeConsulta.getDouble("precioVenta");
            double precioMayoreo = resultadosDeConsulta.getDouble("precioMayoreo");
            double precioCompra = resultadosDeConsulta.getDouble("precioCompra");
            boolean activo = resultadosDeConsulta.getBoolean("estado");
            articulo = new Articulo(codigo, codigoBarras, nombre, cantidad, piezasMayoreo, gananciaMayoreo,
                    gananciaPublico, precioVenta, precioMayoreo, precioCompra, activo, unidad);
        }
        return articulo;
    }

    /**
     * Recupera todos los articulos almacenados en la BD
     *
     * @return Una lista de Articulo
     */
    public List<Articulo> getArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        Connection conexion = Conexion.getConexion();
        Statement consulta;
        ResultSet resultados;
        if (conexion != null) {
            try {
                consulta = conexion.createStatement();
                resultados = consulta.executeQuery(CONSULTA_TODOS_LOS_ARTICULOS);
                while (resultados.next() && resultados != null) {
                    articulos.add(recuperarArticuloDeResultados(resultados));
                }
                conexion.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Articulos-SQLException: getArticulos");
                return null;
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                System.out.println("Articulos-NullPointerException: getArticulos");
                return null;
            }
        } else {
            return null;
        }

        return articulos;
    }

    /**
     * Recupera los articulos que su nombre comiencen con el nombre pasado como parametro
     *
     * @param nombre El nombre del producto el cual se desea buscar
     * @return Una lista de Articulo que coinciden con la busqueda
     */
    public List<Articulo> buscarArticulos(String nombre) {
        List<Articulo> articulos = new ArrayList<>();
        Connection conexion = Conexion.getConexion();
        Statement consulta;
        ResultSet resultados;
        String query = CONSULTA_BUSCAR_ARTICULOS + "'^" + nombre + "';";
        if (conexion != null) {
            try {
                consulta = conexion.createStatement();
                resultados = consulta.executeQuery(query);
                while (resultados.next()) {
                    articulos.add(recuperarArticuloDeResultados(resultados));
                }
                conexion.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Articulos-SQLException: buscarArticulos");
                return null;
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                System.out.println("Articulos-NullPointerException: buscarArticulos");
                return null;
            }
        } else {
            return null;
        }

        return articulos;
    }

    /**
     * Almacena un Articulo en la BD
     *
     * @param articulo El articulo que se almacenara
     * @return El objeto almacecnado con el su codigo o null si ocurrio un error
     */
    public int nuevoArticulo(Articulo articulo) {
        if (articulo != null) {
            String query = AGREGAR_ARTICULO + "'" + articulo.getCodigoBarras() + "','" + articulo.getNombre() + "',"
                    + articulo.getCantidad() + "," + articulo.getPiezasParaMayoreo() + "," + articulo.getGananciaMayoreo()
                    + "," + articulo.getGananciaPublico() + "," + articulo.getPrecioVenta() + "," + articulo.getPrecioMayoreo()
                    + "," + articulo.getPrecioCompra() + "," + articulo.isEstado() + ",'" + articulo.getUnidad() + "');";
            Connection conexion = Conexion.getConexion();
            Statement consulta;
            if (conexion != null) {
                try {
                    consulta = conexion.createStatement();
                    consulta.execute(query);
                    return 1;
                } catch (SQLIntegrityConstraintViolationException no) {
                    System.out.println("Articulos-SQLIntegrityConstraintViolationException: nuevoArticulo");
                    no.printStackTrace();
                    return 0;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("Articulos-SQLException: nuevoArticulo");
                    return -1;
                }
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * Edita un articulo que se encuentra en la BD
     *
     * @param codigo   El codigo del articulo
     * @param articulo El articulo con sus atributos modificados a guardar
     * @return El articulo modificado si se modifico o null si no
     */
    public boolean editarArticulo(int codigo, Articulo articulo) {
        if (codigo < 0 && articulo != null) {
            String query = EDITAR_ARTICULO + "'" + articulo.getCodigoBarras() + "', nombre='" + articulo.getNombre()
                    + "', cantidad=" + articulo.getCantidad() + ", piezasParaMayoreo=" + articulo.getPiezasParaMayoreo()
                    + ", gananciaMayoreo=" + articulo.getGananciaMayoreo() + ", gananciaPublico=" + articulo.getGananciaPublico()
                    + ", precioVenta=" + articulo.getPrecioVenta() + ", precioMayoreo=" + articulo.getPrecioMayoreo()
                    + ", precioCompra=" + articulo.getPrecioCompra() + ", estado=" + articulo.isEstado() + " WHERE codigo="
                    + codigo + ";";
            Connection conexion = Conexion.getConexion();
            Statement consulta;
            if (conexion != null) {
                try {
                    consulta = conexion.createStatement();
                    return consulta.execute(query);
                } catch (SQLException ex) {
                    System.out.println("Articulos-SQLException: editarArticulo");
                    ex.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Recupera el articulo de la BD que coincide con el codigo de barras
     *
     * @param codigoBarras El codigo de barras del producto que se desea recuperar
     * @return El Articulo que coincidio con el codigo de barras o null si no coincidio
     */
    public Articulo recuperarArticulo(String codigoBarras) {
        String query = CONSULTA_RECUPERAR_ARTICULO_POR_BARRAS + "'" + codigoBarras + "';";
        Articulo articulo;
        try {
            articulo = recuperarArticuloDeConsulta(query);
            return articulo;
        } catch (SQLException ex) {
            System.out.print("Articulos-SQLException : recuperarArticulo(codigoBarras)");
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Recupera el articulo de la BD que coincide con el codigo
     *
     * @param codigo El codigo del producto que se desea recuperar
     * @return El Articulo que coincidio con el codigo o null si no coincidio
     */
    public Articulo recuperarArticulo(int codigo) {
        String query = CONSULTA_RECUPERAR_ARTICULO_POR_CODIGO + codigo;
        Articulo articulo;
        try {
            articulo = recuperarArticuloDeConsulta(query);
            return articulo;
        } catch (SQLException ex) {
            System.out.println("Articulos-SQLException : recuperarArticulo(codigo)");
            ex.printStackTrace();
            return null;
        }
    }
  /*
  //Modificar para que sea a traves de una transaccion
  public Articulo descontarCantidadVenta(Articulo articulo, int cantidad){
    String query = CONSULTA_MODIFICAR_CANTIDAD_ARTICULO + (articulo.getCantidad()-cantidad) + "WHERE codigo = " + articulo.getCodigo() + ";";
    Articulo articuloRecuperado = null;
    try{
      articulo = recuperarArticuloDeConsulta(query);
    }catch(SQLException ex){
      System.out.print("Articulos-SQLException: descontarCantidad")
      ex.printStackTrace();
      return null;
    }
  }*/

}
