/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2025-03-02 14:56:03
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2025-03-02 14:56:31
 * @ Description:   Interfaz para servicios de conversión entre diferentes formatos de archivo.
 *                  Sigue el principio de Segregación de Interfaces (ISP) de SOLID.
 */

package cl.philipsoft.service;

import java.io.IOException;

/**
 * Interfaz para servicios de conversión entre diferentes formatos de archivo.
 * Sigue el principio de Segregación de Interfaces (ISP) de SOLID.
 * 
 * @author philipsoft
 * @version 1.0
 */
public interface ConversorService {

    /**
     * Convierte un archivo de origen a un formato destino.
     * 
     * @param archivoOrigen  Ruta del archivo de origen
     * @param archivoDestino Ruta del archivo de destino
     * @throws IOException Si ocurre un error durante la conversión
     */
    void convertir(String archivoOrigen, String archivoDestino) throws IOException;

    /**
     * Obtiene el nombre del formato de origen.
     * 
     * @return El nombre del formato de origen
     */
    String getFormatoOrigen();

    /**
     * Obtiene el nombre del formato de destino.
     * 
     * @return El nombre del formato de destino
     */
    String getFormatoDestino();
}