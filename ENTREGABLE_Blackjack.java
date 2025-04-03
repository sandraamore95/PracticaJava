import java.util.Random;
import java.util.Scanner;

public class ENTREGABLE_Blackjack {

    // Variables de clase
    static String[] deck = new String[52];
    static int deckIndex = 0;
    private static final String[] PALOS = { "♠", "♥", "♦", "♣" };
    private static final String[] RANGOS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

    // método main
    public static void main(String[] args) {

        // Inicializar y barajar el mazo
        iniciarBaraja();
        barajarMazo(deck);

        // Manos de jugador y dealer (se asume máximo 12 cartas por mano)
        String[] jugador = new String[12];
        String[] dealer = new String[12];
        int mazoJugador = 0;
        int mazoDealer = 0;
        boolean mePlanto = false;

        // Repartir dos cartas a cada uno - rellenamos el array del jugador en posicion
        // 0 y 1
        jugador[mazoJugador++] = repartirCarta();  // Asigna a posición 0 y luego incrementa a 1
        jugador[mazoJugador++] = repartirCarta();
        // rellenamos el array del dealer en posicion 0 y 1
        dealer[mazoDealer++] = repartirCarta();
        dealer[mazoDealer++] = repartirCarta();

        // Muestra mensajes de inicialización del juego
        System.out.println("=== EMPIEZA EL JUEGO - BLACKJACK ===\n");
        System.out.println("Barajando cartas...");

        /*
         * = TURNO JUGADOR =
         * 
         * REQUISITOS :
         * Muestra al jugador sus cartas y el valor total de su mano.
         * Pregunta si desea "pedir carta" (hit) o "plantarse" (stand)
         * Si pide carta, añade una nueva carta a su mano
         * Una de las cartas del dealer debe mostrarse y la otra debe
         * permanecer oculta durante el turno del jugador.
         * 
         * CONDICIONES :
         * - El mazo de un jugador puede contener 12 cartas como máximo.
         * - Si el valor total supera 21, el jugador pierde automáticamente.
         * - El jugador puede seguir pidiendo cartas mientras no se plante ni se pase de
         * 21.
         * 
         */
       

        // Turno del jugador
        Scanner scanner = new Scanner(System.in);
        // mientras que no supera las 12 cartas && no se rinde && valor de mazo es menor
        // de 21 -- puede seguir pidiendo cartas
        while (mazoJugador < 12 && !mePlanto && calcularValorMano(jugador, mazoJugador) <= 21) {
            int valorActual = calcularValorMano(jugador, mazoJugador);

            // Verificar si ya se pasó de 21
            if (valorActual > 21) {
                System.out.println("\nJugador has perdido! tu mazo supera el 21");
                return;
            }

            System.out.println("\n--- TU TURNO ---");
            System.out.println("\nTus cartas:");
            imprimeMano(jugador, mazoJugador, false); // se muestra toda la mano
            System.out.println("\nValor total: " + valorActual);
            System.out.println("Cartas del dealer:");
            imprimeMano(dealer, mazoDealer, true);
          

            System.out.println("\n¿Que deseas hacer?");
            System.out.println("1. Pedir carta (Hit)");
            System.out.println("2. Plantarse (Stand)");
            System.out.print("Elige una opción (1 o 2): ");

            int opcion;
            while (true) {
                while (!scanner.hasNextInt()) {
                    System.out.println("Ingresa  opcion (1 o 2)");
                    scanner.next();
                }
                opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion == 1 || opcion == 2)
                    break;
                System.out.println("Opción no válida. Por favor elige 1 o 2.");
            }

            if (opcion == 1) {
                jugador[mazoJugador] = repartirCarta();
                mazoJugador++;
                System.out.println("\nHas recibido: " + jugador[mazoJugador - 1]);
            } else {
                mePlanto = true;
                System.out.println("\nTe has plantado con " + valorActual + " puntos.");
            }
        }

        // ---------------------------------------
        System.out.println("\n=== TURNO DEL DEALER ===");
        System.out.println("Cartas del dealer:");
        imprimeMano(dealer, mazoDealer, false); // aqui si se muestran todas las cartas porque es su turno
        int valorDealer = calcularValorMano(dealer, mazoDealer);
        System.out.println("\nValor total: " + valorDealer);
        // El dealer pide cartas hasta alcanzar al menos 17
        while (valorDealer < 17 && mazoDealer < 12) {
            dealer[mazoDealer] = repartirCarta();
            mazoDealer++;
            System.out.println("\nEl dealer pide carta: " + dealer[mazoDealer - 1]);
            valorDealer = calcularValorMano(dealer, mazoDealer);
            System.out.println("Nuevo valor total: " + valorDealer);
        }

