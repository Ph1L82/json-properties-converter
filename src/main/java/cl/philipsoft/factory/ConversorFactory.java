/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2025-03-02 14:58:35
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2025-03-02 14:58:53
 * @ Description:   Fábrica que crea instancias de servicios de conversión según el modo solicitado.
 *                  Sigue el patrón Factory y el principio de Inversión de Dependencias (DIP) de SOLID.
 */

package cl.philipsoft.factory;

import cl.philipsoft.service.ConversorService;
import cl.philipsoft.service.impl.JsonToPropertiesService;
import cl.philipsoft.service.impl.PropertiesToJsonService;

/**
 * Fábrica que crea instancias de servicios de conversión según el modo
 * solicitado.
 * Sigue el patrón Factory y el principio de Inversión de Dependencias (DIP) de
 * SOLID.
 * 
 * @author philipsoft
 * @version 1.0
 */
public class ConversorFactory {

    // Constantes para los modos de conversión
    public static final String MODO_JSON_A_PROPERTIES = "json2prop";
    public static final String MODO_PROPERTIES_A_JSON = "prop2json";

    /**
     * Crea y devuelve un servicio de conversión según el modo especificado.
     * 
     * @param modo El modo de conversión ("json2prop" o "prop2json")
     * @return Un servicio de conversión apropiado para el modo
     * @throws IllegalArgumentException Si el modo no es reconocido
     */
    public static ConversorService crearConversor(String modo) {
        if (MODO_JSON_A_PROPERTIES.equalsIgnoreCase(modo)) {
            return new JsonToPropertiesService();
        } else if (MODO_PROPERTIES_A_JSON.equalsIgnoreCase(modo)) {
            return new PropertiesToJsonService();
        } else {
            throw new IllegalArgumentException("Modo de conversión no válido: " + modo
                    + ". Use '" + MODO_JSON_A_PROPERTIES + "' o '" + MODO_PROPERTIES_A_JSON + "'.");
        }
    }
}