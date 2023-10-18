package seedu.address.testutil;

import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Record;

/**
 * A utility class to help with building Record objects.
 */
public class RecordBuilder {

    private final Patient patient;
    private String initialObservations;
    private String diagnosis;
    private String treatmentPlan;

    /**
     * Creates a {@code RecordBuilder} with the default details.
     */
    public RecordBuilder() {
        this.patient = new PatientBuilder().build(); // default patient
        this.initialObservations = Record.getDefaultInitialObservations();
        this.diagnosis = Record.getDefaultDiagnosis();
        this.treatmentPlan = Record.getDefaultTreatmentPlan();
    }

    /**
     * Initializes the RecordBuilder with the data of {@code recordToCopy}.
     */
    public RecordBuilder(Record recordToCopy) {
        this.patient = recordToCopy.getPatient();
        this.initialObservations = recordToCopy.getInitialObservations();
        this.diagnosis = recordToCopy.getDiagnosis();
        this.treatmentPlan = recordToCopy.getTreatmentPlan();
    }

    /**
     * Sets the {@code initialObservations} of the {@code Record} that we are building.
     */
    public RecordBuilder withInitialObservations(String initialObservations) {
        this.initialObservations = initialObservations;
        return this;
    }

    /**
     * Sets the {@code diagnosis} of the {@code Record} that we are building.
     */
    public RecordBuilder withDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
        return this;
    }

    /**
     * Sets the {@code treatmentPlan} of the {@code Record} that we are building.
     */
    public RecordBuilder withTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
        return this;
    }

    /**
     * Builds and returns the {@code Record} object.
     */
    public Record build() {
        Record record = new Record(patient);
        record.setInitialObservations(initialObservations);
        record.setDiagnosis(diagnosis);
        record.setTreatmentPlan(treatmentPlan);
        return record;
    }

}
