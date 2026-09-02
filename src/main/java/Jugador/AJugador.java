package Jugador;

import Personaje.ControladorPersonaje;
import Personaje.Personaje;

import java.util.List;
import java.util.Random;

public abstract class AJugador implements IJugador {
    private final Random random = new Random();
    public String nombre;
    ControladorPersonaje controladorPersonaje = new ControladorPersonaje();

    public void cargarPersonajes(List<Personaje> peronsajes) {
        controladorPersonaje.crearPersonajes(peronsajes);
    }

    public List<Personaje> personajes() {
        return controladorPersonaje.personajes();
    }

    public Personaje obtenerPersonaje() {
        return personajes().get(random.nextInt(personajes().size()));
    }

    public Personaje obtenerPersonaje(int id) {
        return personajes().get(id);
    }

    public boolean esElElegido(int id) {
        return controladorPersonaje.obtener(id).esElElegido();
    }

    public AJugador seleccionarOponente(List<AJugador> jugadores) { return jugadores.get(random.nextInt(jugadores.size())); }

    @Override
    public String toString() {
        return nombre;
    }
}
