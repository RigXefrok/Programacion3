package Partida;

import Menu.Menu;
import Personaje.ControladorPersonaje;
import Personaje.Personaje;

public abstract class Partida implements IPartida {
    int VIDAS;
    ControladorPersonaje controladorPersonaje = new ControladorPersonaje();
    Menu selectorPersonaje;
    boolean estaEnJuego;
    boolean salioDelJuego = false;
    Personaje personajeElegido;
    Boolean encontroAlElegido;

    protected String mensajeVictoria = "Felicidades ganaste!!";
    protected String mensajeDerrota = "Perdiste...";
    protected String mensajeJuegoInterrumpido = "La proxima vez capaz lo conseguis.";

    public Partida () {
        controladorPersonaje.crearPersonajes();
    }

    protected int seleccionarPersonajes() {
        selectorPersonaje = new Menu();
        controladorPersonaje.personajes().forEach(personaje -> {
            selectorPersonaje.agregar(personaje.nombreCompleto());
        });
        int opcion = selectorPersonaje.selector();
        if (opcion == 0) {
            salioDelJuego = true;
        }
        return opcion;
    }

    protected void validarPersonaje(Personaje personaje) {
        personajeElegido = personaje;
        encontroAlElegido = personajeElegido.esElElegido();
        if (!encontroAlElegido) {
            pierdeVida();
        }
    }

    protected void pierdeVida() {
        --VIDAS;
    }

    protected void terminarPartida() {
        if (salioDelJuego) {
            System.out.println(mensajeJuegoInterrumpido);
            return;
        }
        if (VIDAS > 0) {
            System.out.println(mensajeVictoria);
            return;
        }
        System.out.println(mensajeDerrota);
    }

}
