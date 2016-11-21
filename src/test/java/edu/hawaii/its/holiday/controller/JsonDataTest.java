package edu.hawaii.its.holiday.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class JsonDataTest {

    @Test
    public void equals() {
        JsonData<String> s0 = new JsonData<>("test");
        JsonData<String> s1 = new JsonData<>("test");
        JsonData<String> s2 = new JsonData<>("nada");
        JsonData<String> s3 = new JsonData<>(null);
        JsonData<String> s4 = new JsonData<>(null);

        assertThat(s0.getKey(), equalTo("data"));
        assertThat(s0.getData(), equalTo("test"));

        assertTrue(s0.equals(s1));
        assertTrue(s0.equals(s0));
        assertFalse(s0.equals(s2));
        assertFalse(s1.equals(s2));

        assertTrue(s3.equals(s4));
        assertTrue(s4.equals(s3));

        assertFalse(s0.equals(s3));
        assertFalse(s2.equals(s3));
        assertFalse(s3.equals(s2));

        assertFalse(s0.equals("test"));
        assertFalse(s1.equals(null));

        JsonData<Date> d0 = new JsonData<>(new Date());
        assertThat(d0.getKey(), equalTo("data"));
        assertFalse(s0.equals(d0));

        JsonData<String> s5 = new JsonData<>("key", "sss");
        JsonData<String> s6 = new JsonData<>("key", "sss");
        JsonData<String> s7 = new JsonData<>(null, "sss");
        JsonData<String> s8 = new JsonData<>("key", "ttt");
        assertTrue(s5.equals(s6));
        assertFalse(s6.equals(s7));
        assertFalse(s7.equals(s6));

        assertFalse(s7.equals(s8));
        assertFalse(s8.equals(s7));
    }
}
