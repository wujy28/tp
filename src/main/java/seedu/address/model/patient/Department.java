package seedu.address.model.patient;

/**
 * Represents the departments in the hospital.
 */
public enum Department {
    EMERGENCY_DEPARTMENT ("Emergency Department"),
    INTENSIVE_CARE_UNIT ("Intensive Care Unit"),
    ANAESTHESIOLOGY ("Anaesthesiology"),
    CARDIOLOGY ("Cardiology"),
    DERMATOLOGY ("Dermatology"),
    ENDOCRINOLOGY("Endocrinology"),
    GENERAL_SURGERY("General Surgery"),
    GERIATRIC_MEDICINE("Geriatric Medicine"),
    GYNAECOLOGY("Gynaecology"),
    HAEMATOLOGY("Haematology"),
    IMMUNOLOGY("Immunology"),
    INFECTIOUS_DISEASES("Infectious Diseases"),
    ONCOLOGY("Oncology"),
    OPHTHALMOLOGY("Ophthalmology"),
    ORTHOPAEDICS ("Orthopaedics"),
    NEUROLOGY("Neurology"),
    NEUROSURGERY("Neurosurgery"),
    PATHOLOGY("Pathology"),
    PALLIATIVE_MEDICINE("Palliative Medicine"),
    PLASTIC_SURGERY("Plastic Surgery"),
    PSYCHIATRY("Psychiatry"),
    RADIOLOGY("Radiology"),
    UROLOGY("Urology");

    /** The string representation of the Department **/
    private final String string;

    Department(String string) {
        this.string = string;
    }

    /**
     * Finds the Department with the given string representation.
     * Returns null if no Department is found.
     *
     * @param string The string representation of the Department.
     */
    public static Department findDepartment (String string) {
        for (Department d : Department.values()) {
            if (string.equalsIgnoreCase(d.string)) {
                return d;
            }
        }
        return null;
    }

    /**
     * Returns true if the given string represents a valid Department.
     */
    public static boolean isValidDepartment(String string) {
        boolean isValid = false;
        for (Department d : Department.values()) {
            if (string.equalsIgnoreCase(d.string)) {
                isValid = true;
            }
        }
        return isValid;
    }

    @Override
    public String toString() {
        return this.string;
    }
}
