package seedu.address.model;
import java.util.ArrayList;

public class VersionedAddressBook extends AddressBook {
    private ArrayList<AddressBook> addressBooksStateList;
    private ArrayList<String> commandHistory;
    private int currentStatePointer;
    public VersionedAddressBook(AddressBook initialState) {
        this.currentStatePointer = 0;
        this.addressBooksStateList = new ArrayList<>();
        this.commandHistory = new ArrayList<>();
        //Initialize the Command History with an empty string
        //Initial state has no commands
        this.commandHistory.add("");
    }

    public void commit(AddressBook newState, String command) {
        if (currentStatePointer != addressBooksStateList.size() - 1) {
            //check it is not already the most recent version
            for (int i = currentStatePointer + 1; i < addressBooksStateList.size(); i++) {
                addressBooksStateList.remove(addressBooksStateList.size() - 1);
                commandHistory.remove(commandHistory.size() - 1);
                //discard the most recent state and the most recent command
            }
        }
        this.currentStatePointer++;
        this.addressBooksStateList.add(newState);
        this.commandHistory.add(command);
    }

    public AddressBook undo() {
        currentStatePointer -= 1;
        return this.addressBooksStateList.get(currentStatePointer).copy();
    }

    public boolean canUndo() {
        return this.currentStatePointer != 0;
    }

    public String getNextCommand() {
        return this.commandHistory.get(currentStatePointer + 1);
    }

    public AddressBook redo() {
        currentStatePointer += 1;
        return this.addressBooksStateList.get(currentStatePointer).copy();
    }

    public boolean canRedo() {
        return this.currentStatePointer != addressBooksStateList.size() - 1;
    }


}
