package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the patient with the IcNumber from patientList
     */
    Patient getPatient(IcNumber icNumber, List<Patient> patientList);

    /**
     * Returns the currentPatientList
     */
    ObservableList<Patient> getCurrentPatientList();

    /**
     * Returns true if a patient with that IcNumber is present
     */

    boolean isPatientWithIcNumberPresent(IcNumber icNumber);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook, String command);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the address book.
     */
    void deletePatient(Patient target, String command);


    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the address book.
     */
    void addPatient(Patient patient, String command);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient in the address
     * book.
     */
    void setPatient(Patient target, Patient editedPatient, String command);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /**
     *  Save current state of the patient data
     * @param command The most recent command
     */
    void commitAddressBook(String command);

    /**
     * Converts to the previous state of the patient data
     * Returns most recent command
     */
    String undoAddressBook();

    /**
     * Converts to the previous state of the patient data
     * Returns most recent command
     */
    String redoAddressBook();

    /**
     * Check if there is a previous state/command to undo to.
     * Returns true if there is a state, and false otherwise.
     */
    boolean canUndoAddressBook();

    /**
     * Check if there is a next state/command to redo to.
     * Returns true if there is a state, and false otherwise.
     * */
    boolean canRedoAddressBook();

}
