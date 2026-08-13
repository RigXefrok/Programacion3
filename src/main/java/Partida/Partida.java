package Partida;

import Menu.Menu;
import Personaje.ControladorPersonaje;
import Personaje.Personaje;
import Terminal.Terminal;

public class Partida {
    int VIDAS;

    public void iniciar() {
        Menu selectorModo = new Menu();
        selectorModo.agregar("Modo busqueda jugador", this::modoBusquedaJugador);
        selectorModo.agregar("Modo busqueda maquina", this::modoBusquedaMaquina);
        selectorModo.mostrar();
        Terminal.salir();
    }

    private void modoBusquedaJugador() {
        VIDAS = 3;
        ControladorPersonaje controladorPersonaje = new ControladorPersonaje();
        controladorPersonaje.crearPersonajes();
        controladorPersonaje.marcarComoElegido();
        boolean estaEnJuego;
        boolean salioDelJuego = false;
        do {
            Menu selectorPersonaje = new Menu();
            controladorPersonaje.personajes().forEach(personaje -> {
                selectorPersonaje.agregar(personaje.nombre());
            });
            int opcion = selectorPersonaje.selector();
            if (opcion == 0) {
                salioDelJuego = true;
                break;
            }
            Personaje elegido = controladorPersonaje.obtener(opcion - 1);
            Boolean encontroAlElegido = elegido.esElElegido();
            if (!encontroAlElegido) {
                System.out.println("UYYY te equivocaste "+ elegido.nombre() + " no es el elegido.");
                --VIDAS;
                System.out.println("Te quedan " + VIDAS + " vidas" );
                controladorPersonaje.eleminarPersonaje(elegido);
            }
            if (VIDAS == 1) {
                Menu menuAyuda = new Menu();
                menuAyuda.agregar("No puedo mas ayudame!!!");
                menuAyuda.agregar("Me la banco solo, GRACIAS");
                int opcionAyuda = menuAyuda.selector();
                if (opcionAyuda == 1) {
                    int indiceDelElegido = controladorPersonaje.obtenerIndiceElegido();
                    System.out.println("El elegido esta entre el indice: " + Math.max(indiceDelElegido - 2, 1) + "~" + Math.min(indiceDelElegido + 2, controladorPersonaje.personajes().size()));
                }
            }
            estaEnJuego = !encontroAlElegido && VIDAS > 0;
        } while (estaEnJuego);
        if (!salioDelJuego) {
            if (VIDAS > 0) {
                System.out.println("Felicidades ganaste");
            } else {
                System.out.println("Perdiste BURRAZO, el personaje elegido era " + controladorPersonaje.obtenerElegido());
            }
        } else {
            System.out.println("La proxima vez capaz lo conseguis.");
        }
    }

    private void modoBusquedaMaquina() {
//        VIDAS = 10;
//        ControladorPersonaje controladorPersonaje = new ControladorPersonaje();
//        controladorPersonaje.crearPersonajes();
//        boolean estaEnJuego;
//        boolean salioDelJuego = false;
//        do {
//            Menu selectorPersonaje = new Menu();
//            controladorPersonaje.personajes().forEach(personaje -> {
//                selectorPersonaje.agregar(personaje.nombre());
//            });
//            int opcion = selectorPersonaje.selector();
//            if (opcion == 0) {
//                salioDelJuego = true;
//                break;
//            }
//            controladorPersonaje.marcarComoElegido(opcion - 1);
//
//        } while (estaEnJuego);
//        if (!salioDelJuego) {
//            if (VIDAS == 0) {
//                System.out.println("Felicidades le ganaste a la maquina");
//            } else {
//                System.out.println("Perdiste BURRAZO, la maquina encontro al jugador " + controladorPersonaje.obtenerElegido());
//            }
//        } else {
//            System.out.println("La proxima vez capaz lo conseguis.");
//        }
    }

}
