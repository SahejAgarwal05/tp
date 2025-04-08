# HightechTR's Project Portfolio Page

CEGStudyBuddy is a desktop application that tracks and plans university courses efficiently for NUS Computer Engineering students who want a structured academic roadmap.

Below are my contributions to this project.

## Functional Code
[Link to the Code Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=hightechtr&breakdown=true)

### Enhancements Implemented

- Implemented the original add course function and help function (without pre-defined course list)
- Implemented InvalidCommand class for invalid commands
- Introduced basic test cases for AddCommand
- Abstracted the Parser class with parser methods from all command classes
- Abstracted some UI methods to the Ui class
- Abstracted the Utils class with utility methods
- Overall ensured all output text is formatted correctly and code follows proper standard and quality

## Documentation

See the above link to the code dashboard for contributions to documentation.

### User Guide

- Implemented the overall format of the UG
- Wrote the Getting Started section, most of the command information and the command summary table

### Developer Guide

- Implemented the overall format of the DG
- Wrote much of the Getting Started, Design and Implementation sections
- Created almost all class diagrams and sequence diagrams

**List of class diagrams created:**

- CommandClassDiagram
- CommandHistoryClassDiagram
- CourseListClassDiagram
- CourseManagerClassDiagram
- ParserClassDiagram
- StorageManagerClassDiagram
- UndoManagerClassDiagram

**List of sequence diagrams created:**

- AddCommandSequence
- DeleteCourseSequence
- DeletePlanSelectionSequence
- DeletePlanSequence
- InitializePlanSequence
- ListPlansSequence
- LoadPlanSequence
- NewPlanSequence
- PredefinedListSequence
- SelectPlanSequence
- UndoCommandSequence

## Team-Based Tasks

- Set up the GitHub team organisation and repository
- Set up GitHub and Gradle tools
- Maintained the issue tracker, cleaning up duplicate bug reports and closing completed issues
- Reviewed and merged many pull requests on GitHub
- Managed all releases on GitHub
- Performed smoke tests, system tests and acceptance tests as well as reporting any bugs found