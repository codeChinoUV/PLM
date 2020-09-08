package org.chinosoft.modelo.persistencia;

import org.chinosoft.modelo.Articulo;

import java.util.List;

public interface ArticulosDAO {
    List<Articulo> getArticulos();

    List<Articulo> buscarArticulos(String nombre);

    Articulo recuperarArticulo(String codigoBarras);

    Articulo recuperarArticulo(int codigo);

    int nuevoArticulo(Articulo articulo);

    boolean editarArticulo(int codigo, Articulo articulo);
}
