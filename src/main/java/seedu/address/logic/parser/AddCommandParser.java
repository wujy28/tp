package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.*;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    public static final Prefix[] RELEVANT_PREFIXES = new Prefix[]{PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
        PREFIX_ADDRESS, PREFIX_TAG};
    public static final Prefix[] REQUIRED_PREFIXES = new Prefix[]{PREFIX_NAME};

    public static final Prefix[] OPTIONAL_PREFIXES = new Prefix[]{PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
        PREFIX_TAG};


    /**
     * Gets prefixes in argMultimap and returns a Prefix array containing all present prefixes
     *
     * @param argMultimap Argument multimap which contains prefix to value mapping
     * @return Prefix array containing present prefixes
     */
    public static Prefix[] getPrefixesPresent(ArgumentMultimap argMultimap) {
        ArrayList<Prefix> prefixesPresent;
        prefixesPresent = new ArrayList<>(List.copyOf(Arrays.asList(REQUIRED_PREFIXES))); // add required
        // prefixes to prefixes present.

        // go through other optional prefixes, check which contains value, add to list
        for (Prefix p : OPTIONAL_PREFIXES) {
            if (argMultimap.getValue(p).isPresent()) {
                prefixesPresent.add(p);
            }
        }
        return prefixesPresent.toArray(new Prefix[]{});
    }

    /**
     * Takes in a list of prefixes and creates patients with its corresponding values in argMultimap
     *
     * @param argMultimap Argument multimap which contains prefix to value mapping
     * @param prefixes    List of prefixes present
     * @return Patient object
     * @throws ParseException if the user input does not conform the expected format
     */
    public static Patient createPatientFromPrefixes(ArgumentMultimap argMultimap, Prefix[] prefixes)
        throws ParseException {
        Name name = new Name("Name not added");
        Phone phone = new Phone("12345678");
        Email email = new Email("default_email@gmail.com");
        Address address = new Address("Address not added");
        Set<Tag> tagList = new HashSet<>();

        for (Prefix p : prefixes) {
            switch (p.getPrefix()) {
            case "n/":
                name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
                break;
            case "p/":
                phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
                break;
            case "e/":
                email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
                break;
            case "a/":
                address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
                break;
            case "t/":
                tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
                break;
            }
        }
        return new Patient(name, phone, email, address, tagList);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, RELEVANT_PREFIXES);

        // check if required prefixes are present and not empty
        if (!areRequiredPrefixesPresent(argMultimap, REQUIRED_PREFIXES) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_ADDRESS);
        Prefix[] prefixesPresent = getPrefixesPresent(argMultimap);

        Patient patient = createPatientFromPrefixes(argMultimap, prefixesPresent);
        return new AddCommand(patient);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the compulsory prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areRequiredPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... requiredPrefixes) {
        return Stream.of(requiredPrefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