        // Mostrar resultados finales
        resultadoJuego(jugador, mazoJugador, dealer, mazoDealer);

    }

    // ===METODOS===
    // Inicializa el mazo con 52 cartas (combinando rangos y palos)
    private static void iniciarBaraja() {
        int index = 0;
        for (String palo : PALOS) {
            for (String rango : RANGOS) {
                deck[index++] = rango + palo;
            }
        }
    }

    // Baraja el mazo utilizando el algoritmo de Fisher-Yates
    private static void barajarMazo(String[] mazo) {
        Random random = new Random();
        // algoritmo Fisher-Yates
        for (int i = mazo.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            String temp = mazo[i];
            mazo[i] = mazo[j];
            mazo[j] = temp;
        }
    }

    // Devuelve la siguiente carta del mazo
    private static String repartirCarta() {
        if (deckIndex >= deck.length) {
            iniciarBaraja();
            barajarMazo(deck); // carta random
            deckIndex = 0;
        }
        return deck[deckIndex++];
    }

    // Devuelve el valor inicial de la carta (A se considera 11; se ajustará luego)
    // Se asume que card es algo como "A♠", "10♥", "J♦", etc.
    private static int calculaValorInicial(String carta) {
        // sacamos el rango de la carta
        String rango = carta.substring(0, carta.length() - 1);

        switch (rango) {
            case "A":
                return 11;
            case "K":
            case "Q":
            case "J":
                return 10;
            default:
                return Integer.parseInt(rango);
        }
    }

    // Calcula el valor total de una mano, ajustando el valor de los ases si es
    // necesario
    public static int calcularValorMano(String[] mano, int numCartas) {
        int total = 0;
        int asesCont = 0;
        
        for (int i = 0; i < numCartas; i++) {
            String carta = mano[i];
            int valor = calculaValorInicial(carta);
            total += valor;
            
            if (carta.startsWith("A")) {
                asesCont++;
            }
        }
        
        return ajustarValorAses(total, asesCont);
    }

    // Ajustar el valor de los ases si se excede 21
    /*
     * - Las cartas J, Q, K valen 10 puntos.
     * - Los ases (A) valen 11 puntos, pero si la suma total supera 21, el valor del
     * As se ajusta a 1.
     * - Implementa un método que calcule correctamente el valor total de una mano,
     * teniendo en cuenta este comportamiento especial de los Ases.
     * 
     */

    private static int ajustarValorAses(int total, int asesCont) {
        while (total > 21 && asesCont > 0) {
            total -= 10; // para que A valga 1
            asesCont--;
        }
        return total;
    }

    // Imprime las cartas de una mano
    // si es dealer durante el turno del jugador - mano oculta
    // si es dealer durante turno dealer  - mano entera
    private static void imprimeMano(String[] mano, int numCartas, boolean manoOculta) {
         if (manoOculta && numCartas > 0) {
        System.out.print(mano[0] + " ");
        for (int i = 1; i < numCartas; i++) {
            System.out.print("[X] ");
        }
    } else {
        for (int i = 0; i < numCartas; i++) {
            System.out.print(mano[i] + " ");
        }
    }
    }

    private static void resultadoJuego(String[] jugador, int mazoJugador,
            String[] dealer, int mazoDealer) {
        int valorJugador = calcularValorMano(jugador,mazoJugador);
        int valorDealer = calcularValorMano(dealer,mazoDealer);

        System.out.println("\n=== RESULTADO FINAL ===");
        System.out.println("Tus cartas:");
        imprimeMano(jugador, mazoJugador, false);
        System.out.println("\nValor total: " + valorJugador);

        System.out.println("\nCartas del dealer:");
        imprimeMano(dealer, mazoDealer, false);
        System.out.println("\nValor total: " + valorDealer);

        if (valorJugador > 21) {
            System.out.println("\n¡Te has pasado de 21! El dealer gana.");
        } else if (valorDealer > 21) {
            System.out.println("\n¡El dealer se ha pasado de 21! ¡Ganas!");
        } else if (valorJugador > valorDealer) {
            System.out.println("\n¡Has ganado!");
        } else if (valorDealer > valorJugador) {
            System.out.println("\nEl dealer gana.");
        } else {
            System.out.println("\n¡Empate!");
        }
    }
}
