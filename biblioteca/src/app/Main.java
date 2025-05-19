package app;

import enums.EstadoRecurso;
import exceptions.UserYaPrestado;
import exceptions.ResourceNoDisponible;
import exceptions.ResourceNoExiste;
import exceptions.UserNoExiste;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal de la aplicación que proporciona un menú interactivo para que el usuario pueda realizar diferentes acciones sobre los prestamos y devoluciones de recursos.
 *
 * @author Sandy
 * @version 1.0
 */

public class Main {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Instancia del gestor de la biblioteca
     */
    private static final BibliotecaManager manager = new BibliotecaManager();

    /**
     * Instancia de la biblioteca
     */
    private static final Biblioteca biblioteca = new Biblioteca();

    /**
     * Metodo principal que ejecuta el menú interactivo del sistema.
     */
    public static void main(String[] args) {
        // Inicialización de recursos y usuarios
        inicializar();

        Scanner scanner = new Scanner(System.in);

        // Menú del sistema
        System.out.println("-- MENU DEL SISTEMA -- ");
        boolean menu = true;
        while (menu) {

            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Prestar Recurso");
            System.out.println("2. Devolver Recurso");
            System.out.println("3. Listar Recursos Disponibles");
            System.out.println("4. Ver Préstamos Activos");
            System.out.println("5. Ver Devoluciones");
            System.out.println("6. Salir");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer
                switch (opcion) {
                    case 1:
                        prestarRecurso(scanner);
                        break;
                    case 2:
                        devolverRecurso(scanner);
                        break;
                    case 3:
                        listarRecursos();
                        break;
                    case 4:
                        mostrarPrestamos();
                        break;
                    case 5:
                        mostrarDevoluciones();
                        break;
                    case 6:
                        System.out.println("Saliendo del sistema...");
                        menu = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, selecciona una opción entre 1 y 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingresa un número.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
        scanner.close();
    }


    //---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Método que inicializa los recursos y usuarios de la biblioteca.
     */
    private static void inicializar() {
        Libro libro = new Libro(1L, "Harry Potter", "Pepa", EstadoRecurso.DISPONIBLE);
        manager.registrarRecurso(libro);
        manager.registrarUsuario(new Usuario("1", "Lucia Perez"));

        DVD dvd = new DVD(23L, "Forrest Gump", 140, EstadoRecurso.DISPONIBLE);
        manager.registrarRecurso(dvd);
        manager.registrarUsuario(new Usuario("2", "Juan Pérez"));

        Revista revista = new Revista(4L, "Hola", 2, EstadoRecurso.DISPONIBLE);
        manager.registrarRecurso(revista);
        manager.registrarUsuario(new Usuario("3", "Lupita Gonzales"));

        // Configuración de estanterías
        Estanteria estanteriaLibros = new Estanteria("ESTANTERIA-1", "Literatura Romance");
        estanteriaLibros.agregarRecurso(libro);
        biblioteca.agregarEstanteria(estanteriaLibros);

        Estanteria estanteriaDVD = new Estanteria("ESTANTERIA-2", "Accion");
        estanteriaDVD.agregarRecurso(dvd);
        biblioteca.agregarEstanteria(estanteriaDVD);

        Estanteria estanteriaRevistas = new Estanteria("ESTANTERIA-3", "Del Corazon");
        estanteriaRevistas.agregarRecurso(revista);
        biblioteca.agregarEstanteria(estanteriaRevistas);
    }

    /**
     * Metodo que permite prestar un recurso a un usuario.
     */
    private static void prestarRecurso(Scanner scanner) {
        System.out.println("\n--- PRESTAR RECURSO ---");
        System.out.print("ID del usuario: ");
        String usuarioId = scanner.nextLine();
        System.out.print("ID del recurso: ");
        Long recursoId = Long.parseLong(scanner.nextLine());

        try {
            // Creamos los objetos Recurso y Usuario
            RecursoBiblioteca recurso = manager.buscarRecursoPorId(recursoId);
            Usuario usuario = manager.buscarUsuarioPorId(usuarioId);

            // Verificamos si el usuario ya tiene el recurso prestado
            manager.usuarioYaTieneRecursoPrestado(usuario, recurso);

            // realizamos la funcion de prestar  -> si no se lleva a cabo es porque no esta disponible el recurso
            boolean prestado = manager.prestar(recurso, usuario);
            if (prestado) {
                System.out.println("Recurso prestado con éxito.");
            }

        } catch (ResourceNoExiste | ResourceNoDisponible e) {
            System.err.println(e.getMessage());
        } catch (UserYaPrestado | UserNoExiste e) {
            System.err.println(e.getMessage());
        } catch (RuntimeException e) { // Otros errores
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }


    /**
     * Metodo que permite devolver un recurso.
     */
    private static void devolverRecurso(Scanner scanner) {
        System.out.println("\n--- DEVOLVER RECURSO ---");
        System.out.print("Introduce ID del recurso para devolver: ");
        Long recursoId = scanner.nextLong();
        scanner.nextLine();

        try {
            // Creamos objeto Recurso
            RecursoBiblioteca recurso = manager.buscarRecursoPorId(recursoId);

            // Verificamos si el recurso está prestado
            if (recurso.getEstado() != EstadoRecurso.PRESTADO) {
                System.out.println("El recurso no está prestado. No se puede devolver.");
                return;
            }

            if (manager.devolver(recurso)) {
                System.out.println("Devolución registrada con éxito.");
            } else {
                System.out.println("No se pudo registrar la devolución. Intenta nuevamente.");
            }

        } catch (ResourceNoExiste e) {
            System.err.println(e.getMessage());
        } catch (RuntimeException e) { // Otros errores
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Metodo que lista todos los recursos junto su estado.
     */
    private static void listarRecursos() {
        System.out.println("\n--- RECURSOS ESTADO ---");
        manager.getRecursos().values().forEach(recurso -> {
            System.out.println(recurso.getClass().getSimpleName() + ": " +
                    recurso.getTitulo() + " (ID: " + recurso.getId() +
                    ", Estado: " + recurso.getEstado() + ")");
        });
    }

    /**
     * Metodo que muestra todos los préstamos activos.
     */
    private static void mostrarPrestamos() {
        System.out.println("\n--- PRÉSTAMOS ACTIVOS ---");
        List<Registro> prestamosActivos = manager.obtenerPrestamosActivos();

        if (prestamosActivos.isEmpty()) {
            System.out.println("No hay préstamos activos");
            return;
        }

        prestamosActivos.forEach(prestamo -> {
            RecursoBiblioteca recurso = prestamo.getRecurso();
            Usuario usuario = prestamo.getUsuario();
            System.out.println(
                    "Recurso: " + recurso.getTitulo() + " \n" +
                            "Usuario: " + usuario.getNombre() + "\n" +
                            "Fecha prestamo: " + prestamo.getFechaPrestamo().format(DATE_FORMATTER) + "\n" +
                            "Devolver antes: " + prestamo.getFechaDevolucion().format(DATE_FORMATTER) + "\n"
            );
        });
    }

    /**
     * Metodo que muestra todas las devoluciones registradas.
     */
    private static void mostrarDevoluciones() {
        System.out.println("\n--- DEVOLUCIONES ---");

        List<Registro> devoluciones = manager.obtenerDevoluciones();

        if (devoluciones.isEmpty()) {
            System.out.println("No hay devoluciones registradas");
            return;
        }

        devoluciones.forEach(devolucion -> {
            RecursoBiblioteca recurso = devolucion.getRecurso();
            Usuario usuario = devolucion.getUsuario();
            LocalDate fechaDevolucion = devolucion.getFechaDevolucion();

            System.out.println(
                    "Recurso: " + recurso.getTitulo() + "\n" +
                            "Usuario: " + usuario.getNombre() + "\n" +
                            "Fecha de Devolución: " + fechaDevolucion.format(DATE_FORMATTER) + "\n"
            );
        });
    }
}
