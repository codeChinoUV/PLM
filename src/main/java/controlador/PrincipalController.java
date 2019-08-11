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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    private Usuario usuario = null;

    private Programa ventanaPrincipal = null;

    @FXML
    private Label lUsuario;

    @FXML
    private Label lFecha;

    @FXML
    private JFXButton btnVentas;

    @FXML
    private JFXButton btnArticulos;

    @FXML
    private JFXButton btnEmpleados;

    @FXML
    private JFXButton btnPromociones;

    @FXML
    private ImageView btnCerrarSesion;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lFecha.setText(LocalDateTime.now().toString());
    }

    /**
     * Muestra en las etiquetas de usuario el nombre y apellido del usuario
     *
     * @param usuario El usuario logeado
     */
    private void mostrarComponentesUsuario(Usuario usuario) {
        if (usuario != null) {
            if (!usuario.getTipo().equals("administrador")) {
                btnEmpleados.setVisible(false);
                btnPromociones.setVisible(false);
            }
        }
    }

    /**
     * Cambia la vista al menu de ventas
     */
    @FXML
    public void mostrarVentanaVentas() {
        try {
            ventanaPrincipal.vistaMenuVentas();
        } catch (IOException ex) {
            System.out.println("PrincipalController-IOException: mostrarMenuVentas");
            ex.printStackTrace();
        }
    }

    /**
     * Cambia la vista al menu articulos
     */
    public void mostrarVentanaArticulos() {
        try {
            ventanaPrincipal.vistaMenuArticulos();
        } catch (IOException ex) {
            System.out.println("PrincipalController-IOException: mostrarMenuArticulos");
            ex.printStackTrace();
        }
    }


    public Programa getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public void setVentanaPrincipal(Programa ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public void setUsuario(Usuario usuario) {
        ventanaPrincipal.mostrarInformacionPrincipal(usuario, lUsuario, lFecha);
        mostrarComponentesUsuario(usuario);
        this.usuario = usuario;
    }
}
