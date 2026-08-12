package Menu;

public class Opcion {
    private final short indice;
    private final String label;
    private Runnable evento;

    Opcion(Short indice, String label) {
        this.indice = indice;
        this.label = label;
    }

    Opcion(Short indice, String label, Runnable evento) {
        this.indice = indice;
        this.label = label;
        this.evento = evento;
    }

    public short getIndice() {
        return indice;
    }

    public void ejecutar() {
        if (evento != null) {
            evento.run();
        }
    }

    @Override
    public String toString() {
        return "["+indice+"] "+label;
    }
}
