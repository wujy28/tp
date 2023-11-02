---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# Advanced&Efficient (A&E) Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of
classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is
in charge of the app launch and shut down.

* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `RecordPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the
visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` or `Record` object residing in the `Model`.

### Logic component

**API
** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API
call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of
PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates
   a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which
   is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a patient).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API
** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`,
which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of
each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>

### Storage component

**API
** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,

* can save both address book data and user preference data in JSON format, and read them back into corresponding
  objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

--------------------------------------------------------------------------------------------------------------------

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

--------------------------------------------------------------------------------------------------------------------


### Add patient feature

#### Implementation

The add patient operation is facilitated by `AddCommandParser`. `AddCommandParser` parses the user input string
and creates the `AddCommand` to be executed by the `LogicManager`. `AddCommand` extends `Command` and implements the
`Command#execute` method.

Given below is an example usage scenario and how the add patient operation is handled in A&E.

Step 1. Assuming the application has been launched, the user enters the required fields followed by any optional fields.

For adding **only** required fields, the user enters `add n/John Tan i/t7654321j`, which is to add
a patient with `Name = John Tan` and `IcNumber = t1234567j`,

For adding **additional** optional fields, the user enters `add n/John Tan i/t7654321j p/90909090`, which is to add the
optional field `Phone = 90909090`.

<box type="info" seamless>

**Note:** From Step 2 onwards, we will assume user enters `add n/John Tan i/t7654321j`.

</box>

Step 2. `LogicManager#execute` would first invoke `AddressBookParser#parseCommand` which splits the
command word `add` and the argument `n/John Tan i/t7654321j`. After splitting, `AddressBookParser#parseCommand` would identify
that the command is `Add` and instantiate `AddCommandParser` and call its `AddCommandParser#parse` to parse the
argument accordingly.

Step 3. `AddCommandParser#parse` will call `AddCommandParser#createPatient` method to create the patient with the relevant fields and fills all optional fields
with default values otherwise. It is then passed as an argument to instantiate the `AddCommand`, which is then returned by
`AddCommandParser#parse`

Step 4. `LogicManager#execute` now invokes `AddCommand#execute` which checks for duplicate `Patient`. `model#addPatient`
is then called to add the `Patient` into the address book. It then returns a `CommandResult` stating the patient has been listed.

<puml src="diagrams/AddSequenceDiagram.puml" alt="AddSequenceDiagram" />

#### Design considerations:

**Aspect: How to display the specified patient:**

* **Alternative 1 (current choice):** Utilize current filteredPatientList display to
  display the patient.
    * Pros: Easy to implement.
    * Cons: Similar way in displaying of patient(s) may lead to confusion between commands.


* **Alternative 2:** Create a new set of JavaFx controls to display that patient differently
  from the current filteredPatientList display.
    * Pros: New display can be customisable for users' needs.
      Cons: New display have to be implemented correctly to integrate with
      existing displays.


### View patient feature

#### Implementation

The view patient operation is facilitated by `ViewCommandParser`. `ViewCommandParser` parses the user input string
and creates the `ViewCommand` to be executed by the `LogicManager`. `ViewCommand` extends `Command` and implements the
`Command#execute` method.

Given below is an example usage scenario and how the view patient operation is handled in A&E.

Step 1. Assuming the application has been launched, the user enters `view i/t1234567j`, which is to find
the specific patient with `IcNumber = t1234567j`. This invokes `LogicManager#execute` to execute the logic of the
command.

Step 2. `LogicManager#execute` would first invoke `AddressBookParser#parseCommand` which splits the
command word `view` and the argument `i/t1234567j`. After splitting, `AddressBookParser#parseCommand` would identify
that the command is `View` and instantiate `ViewCommandParser` and call its `ViewCommandParser#parse` to parse the
argument accordingly.

Step 3. `PatientWithIcNumberPredicate` predicate, which checks if a `Patient` has the `IcNumber` is created. It is
then passed as an argument along with `IcNumber` to instantiate the `ViewCommand`, which is then returned by
`ViewCommandParser#parse`

Step 5. `LogicManager#execute` now invokes `ViewCommand#execute` which calls `model#updateFilteredPatientList` with
`PatientWithIcNumberPredicate` as an argument. This will update the displayed filter list to the predicate.
It then returns a `CommandResult` stating the patient has been listed.

