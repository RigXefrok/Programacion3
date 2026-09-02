package Partida;

import Menu.Menu;

public class PartidaJugador extends Partida {

    public PartidaJugador() {
        super();
        VIDAS = 3;
    }

    public void iniciar() {
        controladorPersonaje.marcarComoElegido();

        do {
            int opcion = seleccionarPersonajes();
            if (salioDelJuego) {
                break;
            }
            validarPersonaje(controladorPersonaje.obtener(opcion - 1));
            if (VIDAS == 1) {
                mostrarAyuda();
            }
            estaEnJuego = !encontroAlElegido && VIDAS > 0;
        } while (estaEnJuego);
        mensajeDerrota += ", el personaje elegido era " + controladorPersonaje.obtenerElegido();
        terminarPartida();
    }

    protected int seleccionarPersonajes() {
        System.out.println("Adivina cual de estos personajes es el elegido");
        return super.seleccionarPersonajes();
    }

    protected void pierdeVida() {
        super.pierdeVida();
        System.out.println("UYYY te equivocaste "+ personajeElegido.nombreCompleto() + " no es el elegido.");
        System.out.println("Te quedan " + VIDAS + " vidas" );
        controladorPersonaje.eleminarPersonaje(personajeElegido);
    };

    private void mostrarAyuda() {
        Menu menuAyuda = new Menu();
        menuAyuda.agregar("No puedo mas ayudame!!!");
        menuAyuda.agregar("Me la banco solo, GRACIAS");
        int opcionAyuda = menuAyuda.selector();
        if (opcionAyuda == 1) {
            int indiceDelElegido = controladorPersonaje.obtenerIndiceElegido();
            System.out.println("El elegido esta entre el indice: " + Math.max(indiceDelElegido - 2, 1) + "~" + Math.min(indiceDelElegido + 2, controladorPersonaje.cantidadPersonajes()));
        }
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
