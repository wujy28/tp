package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the patient record system.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Patient record has been cleared!";


    @Override
    public CommandResult execute(Model model, String command) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook(), command);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
