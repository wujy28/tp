package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Record;

/**
 * A Record that is serializable to JSON format.
 */
public class JsonAdaptedRecord {

    private final Patient patient;
    private final String initialObservations;
    private final String diagnosis;
    private final String treatmentPlan;

    /**
     * Constructs a {@code JsonAdaptedRecord} with the given record details.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("patient") Patient patient,
                             @JsonProperty("initialObservations") String initialObservations,
                             @JsonProperty("diagnosis") String diagnosis,
                             @JsonProperty("treatmentPlan") String treatmentPlan) {
        this.patient = patient;
        this.initialObservations = initialObservations;
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
    }

    /**
     * Converts a given {@code Record} into this class for Jackson use.
     */
    public JsonAdaptedRecord(Record source) {
        this.patient = source.getPatient();
        this.initialObservations = source.getInitialObservations();
        this.diagnosis = source.getDiagnosis();
        this.treatmentPlan = source.getTreatmentPlan();
    }

    /**
     * Converts this Jackson-friendly adapted record object into the model's {@code Record} object.
     *
     * @return the model's Record object.
     */
    public Record toModelType() {
        Record record = new Record(patient);
        record.setInitialObservations(initialObservations);
        record.setDiagnosis(diagnosis);
        record.setTreatmentPlan(treatmentPlan);
        return record;
    }
}
