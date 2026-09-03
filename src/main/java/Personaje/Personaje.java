package Personaje;

public class Personaje {
    private final int id;
    private final String nombre;
    private final String apellido;
    private Boolean esElElegido = false;

    Personaje (byte id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public void marcarElegido() {
        this.esElElegido = true;
    }

    public void desmarcarElegido() {
        this.esElElegido = false;
    }

    public boolean esElElegido() {
        return esElElegido;
    }

    public String nombreCompleto() {
        return "%s %s".formatted(nombre, apellido);
    }

    public int id() {
        return id;
    }

    @Override
    public String toString() {
        return "["+id+"#"+nombre+" "+apellido+"]";
    }
}
