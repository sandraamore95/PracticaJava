package model;

import java.time.LocalDate;

/**
 * Representa un registro de préstamo / devolución  en la biblioteca.
 *
 * @author Sandy
 * @version 1.0
 */
public class Registro {

    /** Usuario que realiza el préstamo */
    private final Usuario usuario;

    /** Recurso que fue prestado */
    private final RecursoBiblioteca recurso;

    /** Fecha en la que se realizó el préstamo */
    private final LocalDate fechaPrestamo;

    /** Fecha en la que se devolvió el recurso (puede ser null si no se ha devuelto) */
    private final LocalDate fechaDevolucion;

    /**
     * Crea un nuevo registro de préstamo.
     *
     * @param usuario Usuario que realiza el préstamo.
     * @param recurso Recurso prestado.
     * @param fechaPrestamo Fecha en que se realizó el préstamo.
     * @param fechaDevolucion Fecha en que se devolvió el recurso (puede ser null).
     */
    public Registro(Usuario usuario, RecursoBiblioteca recurso, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    /*
     * @return {@code true} si la devolución ya se realizó; {@code false} en caso contrario.
     */
    public boolean esDevolucion() {
        return fechaDevolucion != null && !fechaDevolucion.isAfter(LocalDate.now());
    }

    /**
     * @return Fecha de préstamo.
     */
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * @return Fecha de devolución, o {@code null} si no ha sido devuelto.
     */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * @return Usuario del préstamo.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @return Recurso del préstamo.
     */
    public RecursoBiblioteca getRecurso() {
        return recurso;
    }
}
