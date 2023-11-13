package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.AssignedDepartment;
import seedu.address.model.patient.Birthday;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Gender;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Priority;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_GENDER = "NIL";
    private static final String INVALID_BIRTHDAY = "10-11-2000";
    private static final String INVALID_IC_NUMBER = "0376482";
    private static final String INVALID_PRIORITY = "MED";
    private static final String INVALID_ASSIGNED_DEPARTMENT = "cardio";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_GENDER = "female";
    private static final String VALID_BIRTHDAY = "10/11/2000";
    private static final String VALID_IC_NUMBER = "T0376482D";
    private static final String VALID_PRIORITY = "HIGH";
    private static final String VALID_ASSIGNED_DEPARTMENT = "Cardiology";
    private static final String VALID_INITIAL_OBSERVATIONS = "Broken Arm";
    private static final String VALID_DIAGNOSIS = "Hairline Fracture";
    private static final String VALID_TREATMENT_PLAN = "Cast for 2 weeks";

    private static final String WHITESPACE = " \t\r\n";


    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseGender_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGender((String) null));
    }

    @Test
    public void parseGender_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGender(INVALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithoutWhitespace_returnsGender() throws Exception {
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(VALID_GENDER));
    }

    @Test
    public void parseGender_validValueWithWhitespace_returnsTrimmedGender() throws Exception {
        String genderWithWhitespace = WHITESPACE + VALID_GENDER + WHITESPACE;
        Gender expectedGender = new Gender(VALID_GENDER);
        assertEquals(expectedGender, ParserUtil.parseGender(genderWithWhitespace));
    }

    @Test
    public void parseBirthday_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBirthday((String) null));
    }

    @Test
    public void parseBirthday_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday(INVALID_BIRTHDAY));
    }

    @Test
    public void parseBirthday_validValueWithoutWhitespace_returnsBirthday() throws Exception {
        Birthday expectedBirthday = new Birthday(VALID_BIRTHDAY);
        assertEquals(expectedBirthday, ParserUtil.parseBirthday(VALID_BIRTHDAY));
    }

    @Test
    public void parseBirthday_validValueWithWhitespace_returnsTrimmedBirthday() throws Exception {
        String birthdayWithWhitespace = WHITESPACE + VALID_BIRTHDAY + WHITESPACE;
        Birthday expectedBirthday = new Birthday(VALID_BIRTHDAY);
        assertEquals(expectedBirthday, ParserUtil.parseBirthday(birthdayWithWhitespace));
    }

    @Test
    public void parseIcNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIcNumber((String) null));
    }

    @Test
    public void parseIcNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIcNumber(INVALID_IC_NUMBER));
    }

    @Test
    public void parseIcNumber_validValueWithoutWhitespace_returnsIcNumber() throws Exception {
        IcNumber expectedIcNumber = new IcNumber(VALID_IC_NUMBER);
        assertEquals(expectedIcNumber, ParserUtil.parseIcNumber(VALID_IC_NUMBER));
    }

    @Test
    public void parseIcNumber_validValueWithWhitespace_returnsTrimmedIcNumber() throws Exception {
        String icNumberWithWhitespace = WHITESPACE + VALID_IC_NUMBER + WHITESPACE;
        IcNumber expectedIcNumber = new IcNumber(VALID_IC_NUMBER);
        assertEquals(expectedIcNumber, ParserUtil.parseIcNumber(icNumberWithWhitespace));
    }

    @Test
    public void parsePriority_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePriority((String) null));
    }

    @Test
    public void parsePriority_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePriority(INVALID_PRIORITY));
    }

    @Test
    public void parsePriority_validValueWithoutWhitespace_returnsPriority() throws Exception {
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(VALID_PRIORITY));
    }

    @Test
    public void parsePriority_validValueWithWhitespace_returnsTrimmedPriority() throws Exception {
        String priorityWithWhitespace = WHITESPACE + VALID_PRIORITY + WHITESPACE;
        Priority expectedPriority = new Priority(VALID_PRIORITY);
        assertEquals(expectedPriority, ParserUtil.parsePriority(priorityWithWhitespace));
    }

    @Test
    public void parseAssignedDepartment_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAssignedDepartment((String) null));
    }

    @Test
    public void parseAssignedDepartment_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAssignedDepartment(INVALID_ASSIGNED_DEPARTMENT));
    }

    @Test
    public void parseAssignedDepartment_validValueWithoutWhitespace_returnsAssignedDepartment() throws Exception {
        AssignedDepartment expectedAssignedDepartment = new AssignedDepartment(VALID_ASSIGNED_DEPARTMENT);
        assertEquals(expectedAssignedDepartment, ParserUtil.parseAssignedDepartment(VALID_ASSIGNED_DEPARTMENT));
    }

    @Test
    public void parseAssignedDepartment_validValueWithWhitespace_returnsTrimmedAssignedDepartment() throws Exception {
        String assignedDepartmentWithWhitespace = WHITESPACE + VALID_ASSIGNED_DEPARTMENT + WHITESPACE;
        AssignedDepartment expectedAssignedDepartment = new AssignedDepartment(VALID_ASSIGNED_DEPARTMENT);
        assertEquals(expectedAssignedDepartment, ParserUtil.parseAssignedDepartment(assignedDepartmentWithWhitespace));
    }

    @Test
    public void parseInitialObservations_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInitialObservations((String) null));
    }

    @Test
    public void parseInitialObservations_validValueWithoutWhitespace_returnsInitialObservationsString()
            throws Exception {
        String expectedInitialObservations = VALID_INITIAL_OBSERVATIONS;
        assertEquals(expectedInitialObservations, ParserUtil.parseInitialObservations(VALID_INITIAL_OBSERVATIONS));
    }

    @Test
    public void parseInitialObservations_validValueWithWhitespace_returnsTrimmedInitialObservationsString()
            throws Exception {
        String initialObservationsWithWhitespace = WHITESPACE + VALID_INITIAL_OBSERVATIONS + WHITESPACE;
        String expectedInitialObservations = VALID_INITIAL_OBSERVATIONS;
        assertEquals(expectedInitialObservations, ParserUtil
                .parseInitialObservations(initialObservationsWithWhitespace));
    }

    @Test
    public void parseDiagnosis_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDiagnosis((String) null));
    }

    @Test
    public void parseDiagnosis_validValueWithoutWhitespace_returnsDiagnosisString() throws Exception {
        String expectedDiagnosis = VALID_DIAGNOSIS;
        assertEquals(expectedDiagnosis, ParserUtil.parseDiagnosis(VALID_DIAGNOSIS));
    }

    @Test
    public void parseDiagnosis_validValueWithWhitespace_returnsTrimmedDiagnosisString() throws Exception {
        String diagnosisWithWhitespace = WHITESPACE + VALID_DIAGNOSIS + WHITESPACE;
        String expectedDiagnosis = VALID_DIAGNOSIS;
        assertEquals(expectedDiagnosis, ParserUtil.parseDiagnosis(diagnosisWithWhitespace));
    }

    @Test
    public void parseTreatmentPlan_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTreatmentPlan((String) null));
    }

    @Test
    public void parseTreatmentPlan_validValueWithoutWhitespace_returnsTreatmentPlanString() throws Exception {
        String expectedTreatmentPlan = VALID_TREATMENT_PLAN;
        assertEquals(expectedTreatmentPlan, ParserUtil.parseTreatmentPlan(VALID_TREATMENT_PLAN));
    }

    @Test
    public void parseTreatmentPlan_validValueWithWhitespace_returnsTrimmedTreatmentPlanString() throws Exception {
        String treatmentPlanWithWhitespace = WHITESPACE + VALID_TREATMENT_PLAN + WHITESPACE;
        String expectedTreatmentPlan = VALID_TREATMENT_PLAN;
        assertEquals(expectedTreatmentPlan, ParserUtil.parseDiagnosis(treatmentPlanWithWhitespace));
    }
}
