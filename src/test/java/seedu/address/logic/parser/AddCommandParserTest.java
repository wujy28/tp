package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_REQUIRED_COMMAND_NOT_FOUND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BIRTHDAY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BIRTHDAY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FULL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BIRTHDAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
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
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.AddCommandParser.RELEVANT_PREFIXES;
import static seedu.address.logic.parser.AddCommandParser.REQUIRED_PREFIXES;
import static seedu.address.logic.parser.AddCommandParser.createPatientFromPresentPrefixes;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPatients.AMY;
import static seedu.address.testutil.TypicalPatients.BOB;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Birthday;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Gender;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PatientBuilder;


public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();


    @Test
    public void getPrefixesPresent_onlyRequiredPrefixesPresent_returnedCorrectPrefixList() {
        Prefix[] expectedPrefixesList = REQUIRED_PREFIXES;

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(NAME_DESC_AMY + IC_NUMBER_DESC_AMY,
            RELEVANT_PREFIXES);
        Prefix[] actualPrefixesList = AddCommandParser.getPrefixesPresent(testArgMultimap);
        assertEquals(expectedPrefixesList.length, actualPrefixesList.length);
        for (int i = 0; i < expectedPrefixesList.length; i++) {
            assertEquals(expectedPrefixesList[i], actualPrefixesList[i]);
        }
    }

    @Test
    public void getPrefixesPresent_someOptionalPrefixesPresent_returnedCorrectPrefixList() {
        // only optional field phone is present
        String userInput = NAME_DESC_AMY + IC_NUMBER_DESC_AMY + PHONE_DESC_AMY;
        Prefix[] expectedPrefixesList = new Prefix[]{PREFIX_NAME, PREFIX_IC_NUMBER, PREFIX_PHONE};

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, RELEVANT_PREFIXES);
        Prefix[] actualPrefixesList = AddCommandParser.getPrefixesPresent(testArgMultimap);
        assertEquals(expectedPrefixesList.length, actualPrefixesList.length);
        for (int i = 0; i < expectedPrefixesList.length; i++) {
            assertEquals(expectedPrefixesList[i], actualPrefixesList[i]);
        }
    }

    @Test
    public void getPrefixesPresent_allPrefixesPresent_returnedCorrectPrefixList() {
        Prefix[] expectedPrefixesList = new Prefix[]{PREFIX_NAME, PREFIX_IC_NUMBER, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_GENDER, PREFIX_BIRTHDAY, PREFIX_ADDRESS, PREFIX_TAG};

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(FULL_DESC_BOB, RELEVANT_PREFIXES);
        Prefix[] actualPrefixesList = AddCommandParser.getPrefixesPresent(testArgMultimap);
        assertEquals(expectedPrefixesList.length, actualPrefixesList.length);
        for (int i = 0; i < expectedPrefixesList.length; i++) {
            assertEquals(expectedPrefixesList[i], actualPrefixesList[i]);
        }
    }

    @Test
    public void createPatient_allPrefixesPresent_correctPatientCreated() throws ParseException {
        Patient expectedPatient = new PatientBuilder(BOB).build();

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(FULL_DESC_BOB, RELEVANT_PREFIXES);

        Patient actualPatient = AddCommandParser.createPatient(testArgMultimap, RELEVANT_PREFIXES);
        assertEquals(expectedPatient, actualPatient);
    }

    @Test
    public void createPatient_onlyRequiredPrefixesPresent_correctPatientCreated() throws ParseException {
        Patient expectedPatient = new PatientBuilder(BOB).withDefaultValues().withName(VALID_NAME_BOB)
            .withIcNumber(VALID_IC_NUMBER_BOB).build();

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(NAME_DESC_BOB + IC_NUMBER_DESC_BOB,
            RELEVANT_PREFIXES);

        Patient actualPatient = AddCommandParser.createPatient(testArgMultimap, REQUIRED_PREFIXES);
        assertEquals(expectedPatient, actualPatient);
    }

    @Test
    public void createPatient_someOptionalPrefixesPresent_correctPatientCreated() throws ParseException {
        Patient expectedPatient = new PatientBuilder(BOB).withDefaultValues().withName(VALID_NAME_BOB)
            .withIcNumber(VALID_IC_NUMBER_BOB).withEmail(VALID_EMAIL_BOB).build();

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(
            NAME_DESC_BOB + IC_NUMBER_DESC_BOB + EMAIL_DESC_BOB, RELEVANT_PREFIXES);

        Patient actualPatient = AddCommandParser.createPatient(testArgMultimap,
            new Prefix[]{PREFIX_NAME, PREFIX_IC_NUMBER, PREFIX_EMAIL});
        assertEquals(expectedPatient, actualPatient);
    }


    @Test
    public void createPatientFromPresentPrefixes_allPrefixesPresent_correctPatientCreated() throws ParseException {
        Patient expectedPatient = new PatientBuilder(BOB).build();

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(FULL_DESC_BOB, RELEVANT_PREFIXES);
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_HUSBAND));
        tags.add(new Tag(VALID_TAG_FRIEND));
        Patient actualPatient = createPatientFromPresentPrefixes(new Name(VALID_NAME_BOB), new Phone(VALID_PHONE_BOB),
            new Email(VALID_EMAIL_BOB), new Gender(VALID_GENDER_BOB), new IcNumber(VALID_IC_NUMBER_BOB),
            new Birthday(VALID_BIRTHDAY_BOB), new Address(VALID_ADDRESS_BOB), tags, testArgMultimap, RELEVANT_PREFIXES);
        assertEquals(expectedPatient, actualPatient);
    }

    @Test
    public void createPatientFromPresentPrefixes_onlyRequiredFieldsPresent_correctPatientCreated()
        throws ParseException {
        // expectedPatient only have Name field, others default value
        Patient expectedPatient = new PatientBuilder(AMY).withDefaultValues().withName(VALID_NAME_AMY)
            .withIcNumber(VALID_IC_NUMBER_AMY).build();

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(NAME_DESC_AMY + IC_NUMBER_DESC_AMY,
            RELEVANT_PREFIXES);

        // all optional fields have default value
        Patient actualPatient = createPatientFromPresentPrefixes(new Name(VALID_NAME_AMY),
            new Phone(Phone.getDefaultPhone()), new Email(Email.getDefaultEmail()),
            new Gender(Gender.getDefaultGender()), new IcNumber(VALID_IC_NUMBER_AMY),
            new Birthday(Birthday.getDefaultBirthday()), new Address(Address.getDefaultAddress()), new HashSet<>(),
            testArgMultimap, REQUIRED_PREFIXES);
        assertEquals(expectedPatient, actualPatient);
    }


    @Test
    public void createPatientFromPresentPrefixes_someOptionalFieldsPresent_correctPatientCreated()
        throws ParseException {
        // expectedPatient only have Name field and Phone fields
        Patient expectedPatient = new PatientBuilder(AMY).withDefaultValues().withName(VALID_NAME_AMY)
            .withIcNumber(VALID_IC_NUMBER_AMY).withPhone(VALID_PHONE_AMY).build();

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(
            NAME_DESC_AMY + IC_NUMBER_DESC_AMY + PHONE_DESC_AMY, RELEVANT_PREFIXES);

        Prefix[] prefixesPresent = {PREFIX_NAME, PREFIX_PHONE};

        // all optional fields have default value except Phone
        Patient actualPatient = createPatientFromPresentPrefixes(new Name(VALID_NAME_AMY),
            new Phone(Phone.getDefaultPhone()), new Email(Email.getDefaultEmail()),
            new Gender(Gender.getDefaultGender()), new IcNumber(VALID_IC_NUMBER_AMY),
            new Birthday(Birthday.getDefaultBirthday()), new Address(Address.getDefaultAddress()), new HashSet<>(),
            testArgMultimap, prefixesPresent);
        assertEquals(expectedPatient, actualPatient);
    }


    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FULL_DESC_BOB, new AddCommand(expectedPatient));


        // multiple tags - all accepted
        Patient expectedPatientMultipleTags = new PatientBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
            .build();
        assertParseSuccess(parser, FULL_DESC_BOB, new AddCommand(expectedPatientMultipleTags));
    }


    @Test
    public void parse_requiredFieldPresent_success() {
        // Required field Name present while optional fields missing
        Patient expectedPatient = new PatientBuilder(AMY).withDefaultValues().withName(VALID_NAME_AMY)
            .withIcNumber(VALID_IC_NUMBER_AMY).build();

        assertParseSuccess(parser, NAME_DESC_AMY + IC_NUMBER_DESC_AMY, new AddCommand(expectedPatient));
    }

    @Test
    public void parse_emptyUserString_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);


        // Required field Name missing while optional fields present
        expectedMessage = String.format(MESSAGE_REQUIRED_COMMAND_NOT_FOUND_FORMAT, Arrays.toString(REQUIRED_PREFIXES));

        assertParseFailure(parser,
            PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);

        // Required field IC Number missing while optional fields present
        expectedMessage = String.format(MESSAGE_REQUIRED_COMMAND_NOT_FOUND_FORMAT, Arrays.toString(REQUIRED_PREFIXES));

        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);
    }

    @Test
    public void parse_requiredFieldPrefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing Name prefix
        assertParseFailure(parser,
            VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);

        // missing Ic Number prefix
        assertParseFailure(parser,
            VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + VALID_IC_NUMBER_BOB + BIRTHDAY_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        // optional field Phone number missing (ie default phone number used)
        Patient expectedPatient = new PatientBuilder(BOB).withPhone(Phone.getDefaultPhone()).build();

        assertParseSuccess(parser,
            NAME_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPatient));

        // optional field Email missing (ie default email used)
        expectedPatient = new PatientBuilder(BOB).withEmail(Email.getDefaultEmail()).build();

        assertParseSuccess(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPatient));


        // optional field address missing (ie default gender used)
        expectedPatient = new PatientBuilder(BOB).withGender(Gender.getDefaultGender()).build();

        assertParseSuccess(parser,
            NAME_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPatient));


        // optional field address missing (ie default birthday used)
        expectedPatient = new PatientBuilder(BOB).withBirthday(Birthday.getDefaultBirthday()).build();

        assertParseSuccess(parser,
            NAME_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPatient));

        // optional field address missing (ie default address used)
        expectedPatient = new PatientBuilder(BOB).withAddress(Address.getDefaultAddress()).build();

        assertParseSuccess(parser,
            NAME_DESC_BOB + EMAIL_DESC_BOB + PHONE_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPatient));
    }


    @Test
    public void parse_duplicatePrefixes_failure() {
        // duplicate name prefixes
        Prefix[] duplicateNamePrefixes = new Prefix[]{new Prefix("n/"), new Prefix("n/")};
        String expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateNamePrefixes);
        assertParseFailure(parser,
            NAME_DESC_BOB + NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB
                + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);

        // duplicate phone prefixes
        Prefix[] duplicatePhonePrefixes = new Prefix[]{new Prefix("p/"), new Prefix("p/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicatePhonePrefixes);
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + PHONE_DESC_AMY + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB
                + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);

        // duplicate email prefixes
        Prefix[] duplicateEmailPrefixes = new Prefix[]{new Prefix("e/"), new Prefix("e/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateEmailPrefixes);
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + EMAIL_DESC_AMY + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB
                + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);

        // duplicate gender prefixes
        Prefix[] duplicateGenderPrefixes = new Prefix[]{new Prefix("g/"), new Prefix("g/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateGenderPrefixes);
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + GENDER_DESC_AMY + IC_NUMBER_DESC_BOB
                + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);

        // duplicate icNumber prefixes
        Prefix[] duplicateIcNumberPrefixes = new Prefix[]{new Prefix("i/"), new Prefix("i/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateIcNumberPrefixes);
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + IC_NUMBER_DESC_AMY
                + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);

        // duplicate birthday prefixes
        Prefix[] duplicateBirthdayPrefixes = new Prefix[]{new Prefix("b/"), new Prefix("b/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateBirthdayPrefixes);
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + BIRTHDAY_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);

        // duplicate address prefixes
        Prefix[] duplicateAddressPrefixes = new Prefix[]{new Prefix("a/"), new Prefix("a/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateAddressPrefixes);
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + ADDRESS_DESC_BOB + ADDRESS_DESC_AMY + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedMessage);
    }

    @Test

    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
            INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB
                + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
            NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB
                + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB
                + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_GENDER_DESC + IC_NUMBER_DESC_BOB
                + BIRTHDAY_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Gender.MESSAGE_CONSTRAINTS);


        // invalid birthday
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB
                + INVALID_BIRTHDAY_DESC + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            Birthday.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser,
            NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
            INVALID_NAME_DESC + IC_NUMBER_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
            PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