<puml src="diagrams/ViewSequenceDiagram.puml" alt="ViewSequenceDiagram" />

#### Design considerations:

**Aspect: How to display the specified patient:**

* **Alternative 1 (current choice):** Utilize current filteredPatientList display to
  display the patient.
    * Pros: Easy to implement.
    * Cons: Similar way in displaying of patient(s) may lead to confusion between commands.


* **Alternative 2:** Create a new set of JavaFx controls to display that patient differently
  from the current filteredPatientList display.
    * Pros: New display can be customisable for users' needs.
      Cons: New display have to be implemented correctly to integrate with
      existing displays.

### Edit Record feature

#### Implementation

The edit record operation is facilitated by `RecordCommandParser`. `RecordCommandParser` parses the user input string
and creates the `RecordCommand` to be executed by the `LogicManager`. `RecordCommand` extends `Command` and implements the
`Command#execute` method.

Given below is an example usage scenario and how the edit record operation is handled in A&E.

Step 1. Assuming the application has been launched, the user enters `record i/T0201234A o/Broken Arm di/Hairline fracture
tp/Cast for 2 days`, which is to edit the record of the specific patient with `IcNumber = T0201234A` such that `Initial
Observation = Broken Arm`, `Diagnosis = Hairline fracture`, and `Treatment Plan = Cast for 2 days`. This invokes
`LogicManager#execute` to execute the logic of the command.

Step 2. `LogicManager#execute` would first invoke `AddressBookParser#parseCommand` which splits the command word
`record` and the arguments `i/T0201234A`, `o/Broken Arm`, `di/Hairline fracture`, and `tp/Cast for 2 days`. After
splitting, `AddressBookParser#parseCommand` would identify that the command is `Record` and instantiate
`RecordCommandParser` and call its `RecordCommandParser#parse` to parse the arguments accordingly.

Step 3. `RecordCommandParser#parse` would first map the `IcNumber` prefix to its argument, `T0201234A`, the
`initialObservations` prefix to its argument `Broken Arm`, the `diagnosis` prefix to its argument `Hairline fracture`,
and the `treatmentPlan` prefix to its argument `Cast for 2 days` using `ArgumentMultimap`.
The `ArgumentMultimap` would then be used to identify the `IcNumber` and a `ParseException` is thrown if command inputs
are invalid. The `ArgumentMultimap` also invokes `ArgumentMultimap#isPresent` to check if the other prefixes for `Initial
Observation`, `Diagnosis` and `Treatment Plan` are present. If `true` is returned, the arguments will be parsed into a
`EditRecordDescriptor` object.

Step 4. The `EditRecordDescriptor` object calls `EditRecordDescriptor#isAnyFieldEdited`, which checks if any of the fields
of Record has been edited, and throws a `ParseException` if returned `false`. It is then passed as an argument along with
`IcNumber` to instantiate the `RecordCommand`, which is then returned by `RecordCommandParser#parse`

Step 5. `LogicManager#execute` now invokes `RecordCommand#execute` which gets the specified Patient according to the IcNumber.
Then, `RecordCommand#createEditedRecord` is called with the specified Patient's `Record` and the `EditRecordDescriptor` object
which contains the fields to be edited. It then returns a `RecordResult` stating the patient IcNumber and edited Record.
`PatientWithFieldNotFoundException` is thrown if no patient found.

### Assign department feature

#### AssignedDepartment attribute

The `AssignedDepartment` attribute of a patient in A&E is represented by a stored `Department` value. `Department` is
an enumeration that encapsulates all the predefined hospital department values stored by the system and available
to the user. The list of valid departments can be found in the appendix of the User Guide.

#### Design considerations:

**Aspect: How to represent a department in the system:**

* **Alternative 1 (current choice):** Use Java Enumerations.
    * Pros: Ensures type safety. Discrete constants allow for usage in switch-cases, and thus can potentially be used
  to easily categorize patients in future features.
    * Cons: Does not support user-defined categories or departments.

* **Alternative 2:** Using an abstract Department class and inheritance.
    * Pros: Can be made to support user-defined departments. Can specify different behavior for different
  types of departments.
    * Cons: Implementation is more complicated when it comes to storing and keeping track of all the different
  subclasses or limiting valid department values.

