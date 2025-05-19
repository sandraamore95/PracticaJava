package model;

import enums.EstadoRecurso;
import exceptions.ResourceNoDisponible;
import exceptions.ResourceNoExiste;
import exceptions.UserNoExiste;
import exceptions.UserYaPrestado;
import interfaces.Prestamista;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase que gestiona los recursos y usuarios de una biblioteca.
 *
 * @author Sandy
 * @version 1.0
 * @see RecursoBiblioteca
 * @see Usuario
 * @see Registro
 */
public class BibliotecaManager implements Prestamista {

    /**
     * Colección de recursos bibliotecarios.
     */
    private Map<Long, RecursoBiblioteca> recursos;

    /**
     * Colección de usuarios registrados.
     */
    private Map<String, Usuario> usuarios;

    /**
     * Lista de registros de préstamos y devoluciones.
     */
    private List<Registro> registros;

    /**
     * Constructor que inicializa las colecciones internas vacías.
     */
    public BibliotecaManager() {
        this.recursos = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.registros = new ArrayList<>();
    }

    /**
     * Registra un nuevo recurso en la biblioteca.
     *
     * @param recurso Recurso a registrar.
     * @throws IllegalArgumentException si el recurso ya existe.
     */
    public void registrarRecurso(RecursoBiblioteca recurso) {
        if (recursos.containsKey(recurso.getId())) {
            throw new IllegalArgumentException("Recurso ya existe : " + recurso.getId());
        }
        recursos.put(recurso.getId(), recurso);
    }

    /**
     * Registra un nuevo usuario en la biblioteca.
     *
     * @param usuario Usuario a registrar.
     */
    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    /**
     * Devuelve todos los recursos registrados.
     *
     * @return Mapa de recursos.
     */
    public Map<Long, RecursoBiblioteca> getRecursos() {
        return recursos;
    }

    /**
     * Devuelve todos los usuarios registrados.
     *
     * @return Mapa de usuarios.
     */
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Intenta prestar un recurso a un usuario.
     *
     * @param recurso Recurso a prestar.
     * @param usuario Usuario que toma el préstamo.
     * @return true si el préstamo se realizó, false si el recurso no estaba disponible.
     */
    public boolean prestar(RecursoBiblioteca recurso, Usuario usuario) throws ResourceNoDisponible {
        if (recurso.getEstado() == EstadoRecurso.DISPONIBLE) {
            recurso.setEstado(EstadoRecurso.PRESTADO);

            LocalDate fechaPrestamo = LocalDate.now();
            LocalDate fechaDevolucion = fechaPrestamo.plusDays(10);


            Registro registro = new Registro(usuario, recurso, fechaPrestamo, fechaDevolucion);

            registros.add(registro);
            return true;
        } else {
            throw new ResourceNoDisponible("El recurso no está disponible para préstamo.");
        }
    }

    /**
     * Registra la devolución de un recurso.
     *
     * @param recurso Recurso que se desea devolver.
     * @return true si la devolución se registró, false si no se encontró préstamo activo.
     */
    public boolean devolver(RecursoBiblioteca recurso) {
        if (recurso == null) {
        throw new IllegalArgumentException("El recurso no puede ser null");
    }
        recurso.setEstado(EstadoRecurso.DISPONIBLE);

        // Buscar el registro activo del recurso
        Registro prestamoActivo = registros.stream()
                .filter(registro -> !registro.esDevolucion() && registro.getRecurso().equals(recurso))
                .findFirst()
                .orElse(null);

        if (prestamoActivo != null) {
            Registro devolucion = new Registro(prestamoActivo.getUsuario(), recurso, prestamoActivo.getFechaPrestamo(), LocalDate.now());
            registros.add(devolucion);
            registros.remove(prestamoActivo);

            return true;
        } else {
            System.out.println("No se encontró un préstamo activo para este recurso.");
            return false;
        }
    }

    /**
     * Devuelve la lista de préstamos activos.
     *
     * @return Lista de {@link Registro} de prestamos.
     */
    public List<Registro> obtenerPrestamosActivos() {
        return registros.stream()
                .filter(registro -> !registro.esDevolucion())
                .collect(Collectors.toList());
    }

    /**
     * Devuelve la lista de devoluciones realizadas.
     *
     * @return Lista de {@link Registro} de devoluciones.
     */
    public List<Registro> obtenerDevoluciones() {
        return registros.stream()
                .filter(Registro::esDevolucion)
                .collect(Collectors.toList());
    }

    /**
     * Busca un recurso por su ID.
     *
     * @param id ID del recurso.
     * @return {@link RecursoBiblioteca}
     */
    public RecursoBiblioteca buscarRecursoPorId(Long id) {
        RecursoBiblioteca recurso = recursos.get(id);
        if (recurso == null) {
            throw new ResourceNoExiste("El recurso con ID " + id + " no existe.");
        }
        return recurso;
    }

    /**
     * Busca un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return {@link Usuario}
     */
    public Usuario buscarUsuarioPorId(String id) {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            throw new UserNoExiste("El usuario con ID " + id + " no existe.");
        }
        return usuario;
    }

    public void usuarioYaTieneRecursoPrestado(Usuario usuario, RecursoBiblioteca recurso) throws UserYaPrestado {
        boolean tieneRecurso = obtenerPrestamosActivos().stream()
                .anyMatch(p -> p.getUsuario().getId().equals(usuario.getId())
                        && p.getRecurso().getId().equals(recurso.getId()));
        if (tieneRecurso) {
            throw new UserYaPrestado("El usuario ya tiene este recurso prestado.");
        }
    }

}
