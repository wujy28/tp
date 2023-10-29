---
layout: default.md
title: "User Guide"
pageNav: 3
---

# Advanced&Efficient (A&E) User Guide

Advanced&Efficient (A&E) is a **Command-Line Interface (CLI)** focussed app that helps Emergency Department (ED) doctors log
patient reports and connect them to the relevant departments. It is suited for users who type fast and prefer typing
over other modes of inputs.

<!-- * Table of Contents -->

- [Quick-start](#quick-start)
- [Features](#features)
    - [Viewing help : `help`](#viewing-help--help)
    - [Listing all patients: `list`](#listing-all-patients-list)
    - [Adding a patient: `add`](#adding-a-patient-add)
    - [Deleting a patient: `delete`](#deleting-a-patient-edit)
    - [Editing a patient: `edit`](#editing-a-patient-delete)
    - [Viewing a patient record : `view`](#viewing-a-patient-record-view)
    - [Editing a patient record : `record`](#editing-a-patient-record-record)
    - [Assigning a patient to a department : `assign`](#assigning-a-patient-to-a-department--assign)
    - [Exiting the program : `exit`](#exiting-the-program--exit)
- [FAQ](#faq)
- [Known Issues](#known-issues)
- [Command summary](#command-summary)

<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

### [coming soon]

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/ug/helpMessage.png)

Format: `help`

### Listing all patients: `list`

Lists all patients in the system.

Format: `list`

![UG_listPatients.png](images/ug/listPatients.png)

### Adding a patient: `add`

Adds a patient

Format: `add n/NAME i/IC_NUMBER g/GENDER b/BIRTHDAY p/PHONE_NUMBER e/EMAIL a/ADDRESS`

+ Only `NAME` and `IC_NUMBER` fields are **compulsory**.
+ **All** input fields are **case-insensitive** e.g. `T1234567A` is the same as `t1234567a`.
+ The order of the input fields does not matter.

Examples:
+ To add a Patient with `NAME` = John Doe, `IC_Number` = T0123456A, `GENDER` = Male, `BIRTHDAY` = 01/01/1990,

`add n/John Doe i/T0123456A g/Male b/01/01/1990 `


* To add a Patient with Name = Betsy Crowe, IC_Number = S0123456B, PHONE = 90909090, EMAIL = bc@gmail.com

`add n/Betsy Crowe i/S0123456B p/90909090 e/bc@gmail.com`


### Viewing a patient : `view`

Displays a specific patient’s information and medical record.

Format: `view i/IC_NUMBER`

* `IC_NUMBER` field is **compulsory**.
* `IC_NUMBER` field is **case-insensitive** e.g. `T1234567A` is the same as `t1234567a`.

Examples:

+ To view patient with IC_Number = T0123456A

`view i/T0201234A`


### Editing a patient: `edit`

Edits the attributes of a patient

Format: `edit IC_NUMBER g/GENDER b/BIRTHDAY p/PHONE_NUMBER e/EMAIL a/ADDRESS`

+ Only the `IC_NUMBER` field is compulsory.
+ **All** input fields are **case-insensitive** e.g. `john doe` is the same as `JOHN DOE`.
+ The order of the input fields does not matter.

Examples:

* `edit T0123456A n/John Doe a/33 g/m b/01-01-1990 i/T0123456A p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `edit T6543210F n/Betsy Crowe p/87386298 a/20 g/Female b/02-02-2003 e/betsycrowe@example.com a/Brighton Town i/T6543210F`




### Deleting a patient: `delete`

Deletes the patient with the specified IC number from the system.

Format: `delete i/IC_NUMBER`

+ **All** input fields are **case-insensitive** e.g. `T1234567A` is the same as `t1234567a`.
+ The order of the input fields does not matter.

Examples:

+ `delete i/T1234567A`
+ `delete i/T2468012a`



### Editing a patient record: `record`

Edits the medical record of the patient with the specified IC number.

Format: `record i/IC_NUMBER [o/OBSERVATION] [d/DIAGNOSIS] [t/TREATMENT_PLAN]`

* At least one of the optional fields indicated within square brackets must be provided
* **All** input fields are **case-insensitive** e.g. `T1234567A` is the same as `t1234567a`.
* The order of the input fields does not matter.

Examples:

* `record i/T0201234A o/Broken Arm d/Hairline fracture t/Cast for 2 days r/Patient stable condition, no need for anesthetics a/Yes`

### Assigning a patient to a department : `assign`

Assigns the patient to relevant doctor.

Format: `assign i/IC_NUMBER d/DEPARTMENT`

* All input fields are compulsory.
* **All** input fields are **case-insensitive** e.g. `T1234567A` is the same as `t1234567a`.
* `DEPARTMENT` must adhere to British spelling conventions.

Examples:

* `assign i/T0201234A d/cardiology`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Advanced&Efficient data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

### Editing the data file

Advanced&Efficient data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are
welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, Advanced&Efficient will discard all data and start with an empty
data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

### [coming soon]

--------------------------------------------------------------------------------------------------------------------

## Known issues

### [coming soon]

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action      | Format, Examples                                                                                                                                                                                                                |
|-------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**     | `add n/NAME i/IC_NUMBER a/AGE g/GENDER b/BIRTHDAY p/PHONE_NUMBER e/EMAIL a/ADDRESS` <br> e.g., `add n/John Doe a/33 g/m b/01-01-1990 i/T0123456A p/98765432 e/johnd@example.com a/John street, block 123, #01-01`               |
| **Delete**  | `delete i/IC_NUMBER`<br> e.g., `delete i/T2468012A`                                                                                                                                                                             |
| **Edit**    | `edit IC_NUMBER`<br> e.g., `edit T2468012A n/John Doe a/34`                                                                                                                                                                     |
| **View**    | `view i/IC_NUMBER`<br> e.g., `view i/T0201234A`                                                                                                                                                                                 |
| **Record**  | `record i/IC_NUMBER o/OBSERVATION d/DIAGNOSIS t/TREATMENT_PLAN r/REMARKS a/ISACTIVE`<br> e.g.,`record i/T0201234A o/Broken Arm d/Hairline fracture t/Cast for 2 days r/Patient stable condition, no need for anesthetics a/Yes` |
| **Assign**  | `assign i/IC_NUMBER d/DEPARTMENT`<br> e.g., `assign i/T0201234A d/cardiology`                                                                                                                                                   |
| **List**    | `list`                                                                                                                                                                                                                          |
| **Help**    | `help`                                                                                                                                                                                                                          |
| **Exit**    | `exit`                                                                                                                                                                                                                          |
