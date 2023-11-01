package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.AssignedDepartment;
import seedu.address.model.patient.Birthday;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Gender;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Priority;
import seedu.address.model.patient.Record;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * Assigns a patient to a hospital department
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": Assigns a patient identified " + "by the IC number to a hospital department. "
            + "Attempting to assign the patient to the department they are"
            + " currently under will display an error message.\n" + "Parameters: " + PREFIX_IC_NUMBER + "IC_NUMBER "
            + PREFIX_DEPARTMENT + "DEPARTMENT " + "Example: " + COMMAND_WORD + " " + PREFIX_IC_NUMBER + "T0372683C "
            + PREFIX_DEPARTMENT + "cardiology";

    public static final String MESSAGE_ASSIGN_PATIENT_SUCCESS = "Assigned Patient: %s to %s";
    public static final String MESSAGE_SAME_DEPARTMENT = "Patient: %s is already assigned to %s";
    public static final String MESSAGE_INVALID_DEPARTMENT =
        "Department: %s does not exist.\n" + "Refer to the A&E User Guide for the list of valid departments.";

    private final IcNumber icNumber;
    private final AssignedDepartment department;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * @param icNumber   of the patient in the filtered patient list to assign
     * @param department to assign the patient to
     */
    public AssignCommand(IcNumber icNumber, AssignedDepartment department) {
        requireNonNull(icNumber);
        requireNonNull(department);

        this.icNumber = icNumber;
        this.department = department;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, PatientWithFieldNotFoundException {
        requireNonNull(model);
        List<Patient> currentPatientList = model.getCurrentPatientList();
        Patient patientToAssign = model.getPatient(icNumber, currentPatientList);
        if (patientToAssign == null) {
            throw new PatientWithFieldNotFoundException("Ic Number : " + icNumber.value);
        }
        Patient assignedPatient = createAssignedPatient(patientToAssign, department);

        if (patientToAssign.equals(assignedPatient)) {
            throw new CommandException(
                String.format(MESSAGE_SAME_DEPARTMENT, patientToAssign.getName(), this.department));
        }

        model.setPatient(patientToAssign, assignedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        logger.info("AssignCommand : " + this + "\nsuccessfully executed");
        return new CommandResult(
            String.format(MESSAGE_ASSIGN_PATIENT_SUCCESS, patientToAssign.getName(), this.department));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToAssign}
     * assigned to {@code newlyAssignedDepartment}.
     */
    private static Patient createAssignedPatient(Patient patientToAssign, AssignedDepartment newlyAssignedDepartment) {
        assert patientToAssign != null;

        Name name = patientToAssign.getName();
        Phone phone = patientToAssign.getPhone();
        Email email = patientToAssign.getEmail();
        Gender gender = patientToAssign.getGender();
        IcNumber icNumber = patientToAssign.getIcNumber();
        Birthday birthday = patientToAssign.getBirthday();
        Address address = patientToAssign.getAddress();
        Priority priority = patientToAssign.getPriority();
        Set<Tag> tags = patientToAssign.getTags();
        Record record = patientToAssign.getRecord();

        return new Patient(name, phone, email, gender, icNumber, birthday,
                address, priority, tags, newlyAssignedDepartment, record);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignCommand)) {
            return false;
        }

        AssignCommand otherAssignCommand = (AssignCommand) other;
        return icNumber.equals(otherAssignCommand.icNumber) && department.equals(otherAssignCommand.department);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("icNumber", icNumber).add("department", department).toString();
    }
}
