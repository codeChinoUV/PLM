package principal;

import controlador.IniciarSesionController;
import javafx.scene.layout.Pane;
import modelo.Articulo;
import modelo.Persona;
import modelo.Usuario;
import modelo.persistencia.Articulos;
import modelo.persistencia.UsuarioDAO;
import modelo.persistencia.Usuarios;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.util.List;

public class Programa extends Application{
    private final String RUTA_LOGO = "@.." + File.separator + "img" + File.separator+ "logo.png";
    private Stage stagePrincipal;
    private Pane rootPane;

    public void ventanaInicioSesion() throws IOException{
        FXMLLoader loader = new FXMLLoader(Programa.class.getResource("/vistas/InicioSesion.fxml"));
        rootPane = loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Iniciar sesion");
        stagePrincipal.setScene(scene);
        IniciarSesionController controller = loader.getController();
        controller.setVentanaPrincipal(this);
        stagePrincipal.show();
    }
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stagePrincipal = stage;
        stagePrincipal.setMaximized(true);
        stagePrincipal.setResizable(false);
        Image icon = new Image(String.valueOf(Programa.class.getResource("/img/logo.png")));
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
