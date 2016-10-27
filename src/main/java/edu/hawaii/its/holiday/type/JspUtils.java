package edu.hawaii.its.holiday.type;

import java.util.Collections;
import java.util.List;

public class JspUtils {

    private JspUtils() {
        // Prevent instantiation. 
    }

    public static List<Holiday> retrieveHolidays(YearHolidayHolder holder) {
        if (holder == null) {
            return Collections.<Holiday> emptyList();
        }
        return holder.getHolidays(holder.getYear());
    }

}
