package org.example;

import Jugador.Jugador;
import Jugador.JugadorIA;
import Jugador.AJugador;
import Menu.Menu;
import Partida.PartidaJugador;
import Partida.PartidaJugadorVsJugador;
import Partida.PartidaMaquina;
import Terminal.Terminal;

import java.util.List;

public class Main {
    void main() {
        Terminal.saludar();
        Menu selectorModo = new Menu();
        selectorModo.agregar("Modo jugador vs maquina", this::modoJugadorvsMaquina);
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

    private void modoJugadorvsMaquina() {
        Jugador jugador = new Jugador("jugador");
        JugadorIA maquina = new JugadorIA("maquina");
        PartidaJugadorVsJugador partida = new PartidaJugadorVsJugador(List.of(jugador, maquina));
        partida.iniciar();
        AJugador ganador = partida.ganador();
        if (ganador == jugador) {
            System.out.println("GANASTE");
        } else {
            System.out.println("PERDISTE");
        }
    }
}

