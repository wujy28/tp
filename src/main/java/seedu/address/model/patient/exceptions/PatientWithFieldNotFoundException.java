package seedu.address.model.patient.exceptions;

import static seedu.address.logic.Messages.MESSAGE_UNABLE_TO_FIND_PATIENT_WITH_FIELD;

public class PatientWithFieldNotFoundException extends Exception{
    public PatientWithFieldNotFoundException(String message) {
        super(MESSAGE_UNABLE_TO_FIND_PATIENT_WITH_FIELD + message);
    }
}
