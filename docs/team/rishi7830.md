# Rishi7830's Project Portfolio Page

CEG Study Buddy is a CLI-based educational planning tool designed to help Computer Engineering students manage their academic journey. The application supports adding, deleting, finding, and managing courses, checking graduation requirements, and offering undo functionality. It emphasizes robust error handling and clean modular architecture.

Below are my contributions to the project.

---

### Features Implemented

- **`ListCommand`**: Dynamically displays all courses grouped by year and semester, showing only non-empty semesters for a cleaner UI.
- **`DeleteCourse`**: Enables users to delete a course using the course code (e.g., `delete c/CS2040`) with format validation.
- **`GradRequirementCommand`**: Calculates and displays total completed MCs and checks if user meets graduation requirements, with ASCII motivational output.
- **`PrereqCommand`**: Shows prerequisites and descriptions for core Computer Engineering modules using a pre-defined internal map.
- **`Undo` and `Summary` Commands**: Allows users to undo previous `add`, `delete`, and `replace` operations and view session command history.
- **`ReplaceCommand`**: Allows replacement of one course with another while maintaining key details like MCs, year, and semester.
- **`FindCommand`**: Searches and retrieves course details by code with strict format validation and helpful outputs.
- **`Parser`**: Assisted in the making of the parser class to parse all the commands
- **`UndoManager Class`**: Created the UndoManager Class to support the functionality of the Undo Command to undo the operations of:
  - Add
  - Delete
  - Replace
  - Edit
- **`CourseHistoryManager Class`**: Created the Course History Manager Class to support the functionality of the Summary command to list out all the commands executed by the user during the session

---

## Code Contributed
[Link to the Code Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=Rishi7830&breakdown=true)
---

## Project Management

### Timely Task Completion
- Handled and resolved all assigned issues for releases **V1.0**, **V2.0**, and **V2.1** promptly—never submitted last minute.

### Team Collaboration
- Supported the team actively for all milestone releases by:
    - Coordinating work in GitHub projects
    - Resolving blockers and merge conflicts
    - Helping in code integration and testing
- Regularly checked on teammates’ progress and assisted them in bug-fixing and refining features.
- Initiated Issue on the Github Issue Tracker and took responsibility and completed them on time

---

## Enhancements to Existing Features

### Junit Testing
- Wrote additional JUnit tests for commands including:
    - `AddCommand`, `DeleteCourse`, `FindCommand`, `GradRequirementCommand`, `ListCommand`, and `CourseManagerTest`

### Robust Input Validation across Commands
- Improved input validation across multiple commands to:
    - Reject decimal/floating inputs for MCs, year, and semester
    - Handle commands with leading whitespaces
    - Enforce valid course code formats (e.g., CS2040, EE2026, CG2111A)
    - Ensure MCs (1–12), years (1–4), and semesters (1 or 2) are strictly validated

---

## Documentation

### User Guide

- Documented the following features:  
  `delete`, `find`, `replace`, `gradreq`, `list`, `prereq`, `undo`, `summary`
- Wrote detailed user stories to shape the early stages of feature planning.

### Developer Guide

- Contributed significantly to the V2.0 Developer Guide:
    - Authored sections on **Product Scope**, **User Stories**, and **Instructions for Manual Testing**
    - Explained implementation for key classes like `UndoManager` and `CommandHistoryManager`
    - Assisted in creating and reviewing UML diagrams:
        - `CommandComponentDiagram`
        - `UndoManagerClassDiagram`
        - `CommandHistoryManagerClassDiagram`
        - `ReplaceCommandSequenceDiagram`

---

## Tools Used

- **IDE**: IntelliJ IDEA
- **Version Control**: Git, GitHub, SourceTree
- **Resources**: StackOverflow, Khan Academy

---

## Community Contributions

- Reviewed teammates’ PRs and provided constructive feedback.
- Reported **16 bugs** during the PE Dry Run for peers’ applications, helping improve their software before evaluations.

---
