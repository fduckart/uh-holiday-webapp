package edu.hawaii.its.holiday.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class DatesTest {

    protected GregorianCalendar christmas;
    protected GregorianCalendar newYearsDay2000;
    protected GregorianCalendar dayMusicDied;

    protected LocalDate christmasLocalDate;
    protected LocalDate newYearsDay2000LocalDate;
    protected LocalDate dayMusicDiedLocalDate;

    @Before
    public void setUp() {
        christmas = new GregorianCalendar(1962, Calendar.DECEMBER, 25, 0, 0, 0);
        newYearsDay2000 = new GregorianCalendar(2000, Calendar.JANUARY, 1, 0, 0, 0);
        dayMusicDied = new GregorianCalendar(1959, Calendar.FEBRUARY, 3);

        christmasLocalDate = LocalDate.of(1962, Month.DECEMBER, 25);
        newYearsDay2000LocalDate = LocalDate.of(2000, Month.JANUARY, 1);
        dayMusicDiedLocalDate = LocalDate.of(1959, Month.FEBRUARY, 3);

    }

    private Calendar makeCalendar() {
        return Calendar.getInstance();
    }

    private Calendar makeCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal;
    }

    @Test
    public void testNewDate() {
        Date date1 = Dates.newDate(Month.DECEMBER, 25, 1962);
        assertEquals(christmas.getTime(), date1);

        String dateStr = "2006-05-01T18:55:40";
        Date date2 = Dates.toTimestamp(dateStr, "yyyy-MM-dd'T'hh:mm:ss");

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 01);
        cal.set(Calendar.YEAR, 2006);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        cal.set(Calendar.MINUTE, 55);
        cal.set(Calendar.SECOND, 40);
        cal.set(Calendar.MILLISECOND, 0);

        assertEquals(cal.getTime().getTime(), date2.getTime());

        assertEquals(1962, christmasLocalDate.getYear());
        assertEquals(Month.DECEMBER, christmasLocalDate.getMonth());
        assertEquals(25, christmasLocalDate.getDayOfMonth());

    }

    @Test
    public void toTimestamp() {
        Date date = Dates.toTimestamp(" ", "MM/dd/yyyy");
        assertNull(date);

        date = Dates.toTimestamp("", "MM/dd/yyyy");
        assertNull(date);

        date = Dates.toTimestamp("", "MM/12/xsys");
        assertNull(date);

        date = Dates.toTimestamp("12/25/2000", "MM/dd/yyyy");
        assertEquals(Dates.newDate(Month.DECEMBER, 25, 2000).getTime(), date.getTime());
    }

    @Test
    public void testFindPreviousSunday() {
        Date date1 = Dates.newDate(Month.AUGUST, 9, 2010); // A Monday.
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);

        for (int i = 0; i < 75; i++) {
            cal.add(Calendar.DATE, -13); // Move back some days.
            final Date date2 = cal.getTime();

            final Date sunday = Dates.previousSunday(date2);
            Calendar calSunday = Calendar.getInstance();
            calSunday.setTime(sunday);

            assertEquals(Calendar.SUNDAY, calSunday.get(Calendar.DAY_OF_WEEK));
            assertTrue(sunday.compareTo(date2) <= 0);
        }

        Date date4 = Dates.previousSunday(christmas.getTime());
        Calendar cal4 = Calendar.getInstance();
        cal4.setTime(date4);
        assertEquals(Calendar.SUNDAY, cal4.get(Calendar.DAY_OF_WEEK));
        assertEquals(23, cal4.get(Calendar.DAY_OF_MONTH));
        assertEquals(1962, cal4.get(Calendar.YEAR));
        cal4 = null;

        Date date5 = Dates.previousSunday(newYearsDay2000.getTime());
        Calendar cal5 = Calendar.getInstance();
        cal5.setTime(date5);
        assertEquals(Calendar.SUNDAY, cal5.get(Calendar.DAY_OF_WEEK));
        assertEquals(Calendar.DECEMBER, cal5.get(Calendar.MONTH));
        assertEquals(26, cal5.get(Calendar.DAY_OF_MONTH));
        assertEquals(1999, cal5.get(Calendar.YEAR));
        cal5 = null;

        Date date6 = Dates.newDate(Month.AUGUST, 1, 2010); // A Sunday.
        Calendar cal6 = Calendar.getInstance();
        cal6.setTime(date6);
        assertEquals(Calendar.SUNDAY, cal6.get(Calendar.DAY_OF_WEEK));
        assertEquals(Calendar.AUGUST, cal6.get(Calendar.MONTH));
        assertEquals(1, cal6.get(Calendar.DAY_OF_MONTH));
        assertEquals(2010, cal6.get(Calendar.YEAR));
        cal6 = null;
    }

    @Test
    public void testFirstOfYear() {
        assertEquals(Dates.newLocalDate(Month.FEBRUARY, 3, 1959), dayMusicDiedLocalDate);

        Date date0 = Dates.firstOfYear(dayMusicDied.getTime());
        assertEquals(Dates.newDate(Month.JANUARY, 1, 1959), date0);

        Date date1 = Dates.firstOfYear(christmas.getTime());
        assertEquals(Dates.newDate(Month.JANUARY, 1, 1962), date1);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.HOUR_OF_DAY, 4);
        Date date2 = Dates.firstOfYear(cal.getTime());
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2012), date2);

        Calendar cal3 = Calendar.getInstance();
        cal3.setTime(cal.getTime());
        assertEquals(4, cal3.get(Calendar.HOUR_OF_DAY));
        Date date3 = Dates.firstOfYear(cal3.getTime());
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2012), date3);
        cal = null;
        cal3 = null;

        Date dt = Dates.newDate(Month.FEBRUARY, 29, 2012);
        Date date4 = Dates.firstOfYear(dt);
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2012), date4);

        Calendar cal4 = Calendar.getInstance();
        cal4.set(Calendar.SECOND, 33);
        cal4.set(Calendar.MINUTE, 22);
        cal4.set(Calendar.HOUR_OF_DAY, 11);
        cal4.set(Calendar.DAY_OF_MONTH, 29);
        cal4.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal4.set(Calendar.YEAR, 2012);
        Calendar cal5 = Calendar.getInstance();
        cal5.setTime(Dates.firstOfYear(cal4.getTime()));
        cal4 = null;
        assertEquals(1, cal5.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.JANUARY, cal5.get(Calendar.MONTH));
        assertEquals(2012, cal5.get(Calendar.YEAR));
        assertEquals(0, cal5.get(Calendar.SECOND));
        assertEquals(0, cal5.get(Calendar.MINUTE));
        assertEquals(0, cal5.get(Calendar.HOUR_OF_DAY));
        cal5 = null;
    }

    @Test
    public void testFirstOfPreviousYear() {
        Date date0 = Dates.firstOfPreviousYear(dayMusicDied.getTime());
        assertEquals(Dates.newDate(Month.JANUARY, 1, 1958), date0);

        Date date1 = Dates.firstOfPreviousYear(christmas.getTime());
        assertEquals(Dates.newDate(Month.JANUARY, 1, 1961), date1);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2012);
        cal.set(Calendar.HOUR_OF_DAY, 4);
        Date date2 = Dates.firstOfPreviousYear(cal.getTime());
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2011), date2);

        Calendar cal3 = Calendar.getInstance();
        cal3.setTime(cal.getTime());
        assertEquals(4, cal3.get(Calendar.HOUR_OF_DAY));
        Date date3 = Dates.firstOfPreviousYear(cal3.getTime());
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2011), date3);
        cal3 = null;
        cal = null;

        Date dt = Dates.newDate(Month.FEBRUARY, 29, 2012);
        Date date4 = Dates.firstOfPreviousYear(dt);
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2011), date4);

        Calendar cal4 = Calendar.getInstance();
        cal4.set(Calendar.SECOND, 33);
        cal4.set(Calendar.MINUTE, 22);
        cal4.set(Calendar.HOUR_OF_DAY, 11);
        cal4.set(Calendar.DAY_OF_MONTH, 29);
        cal4.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal4.set(Calendar.YEAR, 2012);
        Calendar cal5 = Calendar.getInstance();
        cal5.setTime(Dates.firstOfPreviousYear(cal4.getTime()));
        cal4 = null;
        assertEquals(1, cal5.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.JANUARY, cal5.get(Calendar.MONTH));
        assertEquals(2011, cal5.get(Calendar.YEAR));
        assertEquals(0, cal5.get(Calendar.SECOND));
        assertEquals(0, cal5.get(Calendar.MINUTE));
        assertEquals(0, cal5.get(Calendar.HOUR_OF_DAY));
        cal5 = null;
    }

    @Test
    public void lastDayOfMonth() {

        // Just a bunch of random checks.
        assertEquals(30, Dates.lastDayOfMonth(Month.SEPTEMBER, 1962));
        assertEquals(31, Dates.lastDayOfMonth(Month.JANUARY, 2000));
        assertEquals(29, Dates.lastDayOfMonth(Month.FEBRUARY, 2000));
        assertEquals(29, Dates.lastDayOfMonth(Month.FEBRUARY, 2012));

        // Now just run some comparisons against 
        // methods available from the Calendar class.
        Calendar cal = Calendar.getInstance();
        for (int year = 1979; year < 2039; year++) {
            cal.clear();
            cal.set(Calendar.YEAR, year);
            for (Month month : Month.values()) {
                cal.set(Calendar.MONTH, month.getValue() - 1);
                int lastDayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                assertEquals(lastDayOfMonth, Dates.lastDayOfMonth(month, year));
            }
        }

    }

    @Test
    public void firstOfMonth() {
        Date date1 = Dates.firstOfMonth(Month.DECEMBER, 1962);
        Date date2 = Dates.newDate(Month.DECEMBER, 1, 1962);

        assertEquals(date1, date2);

        Calendar cal = makeCalendar(date1);
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(1962, cal.get(Calendar.YEAR));
        assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
        assertEquals(0, cal.get(Calendar.SECOND));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void firstOfNextMonth() {
        Date date0 = Dates.newDate(Month.DECEMBER, 31, 2010);
        Date date1 = Dates.newDate(Month.JANUARY, 1, 2011);
        Date date2 = Dates.newDate(Month.JANUARY, 31, 2011);
        Date date3 = Dates.newDate(Month.FEBRUARY, 1, 2011);
        Date date4 = Dates.newDate(Month.MARCH, 1, 2011);
        Date date5 = Dates.newDate(Month.FEBRUARY, 29, 2012);
        Date date6 = Dates.newDate(Month.MARCH, 1, 2012);

        assertEquals(date1, Dates.firstOfNextMonth(date0));
        assertEquals(date3, Dates.firstOfNextMonth(date2));
        assertEquals(date4, Dates.firstOfNextMonth(date3));
        assertEquals(date6, Dates.firstOfNextMonth(date5));

        Calendar cal0 = Calendar.getInstance();
        cal0.set(Calendar.SECOND, 33);
        cal0.set(Calendar.MINUTE, 22);
        cal0.set(Calendar.HOUR_OF_DAY, 11);
        cal0.set(Calendar.DAY_OF_MONTH, 3);
        cal0.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal0.set(Calendar.YEAR, 2012);
        Calendar cal = Calendar.getInstance();
        cal.setTime(Dates.firstOfNextMonth(cal0.getTime()));
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.MARCH, cal.get(Calendar.MONTH));
        assertEquals(2012, cal.get(Calendar.YEAR));
        assertEquals(0, cal.get(Calendar.SECOND));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testMonthValues() {
        // The use of these methods is not recommended.
        assertEquals(Month.JANUARY, Month.of(1));
        assertEquals(Month.JANUARY, Month.valueOf("JANUARY"));
        assertEquals(Month.DECEMBER, Month.of(12));
        assertEquals(Month.DECEMBER, Month.valueOf("DECEMBER"));

        int month = 5;
        int year = 1999;
        int day = 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_MONTH, day);
        final Date from = cal.getTime();

        assertEquals(from, Dates.newDate(Month.of(month + 1), day, year));
        assertEquals(from, Dates.firstOfMonth(Month.of(month + 1), year));

        cal = Calendar.getInstance();
        cal.setTime(from);
        assertEquals(1999, cal.get(Calendar.YEAR));
        assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.SECOND));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(0, cal.get(Calendar.MILLISECOND));

        Date first = Dates.firstOfMonth(Month.of(month + 1), year);
        cal = Calendar.getInstance();
        cal.setTime(first);
        assertEquals(1999, cal.get(Calendar.YEAR));
        assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH));
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.SECOND));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(0, cal.get(Calendar.MILLISECOND));
    }

    @Test
    public void fromOffset() {
        final Date dateStart = Dates.newDate(Month.JANUARY, 1, 2011);

        Calendar cal0 = Calendar.getInstance();
        cal0.setTime(dateStart);

        Calendar cal1 = makeCalendar(dateStart);
        assertEquals(cal0, cal1);

        assertEquals(1, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.JANUARY, cal1.get(Calendar.MONTH));
        assertEquals(2011, cal1.get(Calendar.YEAR));
        assertEquals(0, cal1.get(Calendar.HOUR));
        assertEquals(0, cal1.get(Calendar.MINUTE));
        assertEquals(0, cal1.get(Calendar.SECOND));
        assertEquals(0, cal1.get(Calendar.MILLISECOND));

        Date date = Dates.fromOffset(dateStart, 1);
        cal1 = makeCalendar(date);
        assertEquals(2, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.JANUARY, cal1.get(Calendar.MONTH));
        assertEquals(2011, cal1.get(Calendar.YEAR));
        assertEquals(0, cal1.get(Calendar.HOUR));
        assertEquals(0, cal1.get(Calendar.MINUTE));
        assertEquals(0, cal1.get(Calendar.SECOND));
        assertEquals(0, cal1.get(Calendar.MILLISECOND));

        date = Dates.fromOffset(date, 1);
        cal1 = makeCalendar(date);
        assertEquals(3, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.JANUARY, cal1.get(Calendar.MONTH));
        assertEquals(2011, cal1.get(Calendar.YEAR));
        assertEquals(0, cal1.get(Calendar.HOUR));
        assertEquals(0, cal1.get(Calendar.MINUTE));
        assertEquals(0, cal1.get(Calendar.SECOND));
        assertEquals(0, cal1.get(Calendar.MILLISECOND));

        date = Dates.fromOffset(date, -2);
        cal1 = makeCalendar(date);
        assertEquals(1, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.JANUARY, cal1.get(Calendar.MONTH));
        assertEquals(2011, cal1.get(Calendar.YEAR));
        assertEquals(0, cal1.get(Calendar.HOUR));
        assertEquals(0, cal1.get(Calendar.MINUTE));
        assertEquals(0, cal1.get(Calendar.SECOND));
        assertEquals(0, cal1.get(Calendar.MILLISECOND));

        date = Dates.fromOffset(date, -1);
        cal1 = makeCalendar(date);
        assertEquals(31, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.DECEMBER, cal1.get(Calendar.MONTH));
        assertEquals(2010, cal1.get(Calendar.YEAR));
        assertEquals(0, cal1.get(Calendar.HOUR));
        assertEquals(0, cal1.get(Calendar.MINUTE));
        assertEquals(0, cal1.get(Calendar.SECOND));
        assertEquals(0, cal1.get(Calendar.MILLISECOND));

        date = Dates.fromOffset(date, 32);
        cal1 = makeCalendar(date);
        assertEquals(1, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.FEBRUARY, cal1.get(Calendar.MONTH));
        assertEquals(2011, cal1.get(Calendar.YEAR));
        assertEquals(0, cal1.get(Calendar.HOUR));
        assertEquals(0, cal1.get(Calendar.MINUTE));
        assertEquals(0, cal1.get(Calendar.SECOND));
        assertEquals(0, cal1.get(Calendar.MILLISECOND));

        date = Dates.fromOffset(date, -1);
        cal1 = makeCalendar(date);
        assertEquals(31, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.JANUARY, cal1.get(Calendar.MONTH));
        assertEquals(2011, cal1.get(Calendar.YEAR));
        assertEquals(0, cal1.get(Calendar.HOUR));
        assertEquals(0, cal1.get(Calendar.MINUTE));
        assertEquals(0, cal1.get(Calendar.SECOND));
        assertEquals(0, cal1.get(Calendar.MILLISECOND));
    }

    @Test
    public void firstDateOfYear() {
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2000), Dates.firstDateOfYear(2000));
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2009), Dates.firstDateOfYear(2009));
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2010), Dates.firstDateOfYear(2010));
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2011), Dates.firstDateOfYear(2011));
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2012), Dates.firstDateOfYear(2012));

        // Check that the hours/minutes/seconds are zero-ed.
        Date date = Dates.firstDateOfYear(2012);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(0, cal.get(Calendar.SECOND));
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
        assertEquals(2012, cal.get(Calendar.YEAR));
    }

    @Test
    public void firstDateOfYear_Two() {
        for (int year = 2000; year < 2025; year++) {
            Date date = Dates.newDate(Month.JANUARY, 1, year);
            assertEquals(date, Dates.firstDateOfYear(year));
        }
    }

    @Test
    public void lastDateOfYear() {
        assertEquals(Dates.newDate(Month.DECEMBER, 31, 2000), Dates.lastDateOfYear(2000));
        assertEquals(Dates.newDate(Month.DECEMBER, 31, 2009), Dates.lastDateOfYear(2009));
        assertEquals(Dates.newDate(Month.DECEMBER, 31, 2010), Dates.lastDateOfYear(2010));
        assertEquals(Dates.newDate(Month.DECEMBER, 31, 2011), Dates.lastDateOfYear(2011));
        assertEquals(Dates.newDate(Month.DECEMBER, 31, 2012), Dates.lastDateOfYear(2012));

        // Check that the hours/minutes/seconds are zero-ed.
        Date date = Dates.lastDateOfYear(2012);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.MINUTE));
        assertEquals(0, cal.get(Calendar.SECOND));
        assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(Calendar.DECEMBER, cal.get(Calendar.MONTH));
        assertEquals(2012, cal.get(Calendar.YEAR));
    }

    @Test
    public void lastDateOfYear_Two() {
        Date date = Dates.newDate(Month.JANUARY, 1, 2000);
        assertEquals(Dates.newDate(Month.DECEMBER, 31, 2000), Dates.lastDateOfYear(date));
        date = Dates.newDate(Month.JANUARY, 1, 2009);
        assertEquals(Dates.newDate(Month.DECEMBER, 31, 2009), Dates.lastDateOfYear(date));
        date = Dates.newDate(Month.JANUARY, 1, 2010);
        assertEquals(Dates.newDate(Month.DECEMBER, 31, 2010), Dates.lastDateOfYear(date));
        date = Dates.newDate(Month.JANUARY, 1, 2011);
        assertEquals(Dates.newDate(Month.DECEMBER, 31, 2011), Dates.lastDateOfYear(date));
        date = Dates.newDate(Month.FEBRUARY, 29, 2012);
        assertEquals(Dates.newDate(Month.DECEMBER, 31, 2012), Dates.lastDateOfYear(date));
    }

    @Test
    public void toSqlDate() {
        Calendar cal1 = makeCalendar();
        cal1.clear();
        cal1.set(Calendar.MONTH, Calendar.MARCH);
        cal1.set(Calendar.DAY_OF_MONTH, 15);
        cal1.set(Calendar.YEAR, 2000);
        cal1.set(Calendar.HOUR, 4);
        cal1.set(Calendar.MINUTE, 30);
        cal1.set(Calendar.SECOND, 45);
        Date date1 = cal1.getTime();
        assertFalse("2000-03-15".equals(date1.toString()));

        java.sql.Date date2 = Dates.toSqlDate(cal1.getTime());
        cal1 = null;
        assertTrue("2000-03-15".equals(date2.toString()));

        Calendar cal2 = makeCalendar();
        cal2.clear();
        cal2.setTime(date2);

        assertEquals(2000, cal2.get(Calendar.YEAR));
        assertEquals(15, cal2.get(Calendar.DATE));
        assertEquals(Calendar.MARCH, cal2.get(Calendar.MONTH));
        assertEquals(4, cal2.get(Calendar.HOUR));
        assertEquals(30, cal2.get(Calendar.MINUTE));
        assertEquals(45, cal2.get(Calendar.SECOND));
        cal2 = null;

        Date date3 = Dates.newDate(date2);
        Calendar cal3 = makeCalendar();
        cal3.clear();
        cal3.setTime(date3);
        assertEquals(2000, cal3.get(Calendar.YEAR));
        assertEquals(15, cal3.get(Calendar.DATE));
        assertEquals(Calendar.MARCH, cal3.get(Calendar.MONTH));
        assertEquals(0, cal3.get(Calendar.HOUR));
        assertEquals(0, cal3.get(Calendar.MINUTE));
        assertEquals(0, cal3.get(Calendar.SECOND));
        cal3 = null;
    }

    @SuppressWarnings("deprecation")
    @Test(expected = IllegalArgumentException.class)
    public void toSqlDate_AccessorExceptionExpected() {
        Date date1 = new Date();
        try {
            // This should be okay...
            date1.getHours();
        } catch (Exception ex) {
            fail("Exception: " + ex);
        }

        java.sql.Date date2 = Dates.toSqlDate(date1);
        date2.getHours();
    }

    @Test
    public void toSqlDate_NullInputDate() {
        assertNull(Dates.toSqlDate(null));
    }

    @Test
    public void testMisc() {

        for (int year = 1962; year < 2050; year++) {
            final Date endOfYear = Dates.newDate(Month.DECEMBER, 31, year);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MILLISECOND, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, Calendar.DECEMBER);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

            assertTrue("1. endOfYear: " + endOfYear, endOfYear.equals(cal.getTime()));

            cal = makeCalendar(Dates.lastDateOfYear(year));
            assertTrue("2. endOfYear: " + endOfYear, endOfYear.equals(cal.getTime()));

            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date firstOfNextYear = Dates.firstOfMonth(Month.JANUARY, year + 1);
            assertTrue("3. firstOfNextYear: " + firstOfNextYear, firstOfNextYear.equals(cal.getTime()));

            firstOfNextYear = Dates.firstOfNextYear(endOfYear);
            assertTrue("4. firstOfNextYear: " + firstOfNextYear, firstOfNextYear.equals(cal.getTime()));

            firstOfNextYear = Dates.firstOfNextYear(Dates.lastDateOfYear(year));
            assertTrue("5. firstOfNextYear: " + firstOfNextYear, firstOfNextYear.equals(cal.getTime()));
        }
    }

    @Test
    public void month() {
        for (Month month : Month.values()) {
            int year = 2012;
            Date lastOfMonth = Dates.lastDateOfMonth(month, year);
            assertEquals(month.getValue(), Dates.month(lastOfMonth));
            Date firstOfMonth = Dates.firstDateOfMonth(month, year);
            assertEquals(month.getValue(), Dates.month(firstOfMonth));
            assertEquals(1, firstOfMonth.getDate());
        }
    }

    @Test
    public void currentYear() {
        Calendar cal = makeCalendar();
        int year = cal.get(Calendar.YEAR);
        assertEquals(year, Dates.currentYear());
    }

    @Test
    public void yearOfDate() {
        for (int year = 2000; year < 2050; year++) {
            assertEquals(year, Dates.yearOfDate(Dates.firstDateOfYear(year)));
            assertEquals(year, Dates.yearOfDate(Dates.lastDateOfYear(year)));
            assertEquals(year, Dates.yearOfDate(Dates.lastDateOfMonth(Month.FEBRUARY, year)));
        }
    }

    @Test
    public void dayOfWeek() {
        LocalDate cal = Dates.toLocalDate(Dates.firstDateOfYear(2012));
        for (int i = 0; i < 366; i++) {
            int dayOfWeek = cal.getDayOfWeek().getValue();
            assertEquals(dayOfWeek, Dates.dayOfWeek(Dates.toDate(cal)));
            cal = cal.plusDays(1);
        }
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2013), Dates.newDate(Dates.toDate(cal)));

        // Just a random one, a Friday.
        Date date = Dates.newDate(Month.DECEMBER, 7, 2012);
        assertEquals(DayOfWeek.FRIDAY.getValue(), Dates.dayOfWeek(date));
    }

    @Test
    public void dayOfMonth() {
        int checks = 0;
        Calendar cal = makeCalendar(Dates.firstDateOfYear(2013));
        for (int m = 0; m < 12; m++) {
            cal.set(Calendar.MONTH, m);
            int maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int d = 1; d <= maxDays; d++) {
                assertEquals(Dates.dayOfMonth(cal.getTime()), cal.get(Calendar.DAY_OF_MONTH));
                cal.add(Calendar.DAY_OF_MONTH, 1);
                checks++;
            }
            assertEquals(1, Dates.dayOfMonth(cal.getTime()));
        }

        assertEquals(365, checks);
        assertEquals(Dates.newDate(Month.JANUARY, 1, 2014), Dates.newDate(cal.getTime()));
    }

    @Test
    public void formatDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String dateStr0 = new SimpleDateFormat("EEEEEEEEE, MMMMMMMMMM d, yyyy").format(date);
        String dateStr1 = Dates.formatDate(date, "EEEEEEEEE, MMMMMMMMMM d, yyyy");
        assertEquals(dateStr1, dateStr0);

        date = Dates.newDate(Month.FEBRUARY, 29, 2012);
        String dateStr2 = Dates.formatDate(date, "MM/yyyy");
        assertEquals("02/2012", dateStr2);

        date = Dates.newDate(Month.FEBRUARY, 29, 2012);
        String dateStr3 = Dates.formatDate(date, "M/yyyy");
        assertEquals("2/2012", dateStr3);

        date = Dates.newDate(Month.NOVEMBER, 30, 2013);
        String dateStr4 = Dates.formatDate(date, "MM/yyyy");
        assertEquals("11/2013", dateStr4);

        date = Dates.newDate(Month.FEBRUARY, 29, 2012);
        String dateStr5 = Dates.formatDate(date, "MMM/yyyy");
        assertEquals("Feb/2012", dateStr5);

        date = Dates.newDate(Month.NOVEMBER, 30, 2013);
        String dateStr6 = Dates.formatDate(date, "MMM/yyyy");
        assertEquals("Nov/2013", dateStr6);

        date = Dates.newDate(Month.DECEMBER, 30, 2013);
        String dateStr7 = Dates.formatDate(date, "M/yyyy");
        assertEquals("12/2013", dateStr7);
    }

    @Test
    public void format() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String dateStr0 = new SimpleDateFormat("MM/dd/yyyy").format(date);
        String dateStr1 = Dates.formatDate(date);
        assertEquals(dateStr1, dateStr0);

        date = Dates.newDate(Month.FEBRUARY, 29, 2012);
        String dateStr2 = Dates.formatDate(date);
        assertEquals("02/29/2012", dateStr2);

        date = Dates.newDate(Month.FEBRUARY, 29, 2012);
        String dateStr3 = Dates.formatDate(date);
        assertEquals("02/29/2012", dateStr3);

        date = Dates.newDate(Month.NOVEMBER, 30, 2013);
        String dateStr4 = Dates.formatDate(date);
        assertEquals("11/30/2013", dateStr4);

        date = Dates.newDate(Month.DECEMBER, 30, 2013);
        String dateStr7 = Dates.formatDate(date);
        assertEquals("12/30/2013", dateStr7);
    }

    @Test
    public void dateToLocalDate() {
        Date d = Dates.newDate(Month.OCTOBER, 31, 2016);
        LocalDate ld = Dates.toLocalDate(d);
        assertEquals(31, ld.getDayOfMonth());
        assertEquals(2016, ld.getYear());
        assertEquals(java.time.Month.OCTOBER, ld.getMonth());
    }
}
