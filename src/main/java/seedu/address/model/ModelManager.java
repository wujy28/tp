package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Patient;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final VersionedAddressBook versionedAddressBook;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.addressBook.getPatientList());
        this.versionedAddressBook = new VersionedAddressBook(this.addressBook.copy());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook, String command) {
        this.addressBook.resetData(addressBook);
        commitAddressBook(command);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return addressBook.hasPatient(patient);
    }

    @Override
    public void deletePatient(Patient target, String command) {
        addressBook.removePatient(target);
        commitAddressBook(command);
    }

    @Override
    public Patient getPatient(IcNumber icNumber, List<Patient> patientList) {
        for (Patient patient : patientList) {
            if (patient.getIcNumber().equals(icNumber)) {
                return patient;
            }
        }
        return null;
    }


    @Override
    public void addPatient(Patient patient, String command) {
        addressBook.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        commitAddressBook(command);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient, String command) {
        requireAllNonNull(target, editedPatient);
        addressBook.setPatient(target, editedPatient);
        commitAddressBook(command);
    }

    /**
     * Returns true if a {@Code Patient} with that {@Code IcNumber} is present
     *
     * @param icNumber IcNumber to be checked
     * @return true Patient with that IcNumber is present
     */
    @Override
    public boolean isPatientWithIcNumberPresent(IcNumber icNumber) {
        List<Patient> currentPatientList = getCurrentPatientList();
        for (Patient patient : currentPatientList) {
            if (patient.getIcNumber().equals(icNumber)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void commitAddressBook(String command) {
        versionedAddressBook.commit(addressBook.copy(), command);
    }

    @Override
    public String redoAddressBook() {
        AddressBook newData = versionedAddressBook.redo();
        addressBook.resetData(newData);
        return versionedAddressBook.getNextCommand();
    }


    /**
     * Converts to the previous state of the patient data
     * Returns most recent command
     */
    @Override
    public String undoAddressBook() {
        AddressBook newData = versionedAddressBook.undo();
        addressBook.resetData(newData);
        return versionedAddressBook.getNextCommand();
    }

    /**
     * Check if there is a previous state/command to undo to.
     * Returns true if there is a state, and false otherwise.
     */
    public boolean canUndoAddressBook(){
      return versionedAddressBook.canUndo();
    }

    /**
     * Check if there is a newer state/command to redo to.
     * Returns true if there is a state, and false otherwise.
     */
    public boolean canRedoAddressBook(){
        return versionedAddressBook.canRedo();
    }

    /**
     * Returns the current list of {@code Patient} in the address book
     *
     * @return Current list of {@code Patient} in the address book
     */
    public ObservableList<Patient> getCurrentPatientList() {
        return addressBook.getCurrentPatientList();
    }

    /**
     * Sorts the address book with the given {@code comparator}
     *
     * @param comparator used to order the entries in the address book
     */
    public void sortPatientList(Comparator<? super Patient> comparator) {
        requireNonNull(comparator);
        addressBook.sortPatientList(comparator);
    }


    //=========== Filtered Patient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook) && userPrefs.equals(otherModelManager.userPrefs)
            && filteredPatients.equals(otherModelManager.filteredPatients);
    }

}
