package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.AssignedDepartment;
import seedu.address.model.patient.Birthday;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Gender;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Priority;
import seedu.address.model.patient.Record;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String gender;
    private final String icNumber;
    private final String birthday;
    private final String address;
    private final String priority;
    private final String assignedDepartment;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private String initialObservations;
    private String diagnosis;
    private String treatmentPlan;

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("gender") String gender,
                              @JsonProperty("icNumber") String icNumber, @JsonProperty("birthday") String birthday,
                              @JsonProperty("address") String address,
                              @JsonProperty("priority") String priority,
                              @JsonProperty("assignedDepartment") String assignedDepartment,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags,
                              @JsonProperty("initialObservations") String initialObservations,
                              @JsonProperty("diagnosis") String diagnosis,
                              @JsonProperty("treatmentPlan") String treatmentPlan) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.icNumber = icNumber;
        this.birthday = birthday;
        this.address = address;
        this.priority = priority;
        this.assignedDepartment = assignedDepartment;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.initialObservations = initialObservations;
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        gender = source.getGender().value;
        icNumber = source.getIcNumber().value;
        birthday = source.getBirthday().strValue;
        address = source.getAddress().value;
        priority = source.getPriority().toString();
        assignedDepartment = source.getAssignedDepartment().toString();
        tags.addAll(source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
        initialObservations = source.getRecord().getInitialObservations();
        diagnosis = source.getRecord().getDiagnosis();
        treatmentPlan = source.getRecord().getTreatmentPlan();
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Tag> patientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            patientTags.add(tag.toModelType());
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (icNumber == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, IcNumber.class.getSimpleName()));
        }
        if (!IcNumber.isValidIC(icNumber)) {
            throw new IllegalValueException(IcNumber.MESSAGE_CONSTRAINTS);
        }
        final IcNumber modelIcNumber = new IcNumber(icNumber);

        if (birthday == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName()));
        }
        if (!Birthday.isValidBirthdate(birthday)) {
            throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
        }
        final Birthday modelBirthday = new Birthday(birthday);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(priority);

        if (assignedDepartment == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, AssignedDepartment.class.getSimpleName()));
        }
        if (!AssignedDepartment.isValidDepartment(assignedDepartment)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final AssignedDepartment modelAssignedDepartment = new AssignedDepartment(assignedDepartment);

        final Set<Tag> modelTags = new HashSet<>(patientTags);

        if (initialObservations == null || diagnosis == null || treatmentPlan == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Record.class.getSimpleName()));
        }
        final Record record = new Record(initialObservations, diagnosis, treatmentPlan);

        return new Patient(modelName, modelPhone, modelEmail, modelGender, modelIcNumber, modelBirthday, modelAddress,
            modelPriority, modelTags, modelAssignedDepartment, record);
    }

}
