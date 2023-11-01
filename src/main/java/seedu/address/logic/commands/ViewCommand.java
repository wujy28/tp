package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_PATIENT_LISTED_SUCCESS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.PatientWithIcNumberPredicate;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;

/**
 * Views the Patient with a certain {@Code IcNumber}
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": View the Patient with IcNumber\n" + "Parameters: IC_NUMBER\n" + "Example: " + COMMAND_WORD
            + " i/t1234567j";

    private final PatientWithIcNumberPredicate predicate;
    private final IcNumber icNumberToFind;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Creates a ViewCommand object to be executed
     *
     * @param predicate      The predicate used to filter Patients
     * @param icNumberToFind The icNumber entered by user input to be find
     */
    public ViewCommand(PatientWithIcNumberPredicate predicate, IcNumber icNumberToFind)
            throws PatientWithFieldNotFoundException {
        if (icNumberToFind == null) {
            throw new PatientWithFieldNotFoundException("Multiplicity violated. Each patient has 1 non-null IcNumber.");
        }
        this.predicate = predicate;
        this.icNumberToFind = icNumberToFind;
    }

    /**
     * Executes the {@Code executes ViewCommand} using the current model and return {@Code CommandResult}
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult object
     * @throws PatientWithFieldNotFoundException If user enters a field not present in any existing patients
     */
    @Override
    public CommandResult execute(Model model) throws PatientWithFieldNotFoundException {
        requireNonNull(model);
        model.updateFilteredPatientList(predicate);
        if (model.getFilteredPatientList().size() == 0) { // no patient with that IC
            throw new PatientWithFieldNotFoundException("Ic Number : " + icNumberToFind.value);
        }
        logger.info("ViewCommand : " + this + "\nsuccessfully executed");
        return new CommandResult(MESSAGE_PATIENT_LISTED_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand otherViewCommand = (ViewCommand) other;
        return predicate.equals(otherViewCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("predicate", predicate).toString();
    }
}
