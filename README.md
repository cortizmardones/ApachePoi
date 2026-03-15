# 📊 Demo Spring Boot + Apache POI Excel

> Proyecto de ejemplo construido con **Spring Boot** y **Apache POI** para generar y descargar un archivo **Excel `.xlsx`** desde un endpoint REST.

---

## 📌 Resumen

Este repositorio implementa una API simple que genera un archivo Excel en memoria y lo devuelve como descarga al cliente.

La aplicación crea un documento `.xlsx`, agrega una hoja con encabezados y filas de datos, ajusta automáticamente el ancho de las columnas y retorna el archivo como un arreglo de bytes en la respuesta HTTP.

---

## 🎯 Objetivo del proyecto

El objetivo principal de este proyecto es demostrar:

- cómo generar archivos Excel en Java usando **Apache POI**
- cómo exponer la descarga de un Excel desde un endpoint REST
- cómo construir el archivo completamente en memoria
- cómo retornar el contenido como `byte[]` en una respuesta HTTP

---

## 🧱 Stack tecnológico

- Java 25
- Spring Boot
- Spring Web
- Apache POI
- Lombok
- Maven

---

## 🏗️ Arquitectura simple

```text
Cliente REST
   |
   v
ExcelController
   |
   v
ExcelService
   |
   v
ExcelServiceImpl
   |
   v
Apache POI --> Workbook --> Sheet --> Rows --> Excel .xlsx
```

---

## 📂 Estructura principal

### `DemoApplication`

Clase principal de arranque de Spring Boot.

Responsabilidad:

- iniciar la aplicación
- levantar el contexto Spring

---

### `ExcelController`

Controlador REST que expone el endpoint para generar el archivo Excel.

Endpoint:

```text
GET /v1/excel/generateExcel
```

Responsabilidades:

- recibir la solicitud HTTP
- invocar el servicio que genera el archivo
- construir la respuesta con encabezados de descarga
- retornar el archivo como `byte[]`

---

### `ExcelService`

Interfaz que define el contrato del servicio.

Método principal:

```java
byte[] generateExcel();
```

---

### `ExcelServiceImpl`

Implementación del servicio encargada de construir el archivo Excel.

Responsabilidades:

- crear un `Workbook` tipo `XSSFWorkbook`
- crear una hoja llamada `Datos`
- agregar encabezados
- agregar filas con datos de ejemplo
- ajustar automáticamente las columnas
- escribir el archivo en un `ByteArrayOutputStream`
- devolver el contenido como arreglo de bytes

---

## 🧠 ¿Cómo funciona?

Cuando el cliente invoca el endpoint:

```text
GET /v1/excel/generateExcel
```

ocurre lo siguiente:

1. el controlador recibe la request
2. el servicio crea un archivo Excel en memoria
3. se construye una hoja llamada `Datos`
4. se agregan columnas:
    - ID
    - Nombre
    - Correo Electrónico
5. se insertan filas con datos de ejemplo
6. el workbook se convierte a `byte[]`
7. la respuesta HTTP devuelve el archivo como descarga

---

## 🚀 Endpoint disponible

### Generar Excel

```text
GET /v1/excel/generateExcel
```

### Ejemplo

```bash
curl --location 'http://localhost:8080/v1/excel/generateExcel' --output reporte.xlsx
```

---

## 📄 Contenido del Excel generado

La hoja creada se llama:

```text
Datos
```

Y contiene las siguientes columnas:

- ID
- Nombre
- Correo Electrónico

Datos de ejemplo cargados en el archivo:

- 1 | Carlos | cortizmardones@gmail.com
- 2 | Walter | etejeda@gmail.com

---

## 📝 Logs esperados

Durante la generación del archivo se registran logs como:

```text
INFO Generando reporte archivo Excel
INFO Reporte archivo Excel finalizado
```

Si ocurre un error:

```text
ERROR Error al generar archivo Excel : ...
```

---

## ⚙️ Funcionamiento técnico

La generación del archivo se realiza con:

```java
Workbook workbook = new XSSFWorkbook();
```

Esto indica que el archivo será del tipo:

```text
.xlsx
```

Luego se usa `Sheet`, `Row` y `Cell` para construir el contenido del documento.

Finalmente, el Excel se escribe en memoria usando:

```java
ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
workbook.write(byteArrayOutputStream);
```

---

## 📦 Dependencias principales

El proyecto utiliza estas dependencias destacadas:

- `spring-boot-starter-web`
- `lombok`
- `poi-ooxml`
- `poi-ooxml-full`

---

## ✅ Beneficios de esta implementación

- generación de Excel sin guardar archivos temporales en disco
- respuesta directa al cliente como descarga
- uso simple y claro de Apache POI
- base útil para reportes exportables
- fácil de extender con más columnas, hojas y estilos

---

## ⚠️ Consideraciones

- actualmente los datos son estáticos y se crean manualmente en el servicio
- en un caso real, esos datos normalmente vendrían desde base de datos o una API externa
- el nombre del archivo incluye `LocalDateTime.now()`, lo que puede generar caracteres poco cómodos para algunos sistemas si no se formatea
- para archivos grandes convendría evaluar estrategias más eficientes de streaming o generación por lotes

---

## ▶️ Ejecución del proyecto

### Levantar la aplicación

```bash
mvn spring-boot:run
```

### Descargar el Excel

```bash
curl --location 'http://localhost:8080/v1/excel/generateExcel' --output reporte.xlsx
```

---

## 📚 Conclusión

Este proyecto es una demo simple y práctica para entender cómo generar archivos Excel con **Apache POI** dentro de una aplicación **Spring Boot**.

Es una buena base para construir funcionalidades de exportación de reportes en formato `.xlsx` de manera rápida y mantenible.