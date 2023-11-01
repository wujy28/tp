package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Birthday;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Gender;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Priority;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GENDER = "ma1e";
    private static final String INVALID_IC_NUMBER = "ta1234560";
    private static final String INVALID_BIRTHDAY = "2000/20/20";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PRIORITY = "L0W+";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_IC_NUMBER = BENSON.getIcNumber().toString();
    private static final String VALID_BIRTHDAY = BENSON.getBirthday().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_PRIORITY = BENSON.getPriority().toString();
    private static final String VALID_ASSIGNED_DEPARTMENT = BENSON.getAssignedDepartment().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream().map(JsonAdaptedTag::new)
        .collect(Collectors.toList());

    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(BENSON);
        assertEquals(BENSON, patient.toModelType());
    }

//    @Test
//    public void toModelType_invalidName_throwsIllegalValueException() {
//        JsonAdaptedPatient patient = new JsonAdaptedPatient(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
//            VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
//        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
//        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
//    }
//
//    @Test
//    public void toModelType_nullName_throwsIllegalValueException() {
//        JsonAdaptedPatient patient = new JsonAdaptedPatient(null, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
//            VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
//    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_GENDER,
            VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, null, VALID_EMAIL, VALID_GENDER,
            VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_GENDER,
            VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, null, VALID_GENDER,
            VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_GENDER,
                VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidIcNumber_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                INVALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT,
                VALID_TAGS);
        String expectedMessage = IcNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullIcNumber_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                null, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, IcNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidBirthday_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                VALID_IC_NUMBER, INVALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT,
                VALID_TAGS);
        String expectedMessage = Birthday.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullBirthday_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                VALID_IC_NUMBER, null, VALID_ADDRESS, VALID_BIRTHDAY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
            VALID_IC_NUMBER, VALID_BIRTHDAY, INVALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
            VALID_IC_NUMBER, VALID_BIRTHDAY, null, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, INVALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT,
                VALID_TAGS);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
                VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, null, VALID_ASSIGNED_DEPARTMENT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_GENDER,
            VALID_IC_NUMBER, VALID_BIRTHDAY, VALID_ADDRESS, VALID_PRIORITY, VALID_ASSIGNED_DEPARTMENT, invalidTags);
        assertThrows(IllegalValueException.class, patient::toModelType);
    }

}
