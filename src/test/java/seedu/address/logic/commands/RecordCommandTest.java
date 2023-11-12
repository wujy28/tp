package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_UNABLE_TO_FIND_PATIENT_WITH_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.REC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RecordCommand.MESSAGE_EDIT_RECORD_SUCCESS;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.AMY;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RecordCommand.
 */
public class RecordCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        RecordCommand recordCommand = new RecordCommand(ALICE.getIcNumber(), new RecordCommand.EditRecordDescriptor());
        List<Patient> lastShownList = model.getCurrentPatientList();
        Patient editedPatient = model.getPatient(ALICE.getIcNumber(), lastShownList);

        String expectedMessage = String.format(MESSAGE_EDIT_RECORD_SUCCESS,
            Messages.formatRecord(editedPatient, editedPatient.getRecord()));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(recordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_icNumberOfNonExistingPatient_exceptionThrown() throws PatientWithFieldNotFoundException {
        IcNumber testIcNumber1 = new IcNumber("T1234567j");
        RecordCommand.EditRecordDescriptor testEditRecordDescriptor = new RecordCommand.EditRecordDescriptor();
        RecordCommand command = new RecordCommand(testIcNumber1, testEditRecordDescriptor);

        boolean isExceptionThrown = false;
        try {
            command.execute(model);
        } catch (PatientWithFieldNotFoundException | CommandException e) {
            isExceptionThrown = true;
            assertEquals(e.getMessage(),
                MESSAGE_UNABLE_TO_FIND_PATIENT_WITH_FIELD + "Ic Number : " + testIcNumber1.value);
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    public void equals() {
        final RecordCommand standardCommand = new RecordCommand(AMY.getIcNumber(), REC_AMY);

        // same values -> returns true
        RecordCommand.EditRecordDescriptor copyDescriptor = new RecordCommand.EditRecordDescriptor(REC_AMY);
        RecordCommand commandWithSameValues = new RecordCommand(AMY.getIcNumber(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different ic number -> returns false
        assertFalse(standardCommand.equals(new RecordCommand(BENSON.getIcNumber(), REC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new RecordCommand(BENSON.getIcNumber(), REC_BOB)));
    }

    @Test
    public void toStringMethod() {
        IcNumber targetIc = new IcNumber("T0032415E");
        RecordCommand.EditRecordDescriptor editRecordDescriptor = new RecordCommand.EditRecordDescriptor();
        RecordCommand recordCommand = new RecordCommand(targetIc, editRecordDescriptor);
        String expected = RecordCommand.class.getCanonicalName() + "{icNumber=" + targetIc + ", editRecordDescriptor="
            + editRecordDescriptor + "}";
        assertEquals(expected, recordCommand.toString());
    }

}
