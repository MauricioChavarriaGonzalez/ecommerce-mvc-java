# 🛒 Sistema de E-commerce con Patrón MVC

**Universidad de El Salvador**
**Facultad Multidisciplinaria de Occidente**
**Asignatura:** Desarrollo y Reutilización de Software
**Ciclo:** II/2026
**Tema:** 2 - Aplicación de E-commerce utilizando el Patrón MVC

---

## 👥 Integrantes del Grupo 6

| No. | Nombre Completo | Correo Institucional |
| :--- | :--- | :--- |
| 1 | Fernando José Rosales Valdés | Rv19012@ues.edu.sv |
| 2 | David Salomón Martínez Valladares | mv12013@ues.edu.sv |
| 3 | Milton Obed Alas Hernández | ah09062@ues.edu.sv |
| 4 | José Mauricio Chavarría González | cg92088@ues.edu.sv |
| 5 | Jose Franklin Angel Guevara | ag19030@ues.edu.sv |
| 6 | Alexis Alfonso Alvarenga Alvarenga | aa22044@ues.edu.sv |

---

## 📖 Descripción del Proyecto
Este proyecto consiste en una aplicación web de comercio electrónico (E-commerce) desarrollada en **Java** utilizando el framework **Spring Boot**. La aplicación permite a los usuarios visualizar un catálogo de productos, agregarlos a un carrito de compras, modificar las cantidades, eliminar productos y procesar una compra simulada. 

La solución se construyó aplicando estrictamente el patrón de arquitectura **Modelo-Vista-Controlador (MVC)**, garantizando un código modular, mantenible y escalable.

## 🛠️ Tecnologías Utilizadas
*   **Lenguaje:** Java 21 (JDK 21)
*   **Framework Backend:** Spring Boot (Spring MVC, Spring Data JPA)
*   **Motor de Plantillas (Frontend):** Thymeleaf
*   **Base de Datos:** H2 Database (Base de datos en memoria)
*   **Gestor de Dependencias:** Maven
*   **Estilos UI:** Bootstrap 5 (vía CDN)
*   **IDE:** Visual Studio Code

---

## 🏗️ Arquitectura y Aplicación del Patrón MVC

El sistema está dividido en componentes claramente definidos para cumplir con los requisitos de modularidad:

### 1. Modelo (Model)
Representa los datos y la lógica de negocio del sistema.
*   **Entidades (`model`):** `Producto.java`, `Carrito.java`, `ItemCarrito.java`. Se encargan de estructurar la información.
*   **Repositorio (`repository`):** `ProductoRepository.java`. Interfaz que hereda de `JpaRepository` para abstraer el acceso a la base de datos H2 sin escribir SQL manual.
*   **Servicios (`service`):** `ProductoService.java` y `CarritoService.java`. Contienen la lógica de negocio. Destaca el uso de `@SessionScope` en el `CarritoService`, lo que permite que cada usuario tenga un carrito independiente y privado.

### 2. Vista (View)
Representa la interfaz gráfica con la que interactúa el usuario.
*   Implementada mediante **Thymeleaf** (archivos `.html` en `src/main/resources/templates`).
*   `productos/lista.html`: Catálogo de productos.
*   `detalle.html`: Vista del carrito de compras con el cálculo de subtotales y total en tiempo real.

### 3. Controlador (Controller)
Actúa como intermediario entre el Modelo y la Vista.
*   `ProductoController.java`: Gestiona la ruta principal (`/` y `/productos`) y envía la lista de productos a la vista.
*   `CarritoController.java`: Maneja las peticiones de acción como agregar (`/carrito/agregar/{id}`), eliminar y procesar la compra.

---

## ♻️ Reutilización y Calidad de Código

*   **Inyección de Dependencias (IoC):** Se implementó la inyección por constructor (`Constructor Injection`) en lugar de la inyección por campo (`@Autowired`), lo que es una buena práctica recomendada por Spring para facilitar las pruebas unitarias y garantizar la inmutabilidad de las dependencias.
*   **Componentes Reutilizables:** La clase `Carrito` encapsula la lógica de agregar y eliminar productos, evitando duplicidad de código en los controladores.
*   **Formato Profesional:** Se utilizó `BigDecimal` para el manejo de precios (evitando errores de precisión de los tipos `double`/`float`).

---

## 🚀 Instrucciones de Uso

> 📘 Estas instrucciones, junto con una guía para **desarrollar** sin generar un JAR (recarga de vistas, depuración, pruebas y cómo agregar funcionalidades), también están en [INSTRUCCIONES.md](INSTRUCCIONES.md).

