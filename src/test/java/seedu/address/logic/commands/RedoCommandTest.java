package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.DANIEL;
import static seedu.address.testutil.TypicalPatients.ELLE;
import static seedu.address.testutil.TypicalPatients.FIONA;
import static seedu.address.testutil.TypicalPatients.GEORGE;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;

public class RedoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void executeNoStateToRedoTo() {
        RedoCommand redoCommand = new RedoCommand();
        assertThrows(CommandException.class, RedoCommand.MESSAGE_NO_COMMANDS_REDONE, () -> redoCommand.execute(model));
    }

    @Test
    public void executeRedoClearSuccess() throws CommandException {
        model.setAddressBook(new AddressBook(), "");
        model.undoAddressBook();
        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = String.format(RedoCommand.MESSAGE_REDONE_SUCCESS, "");
        expectedModel.setAddressBook(new AddressBook(), "");
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getCurrentPatientList());
    }

    @Test
    public void executeRedoAddSuccess() {
        PatientBuilder patientBuilder = new PatientBuilder();
        Patient patientToAdd = patientBuilder.build();
        model.addPatient(patientToAdd, "");
        model.undoAddressBook();
        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = String.format(RedoCommand.MESSAGE_REDONE_SUCCESS, "");
        expectedModel.addPatient(patientToAdd, "");
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL,
                        DANIEL, ELLE, FIONA, GEORGE, patientToAdd),
                model.getCurrentPatientList());
    }

    @Test
    public void executeRedoDeleteSuccess() {
        Patient patientToDelete = model.getPatient(ALICE.getIcNumber(), model.getCurrentPatientList());
        model.deletePatient(patientToDelete, "");
        model.undoAddressBook();
        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = String.format(RedoCommand.MESSAGE_REDONE_SUCCESS, "");
        expectedModel.deletePatient(ALICE, "");
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, CARL,
                        DANIEL, ELLE, FIONA, GEORGE),
                model.getCurrentPatientList());
    }

    @Test
    public void executeRedoEditSuccess() {
        PatientBuilder patientBuilder = new PatientBuilder();
        Patient editedPatient = patientBuilder.build();
        model.setPatient(model.getPatient(ALICE.getIcNumber(), model.getCurrentPatientList()), editedPatient, "");
        model.undoAddressBook();
        RedoCommand redoCommand = new RedoCommand();
        String expectedMessage = String.format(RedoCommand.MESSAGE_REDONE_SUCCESS, "");
        expectedModel.setPatient(expectedModel.getPatient(ALICE.getIcNumber(),
                expectedModel.getCurrentPatientList()), editedPatient, "");
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(editedPatient, BENSON, CARL,
                        DANIEL, ELLE, FIONA, GEORGE),
                model.getCurrentPatientList());
    }
}
