package org.example;

import Partida.Partida;
import Terminal.Terminal;

public class Main {
    static void main() {
        Partida partida = new Partida();
        Terminal.saludar();
        partida.iniciar();
    }
}

