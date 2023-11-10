package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Priority;
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
    private TextArea initialObservations;
    @FXML
    private TextArea diagnosis;
    @FXML
    private TextArea treatmentPlan;
    @FXML
    private Label priorityLabel;

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
        addPriorityLabel();
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        // Format TextFlow
        this.initialObservations.setWrapText(true);
        this.initialObservations.setText(record.getInitialObservations());
        record.initialObservationsProperty()
                .addListener((observable, oldValue, newValue) -> initialObservations.setText(newValue));

        this.diagnosis.setWrapText(true);
        this.diagnosis.setText(record.getDiagnosis());
        record.diagnosisProperty().addListener((observable, oldValue, newValue) -> diagnosis.setText(newValue));

        this.treatmentPlan.setWrapText(true);
        this.treatmentPlan.setText(record.getTreatmentPlan());
        record.treatmentPlanProperty().addListener((observable, oldValue, newValue) -> treatmentPlan.setText(newValue));
    }

    private void addPriorityLabel() {
        Priority priority = record.getPatient().getPriority();
        switch (priority.value) {
        case HIGH:
            priorityLabel.setText("!!!");
            priorityLabel.getStyleClass().add("high");
            break;
        case MEDIUM:
            priorityLabel.setText("!!");
            priorityLabel.getStyleClass().add("medium");
            break;
        case LOW:
            priorityLabel.setText("!");
            priorityLabel.getStyleClass().add("low");
            break;
        case NIL:
            // Fallthrough
        default:
            Node parent = priorityLabel.getParent();
            if (parent instanceof HBox) {
                HBox container = (HBox) parent;
                container.getChildren().remove(priorityLabel);
            }
        }
    }
}
