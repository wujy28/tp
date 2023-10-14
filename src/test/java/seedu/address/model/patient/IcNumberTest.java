package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IcNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IcNumber(null));
    }

    @Test
    public void constructor_invalidIC_throwsIllegalArgumentException() {
        String invalidIC = "";
        assertThrows(IllegalArgumentException.class, () -> new IcNumber(invalidIC));
    }

    @Test
    public void isValidIC() {
        assertThrows(NullPointerException.class, () -> IcNumber.isValidIC(null));

        // invalid ICs
        assertFalse(IcNumber.isValidIC("")); // empty string
        assertFalse(IcNumber.isValidIC(" ")); // spaces only
        assertFalse(IcNumber.isValidIC("22")); // less than 7 numbers, no alphabets
        assertFalse(IcNumber.isValidIC("january")); // non-numeric
        assertFalse(IcNumber.isValidIC("2307991")); // not in the format of AXXXXXXXXA
                                                          // where A is for alphabet, X is for int
        assertFalse(IcNumber.isValidIC("T 0 1 2 3 56 9 J")); // spaces within digits and alphabets

        // valid ICs
        assertTrue(IcNumber.isValidIC("T1234567N")); // AXXXXXXXXA format
        assertTrue(IcNumber.isValidIC("S0123678B")); // AXXXXXXXXA format
    }

    @Test
    public void equals() {
        IcNumber IC = new IcNumber("T1234567N");

        // same values -> returns true
        assertTrue(IC.equals(new IcNumber("S1357911N")));

        // same object -> returns true
        assertTrue(IC.equals(IC));

        // null -> returns false
        assertFalse(IC.equals(null));

        // different types -> returns false
        assertFalse(IC.equals(5.0f));

        // different values -> returns false
        assertFalse(IC.equals(new IcNumber("T0246810X")));
    }
}