/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2025-03-02 14:43:06
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2025-03-02 14:54:46
 * @ Description:   Pruebas unitarias para la aplicación JsonPropertiesConverter.
 */

package cl.philipsoft;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

/**
 * Pruebas unitarias para la aplicación JsonPropertiesConverter.
 */
public class AppTest extends TestCase {

    private static final String TEST_JSON = "target/test.json";
    private static final String TEST_PROPS = "target/test.properties";

    /**
     * Crea el caso de prueba.
     *
     * @param testName Nombre del caso de prueba
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return La suite de pruebas
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Configuración de las pruebas.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Asegurar que los archivos de prueba no existan
        deleteTestFiles();
    }

    /**
     * Limpieza después de las pruebas.
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        // Eliminar archivos de prueba
        deleteTestFiles();
    }

    /**
     * Prueba básica del funcionamiento de la aplicación.
     */
    public void testApp() {
        assertTrue(true);
    }

    /**
     * Prueba la conversión de JSON a Properties.
     */
    public void testJsonToProperties() throws IOException {
        // Crear un archivo JSON de prueba
        String jsonContent = "{ \"property1\": \"value1\", \"property2\": \"value2\" }";
        createTestJsonFile(jsonContent);

        // Ejecutar la conversión
        JsonPropertiesConverter.jsonToProperties(TEST_JSON, TEST_PROPS);

        // Verificar que el archivo properties se creó
        File propsFile = new File(TEST_PROPS);
        assertTrue("El archivo properties debería existir", propsFile.exists());

        // Cargar las propiedades y verificar su contenido
        Properties props = new Properties();
        props.load(Files.newInputStream(propsFile.toPath()));

        assertEquals("value1", props.getProperty("property1"));
        assertEquals("value2", props.getProperty("property2"));
    }

    /**
     * Prueba la conversión de Properties a JSON.
     */
    public void testPropertiesToJson() throws IOException {
        // Crear un archivo Properties de prueba
        Properties props = new Properties();
        props.setProperty("property1", "value1");
        props.setProperty("property2", "value2");

        try (FileWriter writer = new FileWriter(TEST_PROPS)) {
            props.store(writer, "Test Properties");
        }

        // Ejecutar la conversión
        JsonPropertiesConverter.propertiesToJson(TEST_PROPS, TEST_JSON);

        // Verificar que el archivo JSON se creó
        File jsonFile = new File(TEST_JSON);
        assertTrue("El archivo JSON debería existir", jsonFile.exists());

        // Verificar el contenido del JSON (de manera simple)
        String content = new String(Files.readAllBytes(jsonFile.toPath()));
        assertTrue(content.contains("property1"));
        assertTrue(content.contains("value1"));
        assertTrue(content.contains("property2"));
        assertTrue(content.contains("value2"));
    }

    /**
     * Crea un archivo JSON de prueba con el contenido especificado.
     */
    private void createTestJsonFile(String content) throws IOException {
        try (FileWriter writer = new FileWriter(TEST_JSON)) {
            writer.write(content);
        }
    }

    /**
     * Elimina los archivos de prueba.
     */
    private void deleteTestFiles() {
        new File(TEST_JSON).delete();
        new File(TEST_PROPS).delete();
    }
}