package edu.hawaii.its.holiday.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public final class Dates {

    // Private constructor; prevent instantiation.
    private Dates() {
        // Emtpy.
    }

    public static LocalDate newLocalDate(Month month, int day, int year) {
        return LocalDate.of(year, month, day);
    }

    public static Date newDate(Month month, int day, int year) {
        LocalDate localDate = LocalDate.of(year, month, day);
        return toDate(localDate);
    }

    private static ZoneId zoneId() {
        return ZoneId.systemDefault();
    }

    public static Date newDate(Date date) {
        return dateWithoutTime(date);
    }

    private static Date dateWithoutTime(Date date) {
        if (date == null) {
            return null;
        }

        if (date instanceof java.sql.Date) {
            date = new Date(date.getTime());
        }

        LocalDate localDate = toLocalDate(date);

        return toDate(localDate);
    }

    public static int month(Date date) {
        return toLocalDate(date).getMonthValue();
    }

    public static Date firstOfYear(Date date) {
        int year = Dates.yearOfDate(date);
        return newDate(Month.JANUARY, 1, year);
    }

    public static Date firstOfNextYear(Date date) {
        int year = Dates.yearOfDate(date) + 1;
        return newDate(Month.JANUARY, 1, year);
    }

    public static Date firstOfPreviousYear(Date date) {
        LocalDate ld = toLocalDate(date);
        int year = ld.getYear() - 1;

        return newDate(Month.JANUARY, 1, year);
    }

    public static Date firstOfMonth(Month month, int year) {
        return firstDateOfMonth(month, year);
    }

    public static Date firstDateOfMonth(Month month, int year) {
        return newDate(month, 1, year);
    }

    public static Date firstOfNextMonth(Date date) {
        LocalDate ld0 = toLocalDate(date);
        Month month = ld0.getMonth();
        int year = ld0.getYear();
        ld0 = LocalDate.of(year, month, 1);
        ld0 = ld0.plusMonths(1);

        return toDate(ld0);

    }

    public static Date previousSunday(Date date) {
        LocalDate ld = toLocalDate(date);
        LocalDate dd = ld.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        return toDate(dd);
    }

    public static Date fromOffset(Date date, int days) {
        return toDate(toLocalDate(date).plusDays(days));
    }

    public static int lastDayOfMonth(Month month, int year) {
        LocalDate date = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());

        return lastDayOfMonth.getDayOfMonth();
    }

    public static Date lastDateOfMonth(Month month, int year) {
        LocalDate date = LocalDate.of(year, month, 1);
        LocalDate localDate = date.with(TemporalAdjusters.lastDayOfMonth());

        return toDate(localDate);
    }

    public static Date firstDateOfYear(int year) {
        return newDate(Month.JANUARY, 1, year);
    }

    public static Date lastDateOfYear(int year) {
        return newDate(Month.DECEMBER, 31, year);
    }

    public static Date lastDateOfYear(Date date) {
        LocalDate ld = toLocalDate(date);
        int year = ld.getYear();

        return lastDateOfYear(year);
    }

    public static int dayOfMonth(Date date) {
        return toLocalDate(date).getDayOfMonth();
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

    public static int currentYear() {
        return LocalDate.now().getYear();
    }

    public static int yearOfDate(Date date) {
        return toLocalDate(date).getYear();
    }

    public static int dayOfWeek(Date date) {
        return toLocalDate(date).getDayOfWeek().getValue();
    }

    public static LocalDate toLocalDate(Date date) {
        if (date instanceof java.sql.Date) {
            date = new Date(date.getTime());
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = zoneId();
        ZonedDateTime zoneDateTime = instant.atZone(zoneId);
        return zoneDateTime.toLocalDate();
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(zoneId()).toInstant());
    }
}
