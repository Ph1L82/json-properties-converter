/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2025-03-02 14:57:00
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2025-03-02 15:43:57
 * @ Description:   Implementación del servicio de conversión de JSON a Properties.
 *                  Sigue el principio de Responsabilidad Única (SRP) de SOLID.
 */

package cl.philipsoft.service.impl;

import cl.philipsoft.service.ConversorService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;

/**
 * Implementación del servicio de conversión de JSON a Properties.
 * Sigue el principio de Responsabilidad Única (SRP) de SOLID.
 * 
 * @author philipsoft
 * @version 1.0
 */
public class JsonToPropertiesService implements ConversorService {

    private final ObjectMapper objectMapper;
    private final JavaPropsMapper propsMapper;

    /**
     * Constructor que inicializa los mappers necesarios.
     */
    public JsonToPropertiesService() {
        this.objectMapper = new ObjectMapper();
        // Habilitar soporte para comentarios en JSON
        this.objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // Opcionalmente también permitir comillas simples y datos sin comillas
        this.objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        this.objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        this.propsMapper = new JavaPropsMapper();
    }

    /**
     * Constructor con inyección de dependencias para facilitar pruebas.
     * 
     * @param objectMapper Mapper para procesamiento de JSON
     * @param propsMapper  Mapper para procesamiento de Properties
     */
    public JsonToPropertiesService(ObjectMapper objectMapper, JavaPropsMapper propsMapper) {
        this.objectMapper = objectMapper;
        this.propsMapper = propsMapper;
    }

    @Override
    public void convertir(String archivoOrigen, String archivoDestino) throws IOException {
        // Validar parámetros
        validarArchivos(archivoOrigen, archivoDestino);

        File jsonFile = new File(archivoOrigen);
        validarArchivoExistente(jsonFile);

        // Leer el JSON desde el archivo
        JsonNode jsonNode = objectMapper.readTree(jsonFile);

        // Convertir el JSON a Properties
        Properties properties = propsMapper.writeValueAsProperties(jsonNode);

        // Guardar las propiedades en un archivo .properties
        try (FileOutputStream fos = new FileOutputStream(archivoDestino)) {
            properties.store(fos, "Generado desde JSON por JsonPropertiesConverter by Ph1L82@PhilipSoft");
        }

        System.out.println("Archivo convertido a properties: " + archivoDestino);
    }

    @Override
    public String getFormatoOrigen() {
        return "JSON";
    }

    @Override
    public String getFormatoDestino() {
        return "Properties";
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