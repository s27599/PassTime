/**
 * @author Kaczor Wiktor S27599
 */

package zad1;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class Time {
    public static String passed(String from, String to) {
        try {
            if (from.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}")) {
                LocalDateTime fromTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
                LocalDateTime toTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

                Duration between = Duration.between(fromTime, toTime);

                StringBuilder info = new StringBuilder();
                info.append("OD ").append(fromTime.getDayOfMonth())
                        .append(" ").append(fromTime.getMonth().getDisplayName(TextStyle.FULL, new Locale("pl","PL"))).append(" ")
                            .append(fromTime.getYear()).append(" (").append(fromTime.getDayOfWeek().
                                getDisplayName(TextStyle.FULL, new Locale("pl","PL")));

                info.append(") do ").append(toTime.getDayOfMonth()).append(" ").append(toTime.getMonth().getDisplayName(TextStyle.FULL,new Locale("pl","PL")))
                        .append(" ").append(toTime.getYear()).append(" (").append(toTime.getDayOfWeek().
                                getDisplayName(TextStyle.FULL, new Locale("pl","PL"))).append(")\n");

                StringBuilder nonCalendar = new StringBuilder();
                nonCalendar.append(" - mija: ");
                if (between.toDays() == 1) {
                    nonCalendar.append("1 dzień, ");
                } else {
                    nonCalendar.append(between.toDays() + " dni, ");
                }

                double weeks = (double) between.toDays() / 7.0;
                nonCalendar.append((double) Math.round(weeks * 100) / 100.0);
                nonCalendar.append(" tygodni");

                nonCalendar.append("\n - godzin: ");
                nonCalendar.append(between.toHours());
                nonCalendar.append(", minut: ");
                nonCalendar.append(between.toMinutes());

                LocalDate tmpLocalDateFrom = fromTime.toLocalDate();
                LocalDate tmpLocalDateTo = toTime.toLocalDate();

                StringBuilder sb = getCalendarStringBuilder(tmpLocalDateFrom, tmpLocalDateTo);


                return info.toString()+nonCalendar.toString()+sb.toString();
            }
        } catch (DateTimeParseException e) {
            return ("*** " + e);

        }
        try {
            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);
            StringBuilder sb = getCalendarStringBuilder(fromDate, toDate);
            return String.valueOf(fromDate) + " " + String.valueOf(toDate) + sb.toString();

        } catch (DateTimeParseException e) {
            return ("*** " + e);
        }


    }

    private static StringBuilder getCalendarStringBuilder(LocalDate tmpLocalDateFrom, LocalDate tmpLocalDateTo) {
        Period period = Period.between(tmpLocalDateFrom, tmpLocalDateTo);

        StringBuilder sb = new StringBuilder();
        sb.append("kalendarzowo: ");
        if (period.getYears() == 1)
            sb.append(1 + " rok ");
        else if (period.getYears() > 1 && period.getYears() < 5) {
            sb.append(period.getYears() + " lata ");
        } else if (period.getYears() != 0) {
            sb.append(period.getYears() + " lat ");
        }
        if (period.getMonths() == 1)
            sb.append(1 + " miesiąc ");
        else if (period.getMonths() > 1 && period.getMonths() < 5) {
            sb.append(period.getMonths() + " miesiące ");
        } else if (period.getMonths() != 0) {
            sb.append(period.getMonths() + " miesięcy ");
        }
        if (period.getDays() == 1)
            sb.append(1 + " dzień ");
        else if (period.getDays() != 0) {
            sb.append(period.getDays() + " dni ");
        }
        sb.append("\n");
        return sb;
    }
}
