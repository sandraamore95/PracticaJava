package model;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa una biblioteca que contiene una colección de estanterías.
 *
 * @author Sandy
 * @version 1.0
 * @see Estanteria
 */
public class Biblioteca {

    /**
     * Mapa que almacena las estanterías, usando el ID como clave.
     */
    private Map<String, Estanteria> estanterias;

    /**
     * Constructor que inicializa la biblioteca con una colección vacía de estanterías.
     */
    public Biblioteca() {
        this.estanterias = new HashMap<>();
    }

    /**
     * Agrega una estantería a la biblioteca.
     *
     * @param estanteria Estantería que se desea agregar.
     */
    public void agregarEstanteria(Estanteria estanteria) {
        estanterias.put(estanteria.getId(), estanteria);
    }

    /**
     * Devuelve el mapa de estanterías de la biblioteca.
     *
     * @return Mapa de estanterías con sus identificadores.
     */
    public Map<String, Estanteria> getEstanterias() {
        return estanterias;
    }
}
