package Viaje;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Viaje {
    private final String destino;
    private final LocalDate anno;

    Viaje(String destino, LocalDate fecha) {
        this.destino = destino;
        this.anno = fecha;
    }

    public String getDestino() {
        return destino;
    }

    public String getAnno() {
        return anno.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String toString() {
        return "["+getDestino()+"/"+getAnno()+"]";
    }
}
