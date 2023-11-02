package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes most recent command.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": redoes the most recent command by the user" + "Parameters: None"
                    + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_REDONE_SUCCESS = "Successfully redone the most recent command!";
    public static final String MESSAGE_NO_COMMANDS_REDONE = "There are no recent commands to redo.";
    public RedoCommand() {}
    @Override
    public CommandResult execute(Model model, String commandText) throws CommandException {
        requireNonNull(model);
        if (!model.canRedoAddressBook()) {
            throw new CommandException(MESSAGE_NO_COMMANDS_REDONE);
        }
        String command = model.redoAddressBook();
        return new CommandResult(String.format(MESSAGE_REDONE_SUCCESS, command));

    }
}

