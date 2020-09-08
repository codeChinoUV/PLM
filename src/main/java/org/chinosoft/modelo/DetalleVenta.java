package org.chinosoft.modelo;

public class DetalleVenta {
    private int id;
    private int cantidadVendida;
    private double precioVenta;
    private String tipo;
    private double subtotal;
    private int noNotaVenta;
    private int codigoArticulo;

    public DetalleVenta(int id, int cantidadVendida, double precioVenta, String tipo, double subtotal, int noNotaVenta,
                        int codigoArticulo) {
        this.id = id;
        this.cantidadVendida = cantidadVendida;
        this.precioVenta = precioVenta;
        this.tipo = tipo;
        this.subtotal = subtotal;
        this.noNotaVenta = noNotaVenta;
        this.codigoArticulo = codigoArticulo;
    }

    public DetalleVenta(int cantidadVendida, double precioVenta, String tipo, double subtotal, int noNotaVenta, int codigoArticulo) {
        this.cantidadVendida = cantidadVendida;
        this.precioVenta = precioVenta;
        this.tipo = tipo;
        this.subtotal = subtotal;
        this.noNotaVenta = noNotaVenta;
        this.codigoArticulo = codigoArticulo;
    }

    public DetalleVenta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getNoNotaVenta() {
        return noNotaVenta;
    }

    public void setNoNotaVenta(int noNotaVenta) {
        this.noNotaVenta = noNotaVenta;
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

}
