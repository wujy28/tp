package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Record;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PATIENT_IC = "IC Number should start and end with an alphabet with non negative numbers in between";
    public static final String MESSAGE_PATIENTS_LISTED_OVERVIEW = "%1$d patients listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_PATIENT_LISTED_SUCCESS = "Patient found!!";
    public static final String MESSAGE_UNABLE_TO_FIND_PATIENT_WITH_FIELD = "Unable to find any patient(s) with ";
    public static final String MESSAGE_REQUIRED_COMMAND_NOT_FOUND_FORMAT = "Ensure you have the required prefix(s): "
        + "\n%s";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code patient} for display to the user.
     */
    public static String format(Patient patient) {
        final StringBuilder builder = new StringBuilder();
        builder.append(patient.getName())
                .append("; Phone: ")
                .append(patient.getPhone())
                .append("; Email: ")
                .append(patient.getEmail())
                .append("; Gender: ")
                .append(patient.getGender())
                .append("; IC Number: ")
                .append(patient.getIcNumber())
                .append("; Birthday: ")
                .append(patient.getBirthday())
                .append("; Address: ")
                .append(patient.getAddress())
                .append("; Priority: ")
                .append(patient.getPriority())
                .append("; Department: ")
                .append(patient.getAssignedDepartment())
                .append("; Tags: ");
        patient.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code record} for display to the user.
     */
    public static String formatRecord(Patient patient, Record record) {
        final StringBuilder builder = new StringBuilder();
        builder.append(patient.getIcNumber())
                .append("; Initial Observations: ")
                .append(record.getInitialObservations())
                .append("; Diagnosis: ")
                .append(record.getDiagnosis())
                .append("; Treatment Plan: ")
                .append(record.getTreatmentPlan());
        return builder.toString();
    }

}
