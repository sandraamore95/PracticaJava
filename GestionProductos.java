import java.util.Scanner;

public class GestionProductos {

    /*
     * Enunciado:
     * Crea un programa que gestione un pequeño inventario de productos (máximo 10).
     * Cada producto tiene un nombre, cantidad y precio.
     * 
     * El programa debe permitir:
     * 
     * Añadir productos (si hay espacio).
     * 
     * Mostrar la lista de productos.
     * 
     * Buscar un producto por nombre.
     * 
     * Actualizar el stock de un producto.
     * 
     * Calcular el valor total del inventario.
     * 
     * Requisitos:
     * Uso de arrays paralelos (String[] nombres, int[] cantidades, double[]
     * precios).
     * 
     * Menú principal con opciones.
     * 
     * Métodos para cada operación.
     * 
     * Validar entradas: evitar que el número de productos supere el máximo.
     */

    // declaramos la clase Producto con sus propiedades :

    public static class Producto {
        String nombre;
        int cantidad;
        double precio;

        public Producto(String nombre, int cantidad, double precio) {
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.precio = precio;
        }

        // Getters y Setters
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        @Override
        public String toString() {
            return String.format(
                    "Nombre: %-15s | Cantidad: %-4d | Precio: $%-6.2f",
                    nombre, cantidad, precio);
        }
    }

    private static Scanner scanner = new Scanner(System.in);
    // creamos un array de 10 productos inicializando a vacio

    private static final int MAX_PRODUCTOS = 10; // 10 productos maximo
    private static Producto[] productos = new Producto[MAX_PRODUCTOS];
    private static int contadorProductos = 0;

    public static void main(String[] args) {

        int opcion;
        do {
            menu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> addProducto();
                case 2 -> mostrarProductos();
                case 3 -> buscarProducto();
                case 4 -> actualizarStock();
                case 5 -> calcularValorTotal();
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 6); // si se introduce una opcion no valida del menu , se repite la accion.
    }

    // METODOS
    private static void menu() {
        System.out.println("Programa de gestion de productos");
        System.out.println("1. Añadir producto");
        System.out.println("2. Mostrar inventario");
        System.out.println("3. Buscar producto");
        System.out.println("4. Actualizar stock");
        System.out.println("5. Calcular valor total");
        System.out.println("6. Salir");
        System.out.print("Elige: ");
    }

    private static void addProducto() {

        // mirar si esta al tope el inventario
        if (contadorProductos != MAX_PRODUCTOS) {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();

            System.out.print("Precio: ");
            double precio = scanner.nextDouble();

            // ahora creamos el objeto de producto [posicion] con las propiedades asignadas
            productos[contadorProductos] = new Producto(nombre, cantidad, precio);
            contadorProductos++;
            System.out.println("Producto añadido!");
        } else {
            System.out.println("el inventario esta lleno!!!!");
        }

    }

    private static void mostrarProductos() {
        System.out.println("Asi queda la lista de productos : ");
        if (contadorProductos == 0) {
            System.out.println("No tiene productos en el inventario");
        }
        for (int i = 0; i < contadorProductos; i++) {
            System.out.println((i + 1) + " : " + productos[i]);
        }
    }

    private static void buscarProducto() {
        System.out.print("Nombre a buscar: ");
        String nombre = scanner.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < contadorProductos; i++) {
            if (productos[i].getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Producto encontrado:\n" + productos[i]);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado");
        }
    }

    private static void actualizarStock() {

        // lista de productos para elegir que producto quiere actualizar
        mostrarProductos();
        System.out.print("Escoge el producto para actualizar stock : ");
        int opcion = scanner.nextInt() - 1;
        if (opcion >= 0 && opcion < contadorProductos) {
            System.out.print("Nueva cantidad: ");
            int quantity = scanner.nextInt();
            productos[opcion].setCantidad(quantity);
            System.out.println("Producto actualizado");
        } else {
            System.out.println("El producto no existe");
        }

    }

    private static void calcularValorTotal() {
        double total = 0;
        for (int i = 0; i < contadorProductos; i++) {
            total += productos[i].getCantidad() * productos[i].getPrecio();
        }
        System.out.printf("Valor total del inventario: $%.2f\n", total);
    }

}
