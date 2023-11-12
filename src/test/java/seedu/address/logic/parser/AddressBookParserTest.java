package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ASSIGNED_DEPARTMENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.IC_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNED_DEPARTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_NUMBER_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INITIAL_OBSERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT_PLAN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.AMY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RecordCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.AssignedDepartment;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PatientWithIcNumberPredicate;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PatientUtil;


public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Patient patient = new PatientBuilder(AMY).withAssignedDepartment("Default").buildWithDepartment();
        AddCommand command = (AddCommand) parser.parseCommand(PatientUtil.getAddCommand(AMY));
        assertEquals(new AddCommand(patient), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(DeleteCommand.COMMAND_WORD + " " + "i/T0032415E");
        assertEquals(new DeleteCommand(ALICE.getIcNumber()), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        EditCommand command = (EditCommand) parser.parseCommand(
            EditCommand.COMMAND_WORD + " i/" + patient.getIcNumber().toString() + " "
                + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditCommand(patient.getIcNumber(), descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_assign() throws Exception {
        String userInput = AssignCommand.COMMAND_WORD + IC_NUMBER_DESC_AMY + ASSIGNED_DEPARTMENT_DESC_BOB;
        AssignCommand expectedCommand = new AssignCommand(new IcNumber(VALID_IC_NUMBER_AMY),
            new AssignedDepartment(VALID_ASSIGNED_DEPARTMENT_BOB));
        Command actualCommand = parser.parseCommand(userInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        IcNumber testIcNumber1 = new IcNumber(VALID_IC_NUMBER_AMY);

        PatientWithIcNumberPredicate firstPredicate = new PatientWithIcNumberPredicate(testIcNumber1);

        String userInput = ViewCommand.COMMAND_WORD + IC_NUMBER_DESC_AMY;
        ViewCommand expectedCommand = new ViewCommand(firstPredicate, testIcNumber1);
        Command actualCommand = parser.parseCommand(userInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseCommand_record() throws Exception {
        String userInput =
            RecordCommand.COMMAND_WORD + IC_NUMBER_DESC_AMY + " " + PREFIX_INITIAL_OBSERVATION + "Broken Arm" + " "
                + PREFIX_DIAGNOSIS + "Hairline fracture" + " " + PREFIX_TREATMENT_PLAN + "Cast for 2 days";

        RecordCommand.EditRecordDescriptor testRecordDescriptor = new RecordCommand.EditRecordDescriptor(REC_AMY);
        RecordCommand expectedCommand = new RecordCommand(AMY.getIcNumber(), testRecordDescriptor);

        Command actualCommand = parser.parseCommand(userInput);
        assertEquals(expectedCommand, actualCommand);

    }

    @Test
    public void parseCommand_undo() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws Exception {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + " 3") instanceof RedoCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
