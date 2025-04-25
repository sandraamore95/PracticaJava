package model;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una estantería en la biblioteca, que contiene múltiples recursos.

 * Cada estantería tiene un identificador único y un nombre, y puede almacenar una lista de recursos
 * de la clase {@link RecursoBiblioteca}.
 *
 * @author Sandy
 * @version 1.0
 */
public class Estanteria { // contiene varios RecursosBiblioteca

    /** ID único de la estantería */
    private String id;

    /** Nombre de la estantería */
    private String nombre;

    /** Lista de recursos que contiene la estantería */
    private List<RecursoBiblioteca> recursos;

    /**
     * Crea una nueva estantería con el ID y nombre especificados.
     * Inicializa la lista de recursos vacía.
     *
     * @param id ID único de la estantería.
     * @param nombre Nombre de la estantería.
     */
    public Estanteria(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.recursos = new ArrayList<>();
    }

    /**
     * Agrega un recurso a la estantería.
     *
     * @param recurso Recurso a agregar a la estantería.
     */
    public void agregarRecurso(RecursoBiblioteca recurso) {
        recursos.add(recurso);
    }

    /**
     * Obtiene el ID de la estantería.
     *
     * @return El ID de la estantería.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre de la estantería.
     *
     * @return El nombre de la estantería.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la lista de recursos que contiene la estantería.
     *
     * @return Lista de recursos en la estantería.
     */
    public List<RecursoBiblioteca> getRecursos() {
        return recursos;
    }
}
