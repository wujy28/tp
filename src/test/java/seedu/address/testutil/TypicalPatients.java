package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.patient.Patient;



/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline")
        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com").withGender("female")
        .withBirthday("23/09/2000").withIcNumber("T0032415E").withPhone("94351253").withTags("friends").build();
    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier")
        .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withGender("male")
        .withBirthday("17/03/1987").withIcNumber("S2091742P").withPhone("98765432").withTags("owesMoney", "friends")
        .build();
    public static final Patient CARL = new PatientBuilder().withName("Carl Kurz").withPhone("95352563")
        .withGender("male").withBirthday("08/06/1997").withIcNumber("S2780456R").withEmail("heinz@example.com")
        .withAddress("wall street").build();
    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier").withPhone("87652533")
        .withGender("male").withBirthday("29/01/2001").withIcNumber("T0163826D").withEmail("cornelia@example.com")
        .withAddress("10th street").withTags("friends").build();
    public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer").withPhone("9482224")
        .withGender("female").withBirthday("12/11/1995").withIcNumber("S1839267A").withEmail("werner@example.com")
        .withAddress("michegan ave").build();
    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz").withPhone("9482427")
        .withEmail("lydia@example.com").withGender("female").withBirthday("29/06/1982").withIcNumber("S3729462N")
        .withAddress("little tokyo").build();
    public static final Patient GEORGE = new PatientBuilder().withName("George Best").withPhone("9482442")
        .withGender("male").withBirthday("03/07/1997").withIcNumber("S4839258F").withEmail("anna@example.com")
        .withAddress("4th street").build();

    // Manually added
    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier").withPhone("8482424")
        .withGender("male").withBirthday("07/04/2002").withIcNumber("T0247382S").withEmail("stefan@example.com")
        .withAddress("little india").build();
    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller").withPhone("8482131")
        .withGender("female").withBirthday("29/04/1984").withIcNumber("S5729346L").withEmail("hans@example.com")
        .withAddress("chicago ave").build();

    // Manually added - Patient's details found in {@code CommandTestUtil}
    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
        .withEmail(VALID_EMAIL_AMY).withGender(VALID_GENDER_AMY).withIcNumber(VALID_IC_NUMBER_AMY)
        .withBirthday(VALID_BIRTHDAY_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
        .withEmail(VALID_EMAIL_BOB).withGender(VALID_GENDER_BOB).withIcNumber(VALID_IC_NUMBER_BOB)
        .withBirthday(VALID_BIRTHDAY_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
        .build();

    private TypicalPatients() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical patients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Patient patient : getTypicalPatients()) {
            ab.addPatient(patient);
        }
        return ab;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
