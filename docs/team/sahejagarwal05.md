# Sahej Agarwal - Project Portfolio Page

## Project CEGStudyBuddy: Breif Overview

CEGStudyBuddy is a desktop application that tracks and plans university courses efficiently for NUS Computer Engineering students who want a structured academic roadmap.  
It helps to optimise workload, ensure graduation requirements, and keep students organised with ease, while managing multiple scenarios and possible schedules in their academic journey.

### Summary of Contributions

**StorageManager**

I was responsible for developing and maintaining all storage-related features as well as identifying and resolving related bugs.
In the process, I created the storage manager class, out of which functions related to display and user input where moved to the Ui class for better OOP. 

The above-mentioned features include:
- Initialization: the process in the beginning that allows the user to select from preexisting course plans or create a new plan
- Switching: allowing the user to save the current plan and switch to a pre-existing plan or create a new plan
- Renaming: allowing the user to rename the current plan
- Deleting: allows the user to delete a plan and in case the current plan is deleted, the initialization process starts again
- Saving: process to manually save progress
- Autosave: allows automated saving of the progress

This also involved creating toSaveFormat methods for both Course and CourseList classes so that they can be stored in text files in human editable format.

To allow smooth functioning of the features, testing for the save command was also implemented along with error handling and directory creation when needed.

I also created the required user guide and the developer guid with detailed information about this.

**Workload_for Command**

I created the workload_for command th parser the input(later moved to Parser) to allow the user to get a detailed list of courses and workload for a sem. I included the documentation in the UG and DG.

**Workload_balance Command**

I developed the workload_balance command to help students maintain consistent and balanced workloads across semesters, following the valuable advice shared by senior students. I included the documentation in the UG and DG.

**Common Classes and OOP**

I also created the classes on Command, CEGStudyBuddy, CEGStudyBuddyException, and constant definitions of the function names. I included the documentation in the UG and DG.


**Minor Upgrades**

I made minor upgrades like, improvement in Ui, better OOP etc.


You can find 

