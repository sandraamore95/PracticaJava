package model;

/**
 * Representa a un usuario de la biblioteca.
 * Cada usuario tiene un identificador único y un nombre.
 *
 * @author Sandy
 * @version 1.0
 */
public class Usuario {

    /** Identificador único del usuario */
    private String id;

    /** Nombre del usuario */
    private String nombre;

    /**
     * Crea un nuevo usuario con los datos especificados.
     *
     * @param id ID único del usuario.
     * @param nombre Nombre del usuario.
     */
    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID único del usuario.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }
}
