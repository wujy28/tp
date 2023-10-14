package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's assigned department in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDepartment(String)}
 */
public class AssignedDepartment {

    public static final String MESSAGE_CONSTRAINTS = "Departments can only take certain values, "
            + "must adhere to British spelling conventions, "
            + "and should not be blank.\n"
            + "Please view the user guide for the list of accepted departments.\n"
            + "Link: https://ay2324s1-cs2103t-t14-2.github.io/tp/UserGuide.html";

    /*
     * The first character of the department must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final Department assignedDepartment;

    /**
     * Constructs a default {@code AssignedDepartment}.
     */
    public AssignedDepartment() {
        assignedDepartment = Department.DEFAULT;
    }

    /**
     * Constructs an {@code AssignedDepartment} with the given string.
     *
     * @param department A valid department.
     */
    public AssignedDepartment(String department) {
        requireNonNull(department);
        checkArgument(isValidDepartment(department), MESSAGE_CONSTRAINTS);
        assignedDepartment = Department.findDepartment(department);
    }

    /**
     * Returns true if a given string is a valid department.
     */
    public static boolean isValidDepartment(String test) {
        return test.matches(VALIDATION_REGEX) && Department.isValidDepartment(test);
    }

    @Override
    public String toString() {
        return assignedDepartment.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignedDepartment)) {
            return false;
        }

        AssignedDepartment otherDepartment = (AssignedDepartment) other;
        return assignedDepartment.equals(otherDepartment.assignedDepartment);
    }

    @Override
    public int hashCode() {
        return assignedDepartment.hashCode();
    }
}
