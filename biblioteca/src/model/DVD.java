package model;
import enums.EstadoRecurso;

/**
 * Representa un recurso de tipo DVD en la biblioteca.
 * Hereda de {@link RecursoBiblioteca}.

 * Contiene información adicional como la duración en minutos del DVD.
 *
 * @author Sandy
 * @version 1.0
 */
public class DVD extends RecursoBiblioteca {

    /** Duración del DVD en minutos. */
    private int duracionMinutos;

    /**
     * Crea un nuevo DVD con los datos especificados.
     * Llama al constructor de la clase base {@link RecursoBiblioteca} para establecer el ID, título y estado.
     *
     * @param id ID único del recurso.
     * @param titulo Título del DVD.
     * @param duracionMinutos Duración en minutos del DVD.
     * @param estado Estado del recurso (disponible, prestado, etc.).
     */
    public DVD(Long id, String titulo, int duracionMinutos, EstadoRecurso estado) {
        super(id, titulo, estado); // Llama al constructor del padre
        this.duracionMinutos = duracionMinutos;
    }

    /**
     * Devuelve la duración del DVD.
     *
     * @return Duración en minutos.
     */
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    /**
     * Devuelve una descripción del DVD.
     *
     * @return Texto con el título y duración del DVD.
     */
    @Override
    public String descripcion() {
        return "DVD : " + getTitulo() + ", Duración: " + duracionMinutos + " minutos";
    }
}
