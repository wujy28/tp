---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# Advanced&Efficient (A&E) Developer Guide

<!-- * Table of Contents -->

## Table of Contents

* [**Overview**](#overview)
* [**Acknowledgements**](#acknowledgements)
* [**Setting up, getting started**](#setting-up-getting-started)
* [**Design**](#design)
  * [Architecture](#architecture)
  * [UI component](#ui-component)
  * [Logic component](#logic-component)
  * [Model component](#model-component)
  * [Storage component](#storage-component)
  * [Common classes](#common-classes)
* [**Implementation**](#implementation)
  * [Add patient feature](#add-patient-feature)
  * [Delete patient feature](#delete-patient-feature)
  * [Edit patient feature](#edit-patient-feature)
  * [View patient feature](#view-patient-feature)
  * [Edit record feature](#edit-record-feature)
  * [Assign department feature](#assign-department-feature)
  * [Sort feature](#sort-feature)
  * [Undo/redo feature](#undoredo-feature)
  * [Data archiving](#data-archiving)
* [**Documentation, logging, testing, configuration, dev-ops**](#documentation-logging-testing-configuration-dev-ops)
* [**Appendix: Requirements**](#appendix-requirements)
  * [Product scope](#product-scope)
  * [User stories](#user-stories)
  * [Use cases](#use-cases)
  * [Non-Functional Requirements](#non-functional-requirements)
  * [Glossary](#glossary)
* [**Appendix: Instructions for manual testing**](#appendix-instructions-for-manual-testing)
  * [Launch and shutdown](#launch-and-shutdown)
  * [Adding a patient](#adding-a-patient)
  * [Viewing a patient](#viewing-a-patient)
  * [Deleting a patient](#deleting-a-patient)
  * [Editing a patient](#editing-a-patient)
  * [Editing a patient record](#editing-a-patient-record)
  * [Assigning a patient to a department](#assigning-a-patient-to-a-department)
  * [Sorting patients](#sorting-patients)
  * [Undoing and Redoing a command](#undoing-and-redoing-a-command)
* [**Appendix: Planned Enhancements**](#appendix-planned-enhancements)


--------------------------------------------------------------------------------------------------------------------

## **Overview**

Advanced&Efficient helps Emergency Department (ED) doctors in logging patient reports and connecting patients with
relevant departments. It leverages on ED doctors fast typing skill by using the Command Line Interface (CLI).

This developer guide aims to guide future developers and maintainers of **Advanced&Efficient** by providing the
implementation philosophy of the software design and features.


--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

- Advanced&Efficient is adapted from the [AddressBook3](https://se-education.org/addressbook-level3/) project by SE-EDU
  initiative.

- Libraries used [JavaFx](https://openjfx.io/), [JUnit5](https://github.com/junit-team/junit5),
  [Jackson](https://github.com/FasterXML/jackson), [PlantUML](https://plantuml.com/).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="250" />

The ***Architecture Diagram*** given above explains the high-level design of Advanced&Efficient.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of
classes [`Main`](https://github.com/AY2324S1-CS2103T-T14-2/tp/blob/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2324S1-CS2103T-T14-2/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is
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
the command `delete i/T0012345A`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="270" />

The sections below give more details of each component.

<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2324S1-CS2103T-T14-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `PatientListPanel`, `RecordPanel`, `StatusBarFooter` etc. All these, including
the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the
visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2324S1-CS2103T-T14-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-T14-2/tp/blob/master/src/main/resources/view/MainWindow.fxml).

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Patient` or `Record` object residing in the `Model`.

<div style="page-break-after: always;"></div>

### Logic component

**API**: [`Logic.java`](https://github.com/AY2324S1-CS2103T-T14-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

For examples illustrating the interactions within the `Logic` component, please refer to the
[Implementation](#implementation) section for the implementation of various commands.

<box type="info" seamless>

**Note:** For the explanations below, we'll use the command `XYZCommand` and parser `XYZCommandParser`. `XYZ` is a
placeholder for the specific command name e.g., `AddCommandParser`.

</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates
   a parser that matches the command (e.g., `XYZCommandParser`) and uses it to parse the command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `XYZCommand`) which
   is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to perform the operation in `XYZCommand`).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` which uses the
  other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the
  `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

<div style="page-break-after: always;"></div>

### Model component

**API**: [`Model.java`](https://github.com/AY2324S1-CS2103T-T14-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Patient` objects (which are contained in a `UniquePatientList` object).
* stores the currently displayed `Patient` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Patient>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`,
which `Patient` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of
each `Patient` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>

<div style="page-break-after: always;"></div>

### Storage component
**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-T14-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,

* can save both address book data and user preference data in JSON format (e.g., `aande.json`), and read them back into corresponding
  objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`).

--------------------------------------------------------------------------------------------------------------------

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

--------------------------------------------------------------------------------------------------------------------

### Add patient feature

#### Implementation

The add patient operation is facilitated by `AddCommandParser`. `AddCommandParser` parses the user input string
and creates the `AddCommand` to be executed by the `LogicManager`. `AddCommand` extends `Command` and implements the
`Command#execute` method.

Given below is an example usage scenario and how the add patient operation is handled in A&E.

**Step 1.** Assuming the application has been launched, the user enters the required fields followed by any optional fields.

For adding **only** required fields, the user enters `add n/John Tan i/t7654321j`, which is to add
a patient with `NAME = John Tan` and `IC_NUMBER = t7654321j`.

For adding **additional** optional fields, the user enters `add n/John Tan i/t7654321j p/90909090`, which is to add the
optional field `Phone = 90909090`.

<box type="info" seamless>

**Note:** From Step 2 onwards, we will assume user enters `add n/John Tan i/t7654321j`.

</box>

**Step 2.** `LogicManager#execute` would first invoke `AddressBookParser#parseCommand` which splits the
command word `add` and the argument `n/John Tan i/t7654321j`. After splitting, `AddressBookParser#parseCommand` would
identify
that the command is `Add` and instantiate `AddCommandParser` and call its `AddCommandParser#parse` to parse the
argument accordingly.

**Step 3.** `AddCommandParser#parse` will call `AddCommandParser#createPatient` method to create the patient with the
relevant fields and fills all optional fields
with default values otherwise. It is then passed as an argument to instantiate the `AddCommand`, which is then returned
by
`AddCommandParser#parse`.

**Step 4.** `LogicManager#execute` now invokes `AddCommand#execute` which checks for duplicate `Patient`. `model#addPatient`
is then called to add the `Patient` into the address book. It then returns a `CommandResult` stating the patient has
been listed.

<puml src="diagrams/AddSequenceDiagram.puml" alt="AddSequenceDiagram" />

#### Design considerations:

**Aspect: How to handle required / optional inputs from users:**

* **Alternative 1 (current choice):** Create `Patient` with the required fields and fill non-given
  optional inputs with default values.
    * Pros: Easy to implement.
    * Cons: Since default values have to be valid as well, default values may seem too similar to actual value.
      Eg. Default value for `Gender` is `OTHER`, which is an actual value as well.

<br>

* **Alternative 2:** Use Java `Optional` class to handle fields.
    * Pros: Implementation is neater and fields with no values can be represented by a warning message instead
      of a default value.
    * Cons: Implementation is difficult and `Optional` class have to be implemented correctly to ensure it works
      with other components of the codebase.

<br>
<div style="page-break-after: always;"></div>

### Delete patient feature

#### Implementation
The delete mechanism is facilitated by `DeleteCommandParser`. `DeleteCommandParser` parses the user input
and creates the `DeleteCommand`. This `DeleteCommand` extends `Command` and is then executed by the `LogicManager`.
The `Command#execute` method is then called.

Given below is an example usage scenario and how the mechanism of deleting a patient is performed in Advanced&Efficient.

**Step 1.** Assuming the application has been launched and the records contain the details of the patient to be deleted,
the user enters the required field which is the `IC_NUMBER` of the patient intended to be deleted.

For instance, the user wishes to delete the patient whose IC Number is "S1234567A".
The user enters `delete i/S1234567A`, which is to delete the patient with the corresponding IC Number.

**Step 2.** `LogicManager#execute` would call `AddressBookParser#parseCommand` to parse the user input, splitting the
command word `delete` and the argument `i/S1234567A`. After which, the `AddressBookParser#parseCommand` is called, parsing
the command word and activating `DeleteCommandParser` to parse the argument
which is the IC number of the patient to be deleted.

**Step 3.** This `DeleteCommandParser#parse` method will then be called to parse the argument `i/S1234567A`
to create an `IcNumber` object. If the IC number is in an invalid format, a parse exception would be thrown,
giving the user the message on the right expected format of the IC number.
The `IcNumber` object created is then passed as an argument to instantiate the `DeleteCommand`.

**Step 4.** `LogicManager#execute` will now call `DeleteCommand#execute` to execute the command. The `DeleteCommand#execute`
will then call `model#getPatient` which retrieves the Patient with the matching IC Number as specified by the user.
This Patient object is then passed into the `model#deletePatient` method to delete the target Patient
from the AddressBook. It then returns a `CommandResult` stating the patient has
been deleted.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="DeleteSequenceDiagram" />

#### Design considerations:

**Aspect: How should users delete a patient from the patient list**

* **Alternative 1 (current choice):** Accept `IC_NUMBER` as the argument and use that to iterate through the patient
    list, search for the patient with the specified IC number and delete it.
    * Pros: Easy and user-friendly in extremely large databases.
    * Cons: Potential pitfalls to consider include methods to search for the IC Number which may return null.
      This may be dangerous for large scale systems, and as such, other exceptions have to be thrown in place of null
      as part of defensive programming.

<br>

* **Alternative 2:** Accept indices in place of IC numbers for deleting a patient from the list
    * Pros: Easier and neater implementation as out-of-bounds indices of patients
      are easier to handle compared to a null Patient object which may be introduced in Alternative 1.
    * Cons: Not as user-friendly in extremely large databases where it may be very inconvenient to
      look for the specific patient's index position in the list.

<br>
<div style="page-break-after: always;"></div>

### Edit patient feature

#### Implementation
The edit mechanism is facilitated by `EditCommandParser`. `EditCommandParser` parses the user input
and creates the `EditCommand`. This `EditCommand` extends `Command` and is then executed by the `LogicManager`.
The `Command#execute` method is then called.

Given below is an example usage scenario and how the mechanism of editing a patient is performed in Advanced&Efficient.

**Step 1.** Assuming the application has been launched and the records contain the details of the patient to be edited,
the user enters the required field which is the `IC_NUMBER` and other fields which are to be edited of the target patient.

For instance, the user wishes to edit the patient's name, whose IC Number is "T0000000A".
The user enters `edit i/T0000000A n/Jonathan Tan`, which is to
edit the name of the patient with the corresponding IC Number from John Doe to Jonathan Tan.

**Step 2.** `LogicManager#execute` would call `AddressBookParser#parseCommand` to parse the user input, splitting the
command word `edit` and the argument `i/T0000000A`. After which, the `AddressBookParser#parseCommand` is called, parsing
the command word and activating `EditCommandParser` to parse the argument
which is the IC number of the patient to be edited and other fields of the target patient which are to be edited.

**Step 3.** This `EditCommandParser#parse` method will then be called to parse the argument `i/T0000000A n/Jonathan Tan`
to create an `IcNumber` object. If the IcNumber is in an invalid format, a parse exception would be thrown,
giving the user the message on the right expected format of the IC Number.
In addition, `EditCommandParser#parse` will invoke `EditCommandParser#createEditPatientDescriptor` method to
create the edited descriptor of the patient.
The IcNumber object and the patient descriptor is then passed as an argument to instantiate the `EditCommand`.

**Step 4.** `LogicManager#execute` will now call `EditCommand#execute` to execute the command. The `EditCommand#execute`
will then call `model#getPatient` which retrieves the target `Patient` with the matching IC Number
as specified by the user, in this case the Patient being John Doe.
`EditCommand#execute` will also call `EditCommand#createEditedPatient` to create the edited `Patient` object based on
the patient descriptor created in Step 3, in this case being Jonathan Tan (as the name of the patient was edited).

**Step 5.** The edited `Patient` object (Jonathan Tan), along with the
target Patient to be edited (John Doe) from the AddressBook is then passed into the `model#setPatient` method.
It then returns a `CommandResult` stating that the patient has been edited.

<puml src="diagrams/EditSequenceDiagram.puml" alt="EditSequenceDiagram" />

#### Design considerations:

**Aspect: How should users edit a patient from the patient list**

**Note: Similar considerations as mentioned in the `Delete` feature above**

<br>
<div style="page-break-after: always;"></div>

### View patient feature

#### Implementation

The view patient operation is facilitated by `ViewCommandParser`. `ViewCommandParser` parses the user input string
and creates the `ViewCommand` to be executed by the `LogicManager`. `ViewCommand` extends `Command` and implements the
`Command#execute` method.

Given below is an example usage scenario and how the view patient operation is handled in A&E.

**Step 1.** Assuming the application has been launched, the user enters `view i/t1234567j`, which is to find
the specific patient with `IC_NUMBER = t1234567j`. This invokes `LogicManager#execute` to execute the logic of the
command.

**Step 2.** `LogicManager#execute` would first invoke `AddressBookParser#parseCommand` which splits the
command word `view` and the argument `i/t1234567j`. After splitting, `AddressBookParser#parseCommand` would identify
that the command is `View` and instantiate `ViewCommandParser` and call its `ViewCommandParser#parse` to parse the
argument accordingly.

**Step 3.** `PatientWithIcNumberPredicate`, which checks if a `Patient` has the `IC_NUMBER`, is created. It is
then passed as an argument along with `IC_NUMBER` to instantiate the `ViewCommand`, which is then returned by
`ViewCommandParser#parse`.

**Step 4.** `LogicManager#execute` now invokes `ViewCommand#execute` which calls `model#updateFilteredPatientList` with
`PatientWithIcNumberPredicate` as an argument. This will update the displayed filter list to the predicate.
It then returns a `CommandResult` stating the patient has been listed.

<puml src="diagrams/ViewSequenceDiagram.puml" alt="ViewSequenceDiagram" />

#### Design considerations:

**Aspect: How to display the specified patient:**

* **Alternative 1 (current choice):** Utilize current filteredPatientList display to
  display the patient.
    * Pros: Easy to implement.
    * Cons: Similar way in displaying of patient(s) may lead to confusion between commands.

<br>

* **Alternative 2:** Create a new set of JavaFx controls to display that patient differently
  from the current filteredPatientList display.
    * Pros: New display can be customisable for users' needs.
    * Cons: New display have to be implemented correctly to integrate with
      existing displays.

<br>
<div style="page-break-after: always;"></div>

### Edit record feature

#### Implementation

The edit record operation is facilitated by `RecordCommandParser`. `RecordCommandParser` parses the user input string
and creates the `RecordCommand` to be executed by the `LogicManager`. `RecordCommand` extends `Command` and implements
the `Command#execute` method.

Given below is an example usage scenario and how the edit record operation is handled in A&E.

**Step 1.** Assuming the application has been launched, the user enters `record i/T0201234A o/Broken Arm di/Hairline
fracture tp/Cast for 2 days`, which is to edit the record of the specific patient with `IC_NUMBER = T0201234A` such that
`Initial Observations = Broken Arm`, `Diagnosis = Hairline fracture`, and `Treatment Plan = Cast for 2 days`. This
invokes `LogicManager#execute` to execute the logic of the command.

**Step 2.** `LogicManager#execute` would first invoke `AddressBookParser#parseCommand` which splits the command word
`record` and the arguments `i/T0201234A`, `o/Broken Arm`, `di/Hairline fracture`, and `tp/Cast for 2 days`. After
splitting, `AddressBookParser#parseCommand` would identify that the command is `Record` and instantiate
`RecordCommandParser` and call its `RecordCommandParser#parse` to parse the arguments accordingly.

**Step 3.** `RecordCommandParser#parse` would instantiate `EditRecordDescriptor` from `RecordCommand` and map the
`IC_NUMBER` prefix to its argument `T0201234A`, the `INITIAL_OBSERVATIONS` prefix to its argument `Broken Arm`, the
`DIAGNOSIS` prefix to its argument `Hairline fracture`, and the `TREATMENT_PLAN` prefix to its argument
`Cast for 2 days` using `ArgumentMultimap`. The `ArgumentMultimap` would then be used to identify the `IC_NUMBER` and a
`ParseException` is thrown if command inputs are invalid. The `ArgumentMultimap` also invokes
`ArgumentMultimap#isPresent` to check if the other prefixes for `INITIAL_OBSERVATIONS`, `DIAGNOSIS` and `TREATMENT_PLAN`
are present. If `true` is returned, the arguments will be passed into the `EditRecordDescriptor` object.

The following activity diagram roughly demonstrates how `RecordCommand#parse` works.

<puml src="diagrams/RecordCommandParserActivityDiagram.puml" alt="RecordCommandParserActivityDiagram" height="800"/>

**Step 4.** The `EditRecordDescriptor` object calls `EditRecordDescriptor#isAnyFieldEdited`, which checks if any of the
fields of Record has been edited, and throws a `ParseException` if `false` is returned. It is then passed as an argument
along with `IC_NUMBER` to instantiate the `RecordCommand`, which is then returned by `RecordCommandParser#parse`.

**Step 5.** `LogicManager#execute` now invokes `RecordCommand#execute` which gets the specified Patient and their Record
according to the `IC_NUMBER`. Then, `RecordCommand#createEditedRecord` is called with the specified Patient's `Record`
and the `EditRecordDescriptor` object which contains the values of fields to be edited. Record fields to be edited are
replaced by values stored in the `EditRecordDescriptor`. Meanwhile, fields that are not to be edited will keep their
current values. This is facilitated through the `orElse` method of the `Optional` class. Then, `RecordCommand#execute`
calls `model#updateFilteredPatientList` to update the displayed filter list to show all patients. Finally,
`RecordCommand#execute` returns a `CommandResult` stating the patient `IC_NUMBER` and edited Record details.

The following sequence diagram summarizes the above-mentioned steps.

<puml src="diagrams/RecordSequenceDiagram.puml" alt="RecordSequenceDiagram" />

#### Design considerations:

**Aspect: How to edit the different fields of Record:**

* **Alternative 1 (current choice):** Create `EditRecordDescriptor` nested class to store the details to edit the
    patient record with.
    * Pros: Ensures that previous inputs are kept if user does not edit a specific field.
    * Cons: Implementation would be a little more troublesome.

<br>

* **Alternative 2:** Directly use `Patient` constructor with user input and replace non-given fields with default
    values.
    * Pros: Easy to implement.
    * Cons: This would overwrite all fields in the previous `Record` even if user did not specify to edit a certain field
      in their input.

<br>
<div style="page-break-after: always;"></div>

### Assign department feature

#### AssignedDepartment attribute

The `AssignedDepartment` attribute of a patient in A&E is represented by a stored `Department` value. `Department` is
an enumeration that encapsulates all the predefined hospital department values stored by the system and available
to the user. The list of valid departments can be found in the
[appendix](https://ay2324s1-cs2103t-t14-2.github.io/tp/UserGuide.html#appendix-departments) of the User Guide.

#### Design considerations:

**Aspect: How to represent a department in the system:**

* **Alternative 1 (current choice):** Use Java Enumerations.
    * Pros: Ensures type safety. Discrete constants allow for usage in switch-cases, and thus can potentially be used
      to easily categorize patients in future features.
    * Cons: Does not support user-defined categories or departments.

<br>

* **Alternative 2:** Using an abstract Department class and inheritance.
    * Pros: Can be made to support user-defined departments. Can specify different behavior for different
      types of departments.
    * Cons: Implementation is more complicated when it comes to storing and keeping track of all the different
      subclasses or limiting valid department values.

<br>

* **Alternative 3:** Using Strings.
    * Pros: Very easy to implement.
    * Cons: Values are not exhaustive. Does not support unique behavior for each type of department.

#### Implementation of `assign`

The assign department operation is facilitated by the `AssignCommand` and `AssignCommandParser` classes, similar
to other commands mentioned above. `AssignCommand` extends `Command` and overrides `Command#execute` to perform
its intended behavior, invoked by the `LogicManager` class. `AssignCommandParser` is responsible for parsing the
string of arguments containing an `IC_NUMBER` and `Department` inputted by the user, to create an `AssignCommand` object.

The following sequence diagrams summarize what happens when `AssignCommand#execute` is invoked.

<puml src="diagrams/AssignSequenceDiagram.puml" alt="AssignSequenceDiagram" />

<puml src="diagrams/AssignSequenceDiagramParserUtil.puml" alt="AssignSequenceDiagramParserUtil" />

<br>
<div style="page-break-after: always;"></div>

### Sort feature
The sort operation is facilitated by the `SortCommand` and `SortCommandParser` classes, similar
to other commands mentioned above. `SortCommand` extends `Command` and overrides `Command#execute` to perform
its intended behavior, invoked by the `LogicManager` class. `SortCommandParser` is responsible for parsing the
argument string containing a property inputted by the user, to create a `SortCommand` object.

Currently, the sort operation sorts the entire patient list, even if the command is executed when the displayed list
is filtered (e.g. using the `find` command). This choice will be further elaborated on below.

#### Design considerations:

**Aspect: Behavior of sort operation:**

* **Alternative 1 (current choice):** Sort the entire patient list.
    * Pros: Easy and quick to implement as it only involves calling Java's `sort` method on the underlying
  `UniquePatientList` stored in `AddressBook`.
    * Cons: Behavior might be unintuitive as when the user runs this command, they might expect this command to only
  affect the currently displayed list, which might be filtered.

<br>

* **Alternative 2:** Only sort the currently displayed, or filtered, list.
    * Pros: Allows the user to only alter the visible list, and keep the underlying patient list untouched.
  Behavior is possibly more intuitive.
    * Cons: Implementation is more complicated as the `FilteredList` that stores the currently displayed list and is
  referenced by the UI to display its contents cannot be modified.

#### Sort order
`SortOrder` is an enumeration within `SortCommand` representing the properties by which the user can sort the list.
As of now, the only `SortOrder`s available are the following:
1. Name (alphanumerically ascending, case-insensitive)
2. IC Number (alphanumerically ascending, case-insensitive)
3. Department (by ordinal of constant in `Department` enumeration)
4. Age (numerically ascending, with default age on top)
5. Priority (descending)

Each `SortOrder` constant value stores a `Comparator<? super Patient>` that is used by Java `List`'s build-in `sort`
method to compare two `Patient` objects during the execution of the `sort` command. These comparators make use of the
overridden `compareTo` methods in the related patient attribute classes (i.e. `Name`, `IcNumber`, `AssignedDepartment`,
`Age` and `Priority`). Each `SortCommand` object stores a `SortOrder` value, extracted from the input string by the
`SortCommandParser` object that created it.

#### Sequence diagram of `SortCommand#execute`
As the creation of the `SortCommand` object is very similar to that of `AssignCommand` as shown above, except that
it parses the property and uses `SortOrder#getSortOrder(String string)` to retrieve the `SortOrder` value, the sequence
diagram below will only show the execution of the `SortCommand#execute` method to illustrate how the sort feature
works.

<puml src="diagrams/SortSequenceDiagram.puml" alt="SortSequenceDiagram" />

<br>
<div style="page-break-after: always;"></div>

### Undo/redo feature

#### Implementation

The undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()`— Saves the current address book state in its history.
* `VersionedAddressBook#undo()`— Restores the previous address book state from its history.
* `VersionedAddressBook#redo()`— Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

**Step 1.** The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

**Step 2.** The user executes `delete i/S1234567A` command to delete the patient with IC number "S1234567A" from the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete i/S1234567A` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

**Step 3.** The user executes `add n/David …​` to add a new patient. The `add` command also
calls `Model#commitAddressBook()`, causing another modified address book state to be saved into
the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will
not be saved into the `addressBookStateList`.

</box>

**Step 4.** The user now decides that adding the patient was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no
previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the
case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagrams summarise how the undo and redo operations
work respectively when `UndoCommand#execute` or `RedoCommand#execute` is invoked:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<puml src="diagrams/RedoSequenceDiagram.puml" alt="RedoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` and `RedoCommand`should end at the destroy marker (X) but due to a limitation of PlantUML, the
lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once
to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address
book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()`
to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

**Step 5.** The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

**Step 6.** The user then decides to execute another command, `record`. Commands such as editing patient record
do not use an inherent method in Model which would modify the state of the addressBook.
For instance, simple commands like `edit` and `add` call methods in Model like
`Model#setPatient` and `Model#addPatient` respectively, modifying the state of the AddressBook
and committing that state using `Model#commitAddressBook`. Hence, the `undo` and `redo` commands only work for operations
that both modify the addressBook and commit its state.

**Step 7.** The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
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

<br>

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the patient being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

<br>
<div style="page-break-after: always;"></div>

### Data archiving

The data archiving feature in Advanced&Efficient is designed to ensure that historical patient records are maintained
in a safe, accessible, and organized manner. This feature is crucial for historical reference and data analysis.

#### Implementation Strategy
1. **Archive Format**: Patient records will be archived in a compressed JSON format to save space and maintain data confidentiality.
2. **Trigger and Storage**: Archiving will be triggered automatically. The system will automatically update the JSON file (`AandE.json`) when user modify any patient or record.

#### Example of archived file

An archived file (`AandE.json`) is represented in JSON as follows:

```json
{
  "patients" : [ {
    "name" : "John Doe",
    "phone" : "00000000",
    "email" : "default_email@gmail.com",
    "gender" : "MALE",
    "icNumber" : "T0123456A",
    "birthday" : "01/01/1990",
    "address" : "No address was added",
    "priority" : "NIL",
    "assignedDepartment" : "Default",
    "tags" : [ ],
    "initialObservations" : "No initial observations given",
    "diagnosis" : "No diagnosis given",
    "treatmentPlan" : "No treatment plan given"
  }]
}
```

- **patient**: This field contains the details of the patient associated with the record. The format for a patient is as
  described in the earlier sections of this guide.
- **initialObservations**: A string that stores the initial observations made by the medical professional when the
  patient was examined.
- **diagnosis**: A string that details the medical diagnosis after thorough examination.
- **treatmentPlan**: The recommended treatment plan for the diagnosed ailment.

It's worth noting that the default values for `initialObservations`, `diagnosis`, and `treatmentPlan` are set to
represent that no data was provided. This allows for the record to be initialized even if not all fields are populated
initially.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

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

<br>

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
| `* *`    | ED Doctor | Redo my latest action                                                                              | Quickly redo any change I may have wrongly undone                                                                                                     |
| `* *`    | ED Doctor | Tag patients                                                                                       | Better organize patients into categories so as to better keep track of them                                                                           |
| `* *`    | ED Doctor | Assign the priority to the patient with respect to the severity of the case                        | Keep track of the more critical cases                                                                                                                 |
| `* *`    | ED Doctor | Find patients from the list that match a given property (e.g. `NAME`, `IC_NUMBER`, `PRIORITY`)     | Quickly narrow down the list to view the patients of my interest                                                                                      |
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
| `*`      | ED Doctor | Filter a list of doctors according to their department                                             | Easily identify doctors to assign to from a specific department                                                                                       |
| `*`      | ED Doctor | View the list of doctors                                                                           | Know who I can refer my patients for further treatment to                                                                                             |
| `*`      | ED Doctor | View a list of commands                                                                            | Know the commands needed to carry out my action                                                                                                       |

<br>

### Use cases

(For all use cases below, the **System** is the `Advanced&Efficient`application and the **Actor** is the `ED doctor`,
unless specified otherwise)

\
**Use case: UC01 - View list of patients**

**MSS**

1. ED doctor requests to view list of all patients.
2. Advanced&Efficient shows a list of all patients.

   Use case ends.

\
**Use case: UC02 - Add a patient**

**Guarantees**

- Patient added will not have same name or IC number as any existing patients.

**MSS**

1. ED doctor requests to add a new patient with given details.
2. Advanced&Efficient adds the patient to the system.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that not all required details are given.
    * 1a1. Advanced&Efficient shows an error message saying that not all required details are given.
    * 1a2. Advanced&Efficient requests for all the required details.
    * 1a3. ED doctor enters the required details.

      Steps 1a1-1a3 are repeated until all the required details are given.

      Use case resumes from step 2.

* 1b. Advanced&Efficient detects that the given details are invalid.
    * 1b1. Advanced&Efficient shows an error message saying the given details are invalid.
    * 1b2. Advanced&Efficient requests for valid details.
    * 1b3. ED doctor enters the requested details.

      Steps 1b1-1b3 are repeated until the details are valid.

      Use case resumes from step 2.

* 1c. Advanced&Efficient detects that the given patient is already in the system.
    * 1c1. Advanced&Efficient shows an error message saying patient already exist.

      Use case ends.

\
**Use case: UC03 - View a patient's details**

**MSS**

1. ED doctor requests to view a patient’s details by their IC number.
2. Advanced&Efficient shows the patient’s details.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the given IC number is invalid.
    * 1a1. Advanced&Efficient shows an error message saying the given IC number is invalid.
    * 1a2. Advanced&Efficient requests for valid IC number.
    * 1a3. ED doctor enters the IC number.

      Steps 1a1-1a3 are repeated until the IC number is valid.

      Use case resumes from step 2.

* 1b. Advanced&Efficient detects that the specified patient does not exist.
    * 1b1. Advanced&Efficient shows message saying the patient does not exist.

      Use case ends.

\
**Use case: UC04 - Edit a patient's details**

**MSS**

1. ED doctor requests to edit a patient’s details by their IC number.
2. Advanced&Efficient edits the patient’s details as specified.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the given IC number is invalid.
    * 1a1. Advanced&Efficient shows an error message saying the given IC number is invalid.
    * 1a2. Advanced&Efficient requests for valid IC number.
    * 1a3. ED doctor enters the IC number.

      Steps 1a1-1a3 are repeated until the IC number is valid.

      Use case resumes from step 2.

* 1b. Advanced&Efficient detects that the specified patient does not exist.
    * 1b1. Advanced&Efficient shows message saying the patient does not exist.

      Use case ends.

* 1c. Advanced&Efficient detects that the given details are invalid.
    * 1c1. Advanced&Efficient shows an error message saying the given details are invalid.
    * 1c2. Advanced&Efficient requests for valid details.
    * 1c3. ED doctor enters the requested details.

      Steps 1c1-1c3 are repeated until the details are valid.

      Use case resumes from step 2.

\
**Use case: UC05 - Delete a patient**

**MSS**

1. ED doctor requests to delete a patient by their IC number.
2. Advanced&Efficient deletes the patient from the system.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the given IC number is invalid.
    * 1a1. Advanced&Efficient shows an error message saying the given IC number is invalid.
    * 1a2. Advanced&Efficient requests for valid IC number.
    * 1a3. ED doctor enters the IC number.

      Steps 1a1-1a3 are repeated until the IC number is valid.

      Use case resumes from step 2.

* 1b. Advanced&Efficient detects that the specified patient does not exist.
    * 1b1. Advanced&Efficient shows message saying the patient does not exist.

      Use case ends.

\
**Use case: UC06 - Find patient(s) by name**

**MSS**

1. ED doctor requests to find patient(s) by name.
2. Advanced&Efficient shows a filtered list of matching patient(s).

   Use case ends.

\
**Use case: UC07 - Sort a patient list**

**MSS**

1. ED doctor requests to sort a patient list by a property.
2. Advanced&Efficient shows the list of sorted patients.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the given property is invalid.
    * 1a1. Advanced&Efficient shows an error message saying the given property is invalid.
    * 1a2. Advanced&Efficient requests for valid property.
    * 1a3. ED doctor enters the property.

      Steps 1a1-1a3 are repeated until the property is valid.

      Use case resumes from step 2.

\
**Use case: UC08 - Edit a patient's record**

**MSS**

1. ED doctor requests to edit a patient’s record by their IC number.
2. Advanced&Efficient edits the patient’s record as specified.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the given IC number is invalid.
    * 1a1. Advanced&Efficient shows an error message saying the given IC number is invalid.
    * 1a2. Advanced&Efficient requests for valid IC number.
    * 1a3. ED doctor enters the IC number.

      Steps 1a1-1a3 are repeated until the IC number is valid.

      Use case resumes from step 2.

* 1b. Advanced&Efficient detects that the specified patient does not exist.
    * 1b1. Advanced&Efficient shows message saying the patient does not exist.

      Use case ends.

* 1c. Advanced&Efficient detects that the given record details are invalid.
    * 1c1. Advanced&Efficient shows an error message saying the given record details are invalid.
    * 1c2. Advanced&Efficient requests for valid record details.
    * 1c3. ED doctor enters the record details.

      Steps 1c1-1c3 are repeated until the record details are valid.

      Use case resumes from step 2.

\
**Use case: UC09 - Assign a patient to department**

**MSS**

1. ED doctor requests to assign a patient to a department by their IC number.
2. Advanced&Efficient assigns the patient to the department.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that the given IC number is invalid.
    * 1a1. Advanced&Efficient shows an error message saying the given IC number is invalid.
    * 1a2. Advanced&Efficient requests for valid IC number.
    * 1a3. ED doctor enters the IC number.

      Steps 1a1-1a3 are repeated until the IC number is valid.

      Use case resumes from step 2.

* 1b. Advanced&Efficient detects that the specified patient does not exist.
    * 1b1. Advanced&Efficient shows message saying the patient does not exist.

      Use case ends.

* 1c. Advanced&Efficient detects that the given department is invalid.
    * 1c1. Advanced&Efficient shows an error message saying the given department is invalid.
    * 1c2. Advanced&Efficient requests for valid department.
    * 1c3. ED doctor enters the department.

      Steps 1c1-1c3 are repeated until the department is valid.

      Use case resumes from step 2.

\
**Use case: UC10 - Undoing a command**

**MSS**

1. ED doctor requests to undo the previous command.
2. Advanced&Efficient undos the previous command.

   Use case ends.

**Extensions**

* 1a. Advanced&Efficient detects that there are no previous commands to undo.
    * 1a1. Advanced&Efficient shows an error message saying that there are no previous commands to undo.

      Use case ends.

<br>

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 patients without a noticeable sluggishness in performance for typical usage.
3. An ED doctor with above average typing speed for regular English text should be able to accomplish most of the tasks
   faster using commands than using the mouse.
4. Should not lose any data if the application is closed by any other means besides the `Exit` command.
5. The response to any user action should be visible within 2 seconds.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Department**: A sector of the hospital responsible for a type of healthcare treatment.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
Testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    2. Double-click the jar file.<br>
       Expected: Shows the GUI with a set of sample contacts. The window size may not be optimal.

2. Saving window preferences

    1. Resize the window to an optimal size. Move the window to a different location. Close the window.

    2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a patient

1. Adding a patient with only required prefixes

   1. Prerequisites: None of existing patients have either name `You Wen Ti` and/or IC number `T0374628Z`. (else you may
      replace the name and/or IC number specified in the test cases)

   2. Test case: `add n/You Wen Ti i/T0374628Z`<br>
      Expected: Patient with name `You Wen Ti` and IC number `T0374628Z` is added. Details of the added patient is shown
      in the status message. UI is updated to display newly added patient.

   3. Test case: `add n/Wo Meiyou`<br>
      Expected: No patient is added. Error details shown in the status message. Patient list remains the same.

   4. Other incorrect add commands to try: `add`, `add Wo Meiyou T0365867F`<br>
      Expected: Similar to the previous case.

2. Adding a patient with both required and optional prefixes

   1. Prerequisites: None of existing patients have either name `Ingot Gold` or IC number `T0482756J`. (else you may
      replace the name and/or IC number specified in the test cases)

   2. Test case: `add n/Ingot Gold i/T0482756J g/MALE b/20/05/2004 p/87654487 e/goldie@email.com a/Old Town Road 1
      pr/HIGH t/critical`<br>
      Expected: Patient with name `Ingot Gold`, IC number `T0482756J`, gender `MALE`, birthday `20/05/2004`, phone
      number `87654487`, email `goldie@email.com`, address `Old Town Road 1`, priority `HIGH`, and tag `critical` is
      added. Details of the added patient is shown in the status message. UI is updated to display newly added patient.

   3. Test case: `add n/Betty Crocker i/T0826789S a/Downtown East a/Lowcoast West`<br>
      Expected: No patient is added. Error details shown in the status message. Patient list remains the same.

   4. Other incorrect add commands to try: `add`, `add n/Beckham Low pr/high`<br>
      Expected: Similar to the previous case.

### Viewing a patient

1. Viewing a patient

   1. Prerequisites: Have our sample patient list loaded OR add a patient with IC number `T7654321A`.

   2. Test case: `view i/T7654321A`<br>
      Expected: Patient with IC number `T7654321A` will be displayed in patient list. Details of success of command is
      shown in the status message. Patient list is updated in UI.

   3. Test case: `view I/T7654321A`<br>
      Expected: No patient is viewed. Error details shown in the status message. Patient list remains the same.

   4. Other incorrect view commands to try: `view`, `view i/T8374829X`(where a patient with ic number `T8374829X` does
      not exist)<br>
      Expected: Similar to the previous case.

### Deleting a patient

1. Deleting a patient

    1. Prerequisites: Have our sample patient list loaded OR add a patient with IC number `T7654321A`.

    2. Test case: `delete i/T7654321A`<br>
       Expected: Patient with IC number `T7654321A` is deleted. Details of the deleted patient is shown in the status
       message. Patient list is updated in UI.

    3. Test case: `delete I/T7654321A`<br>
       Expected: No patient is deleted. Error details shown in the status message. Patient list remains the same.

    4. Other incorrect delete commands to try: `delete`, `delete i/T9384758D` (where a patient with IC number
       `T9384758D` does not exist)<br>
       Expected: Similar to the previous case.

### Editing a patient

1. Editing a patient's details

   1. Prerequisites: Have our sample patient list loaded OR add a patient with IC number `T7654321A`.

   2. Test case: `edit i/T7654321A n/John Doe`<br>
      Expected: Patient with IC number `T7654321A` will have their name edited to `John Doe`. Details of the edited
      patient is shown in the status message. UI is updated to display patient's new details.

   3. Test case: `edit i/T7654321A`<br>
      Expected: No patient is edited. Error details shown in the status message. Patient's details remains the same.

   4. Other incorrect edit commands to try: `edit`, `edit i/T0264782A n/Mary Jane` (where a patient with IC number
      `T0264782A` does not exist)<br>
      Expected: Similar to the previous case.

### Editing a patient record

1. Editing a patient's record

   1. Prerequisites: Have our sample patient list loaded OR add a patient with IC number `T7654321A`.

   2. Test case: `record i/T7654321A o/Broken Arm di/Hairline fracture tp/Cast for 2 days`<br>
      Expected: Record of the patient with IC number `T7654321A` is edited to have `Broken Arm` as initial observation,
      `Hairline fracture` as diagnosis, and `Cast for 2 days` as treatment plan. Details of the edited record is shown
      in the status message. Patient's record is updated in UI.

   3. Test case: `record i/T7654321A`<br>
      Expected: No patient record is edited. Error details shown in the status message. Patient's record remains the
      same.

   4. Other incorrect delete commands to try: `record`, `record i/T7654321A o/Broken Pinky o/Dizziness`,
      `record i/T2736487A di/Asthma` (where patient with IC number `T2736487A` does not exist)<br>
      Expected: Similar to the previous case.

### Assigning a patient to a department

1. Assigning a patient to a department

    1. Prerequisites: Have our sample patient list loaded OR add a patient with IC number `T7654321A`.

    2. Test case: `assign i/T7654321A d/cardiology`<br>
       Expected: Patient IC number `T7654321A` is assigned to department `Cardiology`. Details of the assigned
       department is shown in the status message. Patient's assigned department is updated in UI.

    3. Test case: `assign i/T7654321A`<br>
       Expected: Patient is not assigned a department. Error details shown in the status message. Patient's assigned
       department remains the same.

    4. Other incorrect assign commands to try: `assign`, `assign i/T7654321A d/Cardio` (department is not fully spelt),
       `assign i/T7654321A d/Anesthesiology` (department follows American spelling)<br>
       Expected: Similar to the previous case.

### Sorting patients

1. Sorting patients by name

   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list.

   2. Test case: `sort name`<br>
      Expected: Patient list is sorted according to `name` in alphanumeric order. Details of success of command is
      shown in the status message. Order of patients in list is updated in UI.

   3. Test case: `sort`<br>
      Expected: Patient list is not sorted. Error details shown in the status message. Patient list remains the same.

2. Sorting patients by IC number

   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list.

   2. Test case: `sort ic`<br>
      Expected: Patient list is sorted according to `IC number`, in alphanumeric order. Details of success of command
      is shown in the status message. Order of patients in list is updated in UI.

3. Sorting patients by assigned department

   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list. At least 2 different
      assigned departments among all patients in the list.

   2. Test case: `sort department`<br>
      Expected: Patient list is sorted according to `assigned department`, where patients with default departments are
      placed at the bottom. Details of success of command is shown in the status message. Order of patients in list is
      updated in UI.

4. Sorting patients by age

   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list. At least 2 different
      ages (i.e. birth years in birthdays) among all patients in the list.

   2. Test case: `sort age`<br>
      Expected: Patient list is sorted according to `age`, where patients with default birthdays/ages are placed on top,
      followed by the remaining ages in increasing order. Details of success of command is shown in the status message.
      Order of patients in list is updated in UI.

5. Sorting patients by priority

   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list. At least 2 different
      priorities among all patients in the list.

   2. Test case: `sort priority`<br>
      Expected: Patient list is sorted according to descending `priority`. Details of success of command is shown in the
      status message. Order of patients in list is updated in UI.

### Undoing and Redoing a command

1. Undoing and Redoing add command

   1. Prerequisites: Have no patient with name `Shen Qi Feng` and/or IC number `S0473859D` in the patient list. (else
      you may replace the name and/or IC number specified in the test cases)

   2. Test case: `add n/Shen Qi Feng i/S0473859D` then `undo`<br>
      Expected: Patient with name `Shen Qi Feng` and IC number `S0473859D` is added, then removed. Details of success of
      command is shown in the status message. Patient list remains the same in UI after both commands are executed.

   3. Test case: `redo` (after executing the previous test case)<br>
      Expected: Patient with name `Shen Qi Feng` and IC number `S0473859D` is added back. Details of success of command
      is shown in the status message. Patient list is updated in UI.

2. Undoing and Redoing edit command

   1. Prerequisites: Have our sample patient list loaded OR add a patient with IC number `T7654321A`. Patient to be
      edited should have a different address from `Dummy Address Test Street 1`.

   2. Test case: `edit i/T7654321A a/Dummy Address Test Street 1` then `undo`
      Expected: Address of patient with IC number `T7654321A` is changed to `Dummy Address Test Street 1`, then changed
      back to initial address. Details of success of command is shown in the status message. Patient list remains the
      same in UI after both commands are executed.

   3. Test case: `redo` (after executing the previous test case)<br>
      Expected: Address of patient with IC number `T7654321A` is changed back to `Dummy Address Test Street 1`. Details
      of success of command is shown in the status message. Patient's details is updated in UI.

3. Undoing and Redoing delete command

   1. Prerequisites: Have our sample patient list loaded OR add a patient with IC number `T6789031Q`.

   2. Test case: `delete i/T6789031Q` then `undo`<br>
      Expected: Patient with IC number `T6789031Q` is deleted, then added back. Details of success of command is shown
      in the status message. Patient list remains the same in UI after both commands are executed.

   3. Test case: `redo` (after executing the previous test case)<br>
      Expected: Patient with IC number `T6789031Q` is deleted again. Details of success of command is shown in the
      status message. Patient list is updated in UI.

4. Undoing and Redoing assign command

   1. Prerequisites: Have our sample patient list loaded OR add a patient with IC number `T7654321A`. Assigned
      department of patient with IC number `T7654321A` should not be `Endocrinology`.

   2. Test case: `assign i/T7654321A d/Endocrinology` then `undo`<br>
      Expected: Patient with IC number `T7654321A` is assigned to department `Endocrinology`, then assigned back to
      initial department. Details of success of command is shown in the status message. Patient list remains the same in
      UI after both commands are executed.

   3. Test case: `redo` (after executing the previous test case)<br>
      Expected: Patient with IC number `T7654321A` is assigned back to department `Endocrinology`. Details of success of
      command is shown in the status message. Patient's assigned department is updated in UI.

5. Undoing and Redoing clear command

    1. Prerequisites: Have at least one patient in the system.

    2. Test case: `clear` then `undo`<br>
       Expected: Patient list is cleared, then restored. Details of success of command is shown in the status message.
       Patient list remains the same in UI after both commands are executed.

    3. Test case: `redo` (after executing the previous test case)<br>
       Expected: Patient list is cleared again. Details of success of command is shown in the status message. UI is
       updated to display an empty patient list.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Planned Enhancements**

1. Currently, when `View` command is executed, it only shows that `Patient` in the display panel instead of their
   details and record in the record panel. Users would have to click that `Patient` again to display their record.
   Since `IC_NUMBER` is unique for every `Patient`, we plan to show the `Patient` details and record
   directly in the information tab.

2. Currently, the `undo` and `redo` command do not work as expected for `record` command
   which is meant for editing patient record. `record` command modifies the state of the AddressBook.
   However, it does not commit the modified state of the AddressBook. As such, this new state of the AddressBook is not
   stored in the addressBookStateList which contains all the states of the addressBook. Hence, modifications will be made
   to commands like `record` to ensure all modified states of the AddressBook are committed and stored properly. Hence,
   `undo` and `redo` will work on any operation which modifies the AddressBook.

3. Since our project is adapted from the [AddressBook3](https://se-education.org/addressbook-level3/) project
   by SE-EDU initiative, there are multiple usage of `AddressBook` terms in our namings of methods and files. We plan
   to completely refactor all instances of `AddressBook` into `PatientRecordSystem`. For example,
   `AddressBookParser.java` would be renamed to `PatientRecordSystemParser.java`.

4. Currently, age is not displayed in a patient's record card in the record panel, but only the patient card in the
   patient list panel in the UI. This is inconsistent and might make it inconvenient for users to have to look back to
   the patient card for this crucial piece of patient information. We plan to include it as a label in every patient's
   record card subsequently.

5. Invalid parameters/prefixes currently cannot be detected properly by the program. For example, if the user inputs
   `add n/John Doe i/T0123456S bt/O+` to try to add a patient with name "John Doe", IC number "T0123456S" and blood type
   "O+", the program will display the error message "IC Number should start and end with an alphabet with non negative
   numbers in between" instead of indicating that the prefix `bt/` does not exist. We plan to modify parsing-related
   classes (e.g. `ArgumentTokenizer`) so that extraneous or invalid prefixes can be detected in the future.

6. Invalid dates can currently be added as birthdays for patients (e.g. 30 February). This is an issue trickled down
   from Java's `LocalDate`, which does not appear to have built-in checks for invalid dates like these. We plan on
   enhancing our `Birthday` class by manually adding checks in `isValidBirthday` to detect invalid dates.

7. Currently, any alphabet is accepted for the start of an IC number.
   Ideally, only letters "S", "T", "F", "G" or "M" are accepted for the start of an IC number. Therefore, we plan on
   enhancing our `IcNumber` class by manually adding checks to ensure the first alphabet of an IC number is valid.

8. Currently, a patient having too many tags or tags that are too long will cause their tags in the UI to overflow and
   overlap with other UI elements, causing their patient card in the patient list to be unreadable. Therefore, we plan
   on adjusting the UI component containing these tags so that tags are only allowed to take up a certain amount of
   space in the UI and are truncated otherwise.

9. Currently, the `Record` command cannot be undone, which might be unintuitive to users as it updates the field of a
   patient's record, and users will find it helpful to be able to undo a command like this. We plan on modifying the
   `RecordCommand` class to support the `undo` behavior in the future.
