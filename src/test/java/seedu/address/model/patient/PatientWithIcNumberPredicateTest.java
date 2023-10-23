package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPatients.AMY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class PatientWithIcNumberPredicateTest {
    @Test
    public void equals() {
        IcNumber testIcNumber1 = new IcNumber("t1234567a");
        IcNumber testIcNumber2 = new IcNumber("s7654321a");

        PatientWithIcNumberPredicate firstPredicate = new PatientWithIcNumberPredicate(testIcNumber1);
        PatientWithIcNumberPredicate secondPredicate = new PatientWithIcNumberPredicate(testIcNumber2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PatientWithIcNumberPredicate firstPredicateCopy = new PatientWithIcNumberPredicate(testIcNumber1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different ic number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }


    @Test
    public void test_patientIcNumberSame_returnsTrue() {
        // One keyword
        IcNumber testIcNumber = new IcNumber("t1234567a");
        PatientWithIcNumberPredicate predicate = new PatientWithIcNumberPredicate(testIcNumber);
        Patient testPatient = new PatientBuilder(AMY).withIcNumber("t1234567a").build();
        assertTrue(predicate.test(testPatient));
    }

    @Test
    public void test_patientIcNumberDifferent_returnsFalse() {
        // One keyword
        IcNumber testIcNumber = new IcNumber("t1234567a");
        PatientWithIcNumberPredicate predicate = new PatientWithIcNumberPredicate(testIcNumber);
        Patient testPatient = new PatientBuilder(AMY).withIcNumber("s1234567b").build();
        assertFalse(predicate.test(testPatient));
    }

    @Test
    public void toStringMethod() {
        IcNumber testIcNumber = new IcNumber("t1234567a");
        PatientWithIcNumberPredicate predicate = new PatientWithIcNumberPredicate(testIcNumber);

        String expected = PatientWithIcNumberPredicate.class.getCanonicalName() + "{icNumber=" + testIcNumber + "}";
        assertEquals(expected, predicate.toString());
    }


}
