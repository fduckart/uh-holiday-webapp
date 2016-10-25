package edu.hawaii.its.holiday.util;

public enum Month {
    JANUARY(0),
    FEBRUARY(1),
    MARCH(2),
    APRIL(3),
    MAY(4),
    JUNE(5),
    JULY(6),
    AUGUST(7),
    SEPTEMBER(8),
    OCTOBER(9),
    NOVEMBER(10),
    DECEMBER(11);

    private int value;

    private Month(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Month valueOf(int i) {
        if (i < JANUARY.getValue() && i > DECEMBER.getValue()) {
            throw new IllegalArgumentException("Invalid int for Month: " + i);
        }
        return Month.values()[i];
    }
}
