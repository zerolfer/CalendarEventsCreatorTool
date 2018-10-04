package io;

import model.CalendarEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public static final String SPLITTER = ",";
    public static final String NEW_LINE = "\n";

    FileWriter writer;

    public CSVWriter(String src) {
        try {
            File f = new File(src);
            f.createNewFile();
            this.writer = new FileWriter(f);

            // La cabecera requerida por Google Calendar
            writer.append("Subject" + SPLITTER + "Start Date" + SPLITTER + "Start Time" + SPLITTER + "End Date" + SPLITTER +
                    "End Time" + SPLITTER + "Location" + NEW_LINE);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public CSVWriter() {
        this("output/");
    }

    public void close() {
        try {
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLine(CalendarEvent e) {
        addColumn(e.getSubject());
        addColumn(SPLITTER);

        addColumn(e.getStartDate());
        addColumn(SPLITTER);

        addColumn(e.getStartTime());
        addColumn(SPLITTER);

        addColumn(e.getEndDate());
        addColumn(SPLITTER);

        addColumn(e.getEndTime());
        addColumn(SPLITTER);

        addColumn(e.getLocation());
        addColumn(NEW_LINE);
    }

    private void addColumn(String s) {
        try {
            writer.append(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
