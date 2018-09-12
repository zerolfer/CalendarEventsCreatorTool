import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarEvent {

    private static final String DATE_SPLITTER = "/";
    private String subject, startDate, startTime, endDate, endTime, location;

    public CalendarEvent(String subject, String startDate, String startTime, String endDate,
                         String endTime, String location) {
        this.subject = subject;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.location = location;
    }

    public CalendarEvent(CalendarEvent e, String dia) {
        this.subject = e.subject;
        this.startTime = e.startTime;
        this.endTime = e.endTime;
        this.location = e.location;

        startDate = dia;
        endDate = dia;
    }


    public static List<CalendarEvent> crearEventos(Timestamp inicio, Timestamp fin, CalendarEvent evento) {
        List<CalendarEvent> result = new ArrayList<>();
        Timestamp current = new Timestamp(inicio.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(current.getTime());

        while (current.compareTo(fin) <= 0) {

            String dia = getFecha(cal);
            result.add(new CalendarEvent(evento, dia));

            cal.add(Calendar.WEEK_OF_YEAR, 1);

            current.setTime(cal.getTime().getTime());
        }
        return result;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" +
                "subject='" + subject + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endDate='" + endDate + '\'' +
                ", endTime='" + endTime + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    private static String getFecha(Calendar cal) {
        return Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) + DATE_SPLITTER
                + Integer.toString(cal.get(Calendar.MONTH) + 1) + DATE_SPLITTER
                + Integer.toString(cal.get(Calendar.YEAR));
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
