AUCA_ID - AUCA School Management System
========================================

Requirements: Java JDK 17 or newer.

Run in VS Code:
1. Extract this ZIP.
2. Open the AUCA_ID folder (the folder containing src) in VS Code.
3. Open src/com/auca/main/Main.java and click Run, or compile from the terminal:

   javac -d out $(find src -name "*.java")
   java -cp out com.auca.main.Main

The program uses one Scanner and reads console input with nextLine().
Menu options include department, student, teacher, administrator, course,
classroom, enrollment, assessment, fee payment, library book registration,
borrowing and returning, viewing/searching records, academic/payment summaries,
and polymorphism.

Important:
- Records are kept in memory while the program is running. They are not saved
  to a database or file and disappear when you exit.
- Update Existing Record currently locates records and explains the supported
  update limitations; full editing of every field is not implemented.
- This is a functional console assignment foundation. Check your lecturer's
  exact rubric for any extra validation or persistence requirements.
