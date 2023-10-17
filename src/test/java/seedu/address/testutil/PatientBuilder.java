package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.patient.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GENDER = "female";
    public static final String DEFAULT_IC_NUMBER = "t1234567j";
    public static final String DEFAULT_BIRTHDAY = "21/01/1994";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Gender gender;
    private IcNumber icNumber;
    private Birthday birthday;
    private Address address;
    private Set<Tag> tags;
    private AssignedDepartment assignedDepartment;
    private final Record record;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        gender = new Gender(DEFAULT_GENDER);
        icNumber = new IcNumber(DEFAULT_IC_NUMBER);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        assignedDepartment = new AssignedDepartment();
        record = new RecordBuilder(this).build();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        phone = patientToCopy.getPhone();
        email = patientToCopy.getEmail();
        address = patientToCopy.getAddress();
        gender = patientToCopy.getGender();
        icNumber = patientToCopy.getIcNumber();
        birthday = patientToCopy.getBirthday();
        tags = new HashSet<>(patientToCopy.getTags());
        assignedDepartment = patientToCopy.getAssignedDepartment();
        record = patientToCopy.getRecord();
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Patient} that we are building.
     */
    public PatientBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Patient} that we are building.
     */
    public PatientBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code IcNumber} of the {@code Patient} that we are building.
     */
    public PatientBuilder withIcNumber(String ic) {
        this.icNumber = new IcNumber(ic);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Patient} that we are building.
     */
    public PatientBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code Patient} that we are building.
     */
    public PatientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code AssignedDepartment} of the {@code Patient} that we are building.
     */
    public PatientBuilder withAssignedDepartment(String department) {
        this.assignedDepartment = new AssignedDepartment(department);
        return this;
    }

    public Patient build() {
        return new Patient(name, phone, email, gender, icNumber, birthday, address, tags);
    }

}
