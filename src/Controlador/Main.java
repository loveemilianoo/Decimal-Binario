import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        System.out.println("Programa de conversion punto flotante a Binario / Octal");
        System.out.println("Hecho por Emiliano Hidalgo Gasca");
        boolean continuar = true;

        while (continuar){
            System.out.println("\n" + "═".repeat(30));
            System.out.println("Menu");
            System.out.println("1. Realizar conversión"); 
            System.out.println("2. Salir");
            System.out.println("═".repeat(30));
            System.out.print("Selecciona una opción: ");

            int opci = sc.nextInt();

            switch (opci) {
                case 1:
                    System.out.println("Ingrese la base a convertir (ej. 2 para binario, 8 para octal)");
                    int baseConvertidora = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingrese el numero para convertir (ej. 0.23)");
                    String valorDecimal = sc.nextLine();

                    Numero numero = new Numero( valorDecimal, baseConvertidora);
                    numero.conversion();
                    numero.mostrarTabla();
                    break;
            
                case 2:
                    System.out.println("Saliendo...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo");
                    break;
            }

        }

        sc.close();
    }
}