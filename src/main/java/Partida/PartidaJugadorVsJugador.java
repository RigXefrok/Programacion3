package Partida;

import Jugador.AJugador;
import Personaje.Personaje;
import Terminal.Terminal;

import java.util.List;

public class PartidaJugadorVsJugador extends Partida {
    private List<AJugador> jugadores;
    private AJugador ganador;

    public PartidaJugadorVsJugador(List<AJugador> jugadores) {
        this.jugadores = jugadores;
        inicializarJugadores();
    }

    @Override
    public void iniciar() {
        estaEnJuego = true;
        do {
            jugarTurnos();
        } while (estaEnJuego);
        terminarPartida();
    }

    private void inicializarJugador(AJugador jugador) {
        List<Personaje> personajes = controladorPersonaje.personajes();
        jugador.cargarPersonajes(personajes);
        jugador.marcarElegido();
    }

    private void inicializarJugadores() {
        jugadores.forEach(this::inicializarJugador);
    }

    private void turno(AJugador jugador) {
        Terminal.escribir("=========== Turno "+ jugador + "===========");
        List<AJugador> oponentes = jugadores.stream().filter(oponente -> oponente != jugador).toList();
        AJugador oponenteElegido = jugador.seleccionarOponente(oponentes);

        int opcion = jugador.seleccionarPersonaje(oponenteElegido);
        if (opcion < 0) {
            estaEnJuego = false;
            return;
        }

        if (oponenteElegido.esElElegido(opcion)) {
            ganador = jugador;
            estaEnJuego = false;
            return;
        }
        Terminal.escribir(oponenteElegido.obtenerPersonaje(opcion) + " No era el elegido");
    }

    private void jugarTurnos() {
        for (int i = 0; i < jugadores.size(); i++) {
            if (!estaEnJuego) {
                break;
            }
            turno(jugadores.get(i));
        }
    }

    public AJugador ganador() {
        return ganador;
    }

}
