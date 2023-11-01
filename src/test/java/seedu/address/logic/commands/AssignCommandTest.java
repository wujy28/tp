package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PATIENT_LISTED_SUCCESS;
import static seedu.address.logic.Messages.MESSAGE_UNABLE_TO_FIND_PATIENT_WITH_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.AssignedDepartment;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.PatientWithIcNumberPredicate;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;


/**
 * Contains integration tests (interaction with the Model) for {@code AssignCommand}.
 */
public class AssignCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());


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
