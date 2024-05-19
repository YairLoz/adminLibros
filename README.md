# Poyecto Adminitrador Libros
Este un proyecto de ejemplo que utiliza Spring Boot 3.2 y Maven para construir una aplicación web básica para la administración de libros, los datos se almacenan en un archivo JSON.

## Requisitos previos
- Java Development Kit (JDK) 17 o superior
- Apache Maven 3.6.0 o superior
- Un IDE o editor de codigo.

## Instalación (Usando VS Code)
1. Clona el repositorio a tu máquina local.
2. Asegúrate de tener instalada la extensión de Java para VS Code.
3. Abre VS Code y navega a la carpeta del proyecto.

## Seguridad (Autenticación Básica)
Todas las rutas de la API están protegidas con Spring Security utilizando autenticación básica. Para fines de desarrollo, se utilizan las siguientes credenciales:

Usuario: `user`
Password: `admin`

## Ejecución (Usando VS Code)
1. Presiona click derecho sobre el codigo y utiliza `run java` para correr la aplicacion.
2. La aplicación se ejecutará y estará disponible en `http://localhost:8080`.

## Uso de Autenticación Básica en Postman
1. Ve a la pestaña "Authorization".
2. Selecciona "Basic Auth" en el menú desplegable.
3. Ingresa el nombre de usuario (user) y la contraseña (password).
4. Envía la petición.

## Documentación de la API
Puedes encontrar la documentación de los endpoints en el siguiente enlace:
```
https://documenter.getpostman.com/view/27404731/2sA3QmCZgC
```

