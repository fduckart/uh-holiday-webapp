package edu.hawaii.its.holiday.util;

import java.util.Arrays;

public class Strings {

    public static String fill(final char ch, final int size) {
        char[] fill = new char[size];
        Arrays.fill(fill, ch);

        return new String(fill);
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() > 0;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static String truncate(String value, int length) {
        if (value != null && value.length() > length) {
            value = value.substring(0, length);
        }
        return value;
    }
}
