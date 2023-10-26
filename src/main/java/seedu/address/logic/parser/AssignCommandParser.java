package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.AssignedDepartment;
import seedu.address.model.patient.IcNumber;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class AssignCommandParser implements Parser<AssignCommand>{
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the AssignCommand
     * and returns an AssignCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_IC_NUMBER, PREFIX_DEPARTMENT);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_IC_NUMBER, PREFIX_DEPARTMENT);

        IcNumber icNumber;
        try {
            icNumber = ParserUtil.parseIcNumber(argMultimap.getValue(PREFIX_IC_NUMBER).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE), pe);
        }
        assert icNumber != null;

        AssignedDepartment department;
        department = ParserUtil.parseAssignedDepartment(argMultimap.getValue(PREFIX_DEPARTMENT).get());
        assert department != null;

        logger.info(String.format("AssignCommand object with IC Number: %s and Department: %s created",
                icNumber, department));
        return new AssignCommand(icNumber, department);
    }
}
