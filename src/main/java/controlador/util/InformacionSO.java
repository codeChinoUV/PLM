package controlador.util;

public class InformacionSO {
    public static String getSistemaOperativo(){
        String sistemaOperativo = System.getProperty("so.name");
        return sistemaOperativo;
    }
}
