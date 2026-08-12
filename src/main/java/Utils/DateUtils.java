package Utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.function.Predicate;

public abstract class DateUtils {
    public static final DateTimeFormatter FORMATO_JSON = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final DateTimeFormatter FORMATEADOR = DateTimeFormatter
            .ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    public static final Predicate<String> VALIDAR_FECHA = fechaTexto -> {
        if (fechaTexto == null) return false;
        try {
            FORMATEADOR.parse(fechaTexto);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    };
}
