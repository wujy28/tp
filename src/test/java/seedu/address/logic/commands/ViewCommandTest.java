package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PATIENT_LISTED_SUCCESS;
import static seedu.address.logic.Messages.MESSAGE_UNABLE_TO_FIND_PATIENT_WITH_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.PatientWithIcNumberPredicate;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;


/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void equals() {
        IcNumber testIcNumber1 = new IcNumber("t1234567a");
        IcNumber testIcNumber2 = new IcNumber("s7654321a");

        PatientWithIcNumberPredicate firstPredicate = new PatientWithIcNumberPredicate(testIcNumber1);
        PatientWithIcNumberPredicate secondPredicate = new PatientWithIcNumberPredicate(testIcNumber2);

        ViewCommand viewFirstCommand = new ViewCommand(firstPredicate, testIcNumber1);
        ViewCommand viewSecondCommand = new ViewCommand(secondPredicate, testIcNumber2);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(firstPredicate, testIcNumber1);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void execute_icNumberOfExistingPatient_patientFound() {
        IcNumber testIcNumber1 = new IcNumber("T0032415E");
        PatientWithIcNumberPredicate predicate = new PatientWithIcNumberPredicate(testIcNumber1);
        ViewCommand command = new ViewCommand(predicate, testIcNumber1);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, MESSAGE_PATIENT_LISTED_SUCCESS, expectedModel);
    }

    @Test
    public void execute_icNumberOfNonExistingPatient_exceptionThrown() {
        IcNumber testIcNumber1 = new IcNumber("T1234567j");
        PatientWithIcNumberPredicate predicate = new PatientWithIcNumberPredicate(testIcNumber1);
        ViewCommand command = new ViewCommand(predicate, testIcNumber1);

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
    public void toStringMethod() {
        IcNumber testIcNumber1 = new IcNumber("T1234567j");
        PatientWithIcNumberPredicate predicate = new PatientWithIcNumberPredicate(testIcNumber1);
        ViewCommand viewCommand = new ViewCommand(predicate, testIcNumber1);
        String expected = ViewCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, viewCommand.toString());
    }

}
