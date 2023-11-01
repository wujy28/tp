package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Priority;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient patient;

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
    private Label age;
    @FXML
    private Label assignedDepartment;
    @FXML
    private FlowPane tags;
    @FXML
    private Label priorityLabel;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        name.setText(patient.getName().fullName);
        icNumber.setText(patient.getIcNumber().value);
        gender.setText(patient.getGender().value);
        birthday.setText(patient.getBirthday().toString());
        phone.setText(patient.getPhone().value);
        age.setText(patient.getAge().toString());
        assignedDepartment.setText(patient.getAssignedDepartment().toString());
        addPriorityLabel();
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void addPriorityLabel() {
        Priority priority = patient.getPriority();
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
            Node parent = priorityLabel.getParent();
            if (parent instanceof HBox) {
                HBox container = (HBox) parent;
                container.getChildren().remove(priorityLabel);
            }
        }
    }
}
