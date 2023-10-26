package seedu.address.testutil;

import seedu.address.logic.commands.RecordCommand;
import seedu.address.model.patient.Record;

/**
 * A utility class to help with building EditRecordDescriptor objects.
 */
public class EditRecordDescriptorBuilder {

    private final RecordCommand.EditRecordDescriptor descriptor;

    public EditRecordDescriptorBuilder() {
        descriptor = new RecordCommand.EditRecordDescriptor();
    }

    public EditRecordDescriptorBuilder(RecordCommand.EditRecordDescriptor descriptor) {
        this.descriptor = new RecordCommand.EditRecordDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecordDescriptor} with fields containing {@code record}'s details
     */
    public EditRecordDescriptorBuilder(Record record) {
        descriptor = new RecordCommand.EditRecordDescriptor();
        descriptor.setInitialObservations(record.getInitialObservations());
        descriptor.setDiagnosis(record.getDiagnosis());
        descriptor.setTreatmentPlan(record.getDiagnosis());
    }

    /**
     * Sets the {@code Initial Observations} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withInitialObservations(String initialObservations) {
        descriptor.setInitialObservations(initialObservations);
        return this;
    }

    /**
     * Sets the {@code Diagnosis} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withDiagnosis(String diagnosis) {
        descriptor.setDiagnosis(diagnosis);
        return this;
    }

    /**
     * Sets the {@code Treatment Plan} of the {@code EditRecordDescriptor} that we are building.
     */
    public EditRecordDescriptorBuilder withTreatmentPlan(String treatmentPlan) {
        descriptor.setTreatmentPlan(treatmentPlan);
        return this;
    }

    public RecordCommand.EditRecordDescriptor build() {
        return descriptor;
    }
}
