/**
 *
 *  @author Kaczor Wiktor S27599
 *
 */

package zad1;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Time {
    public static String passed(String from, String to) {
        LocalDate fromDate = LocalDate.parse(from,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'gg:mm"));
return String.valueOf(fromDate);
    }
}
