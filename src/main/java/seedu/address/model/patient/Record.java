package seedu.address.model.patient;

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
    private String initialObservations;
    private String diagnosis;
    private String treatmentPlan;

    /**
     * Initializes a Record with the associated patient and initialise the fields with default values
     */
    public Record(Patient patient) {
        this.patient = patient;
        this.initialObservations = defaultInitialObservations;
        this.diagnosis = defaultDiagnosis;
        this.treatmentPlan = defaultTreatmentPlan;
    }

    /**
     * Initializes a default Record
     */
    public Record() {
        this.patient = null; // patient left null, would have to fix when building editing record command
        this.initialObservations = defaultInitialObservations;
        this.diagnosis = defaultDiagnosis;
        this.treatmentPlan = defaultTreatmentPlan;
    }


    public Patient getPatient() {
        return patient;
    }

    public String getInitialObservations() {
        return initialObservations;
    }

    public void setInitialObservations(String initialObservations) {
        this.initialObservations = initialObservations;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
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
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    /**
     * Copies the information of the given Record into this instance of Record.
     * @param recordToCopy the Record with information to copy.
     */
    public void copyRecordInfo(Record recordToCopy) {
        this.initialObservations = recordToCopy.getDiagnosis();
        this.diagnosis = recordToCopy.getDiagnosis();
        this.treatmentPlan = recordToCopy.getTreatmentPlan();
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
        return new ToStringBuilder(this).add("initialObservations", initialObservations).add("diagnosis", diagnosis)
            .add("treatmentPlan", treatmentPlan).toString();
    }
}
