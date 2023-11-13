package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.Assert;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.RecordBuilder;

public class UniqueRecordListTest {

    private UniqueRecordList uniqueRecordList;

    @BeforeEach
    public void setUp() {
        uniqueRecordList = new UniqueRecordList();
    }

    @Test
    public void contains_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.contains(null));
    }

    @Test
    public void contains_recordNotInList_returnsFalse() {
        Record record = new Record();
        assertFalse(uniqueRecordList.contains(record));
    }

    @Test
    public void contains_recordInList_returnsTrue() {
        Record record = new Record();
        uniqueRecordList.add(record);
        assertTrue(uniqueRecordList.contains(record));
    }

    @Test
    public void add_nullRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecordList.add(null));
    }

    @Test
    public void add_duplicateRecord_throwsDuplicateRecordException() {
        Record record = new Record();
        uniqueRecordList.add(record);
        assertThrows(UniqueRecordList.DuplicateRecordException.class, () -> uniqueRecordList.add(record));
    }

    @Test
    public void remove_nonExistentRecord_throwsRecordNotFoundException() {
        Record record = new Record();
        assertThrows(UniqueRecordList.RecordNotFoundException.class, () -> uniqueRecordList.remove(record));
    }

    @Test
    public void remove_existingRecord_removesRecord() {
        Record record = new Record();
        uniqueRecordList.add(record);
        uniqueRecordList.remove(record);
        assertFalse(uniqueRecordList.contains(record));
    }

    @Test
    public void setRecord_nullTargetRecord_throwsNullPointerException() {
        Record testRecord = new Record(ALICE);
        Assert.assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecord(null, testRecord));
    }

    @Test
    public void setRecord_nullEditedRecord_throwsNullPointerException() {
        Record testRecord = new Record(ALICE);
        Assert.assertThrows(NullPointerException.class, () -> uniqueRecordList.setRecord(testRecord, null));
    }

    @Test
    public void setRecord_targetRecordNotInList_throwsRecordNotFoundException() {
        Record testRecord = new Record(ALICE);
        Assert.assertThrows(UniqueRecordList.RecordNotFoundException.class, () -> uniqueRecordList.setRecord(testRecord,
                testRecord));
    }

    @Test
    public void setRecord_editedRecordIsSameRecord_success() {
        Record testRecord = new Record(ALICE);
        uniqueRecordList.add(testRecord);
        uniqueRecordList.setRecord(testRecord, testRecord);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(testRecord);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecord_editedRecordHasSameIdentity_success() {
        Record testRecord = new Record(ALICE);
        uniqueRecordList.add(testRecord);
        PatientBuilder patientBuilder = new PatientBuilder(ALICE);
        Record editedRecord = new RecordBuilder(patientBuilder).withInitialObservations("Coughing non-stop")
                .withDiagnosis("Acute Bronchitis").withTreatmentPlan("Take medicine and drink water").build();
        uniqueRecordList.setRecord(testRecord, editedRecord);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(editedRecord);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }

    @Test
    public void setRecord_editedRecordHasDifferentIdentity_success() {
        Record testRecord1 = new Record(ALICE);
        Record testRecord2 = new Record(BOB);
        uniqueRecordList.add(testRecord1);
        uniqueRecordList.setRecord(testRecord1, testRecord2);
        UniqueRecordList expectedUniqueRecordList = new UniqueRecordList();
        expectedUniqueRecordList.add(testRecord2);
        assertEquals(expectedUniqueRecordList, uniqueRecordList);
    }
}

