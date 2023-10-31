package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Patient;

/**
 * Deletes a patient identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the ic number used in the displayed patient list.\n"
            + "Parameters: IC Number (must start and end with an alphabet with non negative numbers in between)\n"
            + "Example: " + COMMAND_WORD + " i/T0000000A";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Patient: %1$s";
    private final IcNumber icNumber;

    public DeleteCommand(IcNumber icNumber) {
        this.icNumber = icNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> currentPatientList = model.getCurrentPatientList();

        Patient patientToDelete = model.getPatient(icNumber, currentPatientList);
        model.deletePatient(patientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_SUCCESS, Messages.format(patientToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return icNumber.equals(otherDeleteCommand.icNumber);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("icNumber", icNumber)
                .toString();
    }
}
