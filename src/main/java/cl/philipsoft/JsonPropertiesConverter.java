/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2025-03-02 14:38:57
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2025-03-02 14:59:31
 * @ Description:   Clase principal que coordina la conversión entre archivos JSON y Properties.
 *                  Implementa el patrón Fachada (Facade) para simplificar el uso de los servicios subyacentes.
 */

package cl.philipsoft;

import cl.philipsoft.factory.ConversorFactory;
import cl.philipsoft.service.ConversorService;

import java.io.IOException;

/**
 * Clase principal que coordina la conversión entre archivos JSON y Properties.
 * Implementa el patrón Fachada (Facade) para simplificar el uso de los
 * servicios subyacentes.
 * 
 * @author philipsoft
 * @version 1.0
 */
public class JsonPropertiesConverter {

    /**
     * Convierte un archivo JSON a formato Properties.
     * 
     * @param jsonFileName       Ruta del archivo JSON de entrada
     * @param propertiesFileName Ruta del archivo Properties de salida
     * @throws IOException Si ocurre un error durante la lectura/escritura
     */
    public static void jsonToProperties(String jsonFileName, String propertiesFileName) throws IOException {
        ConversorService conversor = ConversorFactory.crearConversor(ConversorFactory.MODO_JSON_A_PROPERTIES);
        conversor.convertir(jsonFileName, propertiesFileName);
    }

    /**
     * Convierte un archivo Properties a formato JSON.
     * 
     * @param propertiesFileName Ruta del archivo Properties de entrada
     * @param jsonFileName       Ruta del archivo JSON de salida
     * @throws IOException Si ocurre un error durante la lectura/escritura
     */
    public static void propertiesToJson(String propertiesFileName, String jsonFileName) throws IOException {
        ConversorService conversor = ConversorFactory.crearConversor(ConversorFactory.MODO_PROPERTIES_A_JSON);
        conversor.convertir(propertiesFileName, jsonFileName);
    }

    /**
     * Método principal que ejecuta la conversión según los argumentos
     * proporcionados.
     * 
     * @param args Argumentos de línea de comandos:
     *             [0] - Modo de conversión: "json2prop" o "prop2json"
     *             [1] - Archivo de entrada
     *             [2] - Archivo de salida
     * @throws IOException Si ocurre un error durante la conversión
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            mostrarAyuda();
            return;
        }

        String modo = args[0];
        String archivoEntrada = args[1];
        String archivoSalida = args[2];

        try {
            ConversorService conversor = ConversorFactory.crearConversor(modo);
            conversor.convertir(archivoEntrada, archivoSalida);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            mostrarAyuda();
        } catch (IOException e) {
            System.err.println("Error durante la conversión: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Muestra las instrucciones de uso del programa.
     */
    private static void mostrarAyuda() {
        System.out.println("Conversor JSON-Properties v1.0");
        System.out.println("Uso:");
        System.out.println("  java -jar json-properties-converter.jar json2prop input.json output.properties");
        System.out.println("  java -jar json-properties-converter.jar prop2json input.properties output.json");
    }
}