package Jugador;

import Menu.Menu;
import Personaje.Personaje;
import Terminal.Terminal;

import java.util.List;

public class Jugador extends AJugador {
    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public void marcarElegido() {
        int id = seleccionarPersonaje(controladorPersonaje.personajes());
        controladorPersonaje.marcarComoElegido(id);
    }

    public int seleccionarPersonaje(List<Personaje> personajes) {
        Menu selectorPersonaje = new Menu();
        personajes.forEach(personaje -> {
            selectorPersonaje.agregar(personaje.nombreCompleto());
        });
        return selectorPersonaje.selector() - 1;
    }

    public int seleccionarPersonaje(AJugador oponente) {
        Terminal.escribir("Eliga un personaje:");
        return seleccionarPersonaje(oponente.personajes());
    }

    public AJugador seleccionarOponente(List<AJugador> oponentes) {
        if (oponentes.size() == 1) {
            return oponentes.getFirst();
        }
        Menu seleccionOponente = new Menu();
        oponentes.forEach(oponente -> {
            seleccionOponente.agregar(String.valueOf(oponente));
        });
        int index = seleccionOponente.selector();
        return oponentes.get(index);
    }
}
