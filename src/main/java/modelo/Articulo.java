package modelo;

public class Articulo {
    private int codigo;
    private String codigoBarras;
    private String nombre;
    private int cantidad;
    private int piezasParaMayoreo;
    private double gananciaMayoreo;
    private double ganancia;
    private double precioVenta;
    private double precioMayoreo;
    private double precioCompra;
    private boolean estadoBorrado;

    public Articulo(int codigo, String codigoBarras, String nombre, int cantidad, int piezasParaMayoreo,
                    double gananciaMayoreo, double ganancia, double precioVenta, double precioMayoreo,
                    double precioCompra, boolean estadoBorrado) {
        this.codigo = codigo;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.piezasParaMayoreo = piezasParaMayoreo;
        this.gananciaMayoreo = gananciaMayoreo;
        this.ganancia = ganancia;
        this.precioVenta = precioVenta;
        this.precioMayoreo = precioMayoreo;
        this.precioCompra = precioCompra;
        this.estadoBorrado = estadoBorrado;
    }

    public Articulo() {
    }

    public Articulo(String codigoBarras, String nombre, int cantidad, int piezasParaMayoreo,
                    double gananciaMayoreo, double ganancia, double precioVenta, double precioMayoreo,
                    double precioCompra, boolean estadoBorrado) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.piezasParaMayoreo = piezasParaMayoreo;
        this.gananciaMayoreo = gananciaMayoreo;
        this.ganancia = ganancia;
        this.precioVenta = precioVenta;
        this.precioMayoreo = precioMayoreo;
        this.precioCompra = precioCompra;
        this.estadoBorrado = estadoBorrado;
    }

    public Articulo(String codigoBarras, String nombre, int cantidad, double ganancia, double precioVenta, double precioCompra, boolean estadoBorrado) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.ganancia = ganancia;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.estadoBorrado = estadoBorrado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPiezasParaMayoreo() {
        return piezasParaMayoreo;
    }

    public void setPiezasParaMayoreo(int piezasParaMayoreo) {
        this.piezasParaMayoreo = piezasParaMayoreo;
    }

    public double getGananciaMayoreo() {
        return gananciaMayoreo;
    }

    public void setGananciaMayoreo(double gananciaMayoreo) {
        this.gananciaMayoreo = gananciaMayoreo;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioMayoreo() {
        return precioMayoreo;
    }

    public void setPrecioMayoreo(double precioMayoreo) {
        this.precioMayoreo = precioMayoreo;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public boolean isEstadoBorrado() {
        return estadoBorrado;
    }

    public void setEstadoBorrado(boolean estadoBorrado) {
        this.estadoBorrado = estadoBorrado;
    }
}