### 1. Requisitos previos
*   **JDK 21 o superior** instalado. Verificar con `java -version`.
*   La variable de entorno `JAVA_HOME` debe apuntar a la carpeta del JDK (o `java` debe estar en el `PATH`).
*   **No es necesario instalar Maven**: el proyecto incluye el *Maven Wrapper* (`mvnw` / `mvnw.cmd`), que descarga Maven automáticamente la primera vez que se ejecuta (requiere conexión a Internet).
*   Un navegador web. Bootstrap se carga desde un CDN, por lo que sin Internet la página funciona pero se ve sin estilos.

### 2. Obtener el código
```bash
git clone <URL-del-repositorio>
cd ecommerce-mvc-java
```

### 3. Ejecutar la aplicación
Desde la carpeta raíz del proyecto (donde está `pom.xml`):

**Windows (PowerShell o CMD):**
```bash
.\mvnw.cmd spring-boot:run
```

**Linux / macOS / Git Bash:**
```bash
./mvnw spring-boot:run
```

Cuando en la consola aparezca `Started EcommerceMvcApplication` y el mensaje `Base de datos inicializada con productos de prueba`, la aplicación está lista. Para detenerla, presionar `Ctrl + C`.

> Si el puerto 8080 ya está ocupado, se puede usar otro (las comillas son necesarias en PowerShell): `./mvnw spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"`

**Desde Visual Studio Code:** con el *Extension Pack for Java* y *Spring Boot Extension Pack* instalados, abrir `EcommerceMvcApplication.java` y hacer clic en **Run** sobre el método `main`.

### 4. Generar un JAR ejecutable (opcional)
```bash
./mvnw clean package -DskipTests
java -jar target/ecommerce-mvc-0.0.1-SNAPSHOT.jar
```

### 5. Uso de la aplicación
Abrir en el navegador **http://localhost:8080**

1.  **Ver catálogo:** la página principal (`/` o `/productos`) muestra los productos cargados desde la base de datos.
2.  **Agregar al carrito:** clic en **"Agregar al Carrito"** en cualquier producto. Si el producto ya está en el carrito, se incrementa su cantidad.
3.  **Ver el carrito:** botón **"Ver Carrito 🛒"**. Se muestra cada producto con precio, cantidad, subtotal y el total a pagar.
4.  **Eliminar un producto:** botón **"Eliminar"** en la fila correspondiente del carrito.
5.  **Procesar la compra:** botón **"Procesar Compra 💳"**. El carrito se vacía y se regresa al catálogo con el mensaje *"¡Compra realizada con éxito!"*.

Cada navegador/sesión tiene su propio carrito (gracias a `@SessionScope`). Para probarlo, abrir la tienda en una ventana normal y en una ventana de incógnito.

### 6. Rutas disponibles

| Método | Ruta | Controlador | Descripción |
| :--- | :--- | :--- | :--- |
| GET | `/` o `/productos` | `ProductoController` | Catálogo de productos |
| GET | `/carrito` | `CarritoController` | Ver el carrito de la sesión |
| GET | `/carrito/agregar/{id}?cantidad=N` | `CarritoController` | Agrega el producto `{id}` (cantidad por defecto: 1) |
| GET | `/carrito/eliminar/{id}` | `CarritoController` | Quita el producto `{id}` del carrito |
| GET | `/carrito/comprar` | `CarritoController` | Simula la compra y vacía el carrito |
| GET | `/h2-console` | (Spring Boot) | Consola web de la base de datos H2 |

### 7. Consola de la base de datos H2
Con la aplicación en ejecución, entrar a **http://localhost:8080/h2-console** y usar:

*   **JDBC URL:** `jdbc:h2:mem:testdb`
*   **User Name:** `sa`
*   **Password:** *(vacío)*

Allí se puede consultar la tabla con `SELECT * FROM PRODUCTO;`.

> Si la consola responde 404, revisar que la línea `spring.h2.console.enabled=true` en `application.properties` no tenga espacios al final.

> **Nota:** H2 es una base de datos **en memoria**: los datos se pierden al detener la aplicación y los 4 productos de prueba se vuelven a crear en cada arranque (`DataInitializer.java`). Para agregar o cambiar productos de prueba, editar ese archivo.

### 8. Estructura del proyecto
```
src/main/java/com/tuuniversidad/ecommerce/
├── EcommerceMvcApplication.java   # Punto de entrada (main)
├── DataInitializer.java           # Carga productos de prueba al iniciar
├── controller/                    # C: ProductoController, CarritoController
├── model/                         # M: Producto (entidad JPA), Carrito, ItemCarrito
├── repository/                    # M: ProductoRepository (Spring Data JPA)
└── service/                       # M: ProductoService, CarritoService (@SessionScope)
src/main/resources/
├── application.properties         # Configuración (H2, JPA)
└── templates/                     # V: vistas Thymeleaf
    ├── productos/lista.html
    └── carrito/detalle.html
```
