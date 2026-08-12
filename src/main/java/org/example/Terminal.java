package org.example;

import Menu.Menu;
import Viaje.GestorViajes;
import Menu.ControladorMenu;

import java.util.Scanner;
import java.util.function.Function;

public abstract class Terminal {
    static Scanner scanner = new Scanner(System.in);
    static void saludar() {
        String nombre = leer("Como te llamas?");
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

    static String leer(String mensaje) {
        System.out.println(mensaje);
        return leer(value -> true);
    }

    static void generarMenu(GestorViajes gestorViajes) {
        Menu menu = new Menu();
        ControladorMenu gestorHandler = new ControladorMenu(gestorViajes);
        menu.agregar("Agregar un viaje", gestorHandler::agregarViaje);
        menu.agregar("Ver viajes", gestorHandler::verViajes);
        menu.agregar("Eliminar un viaje", gestorHandler::eliminarViaje);
        menu.agregar("Guardar viajes", gestorHandler::guardarViajes);
        menu.mostrar();
        scanner.close();
    }
}
