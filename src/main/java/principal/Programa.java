package principal;

import controlador.IniciarSesionController;
import controlador.MenuPrincipalController;
import javafx.scene.layout.Pane;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import modelo.Usuario;

public class Programa extends Application{
    private Stage stagePrincipal;
    private Pane rootPane;
    private Usuario usuarioLogeado;

    public void cambiarAVentanaInicioSesion() throws IOException{
        FXMLLoader loader = new FXMLLoader(Programa.class.getResource("/vistas/inicio_sesion.fxml"));
        rootPane = loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Iniciar sesion");
        stagePrincipal.setScene(scene);
        IniciarSesionController controller = loader.getController();
        controller.setVentanaPrincipal(this);
        stagePrincipal.show();
    }

    public void cambiarAVentanaMenuPrincipal() throws IOException{
        FXMLLoader loader = new FXMLLoader(Programa.class.getResource("/vistas/menu_principal.fxml"));
        rootPane = loader.load();
        Scene scene = new Scene(rootPane);
        stagePrincipal.setTitle("Papeleria la montaña - Menu");
        MenuPrincipalController controller = loader.getController();
        controller.setManejadorDeVentanas(this);
        stagePrincipal.setScene(scene);
        stagePrincipal.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
        this.stagePrincipal = stage;
        stagePrincipal.setMaximized(true);
        stagePrincipal.setResizable(false);
        Image icon = new Image(String.valueOf(Programa.class.getResource("/img/logo.png")));
        stagePrincipal.getIcons().add(icon);
        stagePrincipal.setIconified(true);
        try {
            cambiarAVentanaInicioSesion();
        } catch (IOException ex) {
            System.out.println("IOE: MainApp.start");
            ex.printStackTrace();
        }
    }

    public Usuario getUsuarioLogeado(){
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado){
        this.usuarioLogeado = usuarioLogeado;
    }

    public Stage getStagePrincipal(){
        return stagePrincipal;
    }

    public Pane getRootPane(){
        return rootPane;
    }

    public void setRootPane(Pane rootPane){
        this.rootPane = rootPane;
    }
}
