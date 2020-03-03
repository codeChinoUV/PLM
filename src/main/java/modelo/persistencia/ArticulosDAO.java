package modelo.persistencia;

import modelo.Articulo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ArticulosDAO {
  List<Articulo> getArticulosNoEliminados() throws SQLException, IOException, ClassNotFoundException;
  List<Articulo> buscarArticulos(String nombre) throws SQLException, IOException, ClassNotFoundException;
  int nuevoArticulo(Articulo articulo) throws SQLException, IOException, ClassNotFoundException;
  int editarArticulo(int codigo, Articulo articulo) throws SQLException, IOException, ClassNotFoundException;
}
