package model;
import enums.EstadoRecurso;

/**
 * Representa un libro en la biblioteca, que hereda de {@link RecursoBiblioteca}.

 * Un libro tiene un autor, un título, un estado (disponible, prestado, etc.), y puede ser descrito
 * con la información de su título, autor y estado.
 *
 * @author Sandy
 * @version 1.0
 */
public class Libro extends RecursoBiblioteca {

    /** Autor del libro */
    private String autor;

    /**
     * Crea un nuevo libro con el ID, título, autor y estado especificados.
     * Llama al constructor de la clase base {@link RecursoBiblioteca} para establecer el ID, título y estado.
     *
     * @param id ID único del recurso.
     * @param titulo Título del libro.
     * @param autor Autor del libro.
     * @param estado Estado del recurso (disponible, prestado, etc.).
     */
    public Libro(Long id, String titulo, String autor, EstadoRecurso estado) {
        super(id, titulo, estado); // Llama al constructor del padre
        this.autor = autor;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return El autor del libro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Devuelve una descripción del libro, incluyendo el título, el autor y el estado.
     *
     * @return Descripción completa del libro.
     */
    @Override
    public String descripcion() {
        return "Libro: " + getTitulo() + ", Autor: " + this.autor + ", Estado: " + getEstado();
    }
}
