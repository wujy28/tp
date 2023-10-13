package seedu.address.model.patient;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;



/**
 * Represents a medical record associated with a patient.
 * Stores information about the patient's visits, initial observations, diagnosis, and treatment plan.
 */
public class Record {

    private final Patient patient;
    private final List<String> departmentsVisited;
    private String initialObservations;
    private String diagnosis;
    private String treatmentPlan;

    /**
     * Initializes a Record with the associated patient.
     *
     * @param patient The patient associated with this record.
     */
    public Record(Patient patient) {
        this.patient = patient;
        this.departmentsVisited = new ArrayList<>();
    }

    public Patient getPatient() {
        return patient;
    }

    public List<String> getDepartmentsVisited() {
        return new ArrayList<>(departmentsVisited);
    }

    public void addDepartment(String department) {
        departmentsVisited.add(department);
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
        return new ToStringBuilder(this)
                .add("patient", patient)
                .add("departmentsVisited", departmentsVisited)
                .add("initialObservations", initialObservations)
                .add("diagnosis", diagnosis)
                .add("treatmentPlan", treatmentPlan)
                .toString();
    }
}
