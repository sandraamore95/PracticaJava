import java.util.Scanner;

public class Practicando {

    public static void main(String[] args) {

  
        if (isCapicua("hola")) {
            System.out.println("es capicua");
        } else {
            System.out.println("no es capicua");
        }

        int a = 10;
        int b = a++;
        System.out.println(a + " " + b);

        System.out.println("== EJERCICIOS JAVA ==");

        Scanner scanner = new Scanner(System.in);

        System.out.println("== BUCLE DO WHILE");
        int numero;
        do {
            System.out.println(" ingresa un numero negativo para stop");
            numero = scanner.nextInt();

        } while (numero >= 0);

        System.out.println("stop");

        numero = 0;
        // ahora con el bucle while :
        System.out.println("== BUCLE WHILE ==");
        while (numero >= 0) {

            System.out.println(" ingresa un numero negativo para stop");
            numero = scanner.nextInt();

        }
        scanner.nextLine();

        System.out.print("Introduce una cadena de texto: ");
        String texto = scanner.nextLine();
        System.out.print("Introduce el carácter a contar: ");
        char caracter = scanner.next().charAt(0);
        System.out.println("el caracter aparace " + contadorCaracter(texto, caracter));

        // fizzBuzz();
        int num = 5;
        tablaMultiplicar(num);
        System.out.println("el factorial de " + num + " es " + calculaFactorial(4));
        esPositivo(6);

        num = 6;
        if (esPar(num)) {
            System.out.println(num + " es par");
        } else {
            System.out.println(num + " es impar");
        }

        int sumaN = 3;
        System.out.println("la suma de los N numeros " + sumaValorN(sumaN));

        String palabra = "esternomascloideo";
        System.out.println("la palabra " + palabra + " tiene " + cuantasVocales(palabra) + " vocales");

    }

    // metodos
    private static int calculaFactorial(int num) {
        int factorial = 1;
        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }
        return factorial;

    }

    private static int contadorCaracter(String palabra, char caracter) {
        int total = 0;
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == caracter) {
                total++;
            }
        }
        return total;
    }

    private static void fizzBuzz() {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }

    private static void tablaMultiplicar(int num) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " x " + i + " = " + (num * i));
        }
    }

    private static void esPositivo(int num) {
        if (num > 0) {
            System.out.println("El número es positivo.");
        } else if (num < 0) {
            System.out.println("El número es negativo.");
        } else {
            System.out.println("El número es cero.");
        }
    }

    private static boolean esPar(int num) {
        return num % 2 == 0;
    }

    /// Escribe un programa que sume los primeros N números naturales.
    private static int sumaValorN(int num) {
        // 6
        int suma = 0;
        for (int i = 1; i <= num; i++) {
            suma += i;
        }
        return suma;
    }

    private static int cuantasVocales(String palabra) {
        // recorremos la palabra
        int total = 0;
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
                total++;
            }
        }
        return total;
    }

    public static boolean isCapicua(String palabra) {
     
        String palabraReverse = "";
        for (int i = palabra.length()-1 ; i >= 0; i--) {
            char letra = palabra.charAt(i);
            palabraReverse = palabraReverse + letra;

        }
        // comprar las dos palabras
        if (palabra.equals(palabraReverse)) {
            return true;
        }
        return false;

    }

    /*
     * SOBRECARGARGA - METODO LLAMADO IGUAL DIFERENTE FIRMA
     * 
     * 
     * public static int contadorDeVocales(String palabra) {
     * int cont = 0;
     * for(int i = 0; i<palabra.length(); i++) {
     * if (palabra.charAt(i) == 'e' || palabra.charAt(i) == 'a' || palabra.charAt(i)
     * == 'i' || palabra.charAt(i) == 'o' ||palabra.charAt(i) == 'u' ) {
     * cont++;
     * }
     * }
     * return cont;
     * }
     * 
     * 
     * public static int contadorDeVocales(String palabra, char letra) {
     * int cont = 0;
     * 
     * for (int i = 0; i<palabra.length(); i++) {
     * if (palabra.charAt(i) == letra) {
     * cont++;
     * }
     * }
     * return cont;
     * }
     * 
     * 
     * 
     */

}
