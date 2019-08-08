package modelo.persistencia;

import modelo.Articulo;

import java.util.List;

public interface ArticulosDAO {
  public List<Articulo> getArticulos();
  public List<Articulo> buscarArticulos(String nombre);
  public boolean nuevoArticulo(Articulo articulo);
  public boolean editarArticulo(int codigo, Articulo articulo);
}
