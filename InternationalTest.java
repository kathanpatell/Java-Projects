package studentData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternationalTest {

    Profile profile;
    Date date;
    int credits;
    double tuition_due;
    double total_payment;
    String residingState;
	
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        profile = null;
        date = null;
    }

    @Test
    void tuitionDue_nonstudyabroad()
    {
        profile = new Profile("Matt",Major.valueOf("CS"));
        final double total_tuition = 35655.0;
        credits = 15;
        tuition_due = 0;
        total_payment = 0;
        date = new Date("00/00/00");

    	International international = new International(profile, credits, tuition_due,
                total_payment, date, true, false);

        international.tuitionDue();

        assertTrue(international.getTuitionDue() == total_tuition);
    }

    @Test
    void tuitionDue_nonstudyabroad_over_16credits()
    {
        profile = new Profile("Matt",Major.valueOf("CS"));
        credits = 20;
        final int max_credit_noSurcharge = 16;
        final double nonresident_credit_perCharge = 966.0;
        final double total_tuition = 35655.0 +
                (credits - max_credit_noSurcharge)* nonresident_credit_perCharge;
        tuition_due = 0;
        total_payment = 0;
        date = new Date("00/00/00");

        International international = new International(profile, credits, tuition_due,
                total_payment, date, true, false);

        international.tuitionDue();

        assertTrue(international.getTuitionDue() == total_tuition);
    }
    @Test
    void tuitionDue_studyabroad()
    {
        profile = new Profile("Matt",Major.valueOf("CS"));
        final double total_tuition = 5918.0;
        credits = 12;
        tuition_due = 0;
        total_payment = 0;
        date = new Date("00/00/00");

        International international = new International(profile, credits, tuition_due,
                total_payment, date, true, true);
        international.tuitionDue();

        assertTrue(international.getTuitionDue() == total_tuition);
    }

}