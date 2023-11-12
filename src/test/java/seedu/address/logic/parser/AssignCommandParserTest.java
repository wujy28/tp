package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGNED_DEPARTMENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_IC_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNED_DEPARTMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_NUMBER_AMY;
import static seedu.address.logic.parser.AssignCommandParser.RELEVANT_PREFIXES;
import static seedu.address.logic.parser.AssignCommandParser.checkDepartmentPrefixPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ViewCommandParser.checkIcNumberPrefixPresent;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.model.patient.AssignedDepartment;
import seedu.address.model.patient.IcNumber;

public class AssignCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        AssignCommand.MESSAGE_USAGE);
    private final AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty string
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // whitespaces only
        assertParseFailure(parser, "      ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingParts_failure() {
        // no IC value specified
        assertParseFailure(parser, "i/ d/cardiology", MESSAGE_INVALID_FORMAT);

        // no IC prefix
        assertParseFailure(parser, VALID_IC_NUMBER_AMY + " d/" + VALID_ASSIGNED_DEPARTMENT_AMY, MESSAGE_INVALID_FORMAT);

        // no department value specified
        assertParseFailure(parser, "i/" + VALID_IC_NUMBER_AMY + " d/", MESSAGE_INVALID_FORMAT);

        // no department prefix
        assertParseFailure(parser, "i/" + VALID_IC_NUMBER_AMY + VALID_ASSIGNED_DEPARTMENT_AMY, MESSAGE_INVALID_FORMAT);

        // only ic number present
        assertParseFailure(parser, "i/" + VALID_IC_NUMBER_AMY, MESSAGE_INVALID_FORMAT);

        //only department present
        assertParseFailure(parser, "d/cardiology", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_duplicatePrefixes_failure() {
        // duplicate IC Number
        Prefix[] duplicateIcNumberPrefixes = new Prefix[]{new Prefix("i/"), new Prefix("i/")};
        String expectedMessageIC = Messages.getErrorMessageForDuplicatePrefixes(duplicateIcNumberPrefixes);
        assertParseFailure(parser, IC_NUMBER_DESC_BOB + IC_NUMBER_DESC_AMY + " d/cardiology", expectedMessageIC);

        // duplicate department
        Prefix[] duplicateDepartmentPrefixes = new Prefix[]{new Prefix("d/"), new Prefix("d/")};
        String expectedMessageDepartment = Messages.getErrorMessageForDuplicatePrefixes(duplicateDepartmentPrefixes);
        assertParseFailure(parser, IC_NUMBER_DESC_BOB + " d/cardiology" + " d/Endocrinology",
            expectedMessageDepartment);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid ic followed by valid department
        assertParseFailure(parser, INVALID_IC_NUMBER_DESC + " d/Cardiology", IcNumber.MESSAGE_CONSTRAINTS);

        // valid ic followed by invalid department
        assertParseFailure(parser, " i/T1234567J" + " d/!adasfd",
            AssignedDepartment.MESSAGE_CONSTRAINTS); // invalid format
        assertParseFailure(parser, " i/T1234567J" + " d/paediatrics",
            AssignedDepartment.MESSAGE_CONSTRAINTS); // department does not exist
    }

    @Test
    public void parse_allPrefixesPresent_success() {
        String department = "cardiology";
        String userInput1 = IC_NUMBER_DESC_AMY + " d/cardiology"; // ic followed by department
        String userInput2 = " d/cardiology" + IC_NUMBER_DESC_AMY; // department followed by ic

        AssignCommand assignCommand = new AssignCommand(new IcNumber(VALID_IC_NUMBER_AMY),
            new AssignedDepartment(department));

        assertParseSuccess(parser, userInput1, assignCommand);
        assertParseSuccess(parser, userInput2, assignCommand);
    }

    @Test
    public void checkIcNumberPrefixPresent_icNumberValuePresent_returnTrue() {
        // follow heuristic on at each valid input at least once in a positive test case
        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(IC_NUMBER_DESC_AMY, PREFIX_IC_NUMBER);
        assertTrue(checkIcNumberPrefixPresent(testArgMultimap));
    }

    @Test
    public void checkIcNumberPrefixPresent_icNumberValueAbsent_returnFalse() {
        // follow heuristic on at most one invalid input for each negative test case
        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(ADDRESS_DESC_AMY, PREFIX_IC_NUMBER);
        assertFalse(checkIcNumberPrefixPresent(testArgMultimap));
    }

    @Test
    public void checkDepartmentPrefixPresent_departmentPrefixPresent_returnTrue() {
        String userInput = IC_NUMBER_DESC_AMY + ASSIGNED_DEPARTMENT_DESC_AMY;

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, RELEVANT_PREFIXES);

        assertTrue(checkDepartmentPrefixPresent(testArgMultimap));
    }

    @Test
    public void checkDepartmentPrefixPresent_departmentPrefixAbsent_returnFalse() {
        String userInput = IC_NUMBER_DESC_AMY;

        ArgumentMultimap testArgMultimap = ArgumentTokenizer.tokenize(userInput, RELEVANT_PREFIXES);

        assertFalse(checkDepartmentPrefixPresent(testArgMultimap));
    }
}
