# Proyecto 501 – Sistema de Reservas, Usuarios y Comentarios

Este proyecto implementa un sistema distribuido basado en microservicios que permite gestionar usuarios, reservas de hoteles y comentarios asociados a dichas reservas. El sistema está compuesto por cinco módulos independientes que se comunican entre sí mediante Eureka y un API Gateway.

---

#  Módulos implementados

A continuación se describen los módulos incluidos en el proyecto y su función dentro del sistema.

---

## 1.  Servidor Eureka
- **Nombre del servicio:** eureka
- **Función:** Registro y descubrimiento de microservicios.
- **Características:**
    - Ejecutándose en el puerto configurado (p.ej. 8500).
    - Los microservicios `usuarios`, `reservas`, `comentarios` y `gateway` lo utilizan para descubrimiento.
- **Puntuación asociada:** **0.2 puntos**

---

## 2.  Servicio de Usuarios
- **Nombre del servicio:** usuarios
- **Función:** Gestión de usuarios y validación de credenciales.
- **Características técnicas:**
    - API REST.
    - Estructura: **Controlador – Servicio – Repositorio**.
    - Registrado en Eureka.
    - Implementa todos los métodos requeridos:
        - `crearUsuario`
        - `actualizarUsuario`
        - `eliminarUsuario`
        - `validarUsuario`
        - `obtenerInfoUsuarioPorId`
        - `obtenerInfoUsuarioPorNombre`
        - `checkIfExist`
- **Puntuación máxima:** **0.85 puntos**

---

## 3.  Servicio de Reservas
- **Nombre del servicio:** reservas
- **Función:** Gestión de hoteles, habitaciones y reservas.
- **Características técnicas:**
    - API REST.
    - Estructura: **Controlador – Servicio – Repositorio**.
    - Registrado en Eureka.
    - Implementa todas las funcionalidades requeridas:
        - Gestión de hoteles (crear, actualizar, eliminar)
        - Gestión de habitaciones (crear, actualizar, eliminar)
        - Gestión de reservas (crear, cambiar estado)
        - Consultas:
            - `obtenerIdApartirNombre`
            - `obtenerNombreAPartirId`
            - `listarReservasUsuario`
            - `listarReservasSegunEstado`
            - `checkReserva`
- **Puntuación máxima:** **1.25 puntos**

---

## 4.  Servicio de Comentarios
- **Nombre del servicio:** comentarios
- **Función:** Gestión de comentarios de usuarios sobre reservas.
- **Características técnicas:**
    - API **GraphQL** con **GraphiQL** habilitado.
    - Base de datos **MongoDB** (`comentariosProyecto`, colección `comentarios`).
    - Estructura: **Resolvers – Servicio – Repositorio**.
    - Se comunica con:
        - Servicio Usuarios → validación y obtención de `idUsuario`.
        - Servicio Reservas → obtención de `idHotel` y validación `checkReserva`.
    - Implementa todas las funcionalidades requeridas:
        - `crearComentario`
        - `eliminarComentarios`
        - `eliminarComentarioDeUsuario`
        - `listarComentariosHotel`
        - `listarComentariosUsuario`
        - `mostrarComentarioUsuarioReserva`
        - `puntuacionMediaHotel`
        - `puntuacionesMediasUsuario`
- **Puntuación máxima:** **1.5 puntos**

---

## 5.  API Gateway
- **Nombre del servicio:** gateway
- **Función:** Punto de entrada único al sistema.
- **Características técnicas:**
    - Basado en **Spring Cloud Gateway**.
    - Ejecutándose en el puerto **8080**.
    - Utiliza Eureka para descubrir microservicios.
    - Expone:
        - API REST de:
            - `/usuarios/**`
            - `/reservas/**`
            - `/comentarios/**`
        - GraphQL del servicio Comentarios:
            - `/graphql`
        - GraphiQL del servicio Comentarios:
            - `/comentarios`
- **Puntuación máxima:** **0.2 puntos**

---

#  Puntuación máxima a la que opta este proyecto

| Módulo | Puntuación |
|--------|------------|
| Servicio Comentarios | **1.5 puntos** |
| Servicio Usuarios | **0.85 puntos** |
| Servicio Reservas | **1.25 puntos** |
| Eureka Server + Client | **0.2 puntos** |
| API Gateway | **0.2 puntos** |
| **TOTAL** | **4 puntos (máxima puntuación posible)** |

Este proyecto implementa **todos los módulos requeridos**, cumpliendo todas las especificaciones técnicas y funcionales, por lo que opta a la **puntuación total de 4 puntos**.

---

# ️ Ejecución del sistema

1. Iniciar **Eureka Server**.
2. Iniciar los microservicios:
    - `usuarios`
    - `reservas`
    - `comentarios`
3. Iniciar el **ApiGateway**.
4. Acceso:
    - REST: `http://localhost:8080/...`
    - GraphQL: `http://localhost:8080/graphql`
    - GraphiQL: `http://localhost:8080/comentarios`










