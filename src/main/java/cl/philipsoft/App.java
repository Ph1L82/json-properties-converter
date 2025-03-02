/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2025-03-02 14:43:06
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2025-03-02 14:55:07
 * @ Description:   Clase principal que sirve como punto de entrada alternativo para la
 *                  aplicación.
 *                  Delega la ejecución a JsonPropertiesConverter.
 */

package cl.philipsoft;

/**
 * Clase principal que sirve como punto de entrada alternativo para la
 * aplicación.
 * Delega la ejecución a JsonPropertiesConverter.
 * 
 * @author philipsoft
 * @version 1.0
 */
public class App {

    /**
     * Método principal que simplemente delega a JsonPropertiesConverter.
     * 
     * @param args Argumentos de línea de comandos
     * @throws Exception Si ocurre algún error durante la ejecución
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando conversor JSON-Properties...");
        JsonPropertiesConverter.main(args);
    }
}