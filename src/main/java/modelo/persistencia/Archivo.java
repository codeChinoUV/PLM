package modelo.persistencia;

import modelo.dataclass.ConfiguracionConexionBD;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Archivo {

    private static final String ARCHIVO_CONFIGURACION_BD;

    static {
        ARCHIVO_CONFIGURACION_BD = "PLM-base-datos.cnf";
    }

    /***
     * Calcula la ruta en donde se encuentra almacenado el archivo de configuracion en base al sistema operativo
     * @param sistemaOperativo El nombre del sistema operativo
     * @return Un String con la ruta en donde se encuentra el archivo de configuración
     */
    private static String getRutaArchivoConfiguracionBD(String sistemaOperativo){
        String rutaArchivoConfiguracion = getRutaUsuario(sistemaOperativo);
        rutaArchivoConfiguracion += File.separator + "PLM" + File.separator;
        return rutaArchivoConfiguracion;
    }

    /***
     * Obtiene la ruta raiz del sistema operativo
     * @param sistemaOperativo El sistema operativo del cual se obtendra la ruta
     * @return Un String con la ruta raiz
     */
    private static String getRutaUsuario(String sistemaOperativo){
        String rutaUsuario = "";
        rutaUsuario = System.getProperty("user.home");
        return rutaUsuario;
    }

    /***
     * Verifica si existe el directorio
     * @param directorio El directorio a verificar
     * @return Verdadero si exite, falso si no
     */
    private static boolean existeElDirectorio(String directorio){
        File archivo = new File(directorio);
        return archivo.exists();
    }

    /***
     * Crea la ruta especificada en el directorio
     * @param directorio El directorio que se creara
     * @return Verdadero si se creo falso si no
     */
    private static boolean crearDirectorio(String directorio){
        Boolean seCreo = false;
        File directorios = new File(directorio);
        if (!directorios.exists()) {
            seCreo = directorios.mkdirs();
        }
        return seCreo;
    }

    /***
     * Crea el archivo en el directorio especificado
     * @param directorio El directorio en donde se creara el archivo
     * @param archivo El archivo en el que se creara
     * @return Verdadero si se creo o falso si no
     * @throws IOException Una excepcion que indica que no se pudo crear el archivo
     */
    private static boolean crearArchivo(String directorio, String archivo) throws IOException {
        Boolean seCreo = false;
        File directorioEnDondeSeCreara = new File(directorio);
        if(directorioEnDondeSeCreara.exists()){
            String rutaFinal = directorio + File.separator + archivo;
            File archivoACrear = new File(rutaFinal);
            seCreo = archivoACrear.createNewFile();
        }
        return seCreo;
    }

    /***
     * Lee un archivo de texto
     * @param rutaArchivoALeer La ruta del archivo a leer
     * @return Una lista de string con las lineas del archivo
     * @throws IOException Una excepcion que indica que no se pudo leer el archivo
     */
    private static List<String> leerArchivoTexto(String rutaArchivoALeer) throws IOException{
        List<String> lineasDelArchivo = new ArrayList<>();
        Scanner entrada = new Scanner(new File(rutaArchivoALeer));
        while(entrada.hasNext()){
            String linea = entrada.nextLine();
            lineasDelArchivo.add(linea);
        }
        entrada.close();
        return lineasDelArchivo;
    }

    /***
     * Recupera la información de la configuración de la conexión a la BD
     * @return Un dataclass de la configuracion de la base de datos
     * @throws IOException Una excepcion al momento de leer el archivo
     */
    public static ConfiguracionConexionBD getConfiguracionBD() throws IOException{
        ConfiguracionConexionBD configuracion = new ConfiguracionConexionBD();
        String ruta = getRutaArchivoConfiguracionBD(System.getProperty("os.name"));
        ruta += ARCHIVO_CONFIGURACION_BD;
        List<String> lineasDeLaConfiguracion = leerArchivoTexto(ruta);
        for (String linea: lineasDeLaConfiguracion) {
            String[] lineaDeConfiguracion = linea.split("=");
            if(lineaDeConfiguracion.length == 2 ){
                switch (lineaDeConfiguracion[0]){
                    case "usuario":
                        configuracion.setUsuario(lineaDeConfiguracion[1]);
                        break;
                    case "contraseña":
                        configuracion.setContrasena(lineaDeConfiguracion[1]);
                        break;
                    case "host":
                        configuracion.setHost(lineaDeConfiguracion[1]);
                        break;
                    case "db":
                        configuracion.setDb(lineaDeConfiguracion[1]);
                        break;
                    case "adicional":
                        configuracion.setConfiguracionesExtra(lineaDeConfiguracion[1]);
                        break;
                }
            }
        }
        return configuracion;
    }
}
