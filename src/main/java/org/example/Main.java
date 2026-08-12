package org.example;

import Viaje.GestorViajes;

public class Main {
    static void main() {
        GestorViajes gestorViajes = new GestorViajes();
        Terminal.saludar();
        Terminal.generarMenu(gestorViajes);
    }
}

