package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import static java.util.Objects.requireNonNull;

/**
 * Undoes most recent command.
 */
public class UndoCommand extends Command{
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": undoes the most recent command by the user" + "Parameters: None" +
                    "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDONE_SUCCESS = "Successfully undone the most recent command!";
    public static final String MESSAGE_NO_COMMANDS_UNDONE = "There are no recent commands to undo.";
    public UndoCommand() {}
    @Override
    public CommandResult execute(Model model, String commandText) throws CommandException {
        requireNonNull(model);
        if (!model.canUndoAddressBook()) {
            throw new CommandException(MESSAGE_NO_COMMANDS_UNDONE);
        }
        String command = model.undoAddressBook();
        return new CommandResult(String.format(MESSAGE_UNDONE_SUCCESS, command));

    }
}
