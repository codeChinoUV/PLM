package controlador.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificacionDeTexto {
  private static final String EXPRESION_NOMBRE_USUARIO = "([A-Z]|[a-z]){5,50}";
  private static final String EXPRESION_NUMERO_DE_DIEZ_CIFRAS = "[0-9]{1,10}";
  private static final String EXPRESION_NUMERO_DECIMAL = "([0-9]{0,5}[.]?[0-9]{0,5})";

  /**
   * Verifica que el nombreUsuario coincida con la expresion regular
   * @param nombreUsuario La cadena a verificar si cumple con la condicion
   * @return Verdadero si cumple, o falso si no
   */
  public static boolean esElUsuarioValido(String nombreUsuario){
    Pattern patron = Pattern.compile(EXPRESION_NOMBRE_USUARIO);
    Matcher verificador = patron.matcher(nombreUsuario);
    return verificador.matches();
  }

  /**
   * Verifica que el tamaño de la contraseña sea valida
   * @param contrasena La contrasena a validar
   * @return Verdadero si es mayor o igual a 6 caracteres
   */
  public static boolean esLaContrasenaValida(String contrasena){
    return contrasena.length() >= 6;
  }

  /**
   * Verifica que el numero sea una cifra sea unicamente de 10 cifras
   * @param numero El numero a validar
   * @return Verdadero si cumple con la expresion regular o falso si no
   */
  public static boolean esUnNumeroValido(String numero){
    Pattern patron = Pattern.compile(EXPRESION_NUMERO_DE_DIEZ_CIFRAS);
    Matcher verificador = patron.matcher(numero);
    return (verificador.matches() && !numero.isEmpty());
  }

  /**
   * Verifica que el texto sea una cifra decimal con 5 numeros antes y despues del punto
   * @param decimal El texto a validat
   * @return Verdadero su el decimal cumple con la expresion regular o falso si no
   */
  public static boolean esUnNumeroConDecminasValido(String decimal){
    Pattern patron = Pattern.compile(EXPRESION_NUMERO_DECIMAL);
    Matcher verificador = patron.matcher(decimal);
    return (verificador.matches() && !decimal.isEmpty());
  }

  /**
   * Verifica que la descripcion cumpla con las restricciones del campo
   * @param descripcion La descripcion a validar
   * @return Verdaadero si la descripcion es valida o falso si no
   */
  public static boolean esUnaDescripcionDeArticuloValida(String descripcion){
    final int CANTIDAD_MAXIMA_DE_CARACTERES = 50;
    boolean esValida = false;
    if(!descripcion.isEmpty() && descripcion.length() <= CANTIDAD_MAXIMA_DE_CARACTERES){
      esValida = true;
    }
    return esValida;
  }

  /**
   * Verifica que el codigoBarras cumpla con las restricciones del campo
   * @param codigoBarras El codigo de barras a validar
   * @return Verdadero si el codigo de barras es valido o falso si no
   */
  public static boolean esUnCodigoDeBarrasDeArticuloValido(String codigoBarras){
    final int CANTIDAD_MAXIMA_DE_CARACTERES = 50;
    return (codigoBarras.length() <= CANTIDAD_MAXIMA_DE_CARACTERES);
  }

  /**
   * Verifica que la unidad cumpla con las restricciones del campo
   * @param unidad La unidad a validar
   * @return Verdadero si la unidad es valido o falso si no
   */
  public static boolean esUnaUnidadDeArticuloValida(String unidad){
    final int CANTIDAD_MAXIMA_DE_CARACTERES = 50;
    return (unidad.length() > 0 && unidad.length() <= CANTIDAD_MAXIMA_DE_CARACTERES);
  }

}
