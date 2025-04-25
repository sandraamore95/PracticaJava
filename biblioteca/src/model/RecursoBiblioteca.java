package model;
import enums.EstadoRecurso;

/**
 * Clase abstracta que representa un recurso en la biblioteca.
 * Los recursos pueden ser de diferentes tipos (como libros, DVDs, etc.)
 * y deben implementar el método {@code descripcion()} para proporcionar información detallada.
 *
 * @author Sandy
 * @version 1.0
 */
public abstract class RecursoBiblioteca {

    /** ID único del recurso */
    private final Long id;

    /** Título del recurso */
    private String titulo;

    /** Estado actual del recurso (disponible, prestado, etc.) */
    private EstadoRecurso estado;

    /**
     * Constructor para crear un recurso con los datos especificados.
     *
     * @param id ID único del recurso.
     * @param titulo Título del recurso.
     * @param estado Estado actual del recurso.
     */
    public RecursoBiblioteca(Long id, String titulo, EstadoRecurso estado) {
        this.id = id;
        this.titulo = titulo;
        this.estado = estado;
    }

    /**
     * Metodo abstracto que debe ser implementado por las clases hijas
     *
     * @return Descripción detallada del recurso.
     */
    public abstract String descripcion();

    /**
     * Obtiene el ID único del recurso.
     *
     * @return ID del recurso.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el título del recurso.
     *
     * @return Título del recurso.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el estado actual del recurso.
     *
     * @return Estado del recurso.
     */
    public EstadoRecurso getEstado() {
        return estado;
    }

    /**
     * Establece el estado del recurso.
     *
     * @param estado Nuevo estado del recurso.
     */
    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }
}
