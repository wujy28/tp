package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Patient's Birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthdate(String)}
 */
public class Birthday {
    public static final String MESSAGE_CONSTRAINTS =
            "Birth dates should only contain numbers in valid dd/MM/yyyy format";
    public static final String VALIDATION_REGEX = "\\d{1,2}\\/\\d{1,2}\\/\\d{2,4}";
    public static final String DEFAULT_BIRTHDAY = "01/01/2000";
    public final LocalDate value;
    public final String strValue;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthdate A valid Birthday.
     */
    public Birthday(String birthdate) {
        requireNonNull(birthdate);
        checkArgument(isValidBirthdate(birthdate), MESSAGE_CONSTRAINTS);
        strValue = birthdate;
        value = LocalDate.parse(birthdate, formatter);
    }

    /**
     * Returns true if a given string is a valid Birthdate.
     */
    public static boolean isValidBirthdate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Birthday)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        Birthday otherDate = (Birthday) other;
        return value.equals(otherDate.value);
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
