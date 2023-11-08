package seedu.address.model;
import java.util.ArrayList;

/**
 * This class VersionedAddressBook extends from the AddressBook.
 * This class contains the command history of all the commands entered
 * And the states of the AddressBook in arrayLists.
 * The pointer would be incremented or decremented based on the changes in the states
 */
public class VersionedAddressBook extends AddressBook {
    private ArrayList<AddressBook> addressBooksStateList;
    private ArrayList<String> commandHistory = new ArrayList<>();
    private int currentStatePointer;
    /**
     * Creates an instance of a VersionedAddressBook
     * @param initialState which is the initial state of the AddressBook
     *                     The VersionedAddressBook then stores the different states of the AddressBook
     *                     Based on how it has been modified with the different commands.
     */
    public VersionedAddressBook(AddressBook initialState) {
        this.currentStatePointer = 0;
        this.addressBooksStateList = new ArrayList<>();
        //Initialize the Command History with an empty string
        //Initial state has no commands
        this.commandHistory.add("");
        this.addressBooksStateList.add(initialState);
    }

    /**
     * This method stores the new modified state of the AddressBook
     * in the state arrayList.
     * The latest command is stored in the command History arrayList
     * @param newState
     * @param command
     */
    public void commit(AddressBook newState, String command) {
        if (currentStatePointer != addressBooksStateList.size() - 1) {
            //check it is not already the most recent version
            for (int i = currentStatePointer + 1; i < addressBooksStateList.size(); i++) {
                addressBooksStateList.remove(addressBooksStateList.size() - 1);
                commandHistory.remove(commandHistory.size() - 1);
                //discard the most recent state and the most recent command
            }
        }
        this.addressBooksStateList.add(newState);
        this.commandHistory.add(command);
        this.currentStatePointer++;
    }

    /**
     * This method moves the pointer to the adjacent previous state of the AddressBook
     * in the state arrayList to undo the latest change performed in
     * the AddressBook
     * @return The previous state for the AddressBook
     */
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

    /**
     * This method moves the pointer to the adjacent next state of the AddressBook
     * in the state arrayList to redo the latest change performed in
     * the AddressBook
     * @return The next state for the AddressBook
     */
    public AddressBook redo() {
        currentStatePointer += 1;
        AddressBook state = this.addressBooksStateList.get(currentStatePointer).copy();
        return state;
    }

    public boolean canRedo() {
        return this.currentStatePointer != addressBooksStateList.size() - 1;
    }


}
