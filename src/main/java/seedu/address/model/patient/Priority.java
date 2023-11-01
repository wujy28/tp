package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's priority in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority implements Comparable<Priority> {
    public static final String MESSAGE_CONSTRAINTS =
            "Priority should only be NIL, LOW, MEDIUM or HIGH, and it should not be blank";

    public static final String VALIDATION_REGEX = "\\p{Alnum}*";

    private static String defaultPriority = "NIL";

    public final PriorityLevel value;

    enum PriorityLevel {
        NIL,
        LOW,
        MEDIUM,
        HIGH;

        public static PriorityLevel getPriority(String string) {
            for (Priority.PriorityLevel p : Priority.PriorityLevel.values()) {
                if (p.name().equalsIgnoreCase(string)) {
                    return p;
                }
            }
            return PriorityLevel.NIL;
        }
    }

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority A valid priority.
     */
    public Priority(String priority) {
        String temp = priority.toUpperCase();
        requireNonNull(temp);
        checkArgument(isValidPriority(temp), MESSAGE_CONSTRAINTS);
        value = PriorityLevel.getPriority(temp);
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidPriority(String test) {
        test = test.toUpperCase();
        return test.matches(VALIDATION_REGEX) && isInPrioritiesEnum(test);
    }

    public static String getDefaultPriority() {
        return defaultPriority;
    }

    /**
     * Returns true if given string is in the Priorities enum class.
     *
     * @param test the given string.
     * @return boolean to indicate presence in Priorities enum.
     */
    private static boolean isInPrioritiesEnum(String test) {
        for (Priority.PriorityLevel p : Priority.PriorityLevel.values()) {
            if (p.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return value.name();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Priority)) {
            return false;
        }

        Priority otherPriority = (Priority) other;
        return value.equals(otherPriority.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Priority o) {
        PriorityLevel thisPriorityLevel = this.value;
        PriorityLevel otherPriorityLevel = o.value;

        if (thisPriorityLevel.compareTo(otherPriorityLevel) < 0) {
            return -1;
        } else if (thisPriorityLevel.compareTo(otherPriorityLevel) > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
