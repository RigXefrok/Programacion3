package Menu;

import org.example.Terminal;

import java.util.ArrayList;
import java.util.Objects;

public class Menu {
    ArrayList<Opcion> opciones = new ArrayList<>();

    public Menu() {
        opciones.add(new Opcion((short) 0, "Salir", null));
    }

    public void mostrar() {
        int opcion = 0;
        do {
            System.out.println("\n===============");
            opciones.forEach(System.out::println);
            opcion = seleccionar();
        } while (opcion != 0);
    }

    public short selector() {
        short opcion = 0;
        opciones.forEach(System.out::println);
        opcion = seleccionar();
        return opcion;
    }

    public void agregar(String label) {
        opciones.add(opciones.size() - 1, new Opcion((short) opciones.size(), label));
    }

    public void agregar(String label, Runnable evento) {
        opciones.add(opciones.size() - 1, new Opcion((short) opciones.size(), label, evento));
    }

    private short seleccionar() {
        short opcion = Short.parseShort(Terminal.leer("Ingrese la opcion:", "^[0-%s]$".formatted(opciones.size() - 1)));
        ejecutar(opcion);
        return opcion;
    }

    private void ejecutar(short indice) {
        Objects.requireNonNull(opciones.stream().filter(opcion -> opcion.getIndice() == indice).findFirst().orElse(null)).ejecutar();
    }
}
