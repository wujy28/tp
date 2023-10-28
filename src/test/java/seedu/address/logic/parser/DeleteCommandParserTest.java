package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BIRTHDAY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_IC_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_NUMBER_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.AMY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.patient.IcNumber;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private final DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_duplicatePrefixes_failure() {
        Prefix[] duplicateIcNumberPrefixes = new Prefix[]{new Prefix("i/"), new Prefix("i/")};
        String expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateIcNumberPrefixes);
        assertParseFailure(parser, IC_NUMBER_DESC_BOB + IC_NUMBER_DESC_AMY, expectedMessage);
    }


    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, IC_NUMBER_DESC_AMY, new DeleteCommand(AMY.getIcNumber()));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        // empty user string
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);

        expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " ", expectedMessage);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid argument without any prefix
        assertParseFailure(parser, VALID_IC_NUMBER_AMY,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        // invalid argument
        assertParseFailure(parser, "random string",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
