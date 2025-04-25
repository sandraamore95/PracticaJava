package model;
import enums.EstadoRecurso;

/**
 * Representa una revista dentro de la biblioteca.
 * Hereda de la clase {@link RecursoBiblioteca} y agrega la propiedad de número de edición.
 *
 * @author Sandy
 * @version 1.0
 */
public class Revista extends RecursoBiblioteca {

    /** Número de edición de la revista */
    private int numeroEdicion;

    /**
     * Crea una nueva revista con los datos especificados.
     * Llama al constructor de la clase base {@link RecursoBiblioteca} para establecer el ID, título y estado.
     * @param id ID único del recurso.
     * @param titulo Título de la revista.
     * @param numeroEdicion Número de edición de la revista.
     * @param estado Estado del recurso (disponible, prestado, etc.).
     */
    public Revista(Long id, String titulo, int numeroEdicion, EstadoRecurso estado) {
        super(id, titulo, estado); // Llama al constructor del padre
        this.numeroEdicion = numeroEdicion;
    }

    /**
     * Obtiene el número de edición de la revista.
     *
     * @return El número de edición de la revista.
     */
    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    /**
     * Devuelve una descripción de la revista, incluyendo el título y el número de edición.
     *
     * @return Una cadena con la descripción de la revista.
     */
    @Override
    public String descripcion() {
        return "Revista: " + getTitulo() + " - Edición: " + this.numeroEdicion;
    }
}
