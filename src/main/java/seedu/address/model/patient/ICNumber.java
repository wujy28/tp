package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ICNumber {
    public static final String MESSAGE_CONSTRAINTS =
            "IC Number should start and end with an alphabet with non negative numbers in between";
    public static final String VALIDATION_REGEX = "^[A-Z]\\d{7}[A-Z]$";
    public final String value;

    public ICNumber(String IC) {
        requireNonNull(IC);
        checkArgument(isValidIC(IC), MESSAGE_CONSTRAINTS);
        value = IC;
    }

    public static boolean isValidIC(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ICNumber)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        ICNumber otherIC = (ICNumber) other;
        return value.equals(otherIC.value);
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
