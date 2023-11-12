---
layout: default.md
title: "User Guide"
pageNav: 3
---

# Advanced&Efficient (A&E) User Guide

Welcome to **Advanced&Efficient (A&E)**! Are you an Emergency Department (ED) doctor overwhelmed by the sheer volume of
patient records to manage? Do you wish for a faster, more efficient way to log patient reports and streamline your
workflow? A&E is the tool you’ve been waiting for!

Advanced&Efficient (A&E) is a powerful **desktop application** designed specifically for **ED doctors** who need to *
*create, update, and manage patient records** swiftly and effectively. This application, with its **Command-Line
Interface (CLI)** design, caters to fast typists and professionals who prefer typing over mouse interactions,
significantly speeding up the process of inputting and managing patient data.

Here’s a sneak peek into what A&E offers:

- Streamlined logging of patient reports in a user-friendly CLI environment
- Quick connections to relevant hospital departments for patient referrals
- A comprehensive suite of commands for adding, viewing, editing, and deleting patient records
- An intuitive system for managing patient priority levels and medical tags
- Easy-to-follow command summaries and guides for efficient navigation

A&E combines the simplicity of a CLI with the visual cues of a Graphical User Interface (GUI), offering you the best of
both worlds. If you're comfortable typing and looking for speed and efficiency in patient management, A&E is
tailored just for you.

This User Guide is designed to help you, whether you're a newcomer or an experienced user, to harness the full potential
of A&E. Here, you'll find everything you need to streamline your patient management tasks, making your role in the ED
more manageable and effective.

If you are a **new user**:

- Begin your journey with A&E by exploring the Quick start and Navigating the user guide sections.
- If you’re new to CLI or need a refresher, keep an eye out for our upcoming guide.

If you are an **experienced user**:

- Utilize the Table of contents to swiftly navigate to your desired section.
- Dive into the Features to explore advanced functionalities and optimize your workflow.

Embrace the efficiency of A&E and transform the way you manage patient records in your Emergency Department. Let’s get
started!


<!-- * Table of Contents -->

## Table of Contents

