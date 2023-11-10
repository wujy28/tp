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

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void executeNoStateToUndoTo() {
        UndoCommand undoCommand = new UndoCommand();
        assertThrows(CommandException.class, UndoCommand.MESSAGE_NO_COMMANDS_UNDONE, () -> undoCommand.execute(model));
    }

    @Test
    public void executeUndoClearSuccess() throws CommandException {
        model.setAddressBook(new AddressBook(), "");
        String expectedMessage = String.format(UndoCommand.MESSAGE_UNDONE_SUCCESS, "");
        UndoCommand undoCommand = new UndoCommand();
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL,
                        DANIEL, ELLE, FIONA, GEORGE),
                model.getCurrentPatientList());
    }

    @Test
    public void executeUndoAddSuccess() {
        PatientBuilder patientBuilder = new PatientBuilder();
        Patient patientToAdd = patientBuilder.build();
        model.addPatient(patientToAdd, "");
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = String.format(UndoCommand.MESSAGE_UNDONE_SUCCESS, "");
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL,
                DANIEL, ELLE, FIONA, GEORGE),
                model.getCurrentPatientList());
    }

    @Test
    public void executeUndoDeleteSuccess() {
        Patient patientToDelete = model.getPatient(ALICE.getIcNumber(), model.getCurrentPatientList());
        model.deletePatient(patientToDelete, "");
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = String.format(UndoCommand.MESSAGE_UNDONE_SUCCESS, "");
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL,
                        DANIEL, ELLE, FIONA, GEORGE),
                model.getCurrentPatientList());
    }

    @Test
    public void executeUndoEditSuccess() {
        PatientBuilder patientBuilder = new PatientBuilder();
        Patient editedPatient = patientBuilder.build();
        model.setPatient(model.getPatient(ALICE.getIcNumber(), model.getCurrentPatientList()), editedPatient, "");
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = String.format(UndoCommand.MESSAGE_UNDONE_SUCCESS, "");
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL,
                        DANIEL, ELLE, FIONA, GEORGE),
                model.getCurrentPatientList());
    }

}
