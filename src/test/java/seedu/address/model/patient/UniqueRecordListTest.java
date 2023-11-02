package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}

