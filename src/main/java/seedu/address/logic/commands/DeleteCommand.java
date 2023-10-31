package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;

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


    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": Deletes the patient identified by their IC Number.\n"
            + "Parameters: " + PREFIX_IC_NUMBER + "IC_NUMBER\n" + "Example: " + COMMAND_WORD + " "
            + PREFIX_IC_NUMBER + "T8472898S";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Patient: %1$s";
    private final IcNumber icNumber;

    public DeleteCommand(IcNumber icNumber) {
        this.icNumber = icNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        Patient patientToDelete = model.getPatient(icNumber, lastShownList);
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
