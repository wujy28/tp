package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIC;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.AssignedDepartment;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AssignCommand}.
 */
public class AssignCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArguments_exceptionThrown() {
        // null icNumber
        assertThrows(NullPointerException.class, () -> new AssignCommand(null, new AssignedDepartment()));

        // null assignedDepartment
        assertThrows(NullPointerException.class, () -> new AssignCommand(new IcNumber("T0123456S"), null));
    }

    @Test
    public void execute_differentDepartmentUnfilteredList_success() {
        AssignedDepartment newAssignedDepartment = new AssignedDepartment("emergency department");
        Patient assignedPatient = new PatientBuilder(ALICE)
                .withAssignedDepartment(newAssignedDepartment.toString())
                .buildWithDepartment();
        AssignCommand assignCommand = new AssignCommand(ALICE.getIcNumber(), newAssignedDepartment);

        String expectedMessage = String.format(AssignCommand.MESSAGE_ASSIGN_PATIENT_SUCCESS,
                ALICE.getName(), newAssignedDepartment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        List<Patient> lastShownList = model.getCurrentPatientList();
        Patient patientToAssign = model.getPatient(ALICE.getIcNumber(), lastShownList);
        expectedModel.setPatient(patientToAssign, assignedPatient, "");
        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_differentDepartmentFilteredList_success() throws PatientWithFieldNotFoundException {
        showPatientAtIC(model, ALICE.getIcNumber());
        List<Patient> lastShownList = model.getCurrentPatientList();
        Patient patientInFilteredList = model.getPatient(ALICE.getIcNumber(), lastShownList);
        AssignedDepartment newAssignedDepartment = new AssignedDepartment("emergency department");
        Patient assignedPatient = new PatientBuilder(ALICE)
                .withAssignedDepartment(newAssignedDepartment.toString())
                .buildWithDepartment();
        AssignCommand assignCommand = new AssignCommand(ALICE.getIcNumber(), newAssignedDepartment);

        String expectedMessage = String.format(AssignCommand.MESSAGE_ASSIGN_PATIENT_SUCCESS,
                ALICE.getName(), newAssignedDepartment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPatient(patientInFilteredList, assignedPatient, "");
        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sameDepartment_failure() {
        AssignCommand assignCommand = new AssignCommand(ALICE.getIcNumber(), ALICE.getAssignedDepartment());
        String expectedMessage = String.format(AssignCommand.MESSAGE_SAME_DEPARTMENT,
                ALICE.getName(), ALICE.getAssignedDepartment());

        assertCommandFailure(assignCommand, model, expectedMessage);
    }

    @Test
    public void execute_patientDoesNotExist_exceptionThrown() {
        String invalidIC = "A0000000A";
        AssignCommand assignCommand = new AssignCommand(new IcNumber(invalidIC), ALICE.getAssignedDepartment());
        assertThrows(PatientWithFieldNotFoundException.class, () -> assignCommand.execute(model));
    }

    @Test
    public void equals() throws PatientWithFieldNotFoundException {
        IcNumber testIcNumber1 = new IcNumber("t1234567a");
        IcNumber testIcNumber2 = new IcNumber("s7654321a");
        AssignedDepartment testDepartment1 = new AssignedDepartment("Cardiology");
        AssignedDepartment testDepartment2 = new AssignedDepartment("Dermatology");

        AssignCommand assignFirstCommand = new AssignCommand(testIcNumber1, testDepartment1);
        AssignCommand assignSecondCommand = new AssignCommand(testIcNumber2, testDepartment1);

        // same object -> returns true
        assertTrue(assignFirstCommand.equals(assignFirstCommand));

        // same values -> returns true
        AssignCommand assignFirstCommandCopy = new AssignCommand(testIcNumber1, testDepartment1);
        assertTrue(assignFirstCommand.equals(assignFirstCommandCopy));

        // different types -> returns false
        assertFalse(assignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(assignFirstCommand.equals(null));

        // different departments -> returns false
        AssignCommand assignFirstCommandDifferentDepartment = new AssignCommand(testIcNumber1, testDepartment2);
        assertFalse(assignFirstCommand.equals(assignFirstCommandDifferentDepartment));

        // different patient -> returns false
        assertFalse(assignFirstCommand.equals(assignSecondCommand));
    }


    @Test
    public void toStringMethod() throws PatientWithFieldNotFoundException {
        IcNumber testIcNumber1 = new IcNumber("T1234567j");
        AssignedDepartment testDepartment1 = new AssignedDepartment("Cardiology");
        AssignCommand assignCommand = new AssignCommand(testIcNumber1, testDepartment1);
        String expected =
            AssignCommand.class.getCanonicalName() + "{icNumber=" + testIcNumber1 + ", department=" + testDepartment1
                + "}";
        assertEquals(expected, assignCommand.toString());
    }

}
