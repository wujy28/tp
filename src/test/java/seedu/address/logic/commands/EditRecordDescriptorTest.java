package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.REC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIAGNOSIS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INITIAL_OBSERVATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TREATMENT_PLAN_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditRecordDescriptorBuilder;

public class EditRecordDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        RecordCommand.EditRecordDescriptor descriptorWithSameValues = new RecordCommand.EditRecordDescriptor(REC_AMY);
        assertTrue(REC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(REC_AMY.equals(REC_AMY));

        // null -> returns false
        assertFalse(REC_AMY.equals(null));

        // different types -> returns false
        assertFalse(REC_AMY.equals(5));

        // different values -> returns false
        assertFalse(REC_AMY.equals(REC_BOB));

        // different inital observation -> returns false
        RecordCommand.EditRecordDescriptor editedAmy = new EditRecordDescriptorBuilder(REC_AMY)
                .withInitialObservations(VALID_INITIAL_OBSERVATION_BOB).build();
        assertFalse(REC_AMY.equals(editedAmy));

        // different diagnosis -> returns false
        editedAmy = new EditRecordDescriptorBuilder(REC_AMY).withDiagnosis(VALID_DIAGNOSIS_BOB).build();
        assertFalse(REC_AMY.equals(editedAmy));

        // different treatment plan -> returns false
        editedAmy = new EditRecordDescriptorBuilder(REC_AMY).withTreatmentPlan(VALID_TREATMENT_PLAN_BOB).build();
        assertFalse(REC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        RecordCommand.EditRecordDescriptor editRecordDescriptor = new RecordCommand.EditRecordDescriptor();
        String expected = RecordCommand.EditRecordDescriptor.class.getCanonicalName() + "{initialObservations="
                + editRecordDescriptor.getInitialObservations().orElse(null) + ", diagnosis="
                + editRecordDescriptor.getDiagnosis().orElse(null) + ", treatmentPlan="
                + editRecordDescriptor.getTreatmentPlan().orElse(null) + "}";
        assertEquals(expected, editRecordDescriptor.toString());
    }
}
