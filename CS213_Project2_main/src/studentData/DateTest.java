package studentData;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    Date date;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        date = null;
    }

    @org.junit.jupiter.api.Test
    void isValid_Year_Before_2021() {
        date = new Date("01/01/2000");
        assertFalse(date.isValid());
    }

    @org.junit.jupiter.api.Test
    void isValid_Year_After_2021() {
        date = new Date("01/01/2022");
        assertFalse(date.isValid());
    }

    @org.junit.jupiter.api.Test
    void isValid_negative_month() {
        date = new Date("-1/1/2021");
        assertFalse(date.isValid());
    }

    @org.junit.jupiter.api.Test
    void isValid_negative_day() {
        date = new Date("1/-1/2021");
        assertFalse(date.isValid());
    }

    @org.junit.jupiter.api.Test
    void isValid_Month_outOfBounds() {
        date = new Date("15/14/2021");
        assertFalse(date.isValid());
    }

    @org.junit.jupiter.api.Test
    void isValid_Day_outOfBounds() {
        date = new Date("01/50/2021");
        assertFalse(date.isValid());
    }

    @org.junit.jupiter.api.Test
    void isValid_NonLeapYear_29days() {
        date = new Date("02/29/2021");
        assertFalse(date.isValid());
    }

    @org.junit.jupiter.api.Test
    void isValid_DayBeforeToday_2021Year() {
        date = new Date("01/01/2021");
        assertTrue(date.isValid());
    }

    @org.junit.jupiter.api.Test
    void isValid_DayAfterToday() {
        date = new Date("12/14/2021");
        assertFalse(date.isValid());
    }

    @org.junit.jupiter.api.Test
    void isValid_DayBeforeToday_31Day_30DayMonth_2021Year() {
        date = new Date("31/04/2021");
        assertFalse(date.isValid());
    }

    @org.junit.jupiter.api.Test
    void isValid_Feb28Days_2021Year() {
        date = new Date("02/32/2021");
        assertFalse(date.isValid());
    }
}