package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // null age
        assertThrows(NullPointerException.class, () -> Age.isValidAge(null));

        // invalid ages
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("-3")); // negative number
        assertFalse(Age.isValidAge("age")); // non-numeric
        assertFalse(Age.isValidAge("21y")); // alphabets within digits
        assertFalse(Age.isValidAge("21.5")); // characters within digits
        assertFalse(Age.isValidAge("2 1")); // spaces within digits

        // valid ages
        assertTrue(Age.isValidAge("0"));
        assertTrue(Age.isValidAge("43"));
        assertTrue(Age.isValidAge("99"));
    }

    @Test
    public void equals() {
        Age age = new Age("68");

        // same values -> returns true
        assertTrue(age.equals(new Age("68")));

        // same object -> returns true
        assertTrue(age.equals(age));

        // null -> returns false
        assertFalse(age.equals(null));

        // different types -> returns false
        assertFalse(age.equals(5.0f));

        // different values -> returns false
        assertFalse(age.equals(new Age("57")));
    }
}
