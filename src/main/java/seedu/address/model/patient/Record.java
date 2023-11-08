package seedu.address.model.patient;

import javafx.beans.property.SimpleStringProperty;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a medical record associated with a patient.
 * Stores information about the patient's visits, initial observations, diagnosis, and treatment plan.
 */
public class Record {

    private static final String defaultInitialObservations = "No initial observations given";
    private static final String defaultDiagnosis = "No diagnosis given";
    private static final String defaultTreatmentPlan = "No treatment plan given";


    private final Patient patient;
    private SimpleStringProperty initialObservations;
    private SimpleStringProperty diagnosis;
    private SimpleStringProperty treatmentPlan;

    /**
     * Initializes a Record with the associated patient and initialise the fields with default values
     */
    public Record(Patient patient) {
        this.patient = patient;
        this.initialObservations = new SimpleStringProperty(defaultInitialObservations);
        this.diagnosis = new SimpleStringProperty(defaultDiagnosis);
        this.treatmentPlan = new SimpleStringProperty(defaultTreatmentPlan);
    }

    /**
     * Initializes a default Record
     */
    public Record() {
        this.patient = null; // patient left null, would have to fix when building editing record command
        this.initialObservations = new SimpleStringProperty(defaultInitialObservations);
        this.diagnosis = new SimpleStringProperty(defaultDiagnosis);
        this.treatmentPlan = new SimpleStringProperty(defaultTreatmentPlan);
    }

    /**
     * Initializes a record with defined details
     */
    public Record(String initialObservations, String diagnosis, String treatmentPlan) {
        this.patient = null;
        this.initialObservations = new SimpleStringProperty(initialObservations);
        this.diagnosis = new SimpleStringProperty(diagnosis);
        this.treatmentPlan = new SimpleStringProperty(treatmentPlan);
    }


    public Patient getPatient() {
        return patient;
    }

    public String getInitialObservations() {
        return initialObservations.get();
    }

    public void setInitialObservations(String initialObservations) {
        this.initialObservations.set(initialObservations);
    }

    public SimpleStringProperty initialObservationsProperty() {
        return initialObservations;
    }

    public String getDiagnosis() {
        return diagnosis.get();
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis.set(diagnosis);
    }

    public SimpleStringProperty diagnosisProperty() {
        return diagnosis;
    }

    public static String getDefaultInitialObservations() {
        return defaultInitialObservations;
    }

    public static String getDefaultDiagnosis() {
        return defaultDiagnosis;
    }

    public static String getDefaultTreatmentPlan() {
        return defaultTreatmentPlan;
    }

    public String getTreatmentPlan() {
        return treatmentPlan.get();
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan.set(treatmentPlan);
    }

    public SimpleStringProperty treatmentPlanProperty() {
        return treatmentPlan;
    }

    /**
     * Copies the information of the given Record into this instance of Record.
     * @param recordToCopy the Record with information to copy.
     */
    public void copyRecordInfo(Record recordToCopy) {
        this.initialObservations.set(recordToCopy.getInitialObservations());
        this.diagnosis.set(recordToCopy.getDiagnosis());
        this.treatmentPlan.set(recordToCopy.getTreatmentPlan());
    }

    /**
     * Returns true if both records have same fields
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Record)) {
            return false;
        }

        Record otherRecord = (Record) other;
        return getInitialObservations().equals(otherRecord.getInitialObservations()) && getDiagnosis().equals(
            otherRecord.getDiagnosis()) && getTreatmentPlan().equals(otherRecord.getTreatmentPlan());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("initialObservations", getInitialObservations())
                .add("diagnosis", getDiagnosis())
                .add("treatmentPlan", getTreatmentPlan()).toString();
    }
}
