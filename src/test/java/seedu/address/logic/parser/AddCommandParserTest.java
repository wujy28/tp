package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_REQUIRED_COMMAND_NOT_FOUND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.AddCommandParser.RELEVANT_PREFIXES;
import static seedu.address.logic.parser.AddCommandParser.REQUIRED_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPatients.BOB;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PatientBuilder;


public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();


    @Test
    public void getPrefixesPresent_namePrefixPresent_returnedCorrectPrefixList() {
        Prefix[] expectedPrefixesList = new Prefix[]{PREFIX_NAME};

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(NAME_DESC_AMY, RELEVANT_PREFIXES);
        Prefix[] actualPrefixesList = AddCommandParser.getPrefixesPresent(testArgMultimap);
        assertEquals(expectedPrefixesList.length, actualPrefixesList.length);
        for (int i = 0; i < expectedPrefixesList.length; i++) {
            assertEquals(expectedPrefixesList[i], actualPrefixesList[i]);
        }
    }

    @Test
    public void getPrefixesPresent_nameAndPhonePrefixPresent_returnedCorrectPrefixList() {
        String userInput = NAME_DESC_AMY + PHONE_DESC_AMY;
        Prefix[] expectedPrefixesList = new Prefix[]{PREFIX_NAME, PREFIX_PHONE};

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, RELEVANT_PREFIXES);
        Prefix[] actualPrefixesList = AddCommandParser.getPrefixesPresent(testArgMultimap);
        assertEquals(expectedPrefixesList.length, actualPrefixesList.length);
        for (int i = 0; i < expectedPrefixesList.length; i++) {
            assertEquals(expectedPrefixesList[i], actualPrefixesList[i]);
        }
    }

    @Test
    public void getPrefixesPresent_namePhoneAndEmailPrefixPresent_returnedCorrectPrefixList() {
        String userInput = NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY;
        Prefix[] expectedPrefixesList = new Prefix[]{PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL};

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PHONE,
            PREFIX_EMAIL, PREFIX_ADDRESS);
        Prefix[] actualPrefixesList = AddCommandParser.getPrefixesPresent(testArgMultimap);
        assertEquals(expectedPrefixesList.length, actualPrefixesList.length);
        for (int i = 0; i < expectedPrefixesList.length; i++) {
            assertEquals(expectedPrefixesList[i], actualPrefixesList[i]);
        }
    }

    @Test
    public void getPrefixesPresent_phoneValueNotPresent_returnedCorrectPrefixList() {
        String userInput = NAME_DESC_AMY + PREFIX_PHONE;
        Prefix[] expectedPrefixesList = new Prefix[]{PREFIX_NAME};

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, RELEVANT_PREFIXES);
        Prefix[] actualPrefixesList = AddCommandParser.getPrefixesPresent(testArgMultimap);
        assertEquals(expectedPrefixesList.length, actualPrefixesList.length);
        for (int i = 0; i < expectedPrefixesList.length; i++) {
            assertEquals(expectedPrefixesList[i], actualPrefixesList[i]);
        }
    }


    @Test
    public void createPatientFromPrefixes_allFieldsPresent_correctPatientCreated() throws ParseException {
        String userInput =
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND;
        Patient expectedPatient = new PatientBuilder(BOB).build();

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, RELEVANT_PREFIXES);

        Patient actualPatient = AddCommandParser.createPatientFromPrefixes(testArgMultimap, RELEVANT_PREFIXES);
        assertEquals(expectedPatient, actualPatient);
    }

    @Test
    public void createPatientFromPrefixes_nameFieldPresent_correctPatientCreated() throws ParseException {
        String userInput = NAME_DESC_BOB;
        Patient expectedPatient = new PatientBuilder(BOB).withPhone("12345678").withEmail("default_email@gmail.com")
            .withAddress("Address " + "not added").withTags().build();

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, RELEVANT_PREFIXES);

        Patient actualPatient = AddCommandParser.createPatientFromPrefixes(testArgMultimap, new Prefix[]{PREFIX_NAME});
        assertEquals(expectedPatient, actualPatient);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser,
            PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND,
            new AddCommand(expectedPatient));


        // multiple tags - all accepted
        Patient expectedPatientMultipleTags = new PatientBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
            .build();
        assertParseSuccess(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            new AddCommand(expectedPatientMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPatientString =
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple fields repeated
        assertParseFailure(parser,
            validExpectedPatientString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
                + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPatientString,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPatientString + INVALID_NAME_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPatientString + INVALID_EMAIL_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPatientString + INVALID_PHONE_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedPatientString + INVALID_ADDRESS_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
    }


    @Test
    public void parse_requiredFieldPresent_success() {
        // Required field Name present while optional fields missing
        Patient expectedPatient = new PatientBuilder(BOB).withEmail("default_email@gmail.com").withPhone("12345678")
            .withAddress("Address not added").withTags().build();

        assertParseSuccess(parser, NAME_DESC_BOB, new AddCommand(expectedPatient));
    }

    @Test
    public void parse_requiredFieldMissing_failure() {
        //Required field Name missing while optional fields missing
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);


        // Required field Name missing while optional fields present
        expectedMessage = String.format(MESSAGE_REQUIRED_COMMAND_NOT_FOUND_FORMAT, Arrays.toString(REQUIRED_PREFIXES));

        assertParseFailure(parser,
            PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);
    }

    @Test
    public void parse_requiredFieldPrefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
            expectedMessage);
    }

    @Test
    public void parse_requiredFieldValueMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name value
        assertParseFailure(parser, PREFIX_NAME + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        // optional field Phone number missing (ie default phone number used)
        Patient expectedPatient = new PatientBuilder(BOB).withPhone("12345678").build();

        assertParseSuccess(parser,
            NAME_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            new AddCommand(expectedPatient));

        // optional field Email missing (ie default email used)
        expectedPatient = new PatientBuilder(BOB).withEmail("default_email@gmail.com").build();

        assertParseSuccess(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            new AddCommand(expectedPatient));

        // optional field address missing (ie default address used)
        expectedPatient = new PatientBuilder(BOB).withAddress("Address not added").build();

        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            new AddCommand(expectedPatient));
    }


    @Test
    public void parse_duplicatePrefixes_failure() {
        // duplicate name prefixes
        Prefix[] duplicateNamePrefixes = new Prefix[]{new Prefix("n/"), new Prefix("n/")};
        String expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateNamePrefixes);
        assertParseFailure(parser, NAME_DESC_BOB + NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
            expectedMessage);

        // duplicate phone prefixes
        Prefix[] duplicatePhonePrefixes = new Prefix[]{new Prefix("p/"), new Prefix("p/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicatePhonePrefixes);
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + PHONE_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
            expectedMessage);

        // duplicate email prefixes
        Prefix[] duplicateEmailPrefixes = new Prefix[]{new Prefix("e/"), new Prefix("e/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateEmailPrefixes);
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + EMAIL_DESC_AMY + ADDRESS_DESC_BOB,
            expectedMessage);

        // duplicate address prefixes
        Prefix[] duplicateAddressPrefixes = new Prefix[]{new Prefix("a/"), new Prefix("a/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateAddressPrefixes);
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + ADDRESS_DESC_AMY, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
            INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
            NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND,
            Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
            PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
