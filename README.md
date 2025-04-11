# 📦 ConexionMySQL

Una librería Java que facilita la conexión a una base de datos MySQL, incluyendo funcionalidades para conectarse, crear/eliminar tablas y buscar registros de manera sencilla.

---

## 🎯 Propósito

El propósito principal de esta librería es ayudar al usuario a implementar una base de datos, permitiendo una mejor integración con interfaces gráficas y el registro de datos, ya sea en medios locales o remotos.

---

## ⚙️ Funcionamiento

Al inicio de la librería se crea un objeto estático que mantiene la conexión con la base de datos MySQL.

### 🔑 Métodos principales

- ### `conectar()`
  Se encarga de realizar la conexión, solicitando:
  - Puerto
  - Nombre de la base de datos
  - Usuario
  - Contraseña  
  Utiliza `try-catch` para manejar errores comunes como:
  - Usuario/contraseña incorrectos
  - Puerto inválido
  - Base de datos inexistente

- ### `crearTabla()`
  Recibe:
  - Nombre de la tabla
  - Cadena con nombres de columnas separadas por comas  
  Convierte esta cadena en un arreglo y usa un `StringBuilder` para construir la consulta SQL.  
  Incluye manejo de errores con `try-catch`.

- ### `eliminarTabla()`
  Recibe el nombre de la tabla a eliminar y ejecuta la consulta `DROP TABLE`.  
  Si la tabla no existe, lanza una excepción controlada.

- ### `getConexion()`
  Retorna el objeto `Connection` actual, por si se requiere realizar operaciones personalizadas fuera de la librería.

- ### `buscarTabla()`
  Recibe el nombre de una tabla e imprime su contenido completo.

- ### `buscarRegistro()`
  Recibe:
  - Nombre de la tabla
  - Columna donde buscar
  - Valor a buscar  
  Muestra los registros coincidentes.

---

## 📦 Cómo importar el archivo JAR

1. En tu proyecto, haz clic derecho sobre la carpeta de librerías.
2. Selecciona **Agregar JAR/CARPETA** (`Add JAR/FOLDER`).
3. Busca la ubicación del archivo `.jar` de la librería `ConexionMySQL`.
4. Asegúrate de incluir también la librería **MySQL Connector/J**, descargable desde la [página oficial de MySQL](https://dev.mysql.com/downloads/connector/j/) o desde el repositorio de Git.

### 📥 Importación en código

```java
import libreriaconexion.ConexionMySQL;
```

### Capturas del uso de nuestra libreria

(imagenes/imagen1.jpg)
(imagenes/imagen2.jpg)
(imagenes/imagen3.jpg)
(imagenes/imagen4.jpg)
(imagenes/imagen5.jpg)
(imagenes/imagen6.jpg)
(imagenes/imagen7.jpg)

---

## 🎥 Video de demostración

Puedes ver una muestra en funcionamiento en el siguiente enlace:  
🔗 [https://youtu.be/UOPQ8fnShNU](https://youtu.be/UOPQ8fnShNU)

---

## 👤 Autor

Jhonatan Ivan Poblete Hernandez
Omar Konk Pérez Ramirez  

---
