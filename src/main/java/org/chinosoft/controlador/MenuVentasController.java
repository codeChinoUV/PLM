package org.chinosoft.controlador;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.chinosoft.App;
import org.chinosoft.modelo.Usuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuVentasController implements Initializable {

    private Usuario usuario = null;

    private App ventanaPrincipal = null;

    @FXML
    private Label lUsuario;

    @FXML
    private Label lFecha;

    @FXML
    private JFXButton btnNuevaVenta;

    @FXML
    private JFXButton btnCambio;

    @FXML
    private JFXButton btnProductoDefectuoso;

    @FXML
    private JFXButton btnDevolucion;

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

    public App VentanaPrincipal() {
        return ventanaPrincipal;
    }

    public void setVentanaPrincipal(App ventanaPrincipal) {
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


}
