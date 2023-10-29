package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("^")); // only non-alphanumeric characters
        assertFalse(Gender.isValidGender("male*")); // contains non-alphanumeric characters
        assertFalse(Gender.isValidGender("non-binary")); // not in Genders enum

        // valid gender
        assertTrue(Gender.isValidGender("male")); // lowercase only
        assertTrue(Gender.isValidGender("female"));
        assertTrue(Gender.isValidGender("other"));
        assertTrue(Gender.isValidGender("MALE")); // uppercase only
        assertTrue(Gender.isValidGender("FEMALE"));
        assertTrue(Gender.isValidGender("OTHER"));
        assertTrue(Gender.isValidGender("MaLe")); // mix of lower and uppercase
        assertTrue(Gender.isValidGender("fEmAlE"));
        assertTrue(Gender.isValidGender("OtHeR"));
    }

    @Test
    public void equals() {
        Gender gender = new Gender("female");

        // same values -> returns true
        assertTrue(gender.equals(new Gender("FEMALE")));

        // same object -> returns true
        assertTrue(gender.equals(gender));

        // null -> returns false
        assertFalse(gender.equals(null));

        // different types -> returns false
        assertFalse(gender.equals(5.0f));

        // different values -> returns false
        assertFalse(gender.equals(new Gender("male")));
    }
}
