# JSON Properties Converter

[![License: GPL-3.0](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/license/gpl-3-0)
[![Java Version](https://img.shields.io/badge/Java-8%2B-orange)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
[![Maven Compatible](https://img.shields.io/badge/Maven-Compatible-green)](https://maven.apache.org/)

Una herramienta de línea de comandos para convertir fácilmente entre archivos JSON y Properties.

## 🚀 Características

- **Conversión bidireccional:** Transforma de JSON a Properties y viceversa
- **Preservación de estructura:** Mantiene la estructura jerárquica del JSON
- **Interfaz de línea de comandos sencilla:** Fácil de usar en scripts y procesos automatizados
- **Compatible con múltiples plataformas:** Funciona en Windows, Linux y macOS
- **Código limpio y mantenible:** Implementado siguiendo principios SOLID

## 📋 Requisitos previos

- Java Development Kit (JDK) 8 o superior
- Apache Maven 3.6.0 o superior (para compilación)
- GraalVM (opcional, solo para generar ejecutables nativos)

## 🔨 Compilación

### Compilación básica con Maven

```bash
# Clonar el repositorio
git clone https://github.com/Ph1L82/json-properties-converter
cd json-properties-converter

# Compilar el proyecto
mvn clean compile

# Generar el JAR ejecutable
mvn clean package
```

El archivo JAR ejecutable se generará en la carpeta `target/` con el nombre `json-properties-converter-1.0.jar`.

## 🏃‍♂️ Ejecución

### Usando el JAR ejecutable directamente

```bash
# Convertir de JSON a Properties
java -jar target/json-properties-converter-1.0.jar json2prop input.json output.properties

# Convertir de Properties a JSON
java -jar target/json-properties-converter-1.0.jar prop2json input.properties output.json
```

### Ejemplos de uso

#### Convertir un archivo de configuración JSON a Properties:

```bash
java -jar json-properties-converter-1.0.jar json2prop config.json config.properties
```

#### Convertir un archivo de propiedades a JSON:

```bash
java -jar json-properties-converter-1.0.jar prop2json app.properties app.json
```

## 📜 Creación de scripts ejecutables

### Linux

1. Crea un archivo `conversor.sh` con el siguiente contenido:

```bash
#!/bin/bash
# conversor.sh - Script para ejecutar el conversor JSON-Properties en Linux

# Obtener la ruta donde se encuentra el script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
JAR_PATH="$SCRIPT_DIR/json-properties-converter-1.0.jar"

# Verificar argumentos
if [ $# -lt 3 ]; then
    echo "Conversor JSON-Properties v1.0"
    echo "Uso:"
    echo "  $0 json2prop input.json output.properties"
    echo "  $0 prop2json input.properties output.json"
    exit 1
fi

# Verificar que el JAR existe
if [ ! -f "$JAR_PATH" ]; then
    echo "Error: No se encontró el archivo JAR en $JAR_PATH"
    exit 1
fi

# Ejecutar el JAR con los argumentos proporcionados
java -jar "$JAR_PATH" "$@"
```

2. Haz el script ejecutable:

```bash
chmod +x conversor.sh
```

3. Ejecución:

```bash
./conversor.sh json2prop input.json output.properties
```

### Windows

1. Crea un archivo `conversor.bat` con el siguiente contenido:

```batch
@echo off
rem conversor.bat - Script para ejecutar el conversor JSON-Properties en Windows

rem Obtener la ruta donde se encuentra el script
set "SCRIPT_DIR=%~dp0"
set "JAR_PATH=%SCRIPT_DIR%json-properties-converter-1.0.jar"

rem Verificar argumentos
if "%~3"=="" (
    echo Conversor JSON-Properties v1.0
    echo Uso:
    echo   %~nx0 json2prop input.json output.properties
    echo   %~nx0 prop2json input.properties output.json
    exit /b 1
)

rem Verificar que el JAR existe
if not exist "%JAR_PATH%" (
    echo Error: No se encontro el archivo JAR en %JAR_PATH%
    exit /b 1
)

rem Ejecutar el JAR con los argumentos proporcionados
java -jar "%JAR_PATH%" %*
```

2. Ejecución:

```batch
conversor.bat json2prop input.json output.properties
```

### macOS

El script para macOS es el mismo que para Linux:

1. Crea un archivo `conversor.sh` (mismo contenido que en Linux)
2. Haz el script ejecutable:

```bash
chmod +x conversor.sh
```

3. Ejecución:

```bash
./conversor.sh json2prop input.json output.properties
```

## 🛠️ Generación de ejecutables nativos con GraalVM

### Requisitos para GraalVM

- GraalVM CE o EE 22.3.0+
- Native Image instalado (`gu install native-image`)
- Maven 3.6.0+

### Pasos para generar ejecutables nativos

1. Actualiza el `pom.xml` para incluir el plugin de GraalVM Native Image (el código XML ya está incluido en el proyecto)

2. Configura el entorno para GraalVM:

```bash
# En Linux/macOS
export GRAALVM_HOME=/path/to/graalvm
export JAVA_HOME=$GRAALVM_HOME
export PATH=$GRAALVM_HOME/bin:$PATH

# En Windows (PowerShell)
$env:GRAALVM_HOME = "C:\path\to\graalvm"
$env:JAVA_HOME = $env:GRAALVM_HOME
$env:PATH = "$env:GRAALVM_HOME\bin;$env:PATH"
```

3. Genera el ejecutable nativo:

```bash
mvn clean package -Pnative
```

El ejecutable nativo se generará en la carpeta `target/` con el nombre `json-properties-converter` (en Windows tendrá extensión `.exe`).

4. Uso del ejecutable nativo:

```bash
# En Linux/macOS
./target/json-properties-converter json2prop input.json output.properties

# En Windows
target\json-properties-converter.exe json2prop input.json output.properties
```

## 📦 Distribución recomendada

```
json-properties-converter/
├── bin/
│   ├── conversor.sh             # Script para Linux/macOS
│   ├── conversor.bat            # Script para Windows
│   └── json-properties-converter # Ejecutable nativo (si usas GraalVM)
├── lib/
│   └── json-properties-converter-1.0.jar  # JAR ejecutable
├── LICENSE.txt
└── README.md
```

## 📄 Licencia

Este proyecto está licenciado bajo la [GPL-3.0](LICENSE) - consulta el archivo LICENSE para más detalles.

## 🔄 API

### Conversión de JSON a Properties

```java
JsonPropertiesConverter.jsonToProperties("input.json", "output.properties");
```

### Conversión de Properties a JSON

```java
JsonPropertiesConverter.propertiesToJson("input.properties", "output.json");
```

## 💻 Contribuciones

Las contribuciones son bienvenidas. Por favor, siente libre de:

1. Hacer fork del proyecto
2. Crear una rama para tu característica (`git checkout -b feature/amazing-feature`)
3. Hacer commit de tus cambios (`git commit -m 'Add some amazing feature'`)
4. Push a la rama (`git push origin feature/amazing-feature`)
5. Abrir un Pull Request

## 📱 Contacto

Ph1L82 @PhilipSoft - [@Ph1L82](https://github.com/Ph1L82) - eduardo@philipsoft.cl

Link del Proyecto: [https://github.com/Ph1L82/json-properties-converter](https://github.com/Ph1L82/json-properties-converter)
