package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.*;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_NoPreviousStateToUndoTo() {
        UndoCommand undoCommand = new UndoCommand();
        assertThrows(CommandException.class, UndoCommand.MESSAGE_NO_COMMANDS_UNDONE, () -> undoCommand.execute(model));
    }

    @Test
    public void execute_UndoClearSuccess() throws CommandException {
        model.setAddressBook(new AddressBook(), "");
        String expectedMessage = String.format(UndoCommand.MESSAGE_UNDONE_SUCCESS, "");
        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    /*@Test
    public void execute_UndoAddSuccess() {

    }

    @Test
    public void execute_UndoDeleteSuccess() {

    }

    @Test
    public void execute_UndoEditSuccess() {

    }*/

}
