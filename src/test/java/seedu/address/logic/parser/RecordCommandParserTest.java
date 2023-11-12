package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DIAGNOSIS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INITIAL_OBSERVATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TREATMENT_PLAN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIAGNOSIS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INITIAL_OBSERVATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TREATMENT_PLAN_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPatients.AMY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.RecordCommand;

public class RecordCommandParserTest {

    private final RecordCommandParser parser = new RecordCommandParser();

    @Test
    public void parse_duplicatePrefixes_failure() {
        Prefix[] duplicateIcNumberPrefixes = new Prefix[]{new Prefix("i/"), new Prefix("i/")};
        String expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicateIcNumberPrefixes);
        assertParseFailure(parser, IC_NUMBER_DESC_BOB + IC_NUMBER_DESC_AMY + INITIAL_OBSERVATION_DESC, expectedMessage);
    }

    @Test
    public void parse_validSingleArgs_returnsRecordCommand() {
        // only initial observation present
        RecordCommand.EditRecordDescriptor testEditRecordDescriptor = new RecordCommand.EditRecordDescriptor();
        testEditRecordDescriptor.setInitialObservations(VALID_INITIAL_OBSERVATION_BOB);
        assertParseSuccess(parser, IC_NUMBER_DESC_AMY + INITIAL_OBSERVATION_DESC,
            new RecordCommand(AMY.getIcNumber(), testEditRecordDescriptor));

        // only diagnosis present
        testEditRecordDescriptor = new RecordCommand.EditRecordDescriptor();
        testEditRecordDescriptor.setDiagnosis(VALID_DIAGNOSIS_BOB);
        assertParseSuccess(parser, IC_NUMBER_DESC_AMY + DIAGNOSIS_DESC,
            new RecordCommand(AMY.getIcNumber(), testEditRecordDescriptor));

        // only treatment plan present
        testEditRecordDescriptor = new RecordCommand.EditRecordDescriptor();
        testEditRecordDescriptor.setTreatmentPlan(VALID_TREATMENT_PLAN_BOB);
        assertParseSuccess(parser, IC_NUMBER_DESC_AMY + TREATMENT_PLAN_DESC,
            new RecordCommand(AMY.getIcNumber(), testEditRecordDescriptor));
    }

    @Test
    public void parse_validFullArgs_returnsRecordCommand() {
        RecordCommand.EditRecordDescriptor testEditRecordDescriptor = new RecordCommand.EditRecordDescriptor();
        testEditRecordDescriptor.setInitialObservations(VALID_INITIAL_OBSERVATION_BOB);
        testEditRecordDescriptor.setDiagnosis(VALID_DIAGNOSIS_BOB);
        testEditRecordDescriptor.setTreatmentPlan(VALID_TREATMENT_PLAN_BOB);
        assertParseSuccess(parser, IC_NUMBER_DESC_AMY + INITIAL_OBSERVATION_DESC + DIAGNOSIS_DESC + TREATMENT_PLAN_DESC,
            new RecordCommand(AMY.getIcNumber(), testEditRecordDescriptor));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        // empty user string
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecordCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "", expectedMessage);

        expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecordCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " ", expectedMessage);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid argument without any prefix
        assertParseFailure(parser, VALID_IC_NUMBER_AMY,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecordCommand.MESSAGE_USAGE));

        // invalid argument
        assertParseFailure(parser, "random string",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecordCommand.MESSAGE_USAGE));

        // no argument given
        assertParseFailure(parser, VALID_IC_NUMBER_AMY,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecordCommand.MESSAGE_USAGE));
    }
}
