package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Adds a patient to the patient record system.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": Adds a patient to the patient record. " + "Parameters: " + PREFIX_NAME + "NAME " + PREFIX_PHONE
            + "PHONE " + PREFIX_EMAIL + "EMAIL " + PREFIX_GENDER + "GENDER " + PREFIX_IC_NUMBER + "IC_NUMBER "
            + PREFIX_BIRTHDAY + "BIRTHDAY " + PREFIX_ADDRESS + "ADDRESS " + PREFIX_PRIORITY + "PRIORITY "
            + "[" + PREFIX_TAG + "TAG]...\n" + "Example: "
            + COMMAND_WORD + " " + PREFIX_NAME + "John Doe " + PREFIX_PHONE + "98765432 " + PREFIX_EMAIL
            + "johnd@example.com " + PREFIX_GENDER + "MALE " + PREFIX_IC_NUMBER + "S2840182A " + PREFIX_BIRTHDAY
            + "02/01/1998 " + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 " + PREFIX_PRIORITY + "MEDIUM "
            + PREFIX_TAG + "friends " + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the patient record!";
    public static final String MESSAGE_PATIENT_WITH_IC_NUMBER_ALREADY_EXIST =
        "The patient with that IC Number " + "already exists in the patient record!";

    private final Patient toAdd;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Creates an AddCommand to add the specified {@code Patient}
     */
    public AddCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model, String command) throws CommandException {
        requireNonNull(model);
        if (model.hasPatient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }
        if (model.isPatientWithIcNumberPresent(toAdd.getIcNumber())) {
            throw new CommandException(MESSAGE_PATIENT_WITH_IC_NUMBER_ALREADY_EXIST);
        }
        assert !model.isPatientWithIcNumberPresent(toAdd.getIcNumber()); // ic number should be non-existing
        model.addPatient(toAdd, command);
        logger.info("AddCommand : " + this + "\nsuccessfully executed");
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("toAdd", toAdd).toString();
    }
}
