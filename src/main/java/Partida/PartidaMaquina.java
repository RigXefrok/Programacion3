package Partida;

import Menu.Menu;

public class PartidaMaquina extends Partida {
    private int intentos = 0;

    public PartidaMaquina() {
        super();
        VIDAS = 5;
    }

    public void iniciar() {
        int opcion = seleccionarPersonajes();
        if (salioDelJuego) {
            terminarPartida();
            return;
        }
        controladorPersonaje.marcarComoElegido(opcion - 1);
        do {
            ++intentos;
            validarPersonaje(controladorPersonaje.obtener());
            estaEnJuego = !encontroAlElegido && VIDAS > 0;
        } while (estaEnJuego);
        mensajeVictoria = "Fallaste rotundamente, la maquina encontro a " + personajeElegido + ", en " + intentos + " intentos.";
        mensajeDerrota = "BIEEEN le ganaste a la maquina, no pudo encontrar a " + controladorPersonaje.obtenerElegido();
        mensajeJuegoInterrumpido = "Juga contra la maquina sin miedo che";
        terminarPartida();
    }

    protected int seleccionarPersonajes() {
        System.out.println("Elegi un personaje");
        return super.seleccionarPersonajes();
    }

    protected void pierdeVida() {
        super.pierdeVida();
        System.out.println("Vamoooos la maquina no te encontro, eligio a [" + personajeElegido.nombreCompleto() + "]");
        System.out.println("Tenes que aguantar " + VIDAS + " mas" );
        controladorPersonaje.eleminarPersonaje(personajeElegido);
    };

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
