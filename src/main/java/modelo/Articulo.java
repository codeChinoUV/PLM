package modelo;

public class Articulo {
    private int codigo;
    private String codigoBarras = "";
    private String nombre;
    private String unidad;
    private int cantidad;
    private int piezasParaMayoreo = -1;
    private double gananciaMayoreo = -1;
    private double gananciaPublico = -1;
    private double precioVenta = -1;
    private double precioMayoreo = -1;
    private double precioCompra = -1;
    private boolean estado;

    public Articulo(int codigo, String codigoBarras, String nombre, int cantidad, int piezasParaMayoreo,
                    double gananciaMayoreo, double gananciaPublico, double precioVenta, double precioMayoreo,
                    double precioCompra, boolean estado, String unidad) {
        this.codigo = codigo;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.piezasParaMayoreo = piezasParaMayoreo;
        this.gananciaMayoreo = gananciaMayoreo;
        this.gananciaPublico = gananciaPublico;
        this.precioVenta = precioVenta;
        this.precioMayoreo = precioMayoreo;
        this.precioCompra = precioCompra;
        this.estado = estado;
        this.unidad = unidad;
    }

    public Articulo() {
    }

    public Articulo(String codigoBarras, String nombre, int cantidad, int piezasParaMayoreo,
                    double gananciaMayoreo, double gananciaPublico, double precioVenta, double precioMayoreo,
                    double precioCompra, boolean estado, String unidad) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.piezasParaMayoreo = piezasParaMayoreo;
        this.gananciaMayoreo = gananciaMayoreo;
        this.gananciaPublico = gananciaPublico;
        this.precioVenta = precioVenta;
        this.precioMayoreo = precioMayoreo;
        this.precioCompra = precioCompra;
        this.estado = estado;
        this.unidad = unidad;
    }

    public Articulo(String codigoBarras, String nombre, int cantidad, double gananciaPublico, double precioVenta,
                    double precioCompra, boolean estado, String unidad) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.gananciaPublico = gananciaPublico;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.estado = estado;
        this.unidad = unidad;
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

    public double getGananciaPublico() {
        return gananciaPublico;
    }

    public void setGananciaPublico(double gananciaPublico) {
        this.gananciaPublico = gananciaPublico;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
