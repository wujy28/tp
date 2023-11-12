# Thien My's Project Portfolio Page

## Project: Advanced&Efficient (A&E)
Advanced&Efficient helps Emergency Department (ED) doctors in logging patient reports and connecting patients with
relevant departments and doctors under time pressure during an emergency.

------------------------------------------------------------------------------------------------------------------------
### Summary of Contributions

Given below are my contributions to the project.

* Code contributed
  [View my RepoSense report!](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=thienmy0&breakdown=true#/)

* **New Feature**: Added a Record Command to edit patient's record
  * Pull Request: [#133](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/133)
  * What it does: allows the user to edit a patient's medical record, which includes fields like initial observations,
    diagnosis, and treatment plan.
  * Justification: This feature allows ED doctors to key in real-time updates of patients' condition, which is
    especially crucial during emergency situations, where patient information can change rapidly. The ability to edit
    records also provides a way for doctors to document and communicate these changes, facilitating the making of
    well-informed decisions.
  * Highlights: Users need not fill in all fields of the medical record for the command to work, so they may edit some
    fields first and other fields later if they are busy or suddenly called to handle an emergency.
  * Difficulty Level: Medium as I was stuck on how to implement this at first, but also has the existing AB3 features to
    guide/reference.

------------------------------------------------------------------------------------------------------------------------

* **Enhancement to existing features**: Added and implemented Priority attribute
  * Pull Request: [#156](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/156)
  * What it does: allows the user to tag a Patient with different priorities (i.e. `NIL`, `LOW`, `MEDIUM`, `HIGH`)
  * Justification: This attribute can allow ED doctors to quickly assess the severity of patients' conditions and to
    provide timely treatment to patients in a critical condition. Using this priority attribute to identify the urgency
    of patients medical needs can potentially save lives and enhance ED doctors' efficiency under time pressure.
  * Highlights: Uses enumeration to ensure that only specific inputs are allowed.
  * Difficulty Level: Medium as a lot of edits in other classes and tests were needed due to dependencies.

* **Enhancement to existing features**: Added Gender attribute
  * Pull Request: [#77](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/77)
  * What it does: allows the user to add/edit the gender (i.e. `FEMALE`, `MALE`, `OTHER`) of a patient.
  * Justification: Gender is a piece of personal information of patients that would be useful to know.
  * Highlights: Uses enumeration to ensure that only specific inputs are allowed.
  * Difficulty: Low as current attributes in AB3 were referenced to create this.

* **Enhancement to existing features**: Added Age attribute
  * Pull Request: [#77](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/77)
  * What it does: allows for age of patients to be displayed.
  * Justification: Age is a piece of personal information of patients that would be useful to know.
  * Highlights: Age is auto-computed according to the patient's inputted birthday (though the implementation of the
    auto-computation itself was done by another member)
  * Difficulty: Low as current attributes in AB3 were referenced to create this.

------------------------------------------------------------------------------------------------------------------------

* **Contributions to the UG**:
  * In charge of: Table of Contents, Using this Guide, Feature: Record, Known Issues, Command Summary
  * Assisted in writing, checking and formatting the whole UG as well
  * Relevant Pull Requests: [#57](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/57),
    [#172](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/172),
    [#248](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/248)

* **Contributions to the DG**:
  * Added Implementation of Record Command
    * Wrote implementation details
    * Wrote design considerations and alternatives
    * Created UML sequence diagram
    * Relevant Pull Requests: [#136](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/136),
      [#235](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/235)
  * Edited Appendix: instructions for manual testing
    * Added: Adding a patient, Viewing a patient, Editing a patient record, Assigning a patient to a department, Sorting
      patients, Undoing and Redoing a command
    * Edited: Deleting a patient, Editing a patient
    * Relevant Pull Requests: [#229](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/229),
      [#235](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/235)

------------------------------------------------------------------------------------------------------------------------

* **Contributions to team-based tasks**:
  * Maintaining the issue tracker
  * Updating user/developer docs
  * Fixed various test cases for team: [#158](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/158),
    [#242](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/242)

* **Review/mentoring contributions**:
  * Reviewed and Merged various PRs from team members
    * The following are some of the PRs I have reviewed, with non-trivial review comments:
    * [#153](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/153)
    * [#161](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/161)
    * [#227](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/227)
    * [#240](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/240)
