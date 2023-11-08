package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "n a m e",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)); // spaces between letters
        assertParseFailure(parser, "4g3",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)); // weird characters
        assertParseFailure(parser, "birthday",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)); // sort order does not exist
        assertParseFailure(parser, "name jkasdfasdf",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)); // extra characters
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        SortCommand expectedCommand = new SortCommand(SortCommand.SortOrder.NAME);
        assertParseSuccess(parser, "name", expectedCommand); // same case
        assertParseSuccess(parser, "NAME", expectedCommand); // all caps
        assertParseSuccess(parser, "nAmE", expectedCommand); // weird case
        assertParseSuccess(parser, "   name    ", expectedCommand); // leading and trailing whitespaces
    }
}
