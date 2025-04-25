#  Sistema de Gestión de Biblioteca

Este proyecto simula un sistema básico de biblioteca que permite registrar usuarios, prestar recursos (libros, revistas, DVDs) y devolverlos. Está desarrollado en Java siguiendo principios de POO.

##  Estructura del proyecto

- `RecursoBiblioteca` (abstracta): clase base para recursos.
- `Libro`, `Revista`, `DVD`: heredan de `RecursoBiblioteca`.
- `Usuario`: representa a un usuario de la biblioteca.
- `Registro`: para registrar los préstamos y devoluciones.
- `BibliotecaManager`: gestiona los recursos y préstamos.
- `Prestamista`: interfaz implementada por `BibliotecaManager`.
- `EstadoRecurso`: enum con estados DISPONIBLE y PRESTADO.

##  Funcionalidades

- Añadir recursos y usuarios
- Prestar y devolver recursos
- Ver recursos disponibles o prestados
- Ver historial de préstamos y devoluciones

##   Métodos Principales
- `PRESTAR` recibe como argumento un Recurso y un Usuario
- `DEVOLVER` recibe como argumento un Recurso


##  Ejecución

El sistema se ejecuta desde una clase `Main` con un menú por consola.

##  Requisitos

- Java 17 o superior
- IDE (Eclipse, IntelliJ, NetBeans) o consola

##  Documentación

Este proyecto incluye documentación JavaDoc.
Para generarla:
```bash
javadoc -d doc *.java