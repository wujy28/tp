package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INITIAL_OBSERVATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TREATMENT_PLAN;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Record;

/**
 * Edits the Patient Record with a certain {@Code IcNumber}
 */
public class RecordCommand extends Command {
    public static final String COMMAND_WORD = "record";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the record of the patient identified "
            + "by their IC Number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: i/IC_NUMBER "
            + "[" + PREFIX_INITIAL_OBSERVATION + "INITIAL_OBSERVATIONS] "
            + "[" + PREFIX_DIAGNOSIS + "DIAGNOSIS] "
            + "[" + PREFIX_TREATMENT_PLAN + "TREATMENT_PLAN] "
            + "Example: " + COMMAND_WORD + " " + PREFIX_IC_NUMBER + "T0472687A " + PREFIX_INITIAL_OBSERVATION
            + "Sneezing " + PREFIX_DIAGNOSIS + "Flu " + PREFIX_TREATMENT_PLAN + "Take medicine";

    public static final String MESSAGE_EDIT_RECORD_SUCCESS = "Edited Patient Record: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final IcNumber icNumber;
    private final RecordCommand.EditRecordDescriptor editRecordDescriptor;
    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Creates a RecordCommand to add the specified {@code IcNumber}
     */
    public RecordCommand(IcNumber icNumber, EditRecordDescriptor editRecordDescriptor) {
        requireNonNull(icNumber);
        requireNonNull(editRecordDescriptor);

        this.icNumber = icNumber;
        this.editRecordDescriptor = editRecordDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        Patient patientToEdit = model.getPatient(icNumber, lastShownList);

        if (!model.hasPatient(patientToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_IC);
        }

        Record recordToEdit = patientToEdit.getRecord();
        createEditedRecord(recordToEdit, editRecordDescriptor);

        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        logger.info("RecordCommand : " + this + "\nsuccessfully executed");
        return new CommandResult(String.format(MESSAGE_EDIT_RECORD_SUCCESS, Messages.formatRecord(patientToEdit,
                recordToEdit)));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static void createEditedRecord(Record recordToEdit, RecordCommand.EditRecordDescriptor
            editRecordDescriptor) {
        assert recordToEdit != null;

        String updatedInitialObservations = editRecordDescriptor.getInitialObservations().orElse(
                recordToEdit.getInitialObservations());
        String updatedDiagnosis = editRecordDescriptor.getDiagnosis().orElse(recordToEdit.getDiagnosis());
        String updatedTreatmentPlan = editRecordDescriptor.getTreatmentPlan().orElse(recordToEdit.getTreatmentPlan());

        recordToEdit.setInitialObservations(updatedInitialObservations);
        recordToEdit.setDiagnosis(updatedDiagnosis);
        recordToEdit.setTreatmentPlan(updatedTreatmentPlan);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecordCommand)) {
            return false;
        }

        RecordCommand otherRecordCommand = (RecordCommand) other;
        return icNumber.equals(otherRecordCommand.icNumber) && editRecordDescriptor.equals(
                otherRecordCommand.editRecordDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("icNumber", icNumber)
                .add("editRecordDescriptor", editRecordDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the patient record with. Each non-empty field value will replace the
     * corresponding field value of the patient's record.
     */
    public static class EditRecordDescriptor {
        private String initialObservations;
        private String diagnosis;
        private String treatmentPlan;

        public EditRecordDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRecordDescriptor(RecordCommand.EditRecordDescriptor toCopy) {
            setInitialObservations(toCopy.initialObservations);
            setDiagnosis(toCopy.diagnosis);
            setTreatmentPlan(toCopy.treatmentPlan);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(initialObservations, diagnosis, treatmentPlan);
        }

        public void setInitialObservations(String initialObservations) {
            this.initialObservations = initialObservations;
        }

        public Optional<String> getInitialObservations() {
            return Optional.ofNullable(initialObservations);
        }

        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }

        public Optional<String> getDiagnosis() {
            return Optional.ofNullable(diagnosis);
        }

        public void setTreatmentPlan(String treatmentPlan) {
            this.treatmentPlan = treatmentPlan;
        }

        public Optional<String> getTreatmentPlan() {
            return Optional.ofNullable(treatmentPlan);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof RecordCommand.EditRecordDescriptor)) {
                return false;
            }

            RecordCommand.EditRecordDescriptor otherEditRecordDescriptor = (RecordCommand.EditRecordDescriptor) other;
            return Objects.equals(initialObservations, otherEditRecordDescriptor.initialObservations)
                    && Objects.equals(diagnosis, otherEditRecordDescriptor.diagnosis)
                    && Objects.equals(treatmentPlan, otherEditRecordDescriptor.treatmentPlan);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("initialObservations", initialObservations)
                    .add("diagnosis", diagnosis)
                    .add("treatmentPlan", treatmentPlan).toString();
        }
    }
}
