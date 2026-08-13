package org.example;

import Menu.Menu;
import Partida.PartidaJugador;
import Partida.PartidaMaquina;
import Terminal.Terminal;

public class Main {
    void main() {
        Terminal.saludar();
        Menu selectorModo = new Menu();
        selectorModo.agregar("Modo busqueda jugador", this::modoBusquedaJugador);
        selectorModo.agregar("Modo busqueda maquina", this::modoBusquedaMaquina);
        selectorModo.mostrar();
        Terminal.salir();
    }

    private void modoBusquedaJugador() {
        PartidaJugador partida = new PartidaJugador();
        partida.iniciar();
    }

    private void modoBusquedaMaquina() {
        PartidaMaquina partida = new PartidaMaquina();
        partida.iniciar();
    }
}

