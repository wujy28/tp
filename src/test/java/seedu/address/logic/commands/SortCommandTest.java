package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_UNABLE_TO_FIND_PATIENT_WITH_FIELD;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalPatients.CARL;
import static seedu.address.testutil.TypicalPatients.DANIEL;
import static seedu.address.testutil.TypicalPatients.ELLE;
import static seedu.address.testutil.TypicalPatients.FIONA;
import static seedu.address.testutil.TypicalPatients.GEORGE;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.AssignedDepartment;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {

    @Test
    public void constructor_nullArgument_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    public void sortOrderGetSortOrder_validArguments_correctReturnValues() {
        // same case as enum string values (lower case)
        assertEquals(SortCommand.SortOrder.NAME, SortCommand.SortOrder.getSortOrder("name")); // name
        assertEquals(SortCommand.SortOrder.IC_NUMBER, SortCommand.SortOrder.getSortOrder("ic")); // IC Number
        assertEquals(SortCommand.SortOrder.DEPARTMENT,
                SortCommand.SortOrder.getSortOrder("department")); // department
        assertEquals(SortCommand.SortOrder.AGE, SortCommand.SortOrder.getSortOrder("age")); // age
        assertEquals(SortCommand.SortOrder.PRIORITY, SortCommand.SortOrder.getSortOrder("priority")); // priority

        // different case
        assertEquals(SortCommand.SortOrder.NAME, SortCommand.SortOrder.getSortOrder("Name")); // capital letter
        assertEquals(SortCommand.SortOrder.PRIORITY,
                SortCommand.SortOrder.getSortOrder("PRIORITY")); // all caps
        assertEquals(SortCommand.SortOrder.DEPARTMENT,
                SortCommand.SortOrder.getSortOrder("DeParTMenT")); // weird case
    }

    @Test
    public void sortOrderGetSortOrder_invalidArguments_returnNull() {
        assertEquals(null, SortCommand.SortOrder.getSortOrder("")); // empty string
        assertEquals(null, SortCommand.SortOrder.getSortOrder("    ")); // spaces only
        assertEquals(null, SortCommand.SortOrder.getSortOrder("n a m e")); // spaces between letters
        assertEquals(null, SortCommand.SortOrder.getSortOrder("name ")); // extra space
        assertEquals(null, SortCommand.SortOrder.getSortOrder("birthday")); // sort order does not exist
    }

    @Test
    public void sortOrderGetSortOrder_nullInput_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> SortCommand.SortOrder.getSortOrder(null));
    }

    @Test
    public void execute_sortByName_sortedCorrectly() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        model.addPatient(GEORGE, "");
        model.addPatient(BENSON, "");
        model.addPatient(DANIEL, "");
        model.addPatient(ALICE, "");
        model.addPatient(ELLE, "");
        model.addPatient(FIONA, "");
        model.addPatient(CARL, "");

        SortCommand sortCommand = new SortCommand(SortCommand.SortOrder.NAME);

        // typical patients are already sorted by name
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        String expectedMessage = String.format(SortCommand.MESSAGE_SORT_LIST_SUCCESS, SortCommand.SortOrder.NAME);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByIcNumber_sortedCorrectly() {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPatient(ELLE, "");
        expectedModel.addPatient(BENSON, "");
        expectedModel.addPatient(CARL, "");
        expectedModel.addPatient(FIONA, "");
        expectedModel.addPatient(GEORGE, "");
        expectedModel.addPatient(ALICE, "");
        expectedModel.addPatient(DANIEL, "");

        SortCommand sortCommand = new SortCommand(SortCommand.SortOrder.IC_NUMBER);

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        String expectedMessage = String.format(SortCommand.MESSAGE_SORT_LIST_SUCCESS, SortCommand.SortOrder.IC_NUMBER);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByDepartment_sortedCorrectly() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs());
        Patient patient1 = new PatientBuilder().withName("Paul").withIcNumber("T0123456D")
                .withAssignedDepartment("emergency department").buildWithDepartment();
        Patient patient2 = new PatientBuilder().withName("Gina").withIcNumber("T0123456A")
                .withAssignedDepartment("dermatology").buildWithDepartment();
        Patient patient3 = new PatientBuilder().withName("Eric").withIcNumber("T0123456L")
                .withAssignedDepartment("psychiatry").buildWithDepartment();
        Patient patient4 = new PatientBuilder().withName("Peter").withIcNumber("T0123456P")
                .withAssignedDepartment("default").buildWithDepartment();

        model.addPatient(patient2, "");
        model.addPatient(patient1, "");
        model.addPatient(patient4, "");
        model.addPatient(patient3, "");

        SortCommand sortCommand = new SortCommand(SortCommand.SortOrder.DEPARTMENT);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPatient(patient1, "");
        expectedModel.addPatient(patient2, "");
        expectedModel.addPatient(patient3, "");
        expectedModel.addPatient(patient4, "");

        String expectedMessage = String.format(SortCommand.MESSAGE_SORT_LIST_SUCCESS, SortCommand.SortOrder.DEPARTMENT);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByAge_sortedCorrectly() {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPatient(DANIEL, "");
        expectedModel.addPatient(ALICE, "");
        expectedModel.addPatient(CARL, "");
        expectedModel.addPatient(GEORGE, "");
        expectedModel.addPatient(ELLE, "");
        expectedModel.addPatient(BENSON, "");
        expectedModel.addPatient(FIONA, "");

        SortCommand sortCommand = new SortCommand(SortCommand.SortOrder.AGE);

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        String expectedMessage = String.format(SortCommand.MESSAGE_SORT_LIST_SUCCESS, SortCommand.SortOrder.AGE);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByPriority_sortedCorrectly() {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel.addPatient(BENSON, "");
        expectedModel.addPatient(ELLE, "");
        expectedModel.addPatient(ALICE, "");
        expectedModel.addPatient(FIONA, "");
        expectedModel.addPatient(GEORGE, "");
        expectedModel.addPatient(CARL, "");
        expectedModel.addPatient(DANIEL, "");

        SortCommand sortCommand = new SortCommand(SortCommand.SortOrder.PRIORITY);

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        String expectedMessage = String.format(SortCommand.MESSAGE_SORT_LIST_SUCCESS, SortCommand.SortOrder.PRIORITY);
        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand firstSortCommand = new SortCommand(SortCommand.SortOrder.NAME);
        SortCommand secondSortCommand = new SortCommand(SortCommand.SortOrder.AGE);

        // same object -> returns true
        assertTrue(firstSortCommand.equals(firstSortCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(SortCommand.SortOrder.NAME);
        assertTrue(firstSortCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(firstSortCommand.equals(1));

        // null -> returns false
        assertFalse(firstSortCommand.equals(null));

        // different patient -> returns false
        assertFalse(firstSortCommand.equals(secondSortCommand));
    }

    @Test
    public void toStringMethod() {
        SortCommand sortCommand = new SortCommand(SortCommand.SortOrder.AGE);
        String expected = SortCommand.class.getCanonicalName() + "{sortOrder=" + SortCommand.SortOrder.AGE + "}";
        assertEquals(expected, sortCommand.toString());
    }
}
