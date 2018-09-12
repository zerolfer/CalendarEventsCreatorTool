import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private static Scanner s = new Scanner(System.in);
    private static CSVWriter w = null;

    public static void main(String args[]) {
        print("Bienvenido al sistema de Eventos de Google Calendar para estudiantes");
        print("Para crear un nuevo fichero antes debes ponerle un nombre, escríbelo a continuación (sin extensión): ");
        String nombreFichero = s.nextLine();
        w = new CSVWriter("output/" + nombreFichero + ".csv");
        print("Ya he creado el fichero, se encuentra en el directorio 'output', pero aun está vacío.");

        crearEvento();
        String b;

        do {
            print("Quieres volver a crear un evento? (S/N)");
            b = s.nextLine();
            if (b.equalsIgnoreCase("S"))
                crearEvento();
        }while (!b.equalsIgnoreCase("N"));

        print("De acuerdo, ya puedes importar dicho fichero en tu cuenta de Google Calendar, " +
                "solo tienes que seguir la instrucciones que puedes encontrar en " +
                "https://support.google.com/calendar/answer/37118?hl=es");
        print("¡Hasta pronto!");

        w.close();
    }

    private static void crearEvento() {
        print("Vamos a crear un evento, tienes que decirme la duración del evento, " +
                "es decir desde qué fecha hasta qué fecha quieres que se creé el evento semanalmente");
        print("desde (dd/MM/yyyy): ");
        Timestamp inicio = parseDate(s.nextLine());
        print("hasta (inclusive) (dd/MM/yyyy): ");
        Timestamp fin = parseDate(s.nextLine());

        String subject, startDate, startTime, endDate, endTime, location;

        print("Ahora necesito el nombre del evento");
        subject = s.nextLine();
        startDate = df.format(inicio);
        endDate = startDate;

        print("¿A qué hora comienza? (hh:mm)");
        startTime = s.nextLine();

        print("¿A qué hora termina? (hh:mm)");
        endTime = s.nextLine();

        print("Finalmente, introduce el aula donde será impartida la asginatura habitualmente (¡formato libre!)");
        location = s.nextLine();

        CalendarEvent evento = new CalendarEvent(subject, startDate, startTime, endDate, endTime, location);

        List<CalendarEvent> eventos = CalendarEvent.crearEventos(inicio, fin, evento);
        for (CalendarEvent event : eventos) {
            w.addLine(event);
            w.flush();
        }

    }

    private static void print(String s) {
        System.out.println(s);
        try {
            Thread.sleep(0 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Timestamp parseDate(String s) {
        Timestamp timestamp;
        try {
            timestamp = new Timestamp(df.parse(s).getTime());
            return timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.exit(1);
        return null;
    }
}
