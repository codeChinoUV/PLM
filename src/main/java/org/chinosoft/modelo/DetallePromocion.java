package org.chinosoft.modelo;

public class DetallePromocion {
    private int id;
    private double porcentajeDescuento;
    private double precioConDescuento;
    private int codigoArticulo;
    private int idPromocion;

    public DetallePromocion(int id, double porcentajeDescuento, double precioConDescuento, int codigoArticulo, int idPromocion) {
        this.id = id;
        this.porcentajeDescuento = porcentajeDescuento;
        this.precioConDescuento = precioConDescuento;
        this.codigoArticulo = codigoArticulo;
        this.idPromocion = idPromocion;
    }

    public DetallePromocion(double porcentajeDescuento, double precioConDescuento, int codigoArticulo, int idPromocion) {
        this.porcentajeDescuento = porcentajeDescuento;
        this.precioConDescuento = precioConDescuento;
        this.codigoArticulo = codigoArticulo;
        this.idPromocion = idPromocion;
    }

    public DetallePromocion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double getPrecioConDescuento() {
        return precioConDescuento;
    }

    public void setPrecioConDescuento(double precioConDescuento) {
        this.precioConDescuento = precioConDescuento;
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

}
