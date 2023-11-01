package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_UNABLE_TO_FIND_PATIENT_WITH_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIC;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIcUnfilteredList_success() {
        List<Patient> currentPatientList = model.getCurrentPatientList();
        Patient patientToDelete = model.getPatient(ALICE.getIcNumber(), currentPatientList);
        DeleteCommand deleteCommand = new DeleteCommand(ALICE.getIcNumber());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(patientToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
        public void execute_invalidIcList_throwsIllegalArgumentException() {
        showPatientAtIC(model, ALICE.getIcNumber());
        String invalidIC = "";
        assertThrows(IllegalArgumentException.class, () -> new IcNumber(invalidIC));
        //Hence DeleteCommand cannot be executed because of illegal argument exception in IC
    }

    @Test
    public void execute_validIcFilteredList_success() {
        showPatientAtIC(model, new IcNumber("T0032415E"));
        List<Patient> lastShownList = model.getFilteredPatientList();
        Patient patientToDelete = model.getPatient(ALICE.getIcNumber(), lastShownList);
        DeleteCommand deleteCommand = new DeleteCommand(ALICE.getIcNumber());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS,
                Messages.format(patientToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);
        showNoPatient(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_icNumberOfNonExistingPatient_exceptionThrown() throws PatientWithFieldNotFoundException {
        IcNumber testIcNumber1 = new IcNumber("T1234567j");
        DeleteCommand command = new DeleteCommand(testIcNumber1);

        boolean exceptionThrown = false;
        try {
            command.execute(model);
        } catch (PatientWithFieldNotFoundException e) {
            exceptionThrown = true;
            assertEquals(e.getMessage(),
                MESSAGE_UNABLE_TO_FIND_PATIENT_WITH_FIELD + "Ic Number : " + testIcNumber1.value);
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(ALICE.getIcNumber());
        DeleteCommand deleteSecondCommand = new DeleteCommand(BENSON.getIcNumber());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(new IcNumber("T0032415E"));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        IcNumber targetIc = new IcNumber("T0032415E");
        DeleteCommand deleteCommand = new DeleteCommand(targetIc);
        String expected = DeleteCommand.class.getCanonicalName() + "{icNumber=" + targetIc + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPatient(Model model) {
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
