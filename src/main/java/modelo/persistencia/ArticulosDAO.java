package modelo.persistencia;

import modelo.Articulo;

import java.util.List;

public interface ArticulosDAO {
  List<Articulo> getArticulos();

  List<Articulo> buscarArticulos(String nombre);

  Articulo recuperarArticulo(String codigoBarras);

  Articulo recuperarArticulo(int codigo);

  boolean nuevoArticulo(Articulo articulo);

  boolean editarArticulo(int codigo, Articulo articulo);
}
