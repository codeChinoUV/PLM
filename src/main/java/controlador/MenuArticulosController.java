package controlador;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import modelo.Usuario;
import principal.Programa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuArticulosController implements Initializable {

    private Usuario usuario = null;

    private Programa ventanaPrincipal = null;

    @FXML
    private Label lUsuario;

    @FXML
    private Label lFecha;

    @FXML
    private JFXButton btnNuevo;

    @FXML
    private JFXButton btnListar;

    @FXML
    private JFXButton btnFaltantes;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private ImageView btnCerrarSesion;

    @FXML
    private JFXButton btnInicio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        ventanaPrincipal.mostrarInformacionPrincipal(usuario, lUsuario, lFecha);
        this.usuario = usuario;
    }

    public Programa getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public void setVentanaPrincipal(Programa ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    /**
     * Cambia la vista al menu principal
     */
    @FXML
    public void cambiarVistaPrincipal() {
        try {
            ventanaPrincipal.ventanaPrincipal(this.usuario);
        } catch (IOException ex) {
            System.out.println("MenuArticulosController-IOException: cambiarVistaPrincipal");
        }
    }

    /**
     * Cambia la vista listar articulos
     */
    public void cambiarListarArticulos() {
        try {
            ventanaPrincipal.vistaListarArticulos();
        } catch (IOException ex) {
            System.out.println("MenuArticulos-IOException: cambiarListarArticulos");
            ex.printStackTrace();
        }
    }
}
