package controlador.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Encriptacion {

    /**
     * Hashea una cadena de texto con el algortimo SHA256
     * @param cadenaTexto La cadena de texto a hashear
     * @return El HASH de la cadena
     */
    public static String toSHA256(String cadenaTexto){
        String cadenaEncriptada;
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(cadenaTexto.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            cadenaEncriptada = hexString.toString();
        } catch(Exception ex){
            cadenaEncriptada = "Error";
        }
        return cadenaEncriptada;
    }
}
