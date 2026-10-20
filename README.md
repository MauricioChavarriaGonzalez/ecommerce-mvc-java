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

