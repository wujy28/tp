package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_NUMBER_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ViewCommandParser.checkIcNumberPrefixPresent;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.PatientWithIcNumberPredicate;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;

public class ViewCommandParserTest {

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_icNumberValueAbsent_throwsParseException() {
        assertParseFailure(parser, GENDER_DESC_AMY,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_icNumberValuePresent_returnsViewCommand() throws PatientWithFieldNotFoundException {
        IcNumber testIcNumber = new IcNumber(VALID_IC_NUMBER_AMY);

        ViewCommand expectedViewCommand = new ViewCommand(new PatientWithIcNumberPredicate(testIcNumber), testIcNumber);
        assertParseSuccess(parser, IC_NUMBER_DESC_AMY, expectedViewCommand);
    }

    @Test
    public void parse_duplicateIcNumberValuePresent_throwsParseException() {
        assertParseFailure(parser, IC_NUMBER_DESC_AMY + IC_NUMBER_DESC_BOB,
            Messages.getErrorMessageForDuplicatePrefixes(PREFIX_IC_NUMBER));
    }


    @Test
    public void checkIcNumberPrefixPresent_icNumberValuePresent_returnTrue() {
        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(IC_NUMBER_DESC_AMY, PREFIX_IC_NUMBER);
        assertTrue(checkIcNumberPrefixPresent(testArgMultimap));
    }

    @Test
    public void checkIcNumberPrefixPresent_icNumberValueAbsent_returnFalse() {
        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(ADDRESS_DESC_AMY, PREFIX_IC_NUMBER);
        assertFalse(checkIcNumberPrefixPresent(testArgMultimap));
    }
}
