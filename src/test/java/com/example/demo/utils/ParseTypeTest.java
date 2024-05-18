package com.example.demo.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParseTypeTest {

    @Test
    void parse() {
        String advertising = "02010614FF107803E830CA140EE7794E0170625143150416050942445442";
        int length = advertising.length();
        assertEquals(60, length);
        assertEquals(3815, ParseType.STEP.parse(advertising));
        assertEquals(121, ParseType.BLOOD_PRESSURE_MAX.parse(advertising));
        assertEquals(78, ParseType.BLOOD_PRESSURE_MIN.parse(advertising));
        double temperature = ParseType.BODY_TEMPERATURE.parse(advertising);
        assertEquals(368, temperature);
        assertEquals(37.1, (temperature+3)/10);
        assertEquals(98, ParseType.OXYGEN.parse(advertising));
        assertEquals(81, ParseType.PULSE.parse(advertising));
        assertEquals(67, ParseType.BATTERY.parse(advertising));
    }
}
