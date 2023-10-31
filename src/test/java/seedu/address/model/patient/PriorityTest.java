package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriority_throwsIllegalArgumentException() {
        String invalidPriority = "";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriority));
    }

    @Test
    public void isValidPriority() {
        // null gender
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid gender
        assertFalse(Priority.isValidPriority("")); // empty string
        assertFalse(Priority.isValidPriority(" ")); // spaces only
        assertFalse(Priority.isValidPriority("^")); // only non-alphanumeric characters
        assertFalse(Priority.isValidPriority("low*")); // contains non-alphanumeric characters
        assertFalse(Priority.isValidPriority("mid")); // not in Priorities enum

        // valid gender
        assertTrue(Priority.isValidPriority("nil")); // lowercase only
        assertTrue(Priority.isValidPriority("low"));
        assertTrue(Priority.isValidPriority("medium"));
        assertTrue(Priority.isValidPriority("high"));
        assertTrue(Priority.isValidPriority("NIL")); // uppercase only
        assertTrue(Priority.isValidPriority("LOW"));
        assertTrue(Priority.isValidPriority("MEDIUM"));
        assertTrue(Priority.isValidPriority("HIGH"));
        assertTrue(Priority.isValidPriority("nIL")); // mix of lower and uppercase
        assertTrue(Priority.isValidPriority("LoW"));
        assertTrue(Priority.isValidPriority("MEdIuM"));
        assertTrue(Priority.isValidPriority("hIgH"));
    }

    @Test
    public void equals() {
        Priority priority = new Priority("high");

        // same values -> returns true
        assertTrue(priority.equals(new Priority("HIGH")));

        // same object -> returns true
        assertTrue(priority.equals(priority));

        // null -> returns false
        assertFalse(priority.equals(null));

        // different types -> returns false
        assertFalse(priority.equals(5.0f));

        // different values -> returns false
        assertFalse(priority.equals(new Priority("low")));
    }
}
