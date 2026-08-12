package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.StringJoiner;

import static Utils.DateUtils.FORMATO_JSON;

public abstract class Archivo {
    public static <T> void guardar(T objeto) {
        String ruta = "archivo.txt";
        String contenido = convertirAJson(objeto);
        try {
            Files.writeString(Paths.get(ruta), contenido);
            System.out.println("Archivo guardado con éxito.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar el archivo: " + e.getMessage());
        }
    }

    public static <T> String convertirAJson(T objeto) {
        if (objeto == null) {
            return "null";
        }

        if (objeto instanceof LocalDate) {
            return "\"" + ((LocalDate) objeto).format(FORMATO_JSON) + "\"";
        }

        if (objeto instanceof Iterable) {
            StringJoiner jsonArrayBuilder = new StringJoiner(", ", "[", "]");
            for (Object elemento : (Iterable<?>) objeto) {
                jsonArrayBuilder.add(convertirAJson(elemento));
            }
            return jsonArrayBuilder.toString();
        }

        StringJoiner jsonBuilder = new StringJoiner(", ", "{ ", " }");

        Class<?> clase = objeto.getClass();
        Field[] propiedades = clase.getDeclaredFields();

        for (Field campo : propiedades) {
            try {
                campo.setAccessible(true);

                String nombrePropiedad = campo.getName();
                Object valor = campo.get(objeto);

                if (valor == null) {
                    jsonBuilder.add("\"" + nombrePropiedad + "\": null");
                    continue;
                }

                if (valor instanceof String || valor instanceof Character) {
                    jsonBuilder.add("\"" + nombrePropiedad + "\": \"" + valor + "\"");
                } else if (valor instanceof Number || valor instanceof Boolean || valor instanceof LocalDate) {
                    jsonBuilder.add("\"" + nombrePropiedad + "\": " + convertirAJson(valor));
                } else {
                    jsonBuilder.add("\"" + nombrePropiedad + "\": " + convertirAJson(valor));
                }

            } catch (IllegalAccessException e) {
                System.out.println("No se pudo acceder a la propiedad: " + campo.getName());
            }
        }

        return jsonBuilder.toString();
    }
}