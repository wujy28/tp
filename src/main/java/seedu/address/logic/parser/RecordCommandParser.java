package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INITIAL_OBSERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT_PLAN;

import seedu.address.logic.commands.RecordCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.IcNumber;

/**
 * Parses input arguments and creates a new RecordCommand object
 */
public class RecordCommandParser implements Parser<RecordCommand> {
    public static final Prefix[] RELEVANT_PREFIXES = new Prefix[]{PREFIX_IC_NUMBER, PREFIX_INITIAL_OBSERVATION,
        PREFIX_DIAGNOSIS, PREFIX_TREATMENT_PLAN};

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RecordCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, RELEVANT_PREFIXES);
        IcNumber icNumber;
        if (!checkIcNumberPrefixPresent(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecordCommand.MESSAGE_USAGE));
        }
        icNumber = ParserUtil.parseIcNumber(argMultimap.getValue(PREFIX_IC_NUMBER).get());
        argMultimap.verifyNoDuplicatePrefixesFor(RELEVANT_PREFIXES);

        RecordCommand.EditRecordDescriptor editRecordDescriptor = new RecordCommand.EditRecordDescriptor();
        if (argMultimap.getValue(PREFIX_INITIAL_OBSERVATION).isPresent()) {
            editRecordDescriptor.setInitialObservations(
                ParserUtil.parseInitialObservations(argMultimap.getValue(PREFIX_INITIAL_OBSERVATION).get()));
        }
        if (argMultimap.getValue(PREFIX_DIAGNOSIS).isPresent()) {
            editRecordDescriptor.setDiagnosis(ParserUtil.parseDiagnosis(argMultimap.getValue(PREFIX_DIAGNOSIS).get()));
        }
        if (argMultimap.getValue(PREFIX_TREATMENT_PLAN).isPresent()) {
            editRecordDescriptor.setTreatmentPlan(
                ParserUtil.parseTreatmentPlan(argMultimap.getValue(PREFIX_TREATMENT_PLAN).get()));
        }

        if (!editRecordDescriptor.isAnyFieldEdited()) {
            throw new ParseException(RecordCommand.MESSAGE_NOT_EDITED);
        }

        return new RecordCommand(icNumber, editRecordDescriptor);
    }

    /**
     * Returns true if IcNumber field present and value is non empty
     * {@code ArgumentMultimap}.
     */
    public static boolean checkIcNumberPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_IC_NUMBER).isPresent();
    }
}
