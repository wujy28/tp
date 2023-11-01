package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.AddCommandParser.RELEVANT_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.EditCommandParser.createEditPatientDescriptor;
import static seedu.address.testutil.TypicalPatients.*;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.*;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPatientDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // have prefix, no IC value specified
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "i/", expectedMessage);

        // no prefix, have IC value
        assertParseFailure(parser, VALID_IC_NUMBER_AMY, MESSAGE_INVALID_FORMAT);

        // empty string
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        //empty string (space)
        assertParseFailure(parser, "    ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_duplicatePrefixes_failure() {
        // duplicate phone prefixes
        Prefix[] duplicatePhonePrefixes = new Prefix[]{new Prefix("p/"), new Prefix("p/")};
        String expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicatePhonePrefixes);
        assertParseFailure(parser,
            PHONE_DESC_BOB + PHONE_DESC_AMY + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + PRIORITY_DESC_BOB, expectedMessage);

        // duplicate email prefixes
        Prefix[] duplicateEmailPrefixes = new Prefix[]{new Prefix("e/"), new Prefix("e/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateEmailPrefixes);
        assertParseFailure(parser,
            PHONE_DESC_BOB + EMAIL_DESC_BOB + EMAIL_DESC_AMY + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + PRIORITY_DESC_BOB, expectedMessage);

        // duplicate gender prefixes
        Prefix[] duplicateGenderPrefixes = new Prefix[]{new Prefix("g/"), new Prefix("g/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateGenderPrefixes);
        assertParseFailure(parser,
            PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + GENDER_DESC_AMY + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + PRIORITY_DESC_BOB, expectedMessage);

        // duplicate icNumber prefixes
        Prefix[] duplicateIcNumberPrefixes = new Prefix[]{new Prefix("i/"), new Prefix("i/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateIcNumberPrefixes);
        assertParseFailure(parser,
            PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + IC_NUMBER_DESC_AMY
                + BIRTHDAY_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + ADDRESS_DESC_BOB + PRIORITY_DESC_BOB,
            expectedMessage);

        // duplicate birthday prefixes
        Prefix[] duplicateBirthdayPrefixes = new Prefix[]{new Prefix("b/"), new Prefix("b/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateBirthdayPrefixes);
        assertParseFailure(parser,
            PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + BIRTHDAY_DESC_AMY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + ADDRESS_DESC_BOB + PRIORITY_DESC_BOB,
            expectedMessage);

        // duplicate address prefixes
        Prefix[] duplicateAddressPrefixes = new Prefix[]{new Prefix("a/"), new Prefix("a/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateAddressPrefixes);
        assertParseFailure(parser,
            PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + ADDRESS_DESC_AMY + PRIORITY_DESC_BOB,
            expectedMessage);

        // duplicate priority prefixes
        Prefix[] duplicatePriorityPrefixes = new Prefix[]{new Prefix("pr/"), new Prefix("pr/")};
        expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicatePriorityPrefixes);
        assertParseFailure(parser,
            PHONE_DESC_BOB + EMAIL_DESC_BOB + GENDER_DESC_BOB + IC_NUMBER_DESC_BOB + BIRTHDAY_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + PRIORITY_DESC_BOB + PRIORITY_DESC_AMY,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " i/T1234567J" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, " i/T1234567J" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, " i/T1234567J" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, " i/T1234567J" + INVALID_GENDER_DESC, Gender.MESSAGE_CONSTRAINTS); // invalid gender
        assertParseFailure(parser, " i/T1234567J" + INVALID_BIRTHDAY_DESC, Birthday.MESSAGE_CONSTRAINTS); // invalid
        // birthday
        assertParseFailure(parser, " i/T1234567J" + INVALID_ADDRESS_DESC,
            Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, " i/T1234567J" + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS); // invalid
        // priority
        assertParseFailure(parser, " i/T1234567J" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, " i/T1234567J" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Patient} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, " i/T1234567J" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " i/T1234567J" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
            Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " i/T1234567J" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
            Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
            " i/T1234567J" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        IcNumber testIcNumber = new IcNumber(VALID_IC_NUMBER_AMY);
        String userInput =
            NAME_DESC_AMY + IC_NUMBER_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + GENDER_DESC_AMY + BIRTHDAY_DESC_AMY
                + ADDRESS_DESC_AMY + PRIORITY_DESC_AMY;

        EditPatientDescriptor testEditPatientDescriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withBirthday(VALID_BIRTHDAY_AMY)
            .withGender(VALID_GENDER_AMY).withAddress(VALID_ADDRESS_AMY).withIcNumber(VALID_IC_NUMBER_AMY)
            .withPriority(VALID_PRIORITY_AMY).build();
        EditCommand expectedCommand = new EditCommand(testIcNumber, testEditPatientDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        IcNumber targetIc = AMY.getIcNumber();
        String userInput = IC_NUMBER_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withIcNumber(VALID_IC_NUMBER_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIc, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        IcNumber targetIc = AMY.getIcNumber();
        String userInput = IC_NUMBER_DESC_AMY + NAME_DESC_AMY;
        EditCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
            .withIcNumber(VALID_IC_NUMBER_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIc, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = IC_NUMBER_DESC_AMY + PHONE_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_AMY).withIcNumber(VALID_IC_NUMBER_AMY)
            .build();
        expectedCommand = new EditCommand(targetIc, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = IC_NUMBER_DESC_AMY + EMAIL_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withEmail(VALID_EMAIL_AMY).withIcNumber(VALID_IC_NUMBER_AMY)
            .build();
        expectedCommand = new EditCommand(targetIc, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // gender
        userInput = IC_NUMBER_DESC_AMY + GENDER_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withGender(VALID_GENDER_AMY).withIcNumber(VALID_IC_NUMBER_AMY)
            .build();
        expectedCommand = new EditCommand(targetIc, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // birthday
        userInput = IC_NUMBER_DESC_AMY + BIRTHDAY_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withBirthday(VALID_BIRTHDAY_AMY)
            .withIcNumber(VALID_IC_NUMBER_AMY).build();
        expectedCommand = new EditCommand(targetIc, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = IC_NUMBER_DESC_AMY + PRIORITY_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withPriority(VALID_PRIORITY_AMY)
            .withIcNumber(VALID_IC_NUMBER_AMY).build();

        expectedCommand = new EditCommand(targetIc, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = IC_NUMBER_DESC_AMY + ADDRESS_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).withIcNumber(VALID_IC_NUMBER_AMY)
            .build();
        expectedCommand = new EditCommand(targetIc, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags

        userInput = IC_NUMBER_DESC_AMY + TAG_DESC_FRIEND;
        descriptor = new EditPatientDescriptorBuilder().withTags(VALID_TAG_FRIEND).withIcNumber(targetIc.toString())
            .build();

        expectedCommand = new EditCommand(targetIc, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        IcNumber targetIc = ALICE.getIcNumber();
        String userInput = IC_NUMBER_DESC_AMY + PHONE_DESC_BOB + INVALID_PHONE_DESC; //+PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = IC_NUMBER_DESC_AMY + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple valid fields repeated

        userInput =
            IC_NUMBER_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_AMY
                + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput =
            IC_NUMBER_DESC_AMY + INVALID_PHONE_DESC + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC + INVALID_PHONE_DESC
                + INVALID_ADDRESS_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }

    @Test
    public void parse_resetTags_success() {
        IcNumber targetIc = CARL.getIcNumber();
        String userInput = " i/" + targetIc.toString() + TAG_EMPTY;


        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withTags().withIcNumber(targetIc.toString()).build();

        EditCommand expectedCommand = new EditCommand(targetIc, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void createEditPatientDescriptor_oneFieldSpecific_success() throws ParseException {
        // phone number specified
        String userInput = IC_NUMBER_DESC_BOB + PHONE_DESC_BOB;

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, RELEVANT_PREFIXES);
        EditPatientDescriptor expectedEditPatientDescriptor = new EditPatientDescriptorBuilder().withPhone(
            VALID_PHONE_BOB).withIcNumber(VALID_IC_NUMBER_BOB).build();
        EditPatientDescriptor actualEditPatientDescriptor = createEditPatientDescriptor(testArgMultimap);
        assertEquals(expectedEditPatientDescriptor, actualEditPatientDescriptor);

    }

    @Test
    public void createEditPatientDescriptor_someFieldsSpecific_success() throws ParseException {
        // gender and email specified
        String userInput = IC_NUMBER_DESC_BOB + GENDER_DESC_AMY + EMAIL_DESC_AMY;

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, RELEVANT_PREFIXES);

        EditPatientDescriptor expectedEditPatientDescriptor = new EditPatientDescriptorBuilder().withGender(
            VALID_GENDER_AMY).withEmail(VALID_EMAIL_AMY).withIcNumber(VALID_IC_NUMBER_BOB).build();
        EditPatientDescriptor actualEditPatientDescriptor = createEditPatientDescriptor(testArgMultimap);
        assertEquals(expectedEditPatientDescriptor, actualEditPatientDescriptor);
    }

    @Test
    public void createEditPatientDescriptor_allFieldSpecific_success() throws ParseException {
        String userInput =
            NAME_DESC_AMY + IC_NUMBER_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + GENDER_DESC_AMY + BIRTHDAY_DESC_AMY
                + ADDRESS_DESC_AMY + PRIORITY_DESC_AMY;
        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, RELEVANT_PREFIXES);

        EditPatientDescriptor expectedEditPatientDescriptor = new EditPatientDescriptorBuilder().withName(
                VALID_NAME_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withBirthday(VALID_BIRTHDAY_AMY)
            .withGender(VALID_GENDER_AMY).withAddress(VALID_ADDRESS_AMY).withIcNumber(VALID_IC_NUMBER_AMY)
            .withPriority(VALID_PRIORITY_AMY).build();

        EditPatientDescriptor actualEditPatientDescriptor = createEditPatientDescriptor(testArgMultimap);
        assertEquals(expectedEditPatientDescriptor, actualEditPatientDescriptor);
    }

    @Test
    public void createEditPatientDescriptor_noFieldSpecific_failure() throws ParseException {
        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(IC_NUMBER_DESC_AMY, RELEVANT_PREFIXES);
        boolean isExceptionThrown = false;
        try {
            createEditPatientDescriptor(testArgMultimap);
        } catch (ParseException e) {
            isExceptionThrown = true;
            assertEquals(e.getMessage(), EditCommand.MESSAGE_NOT_EDITED);
        }
        assertTrue(isExceptionThrown);
    }
}
