package org.chinosoft.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Venta {
  private int noNota;
  private LocalDateTime fecha;
  private double total;
  private List<DetalleVenta> productosVendidos;
  private List<Promocion> promocionesAplicadas;

  public Venta(int noNota, LocalDateTime fecha, double total, List<DetalleVenta> productosVendidos,
               List<Promocion> promocionesAplicadas) {
    this.noNota = noNota;
    this.fecha = fecha;
    this.total = total;
    this.productosVendidos = productosVendidos;
    this.promocionesAplicadas = promocionesAplicadas;
  }

  public Venta(LocalDateTime fecha, double total, List<DetalleVenta> productosVendidos, List<Promocion> promocionesAplicadas) {
    this.fecha = fecha;
    this.total = total;
    this.productosVendidos = productosVendidos;
    this.promocionesAplicadas = promocionesAplicadas;
  }

  public Venta(){

  }

  public int getNoNota() {
    return noNota;
  }

  public void setNoNota(int noNota) {
    this.noNota = noNota;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public List<DetalleVenta> getProductosVendidos() {
    return productosVendidos;
  }

  public void setProductosVendidos(List<DetalleVenta> productosVendidos) {
    this.productosVendidos = productosVendidos;
  }

  public List<Promocion> getPromocionesAplicadas() {
    return promocionesAplicadas;
  }

  public void setPromocionesAplicadas(List<Promocion> promocionesAplicadas) {
    this.promocionesAplicadas = promocionesAplicadas;
  }

}