* **Alternative 3:** Using Strings.
    * Pros: Very easy to implement.
    * Cons: Values are not exhaustive. Does not support unique behavior for each type of department.

#### Implementation of `assign`

The assign department operation is facilitated by the `AssignCommand` and `AssignCommandParser` classes, similar
to `ViewCommand` as mentioned above. `AssignCommand` extends `Command` and overrides `Command#execute` to perform
its intended behavior, invoked by the `LogicManager` class. `AssignCommandParser` is responsible for parsing the
string of arguments containing an IC number and department inputted by the user, to create an `AssignCommand` object.

The following sequence diagram summarizes what happens when `AssignCommand#execute` is invoked.

<puml src="diagrams/AssignSequenceDiagram.puml" alt="AssignSequenceDiagram" />

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()`— Saves the current address book state in its history.
* `VersionedAddressBook#undo()`— Restores the previous address book state from its history.
* `VersionedAddressBook#redo()`— Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th patient in the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new patient. The `add` command also
calls `Model#commitAddressBook()`, causing another modified address book state to be saved into
the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will
not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the patient was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no
previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the
case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the
lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once
to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address
book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()`
to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the patient being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has to log patient records into the system
* has to assign patients to the relevant departments
* has to work under time pressure during an emergency
* faces frequent interruption due to emergencies
* has an above average typing speed
* prefers typing over mouse/voice commands
* is comfortable using CLI apps

**Value proposition**: Advanced&Efficient helps Emergency Department (ED) doctors in logging patient reports and
assigning patients to relevant departments under time pressure during an emergency, using the CLI.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​   | I want to …​                                                                                       | So that I can…​                                                                                                                                       |
|----------|-----------|----------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| `* * *`  | ED Doctor | View the record of a patient                                                                       | Keep track of every patient’s condition and treatment plan                                                                                            |
| `* * *`  | ED Doctor | Assign a patient to a department                                                                   | Keep track of where each patient is receiving further treatment                                                                                       |
| `* * *`  | ED Doctor | Edit the existing record of a patient                                                              | Keep the patient’s record relevant and up-to-date                                                                                                     |
| `* * *`  | ED Doctor | Delete a patient from my list                                                                      | Declutter and only keep the active patients on my list                                                                                                |
| `* * *`  | ED Doctor | Add a patient to the list                                                                          | -                                                                                                                                                     |
| `* * *`  | ED Doctor | View the list of patients                                                                          | -                                                                                                                                                     |
| `* *`    | ED Doctor | Undo my latest action                                                                              | Quickly revert a mistake I made                                                                                                                       |
| `* *`    | ED Doctor | Tag patients                                                                                       | Better organize patients into categories so as to better keep track of them                                                                           |
| `* *`    | ED Doctor | Assign the priority to the patient with respect to the severity of the case                        | So that I can keep track of the more critical cases                                                                                                   |
| `* *`    | ED Doctor | Find patients from the list that match a given property (e.g. name, IC number, priority)           | Quickly narrow down the list to view the patients of my interest                                                                                      |
| `*`      | ED Doctor | Quickly view the availability status of a doctor                                                   | Assign them patients                                                                                                                                  |
| `*`      | ED Doctor | Message and communicate with other healthcare staff                                                | Coordinate patient care effectively                                                                                                                   |
| `*`      | ED Doctor | Sort my list of active patients by priority/severity                                               | Provide timely treatment to the patients in critical condition                                                                                        |
| `*`      | ED Doctor | Retrieve the patient’s medical history from the national database after logging them in the system | Gather more information to make a more informed diagnosis and as such provide effective treatment for the patient                                     |
| `*`      | ED Doctor | Customize shortcuts for my most commonly-used commands                                             | Make my workflow more efficient and convenient                                                                                                        |
| `*`      | ED Doctor | Backup the data in my app                                                                          | Ensure that all the patient information is not completely lost in the event that the data file gets corrupted or the computer’s hardware malfunctions |
| `*`      | ED Doctor | Login into the system only using hospital credentials                                              | Ensure the confidentiality and security of patient information and limit its access to hospital personnel only                                        |
| `*`      | ED Doctor | Securely send a patient’s records to the relevant department                                       | Ensure that the other department I am handing my patient to is on the same page as to/updated on the current condition of the patient                 |
| `*`      | ED Doctor | Access clinical guidelines and resources within the application                                    | Diagnose and treat patients with greater ease                                                                                                         |
| `*`      | ED Doctor | View real-time vitals of patients                                                                  | Monitor patient’s condition efficiently                                                                                                               |
| `*`      | ED Doctor | Receive notifications for critical situations                                                      | Provide timely intervention and efficiently handle critical patient conditions                                                                        |
| `*`      | ED Doctor | Save a draft of a message I am writing into the local repository                                   | Return to work on it after an emergency call                                                                                                          |
| `*`      | ED Doctor | Filter a list of doctors according to their department                                             | I can easily identify doctors to assign to from a specific department                                                                                 |
| `*`      | ED Doctor | View the list of doctors                                                                           | I know who I can refer my patients for further treatment to                                                                                           |
| `*`      | ED Doctor | View a list of commands                                                                            | Know the commands needed to carry out my action                                                                                                       |

### Use cases

(For all use cases below, the **System** is the `Advanced&Efficient`application and the **Actor** is the `ED doctor`,
unless specified otherwise)

**Use case: UC01 - Add a patient**

**MSS**

1. ED doctor requests to add a new patient with given details.
2. Advanced&Efficient adds the patient to the patient list.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the given details are invalid.
    * 1a1. Advanced&Efficient shows an error message saying the given details are invalid.
    * 1a2. Advanced&Efficient requests for correct details.
    * 1a3. ED doctor enters the requested details.

      Steps 1a1-1a3 are repeated until the details are valid.

      Use case resumes from step 2.


* 1b. Advanced&Efficient detects that not all required details are given.
    * 1b1. Advanced&Efficient shows an error message saying that not all required details are given.
    * 1b2. Advanced&Efficient requests for all the required details.
    * 1b3. ED doctor enters the required details.

      Steps 1b1-1b3 are repeated until all the required details are given.

      Use case resumes from step 2.


* 1c. Advanced&Efficient detects that the given patient is already on the patient list.
    * 1c1. Advanced&Efficient shows an error message saying patient is already added.

      Use case ends.

**Use case: UC02 - Delete a patient**

**MSS**

1. ED doctor requests to delete a patient of the given IC number.
2. Advanced&Efficient deletes the patient from the list.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the given IC number is invalid.
    * 1a1. Advanced&Efficient shows an error message saying the given IC number is invalid.
    * 1a2. Advanced&Efficient requests for correct IC number.
    * 1a3. ED doctor enters the IC number.

      Steps 1a1-1a3 are repeated until the IC number is valid.

      Use case resumes from step 2.


* 1b. Advanced&Efficient detects that the specified patient does not exist.
    * 1b1. Advanced&Efficient shows message saying patient does not exist.

      Use case ends.

**Use case: UC03 - View the list of patients**

**MSS**

1. ED doctor requests to view the list of all patients.
2. Advanced&Efficient shows a list of all patients.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the list is empty.
    * 1a1. Advanced&Efficient shows a message saying that the list is empty.

      Use case ends.

**Use case: UC04 - View a patient record**

**MSS**

1. ED doctor requests to view a patient’s record.
2. Advanced&Efficient shows the patient’s record.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the specified patient does not exist.
    * 1a1. Advanced&Efficient shows message saying the patient does not exist.

      Use case ends.

**Use case: UC05 - Edit a patient record**

**MSS**

1. ED doctor requests to edit a patient’s record.
2. Advanced&Efficient edits the patient’s record as the ED doctor specifies.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the specified patient does not exist.
    * 1a1. Advanced&Efficient shows message saying the patient does not exist.
      Use case ends.


* 1b. Advanced&Efficient detects that the given details are invalid.
    * 1b1. Advanced&Efficient shows an error message saying the given details are invalid.
    * 1b2. Advanced&Efficient requests for the correct details.
    * 1b3. ED doctor enters the requested details.

      Steps 1b1-1b3 are repeated until the details are valid.

      Use case resumes from step 2.

**Use case: UC06 - Assign a patient to a department**

**MSS**

1. ED doctor requests to assign a patient to a department.
2. Advanced&Efficient assigns the patient to the department based on the input.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the given department is invalid.
    * 1a1. Advanced&Efficient shows an error message saying the given department is invalid.
    * 1a2. Advanced&Efficient requests for the correct department.
    * 1a3. ED doctor enters the department.

      Steps 1a1-1a3 are repeated until the department is valid.

      Use case resumes from step 2.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 patients without a noticeable sluggishness in performance for typical usage.
3. A ED doctor with above average typing speed for regular English text should be able to accomplish most of the tasks
   faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a patient

1. Deleting a patient while all patients are being shown

    1. Prerequisites: List all patients using the `list` command. Multiple patients in the list.
    2. Test case: `delete i/T0000000A`<br>
       Expected: Will iterate through the list to search for Patient with matching IC Number and delete that patient
       Timestamp in the status bar is updated.
    3. Test case: `delete I/T0000000B`<br>
       Expected: If patient is not found or does not exist, no patient is deleted.
       Error details shown in the status message. Status bar remains the same.
    4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Editing a patient
1. Editing a patient's attributes that are specified based on the prefixes in the user inputs
   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list.
   2. Test case: `edit i/T0000000A n/John Doe`<br>
      Expected: Will iterate through the list to search for Patient with matching IC Number and edit that patient
      Timestamp in the status bar is updated.
   3. Test case: `edit i/T0000000A`
      Expected: Will throw an exception if the fields to be edited are left empty.
      Error details shown in the status message. Status bar remains the same.
   4. Other incorrect edit commands to try: `edit`, `delete x`, `...`
      Expected: Similar to previous.
1. _{ more test cases …​ }_

### Class `Birthday` and Testing
1. Patient requires to have a birthday as a compulsory characteristic to be considered
**Format:** A `Birthday` object can be initialized as follows:

{

    Birthday birthday = new Birthday("01/01/1991")

}
### Explanation:

**string input** : User Input must be in the dd/MM/yyyy format to be parsed
correctly using LocalDateTime as implemented in the Birthday Class

**incorrect inputs** : Inputs are checked for null and validity based on regex before initializing the Birthday object
User Inputs which do not follow the correct format will receive an error message accordingly

**tests** : Array of tests to account for various inputs that can be given by the user
and checks on validity of the arguments given to prevent invocation errors

**LocalDateTime** : LocalDateTime used instead of String as Age is a non-constant attribute of the Patient
A patient's age constantly changes. Hence LocalDateTime offers flexibility to auto calculate a patient's age
based on Birthday without requiring manual interferance as a potential extension.

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_
2. Saving Assigned Department to Storage
### Explanation:
**Storage**: Storage classes are to save the details of the patients upon exiting the app. While the basic attributes of the patients like IC Number,
Gender and all were being saved, complicated attributes like Assigned Department were not.

**Assigned Department**: The patient will be assigned to a department when being admitted.

**Enhancement**: Made changes to the storage and RecordBuilder classes and the tests
to ensure the department assigned is saved as well.

1. _{ more test cases …​ }_


### Class `Record` JSON Storage Format

**Format:** A `Record` object is represented in JSON as follows:

```json
{
    "patient": {
        "name": "John Doe",
        "phone": "98765432",
        "email": "johnd@example.com",
        "gender": "MALE",
        "icNumber": "S2840182A",
        "birthday": "02/01/1998",
        "address": "311, Clementi Ave 2, #02-25",
        "tags": ["friend", "owesMoney"]
    },
    "initialObservations": "Patient complains of a persistent cough for 2 weeks.",
    "diagnosis": "Common cold, aggravated due to not taking enough rest.",
    "treatmentPlan": "Rest, Hydration, and prescribed cough syrup."
}
```

#### Explanation:

- **patient**: This field contains the details of the patient associated with the record. The format for a patient is as described in the earlier sections of this guide.
- **initialObservations**: A string that stores the initial observations made by the medical professional when the patient was examined.
- **diagnosis**: A string that details the medical diagnosis after thorough examination.
- **treatmentPlan**: The recommended treatment plan for the diagnosed ailment.

It's worth noting that the default values for `initialObservations`, `diagnosis`, and `treatmentPlan` are set to represent that no data was provided. This allows for the record to be initialized even if not all fields are populated initially.
