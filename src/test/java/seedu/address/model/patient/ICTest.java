package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ICTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ICNumber(null));
    }

    @Test
    public void constructor_invalidIC_throwsIllegalArgumentException() {
        String invalidIC = "";
        assertThrows(IllegalArgumentException.class, () -> new ICNumber(invalidIC));
    }

    @Test
    public void isValidIC() {
        assertThrows(NullPointerException.class, () -> ICNumber.isValidIC(null));

        // invalid ICs
        assertFalse(ICNumber.isValidIC("")); // empty string
        assertFalse(ICNumber.isValidIC(" ")); // spaces only
        assertFalse(ICNumber.isValidIC("22")); // less than 7 numbers, no alphabets
        assertFalse(ICNumber.isValidIC("january")); // non-numeric
        assertFalse(ICNumber.isValidIC("2307991")); // not in the format of AXXXXXXXXA
                                                          // where A is for alphabet, X is for int
        assertFalse(ICNumber.isValidIC("T 0 1 2 3 56 9 J")); // spaces within digits and alphabets

        // valid ICs
        assertTrue(ICNumber.isValidIC("T1234567N")); // AXXXXXXXXA format
        assertTrue(ICNumber.isValidIC("S0123678B")); // AXXXXXXXXA format
    }

    @Test
    public void equals() {
        ICNumber IC = new ICNumber("T1234567N");

        // same values -> returns true
        assertTrue(IC.equals(new ICNumber("S1357911N")));

        // same object -> returns true
        assertTrue(IC.equals(IC));

        // null -> returns false
        assertFalse(IC.equals(null));

        // different types -> returns false
        assertFalse(IC.equals(5.0f));

        // different values -> returns false
        assertFalse(IC.equals(new ICNumber("T0246810X")));
    }
}