- [Table-of-contents](#table-of-contents)
- [Using this Guide](#navigating-the-user-guide)
- [Quick Start](#quick-start)
- [Getting to know A&E](#getting-to-know-ae)
    - [User Interface](#user-interface)
- [Features](#features)
    - [Viewing help : `help`](#viewing-help--help)
    - [Listing all patients: `list`](#listing-all-patients--list)
    - [Adding a patient: `add`](#adding-a-patient--add)
    - [Viewing a patient : `view`](#viewing-a-patient--view)
    - [Editing a patient: `edit`](#editing-a-patient--edit)
    - [Deleting a patient: `delete`](#deleting-a-patient--delete)
    - [Undoing a command : `undo`](#undoing-a-command--undo)
    - [Redoing a command : `redo`](#redoing-a-command--redo)
    - [Finding a patient by name : `find`](#finding-a-patient-by-name--find)
    - [Sorting the patient list : `sort`](#sorting-the-patient-list--sort)
    - [Editing a patient record : `record`](#editing-a-patient-record--record)
    - [Assigning a patient to a department : `assign`](#assigning-a-patient-to-a-department--assign)
    - [Clearing all entries : `clear`](#clearing-all-entries--clear)
    - [Exiting the program : `exit`](#exiting-the-program--exit)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Known Issues](#known-issues)
- [Appendix : Departments](#appendix--departments)
- [Command summary](#command-summary)

<page-nav-print />


--------------------------------------------------------------------------------------------------------------------

## Navigating the user guide

### [coming soon]

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.

<box type="tip">

***How to check?***
1. Open up the Terminal on your computer.
   1. For Windows users, locate it by typing in “Terminal” in the search bar.
   2. For Mac users, locate it by typing in “Terminal” in Spotlight.
2. Type in `java -version` and press Enter.
3. If you have Java installed, you should see your Java version as shown underlined in the picture below.
<img src='images/javaversion.png' width='500'>
4. If you do not have Java or your Java version is below 11, install Java
11 [here](https://www.oracle.com/java/technologies/downloads/#java11) based on your Operating System.

</box>

2. Go to your Desktop and create a folder named “AAndE”.


3. Download Advanced&Efficient [here](https://github.com/AY2324S1-CS2103T-T14-2/tp/releases/tag/v1.3.1)
    1. After clicking on the link, click on `AdvancedAndEfficient.jar` as seen in the picture below.

    <img src='images/jar.png' width='800'>


4. After downloading the application, move it into the “AAndE” folder created in step 2.


5. Open Terminal (as mentioned in step 1 part 1)
    1. For Windows, type in `cd Desktop\AAndE` to navigate to the folder you’ve created, and press Enter.
    2. For Mac, type in `cd Desktop/AAndE` to navigate to the folder you’ve created, and press Enter.

   Then, type in `java -jar AdvancedAndEfficient.jar` and press Enter again to run the application.

   A GUI similar to the picture below should appear in a few seconds. Note how the app contains some sample data.

    <img src='images/ug/gettingStartedUi.png' width='700'>

<box type="tip">

We strongly recommend you to use A&E at a resolution of 1024x640 for the fullest experience. You may also click
on the fullscreen icon at the top right hand corner to enlarge the window.

</box>

6. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open
   the help window.

   Some example commands you can try:

    - `list` : Lists all patients.
    - `add n/John Doe i/T0384762A p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a patient
      named
      John Doe into the system.
    - `clear` : Deletes all patients.
    - `exit` : Exits the app.

--------------------------------------------------------------------------------------------------------------------

## Getting to know A&E

### User Interface

Here is an overview of A&E’s user interface and some of its noteworthy components.

<img src='images/ug/labelledUi.png' width='800'>

1. Command Bar
2. Console
3. Patient List Panel
4. Patient Record Panel

### Command Bar

This is where you type in commands for the program to run. Refer to the [Features](#features) or
[Command Summary](#command-summary) section for the command inputs accepted by the app.

To begin typing, simply select the command bar by clicking the space that says “Enter command here…”. Then, type in
your command and hit Enter to execute the command!

<img src='images/ug/commandBar.png' width='600'>

### Console

This is where status messages are displayed. Status messages are displayed depending on the user input
in the command box.

Status messages provide you with feedback such as:
+ What a command does and its format
+ Why a command input is invalid
+ Whether a command is successful

<img src='images/ug/console.png' width='600'>

### Patient List Panel

This is where the list of patients is displayed. It displays either the complete list of all patients in the system
(after `list` command), a filtered list of patients (after `find` command), or a single patient card
(after `view` command).

Patients are listed out vertically. You can scroll through the list using the scrollbar on the right side, your mouse’s
scroll wheel, or your trackpad. To select a patient in the list, simply click on their patient card. The selected
patient is indicated by the light blue border around their patient card.

<img src='images/ug/patientList.png' width='300'>

Each patient is represented by a patient card in the list. Here is an overview of the information displayed in a
patient card.

<img src='images/ug/patientListCard.png' width='450'>

1. Full Name
2. Age
3. Gender
4. Birthday
5. Phone Number
6. Assigned Department
7. Tag(s)
8. Priority Tag
   <img src='images/ug/prLow.png' height='20'>
   <img src='images/ug/prMedium.png' height='20'>
   <img src='images/ug/prHigh.png' height='20'>
9. IC Number

### Patient Record Panel

This is where the details and record of the selected patient in the list is displayed. It displays all the details of a
patient and all their patient record information, except for their age. The labels below the patient’s name indicate the
patient’s priority level and tags respectively.

If there is no selected patient in the list, the panel will be blank. (The selected patient is indicated by the light
blue border around their patient card in the patient list panel.)

<img src='images/ug/recordPanel.png' width='450'>

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Features

<box type="info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.
  e.g `n/NAME [pr/PRIORITY]` can be used as `n/John Doe pr/HIGH` or as `n/John Doe`

* Items with `...` after them can be used multiple times including zero times.
  e.g. `[t/TAG]...` can be used as ` `(i.e. 0 times), `t/critical`, `t/critical t/bedrest` etc.

* Parameters can be in any order.
  e.g. if the command specifies `n/NAME p/PHONE`, `p/PHONE n/NAME` is also acceptable.

* Except for `n/NAME`, all parameters are case-insensitive. e.g. `n/john doe` is different from `n/John Doe`, but
  `i/t1234567a` is the same as `i/T1234567A`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `undo`, `redo`, and
  `clear`) will be ignored. e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

[Back to Table of Contents](#table-of-contents)

### Prefix Summary

For some commands in the subsequent sections, you might come across parameters specified with a prefix
(e.g. prefix `n/` with parameter `NAME` in `n/NAME`). These parameters refer to patient attributes or record fields that are
accepted by the command. Certain parameters have constraints and can only take on certain values. The table below
gives a summary of all parameters, their associated prefixes and their constraints.

| Attribute                                      | Prefix | Parameter        | Constraints/Format                                                                      |
|------------------------------------------------|--------|------------------|-----------------------------------------------------------------------------------------|
| Patient’s full name                            | `n/`   | `NAME`           | Only alphanumeric characters and spaces                                                 |
| Patient’s NRIC number                          | `i/`   | `IC_NUMBER`      | Must be an alphabet, followed by 7 numerical digits, then an alphabet<br>E.g. T0123456A |
| Patient’s gender                               | `g/`   | `GENDER`         | “Male”, “Female” or “Other”                                                             |
| Patient’s birthday                             | `b/`   | `BIRTHDAY`       | Date in the format, DD/MM/YYYY                                                          |
| Patient’s phone number                         | `p/`   | `PHONE`          | Only numbers, at least 3 digits long                                                    |
| Patient’s email address                        | `e/`   | `EMAIL`          | An email address in the format, username@domain.com                                     |
| Patient’s home address                         | `a/`   | `ADDRESS`        | Any values                                                                              |
| Patient’s priority level                       | `pr/`  | `PRIORITY`       | “High”, “Medium”, “Low” or “NIL”                                                        |
| Patient’s tags                                 | `t/`   | `TAG`            | Alphanumeric characters, no spaces                                                      |
| Patient’s assigned department                  | `d/`   | `DEPARTMENT`     | Refer to the [appendix](#appendix-departments) for the list of accepted values          |
| Patient’s initial observations in their record | `o/`   | `OBSERVATION`    | Any values                                                                              |
| Patient’s diagnosis in their record            | `di/`  | `DIAGNOSIS`      | Any values                                                                              |
| Patient’s treatment plan in their record       | `tp/`  | `TREATMENT_PLAN` | Any values                                                                              |

[Back to Table of Contents](#table-of-contents)

### Viewing help: `help`

Shows a message explaining how to access the help page.

<img src='images/ug/helpMessage.png' width='500'>

Format: `help`

### Listing all patients: `list`

Lists all patients in the system.

Format: `list`

<img src='images/ug/listPatients.png' width='300'>

### Adding a patient: `add`

Adds a patient into the system

Format: `add n/NAME i/IC_NUMBER [g/GENDER] [b/BIRTHDAY] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [pr/PRIORITY]] [t/TAG]...`

+ IC number cannot be edited after the patient has been added.

<box type="warning">

**Caution:**
Duplicate patients are not allowed. Duplicate patients refer to patients with the same name and/or IC
number.
+ Names are case-sensitive
  + `Han Bo` is different from `han bo`
+ IC numbers are case-insensitive
  + `T1234567A` is the same as `t1234567a`

</box>

<box type="info">

**Note:**
Optional fields not specified while adding the patient will be filled with default values as shown below.

</box>

<img src='images/ug/addPatients.png' width='500'>

Examples:

+ To add a Patient with name "John Doe", IC number "T0123456A", gender "male", and birthday on 1 Jan 1990<br>
    `add n/John Doe i/T0123456A g/Male b/01/01/1990 `

* To add a patient with name "Betsy Crowe", IC number "S0123456B", phone number "90909090", and email "bc@gmail.com"<br>
    `add n/Betsy Crowe i/S0123456B p/90909090 e/bc@gmail.com`

[Back to Table-of-contents](#table-of-contents)

### Viewing a patient: `view`

Displays a specific patient’s information and medical record.

Format: `view i/IC_NUMBER`

Examples:

+ To view patient with IC number "T0123456A"<br>
  `view i/T0201234A`

Expected output when command succeeds:

<img src='images/ug/viewPatient.png' width='500'>


<box type="info">

**Note:**
The command will not display the patient’s record card in the record panel immediately. To view the patient’s
details and record card, you have to click on their patient card in the list.

</box>

[Back to Table-of-contents](#table-of-contents)

### Editing a patient: `edit`

Edits the attributes of a patient

Format: `edit i/IC_NUMBER [g/GENDER] [b/BIRTHDAY] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [pr/PRIORITY] [t/TAG]...`

+ Edits the patient with the specified `i/IC_NUMBER`
+ At least one of the optional fields indicated within square brackets must be provided.
+ **All** input fields are **case-insensitive** e.g. `john doe` is the same as `JOHN DOE`.
+ The order of the input fields does not matter.
+ `PRIORITY` can take on values `NIL`,`LOW`,`MEDIUM` or `HIGH`.

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

### Undoing a command: `undo`

Undoes the most recent state of the following commands:

+ `delete`
+ `edit`
+ `clear`
+ `add`

Format: `undo`

+ Input command is **case-sensitive** e.g. `undo` is accepted but not `UNDO`.

Examples:

+ `delete i/T1234567A` deletes this patient from the patient list
+ `undo` restores this patient back into the patient list

### Redoing a command: `redo`

Redoes the most recent state of the following commands:

+ `delete`
+ `edit`
+ `clear`
+ `add`

Format: `redo`

+ Input command is **case-sensitive** e.g. `redo` is accepted but not `REDO`.

Examples:

+ `delete i/T1234567A` deletes this patient from the patient list
+ `undo` restores this patient back into the patient list
+ `redo` once again deletes this patient from the patient list

### Finding a patient by name: `find`

Finds the patients whose names match any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

+ **All** input fields are **case-insensitive** e.g. `hans` is the same as `HANS`.
+ The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
+ Only the name is searched.
+ Only full words will be matched e.g. `Han` will not match `Hans`
+ Persons matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo` will
  return `Hans Gruber`, `Bo Yang`

Examples:

+ To find patients whose name matches ‘John’<br>
  `find john`
+ To find patients whose name matches ‘Mary’ and ‘Jane’<br>
  `find Mary Jane`

### Sorting the patient list: `sort`

Sorts the patient list based on the given property.

<box type="info">

**Note:**
It sorts both the filtered and full patient list. For example, if you run `find John` followed by `sort age` on the
list of search results, it will also sort the complete list of patients (displayed using the `list` command).

</box>

Format: `sort PROPERTY`

+ `PROPERTY` can only be `name`, `ic`, `department`, `age`, or `priority`.

Examples:

+ To sort the patient list according to name in alphanumeric order<br>
  `sort name`
+ To sort the patient list according to IC number in alphanumeric order<br>
  `sort ic`
+ To sort the patient list according to department (with default departments at the bottom of the list)<br>
  `sort department`
+ To sort the patient list according to ascending age (with default age at the top of the list)<br>
  `sort age`
+ To sort the patient list according to descending priority<br>
  `sort priority`

[Back to Table-of-contents](#table-of-contents)

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

### Assigning a patient to a department: `assign`

Assigns a patient to a hospital department.

Format: `assign i/IC_NUMBER d/DEPARTMENT`

<box type="warning">

**Caution:**
`DEPARTMENT` must adhere to British spelling conventions. Refer to the [appendix](#appendix-departments)
below for the list of valid departments and their accepted inputs.

</box>

Examples:

* To assign a patient with IC number "T0201234A" to the cardiology department<br>
  `assign i/T0201234A d/cardiology`

[Back to Table-of-contents](#table-of-contents)

### Clearing all entries: `clear`

Clears all entries from the system.

Format: `clear`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

Advanced&Efficient data are saved in the hard disk automatically after any command that changes the data. There is no
need to
save manually.

### Editing the data file

Advanced&Efficient data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced
users are
welcome to update data directly by editing that data file.

<box type="warning">

**Caution:**
If your changes to the data file makes its format invalid, Advanced&Efficient will discard all data and start with an
empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.

</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: I double-clicked the Jar file and it doesn't run, why?\
**A**: Make sure that Java 11 is installed and do refer to step 5 of [Quick-start](#quick-start) for instructions to run
application.

**Q**: I have a list of patients, how do I view their details?\
**A**: Please click on the patient you are interested in viewing. The details will be on the right hand side in the
Patient Record Panel.

**Q**: The Patient List Panel is cut off, I cant see some details for each patient\
**A**: Please resize the application by dragging the border of the application window with your mouse. If the appplication is already in full screen mode, please drag the divider between the Patient List Panel and Patient
Record Panel to the right.

**Q**: I entered a command and there is no response from the application, what do I do?\
**A**: A critical error might have occured. Please ensure your command is as specified in [Features](#features). Please contact
the technical team in charge of your department to file a bug report on our [issues](tps://github.com/AY2324S1-CS2103T-T14-2/tp/issues) page.

**Q**: I have accidentally cleared the whole patient record system, can I retrieve the lost details?\
**A**: Yes, A&E has the [Undo](#undoing-a-command-undo) feature which allows you to retrieve all the lost details.

**Q**: Will my data be saved immediately?\
**A**: Yes, A&E saves your data after every command executed.

**Q**: How do I transfer my data to another Computer?\
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous AAndE home folder.

[Back to Table-of-contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Known issues

When using multiple screens, if you move the application to a secondary screen, and later switch to using only the
primary screen, the GUI will open off-screen. The remedy is to delete the preferences.json file created by the
application before running the application again.
<br>
<br>
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
| **Undo**   | `undo`                                                                                                                                                                                                                                  |
| **Redo**   | `redo`                                                                                                                                                                                                                                  |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g. `find Mary Jane`                                                                                                                                                                                |
| **Sort**   | `sort PROPERTY`<br> e.g., `sort priority`                                                                                                                                                                                               |
| **Record** | `record i/IC_NUMBER [o/OBSERVATION] [di/DIAGNOSIS] [tp/TREATMENT_PLAN]`<br> e.g.,`record i/T0201234A o/Broken Arm di/Hairline fracture tp/Cast for 2 days`                                                                              |
| **Assign** | `assign i/IC_NUMBER d/DEPARTMENT`<br> e.g., `assign i/T0201234A d/cardiology`                                                                                                                                                           |
| **Clear**  | `clear`                                                                                                                                                                                                                                 |
| **Exit**   | `exit`                                                                                                                                                                                                                                  |

--------------------------------------------------------------------------------------------------------------------

## Appendix: Departments

The table below shows all the hospital departments available in the system and their accepted inputs.

Related commands: [Assign](#assigning-a-patient-to-a-department-assign)

| Department           | Acceptable Input(s)    |
|----------------------|------------------------|
| Default              | `Default`              |
| Anaesthesiology      | `Anaesthesiology`      |
| Cardiology           | `Cardiology`           |
| Dermatology          | `Dermatology`          |
| Emergency Department | `Emergency Department` |
| Endocrinology        | `Endocrinology`        |
| General Surgery      | `General Surgery`      |
| Geriatric Medicine   | `Geriatric Medicine`   |
| Gynaecology          | `Gynaecology`          |
| Haematology          | `Haematology`          |
| Immunology           | `Immunology`           |
| Infectious Diseases  | `Infectious Diseases`  |
| Intensive Care Unit  | `Intensive Care Unit`  |
| Oncology             | `Oncology`             |
| Ophthalmology        | `Ophthalmology`        |
| Orthopaedics         | `Orthopaedics`         |
| Neurology            | `Neurology`            |
| Neurosurgery         | `Neurosurgery`         |
| Pathology            | `Pathology`            |
| Palliative Medicine  | `Palliative Medicine`  |
| Plastic Surgery      | `Plastic Surgery`      |
| Psychiatry           | `Psychiatry`           |
| Radiology            | `Radiology`            |
| Urology              | `Urology`              |

[Back to Table-of-contents](#table-of-contents)
