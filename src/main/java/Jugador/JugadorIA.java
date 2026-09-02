package Jugador;

import Terminal.Terminal;

public class JugadorIA extends AJugador implements IJugador {
    public JugadorIA(String nombre) {
        this.nombre = nombre;
    }

    public void marcarElegido() {
        Terminal.escribir("La maquina marco su personaje");
        controladorPersonaje.marcarComoElegido();
    }

    public int seleccionarPersonaje(AJugador oponente) {
        Terminal.escribir("La maquina eligio su personaje");
        return oponente.obtenerPersonaje().id();
    }
}
