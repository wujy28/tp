package seedu.address.model.patient;

import seedu.address.commons.util.ToStringBuilder;

import java.util.function.Predicate;

/**
 * Tests that a {@code Patient}'s {@code IcNumber} matches the given desired IcNumber
 */
public class PatientWithIcNumberPredicate implements Predicate<Patient> {
    private final IcNumber icNumber;

    public PatientWithIcNumberPredicate(IcNumber icNumber) {
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
