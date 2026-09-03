package Terminal;

import java.util.Scanner;
import java.util.function.Function;

public abstract class Terminal {
    static Scanner scanner = new Scanner(System.in);
    static String nombre;
    public static void saludar() {
        nombre = leer("Bienvenido a Adivina quien\nDime tu nombre");
        System.out.println("Bienvenido " + nombre);
    }

    private static String leer(Function<String, Boolean> condicion) {
        String value = "";
        do {
            try {
                value = scanner.nextLine();
                if (value.isEmpty()) {
                    System.out.println("Por favor ingrese un valor.");
                }
                if (!condicion.apply(value)) {
                    value = "";
                    System.out.println("El valor ingresado no es valido.");
                }
            } catch (Error e) {
                System.out.println("Hubo un error, por favor intente nuevamente.");
            }
        }
        while (value.isEmpty());
        return value;
    }

    public static String leer(String mensaje, Function<String, Boolean> condicion) {
        System.out.println(mensaje);
        return leer(condicion);
    }

    public static String leer(String mensaje, String regex) {
        System.out.println(mensaje);
        return leer(value -> value.matches(regex));
    }

    public static String leer(String mensaje) {
        System.out.println(mensaje);
        return leer(value -> true);
    }

    public static void escribir(String mensaje) {
        System.out.println(mensaje);
    }

    public static void salir() {
        System.out.println("Adios " + nombre + ", volve a jugar despues");
        scanner.close();
    }
}
