# AaronJT1's Project Portfolio Page

## Project: Advanced&Efficient (A&E)

Advanced&Efficient helps Emergency Department (ED) doctors in logging patient reports and connecting patients with
relevant departments.

----------------------------------------------------------------------

### Contribution

Here are my [contributions](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=aaronjt1&breakdown=true) to the
project.

----------------------------------------------------------------------

### Features implemented

View Feature [#119](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/119)

**What it does** - Allows users to view the information of a specific patient with `Ic Number`

**Justification** - Currently there is a find command which allows users to find patients by keywords in names. However
if a user wants to search for a specific patient with Ic Number, there is no option for that. Since find returns a list
of patients with names matching the keyword, the user would have to manually go through the list to find that specific
patient.

**Highlights** - Since Add command is enhanced to prevent patients with duplicate `Ic Number` from being added, users
can be assured that the displayed user is the one they desired.


----------------------------------------------------------------------

### Enhancement to existing features:

#### Enhanced Add Feature

1. Option to add patients with required fields only [#78](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/78)
2. Prevent patient with `IC Number` that already exists in system from being
   added. [#128](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/128)

#### Enhanced exception / error messages for users

1. `PatientWithFieldNotFoundException` thrown when no patient with user-specified field
   found [#119](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/119)
2. Integrated `PatientWithFieldNotFoundException` into all relevant
   commands [#153](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/153)

#### Enhanced `ModelManager` [#128](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/128)

1. `isPatientWithIcNumberPresent` method to check if a patient with the `Ic Number` is present
2. `getCurrentPatientList` method to get current patients in the patient record rather than filtered list
3. Testing utilities for the above mentioned methods. in `ModelManagerTest.java`.

#### Enhanced testing utilities

1. Account for new Patient
   attributes [#102](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/102), [#116](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/116)
2. Significantly improved coverage for AddCommandParser &
   EditCommandParser [#78](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/78), [#160](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/160)
3. Added testing for AssignCommand feature [#163](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/163)

----------------------------------------------------------------------

### Contributions to User Guide:

- Added documentation & screenshot for `add` and `view` commands
- Added `Quick-start` section
- Added `FAQ` section

### Contributions to Developer Guide:

- Added the `Overview` and `Acknowledgements` section
- Updated `Setting up, getting started` guide
- Updated links in `Design` section
- Added `Add Patient` and `View Patient` feature in `Implementation` section
- Added & updated
  whole of `Appendix: Requirements`
  section -- `Product scope`, `User stories`,`Use cases (UC01-UC09)`,`Non-Functional Requirements`, `Glossary`
- Added points 1&2 in `Appendix: Planned Enhancements` section

----------------------------------------------------------------------

### Contributions to team-based task:

* tP Team organisation and Repo setup
* Managed Team Google Docs for ease of use
* Managed GitHub issues tracker, Milestones v1.1-v1.4, Releases v1.2, v1.3.trial and v1.3.1
* Managed product demo for v1.2 and v1.3
* Refactor Person instances to Patient in codebase [#68](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/68)

### Review / mentoring contributions:

* Reviewed pull requests by other group members and provided insightful comments
    * Some non-trivial PRs I have
      reviewed [#77](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/77)
      [#89](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/89) [#96](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/96) [#107](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/107) [#120](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/120) [#145](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/145) [#155](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/155) [#167](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/167)
    * Reviewed, approved and merged a total of 33 PRs out of ..
* Provided guidelines to aid group members in implementation
    * Example : Aided group member in testing of Edit
      command [#145](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/145)


