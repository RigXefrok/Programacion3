package Viaje;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorViajes {
    private final ArrayList<Viaje> viajes = new ArrayList<Viaje>();

    public ArrayList<Viaje> getViajes() {
        return viajes;
    }

    public void agregar(String pais, LocalDate anno) {
        viajes.add(new Viaje(pais, anno));
    }

    public void eliminar(Viaje viaje) {
        viajes.remove(viaje);
    }

    public List<String> obtenerDestinos() {
        return viajes.stream().map(viaje -> viaje.getDestino()).distinct().toList();
    }

    public List<Viaje> obtenerViajes(String destino) {
        return viajes.stream().filter(viaje-> viaje.getDestino().equalsIgnoreCase(destino)).toList();
    }

    public void leer() {
        viajes.forEach(System.out::print);
    }
}
