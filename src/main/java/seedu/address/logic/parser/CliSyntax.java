package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/");
    public static final Prefix PREFIX_IC_NUMBER = new Prefix("i/");
    public static final Prefix PREFIX_DEPARTMENT = new Prefix("d/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("pr/");

    // Prefixes for Record
    public static final Prefix PREFIX_INITIAL_OBSERVATION = new Prefix("o/");
    public static final Prefix PREFIX_DIAGNOSIS = new Prefix("di/");
    public static final Prefix PREFIX_TREATMENT_PLAN = new Prefix("tp/");

}
