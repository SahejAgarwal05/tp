# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

### **Architecture**

### **UI Component**

### **Parser Component**

### **Storage Component**
#### **Saving Course Plans**

#### **Predefined List of CEG Core Courses**

Since there is a fixed group of courses which must be completed to fulfill graduation requirements, users can just search for the course codes and load/import these courses from the database (a .txt file) with accurate and complete information. This prevents users from defining these courses themselves using inaccurate information or making careless mistakes such as typos. It also improves user experience by simplifying user input.

\<insert image>\
For the developers, this list is essential for other enhancement functions such as checking graduation requirements by comparing the users’ course plans with the list of core courses. To conveniently save and load from the list, Gson dependency is added to convert between courses and Json Strings.

## Implementation



## Appendix A: Product scope
### **Target User Profile**

NUS CEG Students who prefer CLI (Command Line Interface) programs.

### **Value Proposition**

This product tracks and plans university courses efficiently
for CEG students who want a structured academic roadmap.
It helps to optimise workload, ensure graduation requirements,
and keep students organised with ease.
It is designed for a CLI interface.

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Appendix C: Non-Functional Requirements

{Give non-functional requirements}

## Appendix D: Glossary

* *glossary item* - Definition

## Appendix E: Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
