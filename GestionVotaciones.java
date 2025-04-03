
/*
    * Crea un programa que permita
     introducir el número de votantes y asignarle nombre a tres candidatos. 
    * Cada votante elige un candidato por número (1, 2 o 3) (switch). 
    * El programa debe validar los votos, contar cuántos ha 
    * recibido cada candidato, mostrar el porcentaje de votos 
    * y declarar al ganador.
    * 
    * Requisitos:
    * Uso de arrays para almacenar los votos.
    * 
    * Métodos para:
    *      Mostrar menú.
    *      Leer votos.
    *      Validar voto.
    *      Determinar el ganador.
    * Controlar errores de entrada (votos fuera del rango).
    */

import java.util.Scanner;

public class GestionVotaciones {
    private static final Scanner scanner = new Scanner(System.in);
    private static String[] candidatos = new String[3];
    private static int[] votos = new int[3];
    private static int votantes_total;

    public static void main(String[] args) {

        System.out.println("ola");
        inicializar_elecciones();

    }

    private static void inicializar_elecciones() {
        menu();
        leerVotos();

        System.out.println(ganador(votos));
    }

    // metodos
    private static void menu() {

        do {
            System.out.print("Ingresa el número total de votantes: ");
            votantes_total = scanner.nextInt();
            if (votantes_total <= 0) {
                System.out.println("numero invalido! Ingrese numero valido");
            }
        } while (votantes_total <= 0);

        scanner.nextLine();
        // ase asigna nombre a cada candidato
        for (int i = 0; i < candidatos.length; i++) {
            System.out.print("Ingrese el nombre del candidato " + (i + 1) + ": ");
            candidatos[i] = scanner.nextLine();
        }

        // mostramos en pantalla -> votantestotales y candidatos
        System.out.println("has introducido  " + votantes_total + " votantes en total");
        for (String candidato : candidatos) {
            System.out.println("Candidato " + candidato);
        }

        // cada votante asigna votos a cada candidato
        int opcion = 0;
        for (int i = 0; i < votantes_total; i++) {
            do {
                System.out.print(" Votante" + (i + 1) + " a quien quieres votar\n");
                leerCandidatos();
                opcion = scanner.nextInt();
                // validacion >0 && <=3
                if (validaVoto(opcion)) {
                    switch (opcion) {
                        case 1:
                            votos[0]++;
                            break;
                        case 2:
                            votos[1]++;
                            break;
                        case 3:
                            votos[2]++;
                            break;
                        default:
                    }
                } else {
                    System.out.println("voto introducido no valido");
                    scanner.nextLine();
                }
            } while (!validaVoto(opcion));
        }
    }

    private static void leerVotos() {
        // cuenta de votos
        for (int i = 0; i < votos.length; i++) {
            System.out.println("el candidato " + (candidatos[i]) + " ha recibido " + votos[i] + "votos");
        }
    }

    private static void leerCandidatos() {
        for (int i = 0; i < candidatos.length; i++) {
            System.out.println("Candidato " + (i + 1) + ": " + candidatos[i]);
        }
    }

    private static boolean validaVoto(int opcion) {
        System.out.println(opcion);
        return opcion >= 1 && opcion <= 3;
    }

    private static String ganador(int[] votos) {
        int maximo = votos[0];
        int indiceMaximo = 0;
        boolean empate = false;

        for (int i = 1; i < votos.length; i++) {
            if (votos[i] > maximo) {
                maximo = votos[i];
                indiceMaximo = i;
                empate = false;
            } else if (votos[i] == maximo) {
                empate = true;
            }
        }

        if (empate) {
            return "Empate!";
        }

        return "El candidato ganador es " + candidatos[indiceMaximo] + " con " + " votos.";
    }

}
