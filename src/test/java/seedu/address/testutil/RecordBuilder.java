package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Record;

/**
 * A utility class to help with building Record objects.
 */
public class RecordBuilder {

    private final Patient patient;
    private List<String> departmentsVisited;
    private String initialObservations;
    private String diagnosis;
    private String treatmentPlan;

    /**
     * Creates a {@code RecordBuilder} with the default details.
     */
    public RecordBuilder() {
        this.patient = new PatientBuilder().build(); // default patient
        this.departmentsVisited = new ArrayList<>();
        this.initialObservations = "";
        this.diagnosis = "";
        this.treatmentPlan = "";
    }

    /**
     * Initializes the RecordBuilder with the data of {@code recordToCopy}.
     */
    public RecordBuilder(Record recordToCopy) {
        this.patient = recordToCopy.getPatient();
        this.departmentsVisited = new ArrayList<>(recordToCopy.getDepartmentsVisited());
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
     * Adds a {@code department} to the {@code Record} that we are building.
     */
    public RecordBuilder addDepartment(String department) {
        this.departmentsVisited.add(department);
        return this;
    }

    /**
     * Builds and returns the {@code Record} object.
     */
    public Record build() {
        Record record = new Record(patient);
        for (String department : departmentsVisited) {
            record.addDepartment(department);
        }
        record.setInitialObservations(initialObservations);
        record.setDiagnosis(diagnosis);
        record.setTreatmentPlan(treatmentPlan);
        return record;
    }

}
