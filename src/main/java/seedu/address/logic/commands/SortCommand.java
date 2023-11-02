package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Sorts the patient list based on the given property.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the patient list "
            + "based on the given property.\n"
            + "Parameters: PROPERTY\n"
            + "PROPERTY can only be one of the following: name, ic, department, age, priority"
            + "Example: " + COMMAND_WORD + " priority";
    public static final String MESSAGE_SORT_LIST_SUCCESS = "Sorted current list by %s";

    private final SortOrder sortOrder;

    /**
     * @param sortOrder property to sort the list by
     */
    public SortCommand(SortOrder sortOrder) {
        requireNonNull(sortOrder);
        this.sortOrder = sortOrder;
    }

    @Override
    public CommandResult execute(Model model, String str) {
        requireNonNull(model);
        model.sortPatientList(sortOrder.getComparator());
        return new CommandResult(String.format(MESSAGE_SORT_LIST_SUCCESS, sortOrder));
    }

    /**
     * Enumeration for sort orders.
     * These are the available properties to sort the list by.
     */
    public enum SortOrder {
        NAME(Comparator.comparing(Patient::getName), "name"),
        IC_NUMBER(Comparator.comparing(Patient::getIcNumber), "ic"),
        DEPARTMENT(Comparator.comparing(Patient::getAssignedDepartment), "department"),
        AGE(Comparator.comparing(Patient::getAge), "age"),
        PRIORITY((p1, p2) -> -(p1.getPriority().compareTo(p2.getPriority())), "priority");


        private Comparator<? super Patient> comparator;
        private String string;

        SortOrder(Comparator<? super Patient> comparator, String string) {
            this.comparator = comparator;
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }

        public Comparator<? super Patient> getComparator() {
            return comparator;
        }

        public static SortOrder getSortOrder(String string) {
            for (SortOrder sortOrder : SortOrder.values()) {
                if (string.equalsIgnoreCase(sortOrder.string)) {
                    return sortOrder;
                }
            }
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherFindCommand = (SortCommand) other;
        return sortOrder.equals(otherFindCommand.sortOrder);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("sortOrder", sortOrder).toString();
    }
}
