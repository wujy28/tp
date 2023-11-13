# Jingya's Project Portfolio Page

## Project: Advanced&Efficient (A&E)
Advanced&Efficient helps Emergency Department (ED) doctors in logging patient reports and connecting patients with
relevant departments and doctors under time pressure during an emergency.

Given below are my contributions to the project.

**Code contributed:** [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=wujy28&breakdown=true)

----

### New Features

1. Added the department attribute to Patient ([#88](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/88))
   + Created the Department Enumeration
     + An enumeration that represents the valid hospital department values stored
       in the program
   + Created the AssignedDepartment class
     + An attribute class for Patient that stores the patient's currently assigned department
   + Both of these additions allow the program to store the information required for
   AssignCommand
   + Added testing for these classes
2. Added the AssignCommand for assigning Patients to Departments
   ([#135](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/135))
   + A crucial feature that allows the user to assign a Patient to a Department
   (i.e. edit the AssignedDepartment field of a Patient)
   + Created the AssignCommand and AssignCommandParser classes
   + Added testing for these classes ([#226](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/226))
3. Added the SortCommand for sorting of Patients by property
   ([#166](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/166))
   + A good-to-have feature that allows the user to sort the patient list by a given property
   (i.e. name, IC number, department, age, or priority)
   + Created the SortCommand and SortCommandParser classes, as well as compareTo methods for
   the attributes involved
   + Added testing for these classes ([#226](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/226))

----

### Enhancements To Existing Features

1. Revamped the UI of the program
   + Redesigned the UI and created the initial mockup on Figma
   ([Image](https://github.com/wujy28/tp/blob/28fc1b9cdd7e0bbc0e7b7548fa900aa404761059/docs/images/Ui.png))
   + Reformatted the UI to accommodate the viewing of patient list and patient record side-by-side, according to the
   mockup ([#110](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/110),
   [#166](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/166))
   + Added the UI components and controllers for patient record
   ([#110](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/110),
   [#173](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/173))
   + Added the functionality to view a patient's record by clicking on a patient in the patient list
     ([#110](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/110))
   + Styled the UI using CSS ([#150](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/150))
2. Added auto-computation behavior for the Age attribute from a Patient
   + Made modifications to the initially unutilized Age attribute so that it can automatically initialize with the
   correct value given a patient's birthday ([#161](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/161),
   [#162](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/162))

----
### Contributions to team-based tasks

+ Consolidated and organized bugs from PE-D
+ Helped in preparing documentation for final submission (proofreading and formatting)

### Review/mentoring contributions

+ PRs reviewed (with non-trivial review comments):
  [#102](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/102),
  [#119](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/119),
  [#145](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/145),
  [#227](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/227)

### Contributions to documentation

+ User Guide
  + Added documentation for the features `assign` and `sort`
  ([#171](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/171),
  [#243](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/243))
  + Added the appendix for the list of available departments
  ([#171](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/171))
  + Wrote the 'Getting to know A&E' section
  ([#243](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/243))
  + Wrote the 'Prefix Summary' section
  ([#243](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/243))
+ Developer Guide
  + Added implementation details for `assign` feature
  ([#137](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/137))
  + Added implementation details for `sort` feature
  ([#241](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/241))
  + Updated the explanation and class diagram for UI Component section
  ([#137](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/137))
  + Updated the diagram for Model Component section
  ([#241](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/241))
  + Added on to the 'Planned Enhancements' appendix
  ([#254](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/254))
