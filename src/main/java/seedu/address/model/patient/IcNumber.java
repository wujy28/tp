package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's IC Number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIC(String)}
 */
public class IcNumber {
    public static final String MESSAGE_CONSTRAINTS =
            "IC Number should start and end with an alphabet with non negative numbers in between";
    public static final String VALIDATION_REGEX = "^[A-Z]\\d{7}[A-Z]$";
    private static String defaultIcNumber = "t0000000a";
    public final String value;

    /**
     * Constructs a {@code IcNumber}.
     *
     * @param ic A valid IC Number.
     */
    public IcNumber(String ic) {
        ic = ic.toUpperCase();
        requireNonNull(ic);
        checkArgument(isValidIC(ic), MESSAGE_CONSTRAINTS);
        value = ic;
    }

    /**
     * Returns true if a given string is a valid IC.
     */
    public static boolean isValidIC(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    public static String getDefaultIcNumber() {
        return defaultIcNumber;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof IcNumber)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        IcNumber otherIC = (IcNumber) other;
        return value.equals(otherIC.value);
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
