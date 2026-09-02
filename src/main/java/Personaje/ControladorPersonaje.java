package Personaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControladorPersonaje {
    private final byte CAPACIDAD_MAXIMA = 7;
    private final Random random = new Random();
    private Personaje personajeElegido;

    List<Personaje> personajes = new ArrayList<Personaje>();
    List<String> nombres = List.of("Pedro", "Juan", "Maria", "Lautaro", "Carlos", "Martin", "Sabrina");
    List<String> apellidos = List.of("Pascal", "Bro", "Fernandez", "Martinez", "Tenison");

    Boolean hayElegido = false;

    public void crearPersonaje() {
        if (personajes.size() >= CAPACIDAD_MAXIMA) {
            throw new Error("La cantidad de personajes esta al maximo");
        }
        byte id = (byte) personajes.size();
        int indiceNombre = random.nextInt(nombres.size());
        int indiceApellido = random.nextInt(apellidos.size());
        Personaje personaje = new Personaje(id, nombres.get(indiceNombre), apellidos.get(indiceApellido));
        personajes.add(personaje);
    }

    public void crearPersonajes() {
        for (int i = 0; i < CAPACIDAD_MAXIMA; i++) {
            crearPersonaje();
        }
    }

    public void crearPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public void eleminarPersonaje(Personaje personaje) {
        personajes.remove(personaje);
    }

    private void limpiarPersonajes() {
        personajes.forEach(personaje -> {
            if (personaje.esElElegido()) {
                personaje.desmarcarElegido();
            }
        });
        hayElegido = false;
    }

    public void marcarComoElegido(int id) {
        limpiarPersonajes();
        personajeElegido = personajes.get(id);
        personajeElegido.marcarElegido();
        hayElegido = true;
    }

    public void marcarComoElegido() {
        int indice = random.nextInt(personajes.size());
        marcarComoElegido(indice);
    }

    public Personaje obtenerElegido() {
        if (!hayElegido) {
            throw new Error("No hay personaje elegido");
        }
        return personajeElegido;
    }

    public Personaje obtener() {
        return obtener(random.nextInt(personajes.size()));
    }

    public Personaje obtener(int id) {
        if (id < 0 || id > personajes.size()) {
            throw new Error("No hay personaje elegido");
        }
        return personajes.get(id);
    }

    public Personaje obtenerRecursivo(List<Personaje> personajesRecursivos) {
        int indice = personajesRecursivos.size()/ 2;
        Personaje personaje = personajesRecursivos.get(indice);
        if (personaje.esElElegido()) {
            return personaje;
        }
        if (personajeElegido.id() > personaje.id()) {
            return obtenerRecursivo(personajesRecursivos.subList(indice + 1, personajesRecursivos.size()));
        }
        return obtenerRecursivo(personajesRecursivos.subList(0, indice));
    }

    public int obtenerIndiceElegido() {
        if (!hayElegido) {
            throw new Error("No hay personaje elegido");
        }
        return personajes.indexOf(obtenerRecursivo(personajes));
    }

    public List<Personaje> personajes() {
        return personajes;
    }

    public int cantidadPersonajes() {
        return personajes.size();
    }
}
