package modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Promocion {
  private int id;
  private String nombre;
  private String descripcion;
  private boolean activa;
  private LocalDateTime fechaInicio;
  private LocalDateTime fechaFin;
  private List<DetallePromocion> detallesPromocion;

  public Promocion(int id, String nombre, String descripcion, boolean activa, LocalDateTime fechaInicio,
                   LocalDateTime fechaFin, List<DetallePromocion> detallesPromocion) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.activa = activa;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
    this.detallesPromocion = detallesPromocion;
  }

  public Promocion(String nombre, String descripcion, boolean activa, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.activa = activa;
    this.fechaInicio = fechaInicio;
    this.fechaFin = fechaFin;
  }

  public Promocion(){
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public boolean isActiva() {
    return activa;
  }

  public void setActiva(boolean activa) {
    this.activa = activa;
  }

  public LocalDateTime getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(LocalDateTime fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public LocalDateTime getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(LocalDateTime fechaFin) {
    this.fechaFin = fechaFin;
  }

  public List<DetallePromocion> getDetallesPromocion() {
    return detallesPromocion;
  }

  public void setDetallesPromocion(List<DetallePromocion> detallesPromocion) {
    this.detallesPromocion = detallesPromocion;
  }
}

