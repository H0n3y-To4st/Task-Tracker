import java.time.*;
import java.time.format.DateTimeFormatter;

/*
 * This was originally used to showcase the capabilities of this package for the grads meeting
 * Will be utilised more effectively soon
 */
public class DateHandler {

    public static void main(String[] args) {
        System.out.println("LocalDate: " + getLocalDate());
        System.out.println("LocalTime: " + getLocalTime());
        System.out.println("LocalDateTime: " + getLocalDateTime());
        System.out.println("Formatted DateTime: " + getDateTime());
        System.out.println("ZonedDateTime: " + getZonedDateTime());

        System.out.println("Date + 5 days: " + addDaysToDate(5));

        LocalDate start = LocalDate.of(2025, 6, 10);
        LocalDate end = LocalDate.of(2025, 6, 20);
        System.out.println("Days between " + start + " and " + end + ": " + daysBetween(start, end));

        LocalDate dateToCheck = LocalDate.of(2025, 6, 15);
        System.out.println("Is " + dateToCheck + " before today? " + isBeforeNow(dateToCheck));
    }

    public static String getLocalDate() {
        return LocalDate.now().toString();
    }

    public static String getLocalTime() {
        return LocalTime.now().toString();
    }

    public static String getLocalDateTime() {
        return LocalDateTime.now().toString();
    }

    public static String getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    // get specific time zones, need to input correct ZoneId
    // relies on TZDB - timezone database
    public static String getZonedDateTime() {
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        return zdt.toString();
    }

    public static String addDaysToDate(int days) {
        return LocalDate.now().plusDays(days).toString();
    }

    public static long daysBetween(LocalDate start, LocalDate end) {
        return Period.between(start, end).getDays();
    }

    public static boolean isBeforeNow(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }
}
