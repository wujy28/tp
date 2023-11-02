---
layout: default.md
title: "User Guide"
pageNav: 3
---

# Advanced&Efficient (A&E) User Guide

Welcome to **Advanced&Efficient (A&E)**! Are you an Emergency Department (ED) doctor overwhelmed by the sheer volume of patient records to manage? Do you wish for a faster, more efficient way to log patient reports and streamline your workflow? A&E is the tool you’ve been waiting for!

Advanced&Efficient (A&E) is a powerful **desktop application** designed specifically for **ED doctors** who need to **create, update, and manage patient records** swiftly and effectively. This application, with its **Command-Line Interface (CLI)** design, caters to fast typists and professionals who prefer typing over mouse interactions, significantly speeding up the process of inputting and managing patient data.

Here’s a sneak peek into what A&E offers:

- Streamlined logging of patient reports in a user-friendly CLI environment
- Quick connections to relevant hospital departments for patient referrals
- A comprehensive suite of commands for adding, viewing, editing, and deleting patient records
- An intuitive system for managing patient priority levels and medical tags
- Easy-to-follow command summaries and guides for efficient navigation

A&E combines the simplicity of a CLI with the visual cues of a Graphical User Interface (GUI), offering you the best of both worlds. If you're comfortable with typing and looking for speed and efficiency in patient management, A&E is tailored just for you.

This User Guide is designed to help you, whether you're a newcomer or an experienced user, to harness the full potential of A&E. Here, you'll find everything you need to streamline your patient management tasks, making your role in the ED more manageable and effective.

If you are a **new user**:

- Begin your journey with A&E by exploring the Quick start and Navigating the user guide sections.
- If you’re new to CLI or need a refresher, keep an eye out for our upcoming guide.

If you are an **experienced user**:
- Utilize the Table of contents to swiftly navigate to your desired section.
- Dive into the Features to explore advanced functionalities and optimize your workflow.

Embrace the efficiency of A&E and transform the way you manage patient records in your Emergency Department. Let’s get started!


<!-- * Table of Contents -->
## Table of Contents
- [Table-of-content](#table-of-contents)
- [Navigate](#navigating-the-user-guide)
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
## Navigating the user guide

### [coming soon]

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

<img src='images/ug/helpMessage.png' width='500'>

Format: `help`

### Listing all patients: `list`

Lists all patients in the system.

Format: `list`

<img src='images/ug/listPatients.png' width='300'>


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

* To edit the medical record of a patient with `IC_NUMBER = T0201234A`<br>
  `record i/T0201234A o/Broken Arm d/Hairline fracture t/Cast for 2 days r/Patient stable condition, no need for anesthetics a/Yes`
* To edit the medical record of a patient with `IC_NUMBER = S2374912B`<br>
  `record i/S2374912B di/Asthma o/Shortness of breath and chest tightness`

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

Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous A&E home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

When using multiple screens, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the preferences.json file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                        |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**   | `help`                                                                                                                                                                                                                                  |
| **List**   | `list`                                                                                                                                                                                                                                  |
| **Add**    | `add n/NAME i/IC_NUMBER [g/GENDER] [b/BIRTHDAY] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [pr/PRIORITY] [t/TAG]...` <br> e.g., `add n/Betsy Crowe i/S0123456B p/90909090 e/bc@gmail.com a/Old Town Road 4 pr/LOW t/Allergic to Medicine`   |
| **View**   | `view i/IC_NUMBER`<br> e.g., `view i/T0201234A`                                                                                                                                                                                         |
| **Edit**   | `edit i/IC_NUMBER [g/GENDER] [b/BIRTHDAY] [p/PHONE] [e/EMAIL] [a/ADDRESS] [pr/PRIORITY] [t/TAG]...`<br> e.g., `edit i/T0201234A g/MALE b/08/08/1999 p/93827836 e/example@email.com a/Old Time Road Block 3 pr/MEDIUM t/Allergic to Fur` |
| **Delete** | `delete i/IC_NUMBER`<br> e.g., `delete i/T2468012A`                                                                                                                                                                                     |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g. `find Mary Jane`                                                                                                                                                                                |
| **Record** | `record i/IC_NUMBER [o/OBSERVATION] [di/DIAGNOSIS] [tp/TREATMENT_PLAN]`<br> e.g.,`record i/T0201234A o/Broken Arm di/Hairline fracture tp/Cast for 2 days`                                                                              |
| **Assign** | `assign i/IC_NUMBER d/DEPARTMENT`<br> e.g., `assign i/T0201234A d/cardiology`                                                                                                                                                           |
| **Clear**  | `clear`                                                                                                                                                                                                                                 |
| **Exit**   | `exit`                                                                                                                                                                                                                                  |
