package edu.hawaii.its.holiday.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Dates {

    public static final long SECONDS_PER_DAY = 86400;
    public static final long MILLISECONDS_PER_SECOND = 1000;
    public static final long MILLISECONDS_PER_DAY = MILLISECONDS_PER_SECOND * SECONDS_PER_DAY;
    public static final int MINUTES_PER_HOUR = 60;

    // Private constructor; prevent instantiation.
    private Dates() {
        // Emtpy.
    }

    public static Date newDate(Month month, int day, int year) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month.getValue());
        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }

    public static Date newDate(Date date) {
        return dateWithoutTime(date);
    }

    public static Date newDate() {
        Calendar calSwap = Calendar.getInstance();
        Calendar calNew = Calendar.getInstance();
        calNew.clear();
        calNew.set(Calendar.YEAR, calSwap.get(Calendar.YEAR));
        calNew.set(Calendar.MONTH, calSwap.get(Calendar.MONTH));
        calNew.set(Calendar.DAY_OF_MONTH, calSwap.get(Calendar.DAY_OF_MONTH));

        return calNew.getTime();
    }

    public static Date newDateWithoutTime() {
        Calendar calSwap = Calendar.getInstance();
        Calendar calNew = Calendar.getInstance();
        calNew.clear();
        calNew.set(Calendar.YEAR, calSwap.get(Calendar.YEAR));
        calNew.set(Calendar.MONTH, calSwap.get(Calendar.MONTH));
        calNew.set(Calendar.DAY_OF_MONTH, calSwap.get(Calendar.DAY_OF_MONTH));

        return calNew.getTime();
    }

    private static Date dateWithoutTime(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calSwap = Calendar.getInstance();
        calSwap.setTime(date);

        Calendar calNew = Calendar.getInstance();
        calNew.clear();
        calNew.set(Calendar.YEAR, calSwap.get(Calendar.YEAR));
        calNew.set(Calendar.MONTH, calSwap.get(Calendar.MONTH));
        calNew.set(Calendar.DAY_OF_MONTH, calSwap.get(Calendar.DAY_OF_MONTH));

        return calNew.getTime();
    }

    public static int daysToSeconds(int days) {
        return days * 24 * 60;
    }

    public static int month(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static Date firstOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);

        return newDate(Month.JANUARY, 1, year);
    }

    public static Date firstOfNextYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR) + 1;

        return newDate(Month.JANUARY, 1, year);
    }

    public static Date firstOfPreviousYear(Date date) {
        Calendar calSwap = Calendar.getInstance();
        calSwap.setTime(date);
        int year = calSwap.get(Calendar.YEAR) - 1;

        return newDate(Month.JANUARY, 1, year);
    }

    public static Date firstOfMonth(Date date) {
        Calendar calSwap = Calendar.getInstance();
        calSwap.setTime(date);

        Calendar calNew = Calendar.getInstance();
        calNew.clear();
        calNew.set(Calendar.DAY_OF_MONTH, 1);
        calNew.set(Calendar.MONTH, calSwap.get(Calendar.MONTH));
        calNew.set(Calendar.YEAR, calSwap.get(Calendar.YEAR));

        return calNew.getTime();

    }

    public static Date firstOfMonth(Month month, int year) {
        return firstDateOfMonth(month, year);
    }

    public static Date firstDateOfMonth(Month month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, month.getValue());
        cal.set(Calendar.YEAR, year);

        return cal.getTime();
    }

    public static Date firstOfNextMonth(Date date) {
        Calendar calSwap = Calendar.getInstance();
        calSwap.setTime(date);

        Calendar calNew = Calendar.getInstance();
        calNew.clear();
        calNew.set(Calendar.DAY_OF_MONTH, 1);
        calNew.set(Calendar.MONTH, calSwap.get(Calendar.MONTH));
        calNew.set(Calendar.YEAR, calSwap.get(Calendar.YEAR));
        calNew.add(Calendar.MONTH, 1);

        return calNew.getTime();
    }

    public static Date previousSunday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    public static Date fromOffset(Date date, int days) {
        Calendar calSwap = Calendar.getInstance();
        calSwap.setTime(date);

        Calendar calNew = Calendar.getInstance();
        calNew.clear();
        calNew.set(Calendar.YEAR, calSwap.get(Calendar.YEAR));
        calNew.set(Calendar.MONTH, calSwap.get(Calendar.MONTH));
        calNew.set(Calendar.DAY_OF_MONTH, calSwap.get(Calendar.DAY_OF_MONTH));

        calNew.add(Calendar.DATE, days);

        return calNew.getTime();
    }

    public static Date datePlusDays(Date date, int days) {
        return fromOffset(date, days);
    }

    public static int lastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static int lastDayOfMonth(Month month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1); // Need this here.
        cal.set(Calendar.MONTH, month.getValue());
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date lastDateOfMonth(Month month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.DAY_OF_MONTH, 1); // Need this here.
        cal.set(Calendar.MONTH, month.getValue());
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date firstDateOfYear(int year) {
        return newDate(Month.JANUARY, 1, year);
    }

    public static Date lastDateOfYear(int year) {
        return newDate(Month.DECEMBER, 31, year);
    }

    public static Date lastDateOfYear(Date date) {
        Calendar cal = makeCalendar(date);
        int year = cal.get(Calendar.YEAR);

        return lastDateOfYear(year);
    }

    public static int dayOfMonth(Date date) {
        return makeCalendar(date).get(Calendar.DAY_OF_MONTH);
    }

    public long elapsedSeconds(Date start, Date end) {
        return ((end.getTime() - start.getTime()) / MILLISECONDS_PER_SECOND);
    }

    public static long elapsedDays(Date start, Date end) {
        return ((end.getTime() - start.getTime()) / MILLISECONDS_PER_DAY);
    }

    public static java.sql.Date toSqlDate(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    private static java.sql.Timestamp toTimestamp(Date date) {
        if (date == null) {
            return null; // Not good.
        }
        return new java.sql.Timestamp(date.getTime());
    }

    public static java.sql.Timestamp toTimestamp(String dateStr, String format) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            date = formatter.parse(dateStr, new ParsePosition(0));
        } catch (Exception ex) {
            // Ignored.
        }
        return toTimestamp(date);
    }

    public static java.sql.Timestamp addDays(java.sql.Timestamp startTime, long days) {
        startTime.setTime(startTime.getTime() + (days * MILLISECONDS_PER_DAY));
        return startTime;
    }

    public static java.sql.Timestamp newTimestamp() {
        Date date = newDate();
        long milliTime = date.getTime();

        return new java.sql.Timestamp(milliTime);
    }

    public static String formatDate(java.util.Date date, String formatStr) {
        String result = date.toString();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            result = formatter.format(date);
        } catch (IllegalArgumentException e) {
            // Ignored.
        }

        return result;

    }

    // Not sure we really need this method.
    public static String formatDate(java.util.Date date) {
        return formatDate(date, "MM/dd/yyyy");
    }

    public static String formatDateString(final String dateStr, String format, String newFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date date = formatter.parse(dateStr, pos);

        return formatDate(date, newFormat);
    }

    public static int currentYear() {
        return makeCalendar().get(Calendar.YEAR);
    }

    public static int yearOfDate(Date date) {
        return makeCalendar(date).get(Calendar.YEAR);
    }

    public static Calendar makeCalendar() {
        return Calendar.getInstance();
    }

    public static Calendar makeCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal;
    }

    public static int dayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_WEEK);
    }

}
