package seedu.address.model.patient;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.patient.exceptions.PatientWithFieldNotFoundException;


/**
 * Tests that a {@code Patient}'s {@code IcNumber} matches the given desired IcNumber
 */
public class PatientWithIcNumberPredicate implements Predicate<Patient> {
    private final IcNumber icNumber;

    /**
     * Constructs a {@code PatientWithIcNumberPredicate}.
     */
    public PatientWithIcNumberPredicate(IcNumber icNumber) throws PatientWithFieldNotFoundException {
        if (icNumber == null) {
            throw new PatientWithFieldNotFoundException("Multiplicity violated. Each patient has 1 non-null IcNumber.");
        }
        this.icNumber = icNumber;
    }

    @Override
    public boolean test(Patient patient) {
        return icNumber.equals(patient.getIcNumber());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientWithIcNumberPredicate)) {
            return false;
        }

        PatientWithIcNumberPredicate otherPatientWithIcNumberPredicate = (PatientWithIcNumberPredicate) other;
        return icNumber.equals(otherPatientWithIcNumberPredicate.icNumber);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).add("icNumber", icNumber).toString();
    }


}
