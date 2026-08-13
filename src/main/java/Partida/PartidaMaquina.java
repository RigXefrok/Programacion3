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
        System.out.println("Vamoooos la maquina no te encontro, eligio a [" + personajeElegido.nombre() + "]");
        System.out.println("Tenes que aguantar " + VIDAS + " mas" );
        controladorPersonaje.eleminarPersonaje(personajeElegido);
    };

    private void mostrarAyuda() {
        Menu menuAyuda = new Menu();
        menuAyuda.agregar("No puedo mas ayudame!!!");
        menuAyuda.agregar("Me la banco solo, GRACIAS");
        int opcionAyuda = menuAyuda.selector();
        if (opcionAyuda == 1) {
            int indiceDelElegido = controladorPersonaje.obtenerIndiceElegido();
            System.out.println("El elegido esta entre el indice: " + Math.max(indiceDelElegido - 2, 1) + "~" + Math.min(indiceDelElegido + 2, controladorPersonaje.personajes().size()));
        }
    }
}
