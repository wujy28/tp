package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INITIAL_OBSERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT_PLAN;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.EditRecordDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_GENDER_AMY = "FEMALE";
    public static final String VALID_GENDER_BOB = "MALE";
    public static final String VALID_IC_NUMBER_AMY = "T1234567J";
    public static final String VALID_IC_NUMBER_BOB = "S7654321J";
    public static final String VALID_BIRTHDAY_AMY = "03/01/2001";
    public static final String VALID_BIRTHDAY_BOB = "05/06/2007";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_PRIORITY_AMY = "HIGH";
    public static final String VALID_PRIORITY_BOB = "MEDIUM";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_INITIAL_OBSERVATION = "Broken left arm";
    public static final String VALID_DIAGNOSIS = "Hairline fracture on left arm";
    public static final String VALID_TREATMENT_PLAN = "Carbon cast for 2 weeks";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String IC_NUMBER_DESC_AMY = " " + PREFIX_IC_NUMBER + VALID_IC_NUMBER_AMY;
    public static final String IC_NUMBER_DESC_BOB = " " + PREFIX_IC_NUMBER + VALID_IC_NUMBER_BOB;
    public static final String BIRTHDAY_DESC_AMY = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_AMY;
    public static final String BIRTHDAY_DESC_BOB = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String PRIORITY_DESC_AMY = " " + PREFIX_PRIORITY + VALID_PRIORITY_AMY;
    public static final String PRIORITY_DESC_BOB = " " + PREFIX_PRIORITY + VALID_PRIORITY_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String INITIAL_OBSERVATION_DESC = " " + PREFIX_INITIAL_OBSERVATION + VALID_INITIAL_OBSERVATION;
    public static final String DIAGNOSIS_DESC = " " + PREFIX_DIAGNOSIS + VALID_DIAGNOSIS;
    public static final String TREATMENT_PLAN_DESC = " " + PREFIX_TREATMENT_PLAN + VALID_TREATMENT_PLAN;

    public static final String FULL_DESC_BOB =
        NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
            + ADDRESS_DESC_BOB + PRIORITY_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND;

    public static final String FULL_DESC_AMY =
        NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + GENDER_DESC_AMY + IC_NUMBER_DESC_AMY + BIRTHDAY_DESC_AMY
            + ADDRESS_DESC_AMY + PRIORITY_DESC_AMY + TAG_DESC_FRIEND;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "MALE-"; // '-' not allowed in gender
    public static final String INVALID_IC_NUMBER_DESC = " " + PREFIX_IC_NUMBER + "t!234567j"; // '!' not allowed in
    // ic number
    public static final String INVALID_BIRTHDAY_DESC = " " + PREFIX_BIRTHDAY + "10-10-1980"; // invalid date format
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "low+"; // '+' not allowed in priority
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPatientDescriptor DESC_AMY;
    public static final EditCommand.EditPatientDescriptor DESC_BOB;

    public static final RecordCommand.EditRecordDescriptor REC_AMY;
    public static final RecordCommand.EditRecordDescriptor REC_BOB;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withGender(VALID_GENDER_AMY).withIcNumber(VALID_IC_NUMBER_AMY)
            .withBirthday(VALID_BIRTHDAY_AMY).withAddress(VALID_ADDRESS_AMY).withPriority(VALID_PRIORITY_AMY)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withGender(VALID_GENDER_BOB).withIcNumber(VALID_IC_NUMBER_BOB)
            .withBirthday(VALID_BIRTHDAY_BOB).withAddress(VALID_ADDRESS_BOB).withPriority(VALID_PRIORITY_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        REC_AMY = new EditRecordDescriptorBuilder().withInitialObservations("Broken Arm")
            .withDiagnosis("Hairline fracture").withTreatmentPlan("Cast for 2 days").build();
        REC_BOB = new EditRecordDescriptorBuilder().withInitialObservations("Shortness of breath and chest tightness")
                .withDiagnosis("Asthma").withTreatmentPlan("Rest").build();
    }

    public static String getUserInputForBob() {
        return NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB
            + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB + PRIORITY_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND;
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException | PatientWithFieldNotFoundException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered patient list and selected patient in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Patient> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPatientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the patient with the given {@code icNumber} in the
     * {@code model}'s address book.
     */
    public static void showPatientAtIC(Model model, IcNumber icNumber) {
        List<Patient> lastShownList = model.getFilteredPatientList();
        Patient patient = model.getPatient(icNumber, lastShownList);
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredPatientList().size());
    }

}
