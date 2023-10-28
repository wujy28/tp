package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;

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
    private final Logger logger = LogsCenter.getLogger(getClass());

    public DeleteCommand(IcNumber icNumber) {
        this.icNumber = icNumber;
    }

    @Override
    public CommandResult execute(Model model) throws  PatientWithFieldNotFoundException {
        requireNonNull(model);
        List<Patient> currentPatientList = model.getCurrentPatientList();

        Patient patientToDelete = model.getPatient(icNumber, currentPatientList);

        if (patientToDelete == null) { // no patient with that IC
            throw new PatientWithFieldNotFoundException("Ic Number : " + icNumber.value);
        }
        logger.info("DeleteCommand : " + this + "\nsuccessfully executed");
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
        return new ToStringBuilder(this).add("icNumber", icNumber).toString();
    }
}
