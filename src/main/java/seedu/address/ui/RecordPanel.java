package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.patient.Patient;

/**
 * A ui for the record panel that is displayed on the right of the application.
 */
public class RecordPanel extends UiPart<Region> {

    private static final String FXML = "RecordPanel.fxml";
    private final PatientListPanel patientListPanel; // a reference to the patient list to listen for selection event

    @FXML
    private VBox recordView;

    public RecordPanel(PatientListPanel patientList) {
        super(FXML);
        this.patientListPanel = patientList;
        patientListPanel.getPatientListView().getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Patient>() {
                    @Override
                    public void changed(ObservableValue<? extends Patient> observable,
                                        Patient oldValue, Patient newValue) {
                        if (newValue != null) {
                            displayRecord(newValue);
                        }
                    }
                }
        );
    }

    public void displayRecord(Patient patient) {
        requireNonNull(patient);
        recordView.getChildren().clear();
        recordView.getChildren().add(new RecordCard(patient.getRecord()).getRoot());
    }
}
