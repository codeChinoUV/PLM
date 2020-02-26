package modelo.dataclass;

public class ConfiguracionConexionBD {
    private String usuario;
    private String contrasena;
    private String host;
    private String db;
    private String configuracionesExtra;

    public ConfiguracionConexionBD(){}

    public ConfiguracionConexionBD(String usuario, String contrasena, String host,
                                   String db, String configuracionesExtra){
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.db = db;
        this.host = host;
        this.configuracionesExtra = configuracionesExtra;
    }

    /**
     * Crea el url de conexion en base a las configuraciones
     * @return El URL de conexion de la BD
     */
    public String getURl(){
        String url = "jdbc:mysql://" + host + "/" + db
                + configuracionesExtra;
        return url;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public void setConfiguracionesExtra(String configuracionesExtra) {
        this.configuracionesExtra = configuracionesExtra;
    }
}
