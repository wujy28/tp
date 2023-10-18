package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Record;

/**
 * An UI component that displays information of a {@code Record}.
 */
public class RecordCard extends UiPart<Region> {

    private static final String FXML = "RecordCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Record record;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label icNumber;
    @FXML
    private Label gender;
    @FXML
    private Label birthday;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label assignedDepartment;
    @FXML
    private TextFlow initialObservations;
    @FXML
    private TextFlow diagnosis;
    @FXML
    private TextFlow treatmentPlan;
    @FXML
    private TextFlow remarks;

    /**
     * Creates a {@code RecordCard} with the given {@code Record}.
     */
    public RecordCard(Record record) {
        super(FXML);
        this.record = record;

        Patient patient = record.getPatient();
        name.setText(patient.getName().fullName);
        icNumber.setText(patient.getIcNumber().value);
        gender.setText(patient.getGender().value);
        birthday.setText(patient.getBirthday().toString());
        phone.setText(patient.getPhone().value);
        address.setText(patient.getAddress().value);
        email.setText(patient.getEmail().value);
        assignedDepartment.setText(patient.getAssignedDepartment().toString());
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        Text initialObservations = new Text(record.getInitialObservations());
        this.initialObservations.getChildren().add(initialObservations);
        Text diagnosis = new Text(record.getDiagnosis());
        this.diagnosis.getChildren().add(diagnosis);
        Text treatmentPlan = new Text(record.getTreatmentPlan());
        this.treatmentPlan.getChildren().add(treatmentPlan);
        Text remarks = new Text("-"); // Since we do not store remarks in record yet
        this.remarks.getChildren().add(remarks);
    }
}
