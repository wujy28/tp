package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

public class AgeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age((String) null));
        assertThrows(NullPointerException.class, () -> new Age((Birthday) null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));

        String negativeAge = "-1";
        assertThrows(IllegalArgumentException.class, () -> new Age(negativeAge));
    }

    @Test
    public void constructor_validInputs_constructedCorrectly() {
        // valid age
        String validAge0 = "0";
        Age age1 = new Age(validAge0);
        assertTrue(age1.value == 0);

        String validAge1 = "1";
        Age age2 = new Age(validAge1);
        assertTrue(age2.value == 1);

        // default birthday
        Birthday defaultBday = new Birthday(Birthday.getDefaultBirthday());
        Age age3 = new Age(defaultBday);
        assertTrue(age3.value == -1);

        // valid birthday
        Birthday validBday1 = new Birthday("03/01/2003");
        Age age4 = new Age(validBday1);
        int ageValueInt1 = findAge(validBday1);
        assertTrue(age4.value == ageValueInt1);

        // valid birthday
        Birthday validBday2 = new Birthday("30/12/2000");
        Age age5 = new Age(validBday2);
        int ageValueInt2 = findAge(validBday2);
        assertTrue(age5.value == ageValueInt2);
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
        Age age1 = new Age("68");

        // same values -> returns true
        assertTrue(age1.equals(new Age("68")));

        // same object -> returns true
        assertTrue(age1.equals(age1));

        // null -> returns false
        assertFalse(age1.equals(null));

        // different types -> returns false
        assertFalse(age1.equals(5.0f));

        // different values -> returns false
        assertFalse(age1.equals(new Age("57")));
    }

    @Test
    public void testToString() {
        // string input
        Age age1 = new Age("68");
        String string1 = age1.toString();
        assertEquals("68", string1);

        // default Birthday
        Birthday defaultBday = new Birthday(Birthday.getDefaultBirthday());
        Age age2 = new Age(defaultBday);
        String string2 = age2.toString();
        assertEquals(Age.getDefaultAgeString(), string2);

        // valid birthday
        Birthday validBday = new Birthday("10/10/2003");
        Age age3 = new Age(validBday);
        Integer ageValue = findAge(validBday);
        String expected = ageValue.toString();
        String actual = age3.toString();
        assertEquals(expected, actual);
    }

    private Integer findAge(Birthday birthday) {
        LocalDate today = LocalDate.now();
        Long ageValueLong = ChronoUnit.YEARS.between(birthday.value, today);
        return ageValueLong.intValue();
    }

    @Test
    public void testCompareTo() {
        Age age1 = new Age("22");

        // less than -> returns -1
        assertEquals(-1, age1.compareTo(new Age("68")));

        // equal -> returns 0
        assertEquals(0, age1.compareTo(new Age("22")));

        // more than -> returns 1
        assertEquals(1, age1.compareTo(new Age("17")));
    }
}
