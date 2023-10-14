package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class AssignedDepartmentTest {

    @Test
    public void defaultConstructor_noArguments_createsADefaultAssignedDepartment() {
        AssignedDepartment test = new AssignedDepartment();
        assertEquals(Department.DEFAULT, test.assignedDepartment);
    }

    @Test
    public void constructor_validInput_createsCorrectObject() {
        AssignedDepartment test = new AssignedDepartment("Emergency Department");
        assertEquals(Department.EMERGENCY_DEPARTMENT, test.assignedDepartment);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignedDepartment(null));
    }

    @Test
    public void constructor_invalidDepartment_throwsIllegalArgumentException() {
        String invalidDepartment = "";
        assertThrows(IllegalArgumentException.class, () -> new AssignedDepartment(invalidDepartment));


    }
    @Test
    void isValidDepartment() {
        // null department
        assertThrows(NullPointerException.class, () -> AssignedDepartment.isValidDepartment(null));

        // invalid department
        assertFalse(AssignedDepartment.isValidDepartment("")); // empty string
        assertFalse(AssignedDepartment.isValidDepartment(" ")); // spaces only
        assertFalse(AssignedDepartment.isValidDepartment("-")); // only non-alphanumeric characters
        assertFalse(AssignedDepartment.isValidDepartment("Cardiol*gy")); // contains non-alphanumeric characters
        assertFalse(AssignedDepartment.isValidDepartment(" Orthopaedics")); // first character is a space
        assertFalse(AssignedDepartment.isValidDepartment("Orthopedics")); // wrong spelling (American)
        assertFalse(AssignedDepartment.isValidDepartment("Operating Theatre")); // department does not exist

        // valid department
        assertTrue(AssignedDepartment.isValidDepartment("Geriatric Medicine")); // department exists
        assertTrue(AssignedDepartment.isValidDepartment("intensive care unit")); // lower case only
        assertTrue(AssignedDepartment.isValidDepartment("oNcoLOGy")); // weird casing
    }

    @Test
    public void equals() {
        AssignedDepartment assignedDepartment = new AssignedDepartment("Endocrinology");

        // same values -> returns true
        assertTrue(assignedDepartment.equals(new AssignedDepartment("enDocrinology")));

        // same object -> returns true
        assertTrue(assignedDepartment.equals(assignedDepartment));

        // null -> returns false
        assertFalse(assignedDepartment.equals(null));

        // different types -> returns false
        assertFalse(assignedDepartment.equals(5.0f));

        // different values -> returns false
        assertFalse(assignedDepartment.equals(new AssignedDepartment("Immunology")));
    }

    @Test
    public void toString_differentObjects_stringReturnedIsCorrect() {
        // assignedDepartment created with string's case matching Department's string
        AssignedDepartment neurology = new AssignedDepartment("Neurology");
        assertEquals("Neurology", neurology.toString());

        // assignedDepartment created with string's case different Department's string
        AssignedDepartment radiology = new AssignedDepartment("raDiOlogY");
        assertEquals("Radiology", radiology.toString());
    }
}
