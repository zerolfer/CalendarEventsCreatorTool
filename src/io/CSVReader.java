package io;

import model.CalendarEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static final String SPLITTER = ";";

    private final static String src = "input/calendario_festivos.csv";

    BufferedReader reader;

    public CSVReader() throws RuntimeException {
        try {
            File f = new File(src);
            this.reader = new BufferedReader(new FileReader(f));

        } catch (IOException e) {
            throw new RuntimeException("No se encuentra el fichero calendario_festivos.csv");
        }

    }

    public List<CalendarEvent> read() {
        List<CalendarEvent> result = new ArrayList<>();
        if (reader == null) return result;
        String ln;
        try {
            ln = reader.readLine(); // saltamos linea de cabecera
            while ((ln = reader.readLine()) != null) {

                String[] columnas = ln.split(SPLITTER);
                result.add(new CalendarEvent(columnas[1], columnas[0], columnas[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

}
