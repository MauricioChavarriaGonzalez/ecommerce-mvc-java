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
Este proyecto consiste en una aplicación web de comercio electrónico (E-commerce) desarrollada en **Java** utilizando el framework **Spring Boot**. La aplicación permite a los usuarios visualizar un catálogo de productos, agregarlos a un carrito de compras (persistido en la sesión del usuario), eliminarlos y procesar una compra. 

A diferencia de una simulación simple, al procesar la compra, el sistema **genera una Orden de compra real y la guarda en la base de datos**, registrando el detalle de los productos, el precio histórico y el total pagado.

La solución se construyó aplicando estrictamente el patrón de arquitectura **Modelo-Vista-Controlador (MVC)**, garantizando un código modular, mantenible y escalable.

## 🛠️ Tecnologías Utilizadas
*   **Lenguaje:** Java 17 (JDK 17)
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
Representa los datos y la lógica de negocio.
*   **Entidades (`model`):** `Producto.java`, `Carrito.java`, `ItemCarrito.java`, `Orden.java` y `DetalleOrden.java`. Se encargan de estructurar la información.
*   **Repositorios (`repository`):** `ProductoRepository.java` y `OrdenRepository.java`. Interfaces que heredan de `JpaRepository` para abstraer el acceso a la base de datos H2 sin escribir SQL manual.
*   **Servicios (`service`):** `ProductoService.java`. Contiene la lógica de negocio. *(El carrito se maneja directamente en la sesión HTTP).*

### 2. Vista (View)
Representa la interfaz gráfica con la que interactúa el usuario.
*   Implementada mediante **Thymeleaf** (archivos `.html` en `src/main/resources/templates`).
*   `productos/lista.html`: Catálogo de productos con contador de carrito dinámico.
*   `carrito/detalle.html`: Vista del carrito de compras con el cálculo de subtotales y total en tiempo real.

### 3. Controlador (Controller)
Actúa como intermediario entre el Modelo y la Vista.
*   `ProductoController.java`: Gestiona la ruta principal (`/` y `/productos`).
*   `CarritoController.java`: Maneja las peticiones de agregar, eliminar, ver el carrito y procesar la compra. Utiliza `HttpSession` para garantizar que el carrito no se pierda al navegar.

---

## ♻️ Reutilización y Calidad de Código

*   **Inyección de Dependencias (IoC):** Se implementó la inyección por constructor (`Constructor Injection`), lo que es una buena práctica recomendada por Spring para facilitar las pruebas unitarias y garantizar la inmutabilidad de las dependencias.
*   **Componentes Reutilizables:** La clase `Carrito` encapsula la lógica de agregar y eliminar productos, evitando duplicidad de código en los controladores.
*   **Manejo de Sesión (`HttpSession`):** Asegura que cada usuario (cada navegador) tenga un carrito independiente y privado, sin necesidad de usar bases de datos para el carrito temporal.
*   **Formato Profesional:** Se utilizó `BigDecimal` para el manejo de precios (evitando errores de precisión de los tipos `double`/`float`) y se guarda el precio histórico en la orden para que no se altere si el producto cambia de precio en el futuro.

---

## 🚀 Instrucciones de Uso

### 1. Requisitos previos
*   **JDK 17 o superior** instalado. Verificar con `java -version`.
*   La variable de entorno `JAVA_HOME` debe apuntar a la carpeta del JDK.
*   **No es necesario instalar Maven**: el proyecto incluye el *Maven Wrapper* (`mvnw` / `mvnw.cmd`).

### 2. Obtener el código
```bash
git clone <URL-del-repositorio>
cd ecommerce-mvc-java 