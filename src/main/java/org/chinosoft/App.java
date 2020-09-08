package org.chinosoft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.chinosoft.controlador.*;
import org.chinosoft.modelo.Usuario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage stagePrincipal;
    private Pane rootPane;
    private Usuario usuarioLogeado = null;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Muestra en las etiquetas de usuario el nombre y apellido del usuario y la fecha
     *
     * @param usuario El usuario logeado
     */
    public void mostrarInformacionPrincipal(Usuario usuario, Label lUsuario, Label lFecha) {
        if (usuario != null) {
            lUsuario.setText(usuario.getPersona().getNombre() + " " + usuario.getPersona().getApellidos());
            lFecha.setText(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    /**
     * Cambia la vista a Inicio de sesion
     *
     * @throws IOException Una excepcion si la ventana no se encuentra
     */
    private void ventanaInicioSesion() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/InicioSesion.fxml"));
        rootPane = loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Papeleria la monta単a-Iniciar sesion");
        stagePrincipal.setScene(scene);
        IniciarSesionController controller = loader.getController();
        controller.setVentanaPrincipal(this);
        stagePrincipal.show();
    }

    /**
     * Cambia la vista a MenuPrincipal
     *
     * @param usuarioLogeado El usuario que se logeo en el login
     * @throws IOException Una excepcion si no se encuentra el archivo de la vista
     */
    public void ventanaPrincipal(Usuario usuarioLogeado) throws IOException {
        this.usuarioLogeado = usuarioLogeado;
        System.out.println("Recibio el usuario " + usuarioLogeado.getUsuario());
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/Principal.fxml"));
        rootPane = loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Papeleria la monta単a-Principal");
        PrincipalController controller = loader.getController();
        controller.setVentanaPrincipal(this);
        controller.setUsuario(this.usuarioLogeado);
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }

    /**
     * Cambia la vista a vistaMenuVentas
     *
     * @throws IOException Una excepcion si la vista no se encuentra
     */
    public void vistaMenuVentas() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/MenuVentas.fxml"));
        rootPane = loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Papeleria la monta単a-Ventas");
        MenuVentasController controller = loader.getController();
        controller.setVentanaPrincipal(this);
        controller.setUsuario(this.usuarioLogeado);
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }

    /**
     * Cambia la vista a MenuArticulos
     *
     * @throws IOException Una excepcion si la vista no se encuentra
     */
    public void vistaMenuArticulos() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/MenuArticulos.fxml"));
        rootPane = loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Papeleria la monta単a-Articulos");
        MenuArticulosController controller = loader.getController();
        controller.setVentanaPrincipal(this);
        controller.setUsuario(this.usuarioLogeado);
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }

    /**
     * Cambia la vista a ListarArticulos
     *
     * @throws IOException Una excepcion si la vista no se encuentra
     */
    public void vistaListarArticulos() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/ListarArticulos.fxml"));
        rootPane = loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Papeleria la monta単a-Articulos-Listar");
        ListarArticulosController controller = loader.getController();
        controller.setVentanaPrincipal(this);
        controller.setUsuario(this.usuarioLogeado);
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }

    /**
     * Cambia la vista a AgregarArticulo
     *
     * @throws IOException Una excepcion si el archivo de la vista no se encuentra
     */
    public void vistaAgregarArticulos() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/vistas/AgregarArticulo.fxml"));
        rootPane = loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Papeleria la montaña - Agregar articulo");
        AgregarArticuloController controller = loader.getController();
        controller.setUsuarioLogeado(usuarioLogeado);
        controller.setVentanaPrincipal(this);
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }

    @Override
    public void start(Stage stage) {
        this.stagePrincipal = stage;
        stagePrincipal.setMaximized(true);
        stagePrincipal.setResizable(false);
        Image icon = new Image(String.valueOf(App.class.getResource("/img/logo.png")));
        stagePrincipal.getIcons().add(icon);
        stagePrincipal.setIconified(true);
        try {
            ventanaInicioSesion();
        } catch (IOException ex) {
            System.out.println("IOE: MainApp.start");
            ex.printStackTrace();
        }
    }
}