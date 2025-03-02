/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2025-03-02 14:57:45
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2025-03-02 14:58:10
 * @ Description:   Implementación del servicio de conversión de Properties a JSON.
 *                  Sigue el principio de Responsabilidad Única (SRP) de SOLID.
 */

package cl.philipsoft.service.impl;

import cl.philipsoft.service.ConversorService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;

/**
 * Implementación del servicio de conversión de Properties a JSON.
 * Sigue el principio de Responsabilidad Única (SRP) de SOLID.
 * 
 * @author philipsoft
 * @version 1.0
 */
public class PropertiesToJsonService implements ConversorService {

    private final ObjectMapper objectMapper;
    private final JavaPropsMapper propsMapper;

    /**
     * Constructor que inicializa los mappers necesarios.
     */
    public PropertiesToJsonService() {
        this.objectMapper = new ObjectMapper();
        this.propsMapper = new JavaPropsMapper();
    }

    /**
     * Constructor con inyección de dependencias para facilitar pruebas.
     * 
     * @param objectMapper Mapper para procesamiento de JSON
     * @param propsMapper  Mapper para procesamiento de Properties
     */
    public PropertiesToJsonService(ObjectMapper objectMapper, JavaPropsMapper propsMapper) {
        this.objectMapper = objectMapper;
        this.propsMapper = propsMapper;
    }

    @Override
    public void convertir(String archivoOrigen, String archivoDestino) throws IOException {
        // Validar parámetros
        validarArchivos(archivoOrigen, archivoDestino);

        File propsFile = new File(archivoOrigen);
        validarArchivoExistente(propsFile);

        // Leer las propiedades desde el archivo .properties
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(propsFile)) {
            properties.load(fis);
        }

        // Convertir las propiedades a JSON
        JsonNode jsonNode = propsMapper.readPropertiesAs(properties, JsonNode.class);

        // Escribir el JSON en un archivo con formato bonito
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(archivoDestino), jsonNode);

        System.out.println("Archivo convertido a JSON: " + archivoDestino);
    }

    @Override
    public String getFormatoOrigen() {
        return "Properties";
    }

    @Override
    public String getFormatoDestino() {
        return "JSON";
    }

    /**
     * Valida que los nombres de archivo no sean nulos.
     * 
     * @param archivoOrigen  Archivo de origen
     * @param archivoDestino Archivo de destino
     */
    private void validarArchivos(String archivoOrigen, String archivoDestino) {
        if (archivoOrigen == null || archivoDestino == null) {
            throw new IllegalArgumentException("Los nombres de archivo no pueden ser nulos");
        }
    }

    /**
     * Valida que el archivo de origen exista.
     * 
     * @param archivo Archivo a validar
     * @throws FileNotFoundException Si el archivo no existe
     */
    private void validarArchivoExistente(File archivo) throws FileNotFoundException {
        if (!archivo.exists()) {
            throw new FileNotFoundException("El archivo no existe: " + archivo.getPath());
        }
    }
}