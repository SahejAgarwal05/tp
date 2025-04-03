# CEG StudyBuddy User Guide

## Introduction

**CEGStudyBuddy** is a desktop application that tracks and plans university courses efficiently for NUS Computer Engineering students who want a structured academic roadmap.  
It helps to optimise workload, ensure graduation requirements, and keep students organised with ease.

This application is optimised for a **Command Line Interface (CLI)**.  
If you are a fast typer, you can plan and track your courses faster than NUSMods!

---

## Quick Start

1. Ensure you have **Java 17** installed on your computer.  
   You may download Java 17 from [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

   - **Windows Users**: Any Windows installer will work, but the Windows x64 Installer is recommended.
   - **Mac Users**: Use the macOS x64 DMG Installer if you have an Intel CPU, or the macOS Arm 64 DMG Installer for Apple M-series CPUs.
   - **Linux Users**: Use the Debian Package for Debian-based distros (Ubuntu, Linux Mint) or the RPM Package for Red Hat-based distros (Fedora, CentOS).

2. Download the latest `.jar` file for CEGStudyBuddy [here](http://link.to/cegstudybuddy).

3. Place the `.jar` file in a desired folder.  
   This folder will act as the working directory for the application.

4. Open your terminal in that folder:
   - On **Windows**: Right-click in the folder and select *Open in Terminal*.
   - On **Mac/Linux**: Use `cd` to navigate to the folder in Terminal.

5. Run the application:
   ```
java -jar cegstudybuddy.jar


6. Start typing commands and hit `Enter`.

Refer to the **Features** section below for all available commands.

---

## Features

### Notes about the Command Format

- Words in `UPPER_CASE` are parameters to be supplied by the user.  
  Example: `add c/CS2113`
- Square brackets `[]` indicate optional fields.  
  Example: `edit c/CODE [t/TITLE]`
- Extra parameters in commands that don’t require them (e.g. `list`) will be ignored.

---

### Adding a Course: `add`

Adds a course to the planner.

**Format:**
```
add c/CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER
```

**Examples:**
```
add c/CS2040 t/Data Structures mc/4 y/2 s/1
add c/MA1101 t/Linear Algebra mc/4 y/1 s/2
```

---

### Deleting a Course: `delete`

Removes a course from the planner.

**Format:**
```
delete c/CODE
```

**Examples:**
```
delete c/CS2040
delete c/MA1101
```

---

### Listing Courses: `list`

Displays all added courses by Year and Semester.

**Format:**
```
list
```

**Example Output:**
```
Y1S1 Courses:
No courses taken!

Y1S2 Courses:
1. MA1101 - Linear Algebra (4 MCs)

Y2S1 Courses:
1. CS2040 - Data Structures (4 MCs)

Y2S2 Courses:
No courses taken!

Y3S1 Courses:
No courses taken!

Y3S2 Courses:
No courses taken!

Y4S1 Courses:
No courses taken!

Y4S2 Courses:
No courses taken!
```

---

### Editing a Course: `edit`

Modifies course details.  
At least one optional field must be provided.

**Format:**
```
edit c/CODE [t/TITLE] [mc/MODULAR_CREDITS] [y/YEAR] [s/SEMESTER]
```

**Examples:**
```
edit c/CS2040 t/Advanced Data Structures mc/5
edit c/MA1101 y/2 s/1
```

---

### Help Command: `help`

Displays available commands and a help link.

**Format:**
```
help
```

**Example Output:**
```
Available Commands:
1. add c/CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER - Adds a course.
2. delete c/CODE - Deletes a course.
3. list - Lists all courses.
4. edit c/CODE [t/TITLE] [mc/MODULAR_CREDITS] [y/YEAR] [s/SEMESTER] - Edits a course.
```

---

### Workload Command: `workload`

Displays the total Modular Credits (MCs) per semester.

**Format:**
```
workload
```

**Example Output:**
```
Year 1 Semester 1: 16 MCs  
Year 1 Semester 2: 18 MCs  
Year 2 Semester 1: 20 MCs  
Year 2 Semester 2: 16 MCs  
Year 3 Semester 1: 18 MCs  
Year 3 Semester 2: 16 MCs  
Year 4 Semester 1: 14 MCs  
Year 4 Semester 2: 12 MCs

```
---

### Saving Data

CEGStudyBuddy automatically saves your data to disk every time changes are made.  
No manual saving is required.

> 📄 For detailed instructions, visit: [Google Doc](https://docs.google.com/document/d/1BYTlajOCgwL7bUaDveu3yrMp9dUQFgjM5Xe7gw2Bl-I/edit?tab=t.0)

---

### Finding a Course: `find`

Searches for a course by its code.

**Format:**
```
find c/CODE
```

**Example:**
```
find c/CS2040
```

**Example Output:**
```
Course Code: CS2040  
Course Title: Data Structures and Algorithms  
Number of MCs: 4  
Year and Sem: Y2S2
```

If not found:
```
Course CS2040 not found in your course list.
```

---

### Graduation Requirement: `gradreq`

Displays current MCs, total required, and MCs left to graduate.

**Format:**
```
gradreq
```

**Example Output (if <160 MCs):**
```
Current MCs Completed: 100 MCs  
Graduation Requirement: 160 MCs  
Remaining MCs: 60 MCs

Oh no! You don't meet graduation requirement yet.  
Keep on going Champ! You got this! 👍
```

**Example Output (if 160 MCs or more):**
```
Current MCs Completed: 160 MCs  
Graduation Requirement: 160 MCs  
Remaining MCs: 0 MCs

🎓 Congratulations! You have met the graduation requirement!
```

---

## Command Summary

| **Action**     | **Format**                                                                 |
|----------------|-----------------------------------------------------------------------------|
| **Add**        | `add c/CODE t/TITLE mc/MODULAR_CREDITS y/YEAR s/SEMESTER`                  |
|                | E.g., `add c/CS2040 t/Data Structures mc/4 y/2 s/1`                         |
| **Delete**     | `delete c/CODE`                                                             |
|                | E.g., `delete c/CS2040`                                                     |
| **List**       | `list`                                                                      |
| **Edit**       | `edit c/CODE [t/TITLE] [mc/MODULAR_CREDITS] [y/YEAR] [s/SEMESTER]`         |
|                | E.g., `edit c/CS2040 t/Advanced Data Structures mc/5`                      |
| **Help**       | `help`                                                                      |
| **Workload**   | `workload`                                                                  |
| **Find**       | `find c/CODE`                                                               |
|                | E.g., `find c/CS2113`                                                       |
| **Graduation** | `gradreq`                                                                   |

---

## FAQ

### ❓ Q: Do I need an internet connection to use the app?

**A:** Nope! 🎉  
CEGStudyBuddy is a fully offline application. No internet required.

---

### ❓ Q: What happens if I accidentally delete a course?

**A:** Unfortunately, the app doesn’t support an undo feature (yet 😢).  
Make sure to double-check before deleting. You can always re-add the course using the `add` command.

---

### ❓ Q: Can I plan courses beyond 4 years?

**A:** Currently, CEGStudyBuddy supports up to Year 4 Semester 2.  
If you wish to extend it, you can fork the project and modify the planner's backend to support more semesters!

---

### ❓ Q: Is this application open-source?

**A:** Yes! You are free to fork, contribute, and improve the application. Contributions are welcome!  
Check out the [GitHub repository](http://link.to/cegstudybuddy) for more.

---

### ❓ Q: I'm seeing errors when running the `.jar` file. What do I do?

**A:** Make sure:
- You have **Java 17 or above** installed.
- You are running the `.jar` file from **terminal/command prompt** using:

