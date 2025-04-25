package interfaces;

import model.RecursoBiblioteca;
import model.Usuario;

public interface Prestamista {
    // estos metodos sabemos que van a hacer pero aun no sabemos como van a hacerlo  de eso se encargara la clase que implemente la interfaz junto sus metodos.


    /*
    prestar recibe como parametro un objeto de tipo RecursoBiblioteca  y de Usuario
    se trata de un booleano , returnara true o false
    basicamente sirve para indicar que el prestamo con ese recurso y ese usuario se ha efectuado correctamente o no.
    a su vez el estado del recurso dado por argumento , se marcara de disponible ->prestado */

    boolean prestar(RecursoBiblioteca recurso, Usuario usuario);


    /*
     devolver tiene como parametro un objeto de tipo RecursoBiblioteca
     se trata de un booleano , returnara true o false
     basicamente sirve para indicar que recurso ha sido devuelto  correctamente o no
     a su vez el estado del recurso dado por argumento , se marcara de prestado  -> disponible porque es la inversa*/

    boolean devolver(RecursoBiblioteca recurso);
}
