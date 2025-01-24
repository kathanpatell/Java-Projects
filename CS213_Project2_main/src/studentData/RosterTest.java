package studentData;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RosterTest {

    Roster testRoster;
    Profile profile;
    Date date;
    int credits;
    Student student;
    double tuition_due;
    double total_payment;
    String residingState;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
       testRoster = new Roster();
    }
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        testRoster = null;
        profile = null;
        date = null;
        student = null;
    }

    @Test
    void add_valid_student()
    {
        profile = new Profile("Matt",Major.valueOf("CS"));
        date = new Date("00/00/00");
        credits = 12;
        tuition_due = 0;
        total_payment = 0;
        residingState = "NJ";
        student = new Student(profile, credits,
                tuition_due, total_payment, date, true, residingState);
        assertTrue(testRoster.add(student));
        assertTrue(testRoster.getSize() == 1);
    }

    @Test
    void add_null_student()
    {
        student = null;
        assertFalse(testRoster.add(student));
        assertTrue(testRoster.getSize() == 0);
    }

    @Test
    void remove_existing_student() {
        profile = new Profile("Matt",Major.valueOf("CS"));
        date = new Date("00/00/00");
        credits = 12;
        tuition_due = 0;
        total_payment = 0;
        residingState = "NJ";
        Student student1 = new Student(profile, credits,
                tuition_due, total_payment, date, true, residingState);

        testRoster.add(student1);

        profile = new Profile("Mike",Major.valueOf("EE"));
        date = new Date("00/00/00");
        credits = 15;
        tuition_due = 0;
        total_payment = 0;
        residingState = "NY";
        Student student2 = new Student(profile, credits,
                tuition_due, total_payment, date, true, residingState);

        testRoster.add(student2);

        assertTrue(testRoster.getSize() == 2);

        assertTrue(testRoster.remove(student2));

        assertTrue(testRoster.getSize() == 1);
    }


    @Test
    void remove_non_existing_student() {
        profile = new Profile("Matt",Major.valueOf("CS"));
        date = new Date("00/00/00");
        credits = 12;
        tuition_due = 0;
        total_payment = 0;
        residingState = "NJ";
        Student student1 = new Student(profile, credits,
                tuition_due, total_payment, date, true, residingState);

        testRoster.add(student1);

        profile = new Profile("Mike",Major.valueOf("EE"));
        date = new Date("00/00/00");
        credits = 15;
        tuition_due = 0;
        total_payment = 0;
        residingState = "NY";
        Student student2 = new Student(profile, credits,
                tuition_due, total_payment, date, true, residingState);

        testRoster.add(student2);

        assertTrue(testRoster.getSize() == 2);

        profile = new Profile("Ram",Major.valueOf("EE"));
        date = new Date("00/00/00");
        credits = 16;
        tuition_due = 0;
        total_payment = 0;
        residingState = "CT";
        Student student3 = new Student(profile, credits,
                tuition_due, total_payment, date, true, residingState);


        assertFalse(testRoster.remove(student3));

        assertTrue(testRoster.getSize() == 2);
    }

}