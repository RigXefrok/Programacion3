package Menu;

import Utils.DateUtils;
import org.example.Archivo;
import Viaje.GestorViajes;
import org.example.Terminal;
import Viaje.Viaje;

import java.time.LocalDate;
import java.util.List;

public class ControladorMenu {
    static GestorViajes gestorViajes;

    public ControladorMenu(GestorViajes gestorViajes) {
        this.gestorViajes = gestorViajes;
    }

    public void agregarViaje() {
        System.out.println("===============");
        String pais = Terminal.leer("Agregue un destino", "^[a-zA-Z]+$");
        LocalDate anno = LocalDate.parse(
                Terminal.leer("Ingrese la fecha del viaje (dd-mm-yyyy)", DateUtils.VALIDAR_FECHA::test),
                DateUtils.FORMATEADOR);
        gestorViajes.agregar(pais, anno);
    }

    public void eliminarViaje() {
        System.out.println("===============");
        List<String> destinos = gestorViajes.obtenerDestinos();
        if (destinos.isEmpty()) {
            System.out.println("No se encontraron destinos");
            return;
        }
        Menu selectorDestino = new Menu();
        destinos.forEach(selectorDestino::agregar);
        short indiceDestino = destinos.size() == 1 ? 0 : (short) (selectorDestino.selector() - 1);
        if (indiceDestino < 0) {
            return;
        }
        List<Viaje> paises = gestorViajes.obtenerViajes(destinos.get(indiceDestino));
        if (paises.size() == 1) {
            Viaje viaje = paises.getFirst();
            gestorViajes.eliminar(viaje);
            System.out.println("Se elimino el viaje: " + viaje);
            return;
        }
        Menu selectorViaje = new Menu();
        paises.forEach(pais -> selectorViaje.agregar(pais.getDestino() + " - " + pais.getAnno()));
        short indiceViaje = paises.size () == 1 ? 0 : (short) (selectorViaje.selector() - 1);
        if (indiceViaje < 0) {
            return;
        }
        Viaje viaje = paises.get(indiceViaje);
        gestorViajes.eliminar(paises.get(indiceViaje));
        System.out.println("Se elimino el viaje: " + viaje);
    }

    public void guardarViajes() {
        Archivo.guardar(gestorViajes.getViajes());
    }

    public void verViajes() {
        gestorViajes.leer();
    }
}