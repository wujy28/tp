package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthdate_throwsIllegalArgumentException() {
        String invalidBirthdate = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthdate));
    }

    @Test
    public void isValidBirthdate() {
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthdate(null));

        // invalid birthdates
        assertFalse(Birthday.isValidBirthdate("")); // empty string
        assertFalse(Birthday.isValidBirthdate(" ")); // spaces only
        assertFalse(Birthday.isValidBirthdate("22")); // less than 8 numbers
        assertFalse(Birthday.isValidBirthdate("january")); // non-numeric
        assertFalse(Birthday.isValidBirthdate("23071991")); // not in the format of dd/mm/yyyy
        assertFalse(Birthday.isValidBirthdate("22 /01 / 1997")); // spaces within digits
        assertFalse(Birthday.isValidBirthdate("01 /01 / 3000")); // date in the future


        // valid birthdates
        assertTrue(Birthday.isValidBirthdate("21/01/1994")); // dd/mm/yyyy format, a date in the past
        assertTrue(Birthday.isValidBirthdate("20/01/1974")); // dd/mm/yyyy format, a date in the past
        assertTrue(Birthday.isValidBirthdate(LocalDate.now().format(Birthday.getFormatter()))); // the current date
    }

    @Test
    public void equals() {
        Birthday birthday = new Birthday("21/01/1990");

        // same values -> returns true
        assertTrue(birthday.equals(new Birthday("21/01/1990")));

        // same object -> returns true
        assertTrue(birthday.equals(birthday));

        // null -> returns false
        assertFalse(birthday.equals(null));

        // different types -> returns false
        assertFalse(birthday.equals(5.0f));

        // different values -> returns false
        assertFalse(birthday.equals(new Birthday("23/01/2007")));
    }
}
