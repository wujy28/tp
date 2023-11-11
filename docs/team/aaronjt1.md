# AaronJT1's Project Portfolio Page

## Project: Advanced&Efficient (A&E)

Advanced&Efficient helps Emergency Department (ED) doctors in logging patient reports and connecting patients with
relevant departments.

----------------------------------------------------------------------

## Contribution

Here are my contributions to the project.

* Code contributed
  [View my code!](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=aaronjt1&breakdown=true)

----------------------------------------------------------------------

## Features implemented

### View Feature [#119](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/119)
**What it does** - Allows users to view the information of a specific patient with `Ic Number`

**Justification** - Currently there is a find command which allows users to find patients by keywords in names. However if a user wants to search for a specific patient with Ic Number, there is no option for that. Since find returns a list of patients with names matching the keyword, the user would have to manually go through the list to find that specific patient.


**Highlights** - Since Add command is enhanced to prevent patients with duplicate `Ic Number` from being added, users can be assured that the displayed user is the one they desired.


----------------------------------------------------------------------

## Enhancement to existing features:

### Enhanced Add Feature

1. Option to add patients with compulsory fields only [#78](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/78),
    - Previously, users have to fill in all the patient fields for the patient to added.
    - Currently, users only need to fill in `Name` and `Ic Number` to add a patient
2. Check for duplicate `Ic Number` before adding a patient [#128](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/128)
    - Previously the users are able to add patients with `Ic Number` that already exists
   in another patient in the patient record.
    - Currently, user will not allowed to add another patient with existing `Ic Number`
   and an error message will be shown.


### Enhanced exception / error messages for users

1. `PatientWithFieldNotFoundException` thrown when no patient with user-specified field found [#119](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/119)
    - Previously, when user input an `Ic Number` that does not exist in a patient record,
   it does not show any helpful message but simply showed an empty list.
    - Currently, when user input an `Ic Number` that does not exist in a patient record,
   an exception will be thrown and helpful message will be displayed to inform users about it.
2. Integrated `PatientWithFieldNotFoundException` into all relevant commands [#153](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/153)
    - Assign, Delete, Edit, Record - Thrown when user entered an `Ic Number` that does not exist in any patient.
    - Find - Thrown when user entered keyword(s) that does not exist in any patient's name.


### Enhanced `ModelManager` [#128](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/128)

1. `isPatientWithIcNumberPresent` method to check if a patient with the `Ic Number` is present
    - Useful for commands which uses `Ic Number` to find a patient like Find, View, etc.
2. `getCurrentPatientList` method to get current patients in the patient record rather than filtered list
    - Useful for commands which aims to perform commands on patients outside of the displayed list.
3. Testing utilities for the above mentioned methods. in `ModelManagerTest.java`.



### Enhanced testing utilities

1. Account for new Patient attributes [#102](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/102), [#116](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/116)
   - Refactored testing facilities which utilized patients like `CommandTestUtil`, `TypicalPatients` and `PatientBuilder`
2. Significantly improved coverage for AddCommandParser & EditCommandParser [#78](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/78), [#160](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/160)
3. Added testing for AssignCommand feature [#163](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/163)
----------------------------------------------------------------------

## Contributions to team-based task:

* tP Team organisation and Repo setup
* Managed Team Google Docs for ease of use
* Managed GitHub issues tracker
* Managed GitHub Milestones v1.1-v1.4
* Managed GitHub Releases v1.2, v1.3.trial and v1.3.1
* Managed product demo for v1.2 and v1.3
* Refactor Person instances to Patient in codebase [#68](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/68)

## Review / mentoring contributions:
* Reviewed pull requests by other group members and provided insightful comments
    * Some non-trivial PRs i have
      reviewed [#77](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/77)
      [#89](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/89) [#96](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/96) [#107](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/107) [#120](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/120) [#145](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/145) [#155](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/155) [#167](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/167)
    * Reviewed, approved and merged a total of 33 PRs out of ..
* Provided guidelines to aid group members in implementation
    * Example : Aided group member in testing of Edit command [#145](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/145)


