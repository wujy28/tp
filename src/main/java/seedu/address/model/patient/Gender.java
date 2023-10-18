package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {
    public static final String MESSAGE_CONSTRAINTS =
            "Gender should only be MALE, FEMALE or OTHER, and it should not be blank";

    public static final String VALIDATION_REGEX = "\\p{Alnum}*";
    public static final String DEFAULT_GENDER = "OTHER";
    public final String value;

    enum Genders {
        MALE,
        FEMALE,
        OTHER
    }

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        String temp = gender.toUpperCase();
        requireNonNull(temp);
        checkArgument(isValidGender(temp), MESSAGE_CONSTRAINTS);
        value = temp;
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX) && isInGendersEnum(test);
    }

    /**
     * Returns true if given string is in the Genders enum class.
     *
     * @param test the given string.
     * @return boolean to indicate presence in Genders enum.
     */
    private static boolean isInGendersEnum(String test) {
        for (Genders g : Genders.values()) {
            if (g.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Gender)) {
            return false;
        }

        Gender otherGender = (Gender) other;
        return value.equals(otherGender.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
