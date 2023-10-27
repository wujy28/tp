package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.PatientWithIcNumberPredicate;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException, PatientWithFieldNotFoundException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_IC_NUMBER);

        if (!checkIcNumberPrefixPresent(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_IC_NUMBER);
        assert argMultimap.getValue(PREFIX_IC_NUMBER).isPresent();

        IcNumber icNumber = ParserUtil.parseIcNumber(argMultimap.getValue(PREFIX_IC_NUMBER).get());
        PatientWithIcNumberPredicate predicate = new PatientWithIcNumberPredicate(icNumber);
        logger.info("ViewCommand object with Predicate : " + predicate + " and Ic Number : " + icNumber + "\ncreated");
        return new ViewCommand(predicate, icNumber);
    }

    /**
     * Returns true if IcNumber field present and value is non empty
     * {@code ArgumentMultimap}.
     */
    public static boolean checkIcNumberPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_IC_NUMBER).isPresent();
    }
}
