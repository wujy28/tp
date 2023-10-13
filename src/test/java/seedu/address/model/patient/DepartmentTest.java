package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DepartmentTest {

    @Test
    public void findDepartment() {
        // null input
        assertEquals(null, Department.findDepartment(null));

        // input with invalid department
        assertEquals(null, Department.findDepartment("")); // empty string
        assertEquals(null, Department.findDepartment(" ")); // space string
        assertEquals(null, Department.findDepartment("operating threatre")); // department does not exist
        assertEquals(null, Department.findDepartment("hematology")); // wrongly spelled department
        assertEquals(null, Department.findDepartment(" neurology")); // wrong string format

        // input with valid department
        assertEquals(Department.DEFAULT, Department.findDepartment("-")); // default
        assertEquals(Department.UROLOGY, Department.findDepartment("Urology")); // valid department
        assertEquals(Department.ANAESTHESIOLOGY, Department.findDepartment("aNaesThesIolOgY")); // weird case
    }

    @Test
    public void isValidDepartment() {
        // invalid department
        assertFalse(Department.isValidDepartment("")); // empty string
        assertFalse(Department.isValidDepartment(" ")); // spaces only
        assertFalse(Department.isValidDepartment("Psych1atry")); // Name does not match
        assertFalse(Department.isValidDepartment(" Plastic Surgery")); // first character is a space
        assertFalse(Department.isValidDepartment("Anesthesiology")); // wrong spelling (American)
        assertFalse(Department.isValidDepartment("Burn Unit")); // department does not exist

        // valid department
        assertTrue(Department.isValidDepartment("-")); // default department
        assertTrue(Department.isValidDepartment("Palliative Medicine")); // department exists
        assertTrue(Department.isValidDepartment("emergency department")); // lower case only
        assertTrue(Department.isValidDepartment("neUroSURGerY")); // weird casing
    }
}
