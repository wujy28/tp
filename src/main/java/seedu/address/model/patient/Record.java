package seedu.address.model.patient;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a medical record associated with a patient.
 * Stores information about the patient's visits, initial observations, diagnosis, and treatment plan.
 */
public class Record {

    public static String DEFAULT_INITIAL_OBSERVATIONS = "No initial observations given";
    public static String DEFAULT_DIAGNOSIS = "No diagnosis given";
    public static String DEFAULT_TREATMENT_PLAN = "No treatment plan given";

    private final Patient patient;
    private String initialObservations;
    private String diagnosis;
    private String treatmentPlan;

    /**
     * Initializes a Record with the associated patient and initialise the fields with default values
     *
     * @param patient The patient associated with this record.
     */
    public Record(Patient patient) {
        this.patient = patient;
        this.initialObservations = DEFAULT_INITIAL_OBSERVATIONS;
        this.diagnosis = DEFAULT_DIAGNOSIS;
        this.treatmentPlan = DEFAULT_TREATMENT_PLAN;
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

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("patient", patient).add("initialObservations", initialObservations)
            .add("diagnosis", diagnosis).add("treatmentPlan", treatmentPlan).toString();
    }
}
