package com.auca.main;

import java.util.*;
import java.time.LocalDate;
import java.math.BigDecimal;
import com.auca.interfaces.*;
import com.auca.exceptions.*;
import com.auca.abstracts.Person;
import com.auca.student.Student;
import com.auca.teacher.Teacher;
import com.auca.administrator.Administrator;
import com.auca.department.Department;
import com.auca.course.Course;
import com.auca.classroom.Classroom;
import com.auca.enrollment.Enrollment;
import com.auca.assessment.Assessment;
import com.auca.feepayment.FeePayment;
import com.auca.librarybook.LibraryBook;
import com.auca.bookloan.BookLoan;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final List<Department> departments = new ArrayList<>();
    private static final List<Student> students = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static final List<Administrator> admins = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private static final List<Classroom> rooms = new ArrayList<>();
    private static final List<Enrollment> enrollments = new ArrayList<>();
    private static final List<Assessment> assessments = new ArrayList<>();
    private static final List<FeePayment> payments = new ArrayList<>();
    private static final List<LibraryBook> books = new ArrayList<>();
    private static final List<BookLoan> loans = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to AUCA School Management System");
        while (true) {
            menu();
            String choice = ask("Select an option: ");
            try {
                switch (choice) {
                    case "1": registerDepartment(); break;
                    case "2": registerStudent(); break;
                    case "3": registerTeacher(); break;
                    case "4": registerAdmin(); break;
                    case "5": registerCourse(); break;
                    case "6": registerClassroom(); break;
                    case "7": enrollStudent(); break;
                    case "8": recordAssessment(); break;
                    case "9": recordPayment(); break;
                    case "10": registerBook(); break;
                    case "11": borrowBook(); break;
                    case "12": returnBook(); break;
                    case "13": viewAll(); break;
                    case "14": searchById(); break;
                    case "15": updateRecord(); break;
                    case "16": academicSummary(); break;
                    case "17": paymentHistory(); break;
                    case "18": availableBooks(); break;
                    case "19": demonstratePolymorphism(); break;
                    case "0": System.out.println("Goodbye!"); return;
                    default: System.out.println("Please select a number from 0 to 19.");
                }
            } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
            System.out.println();
        }
    }

    private static void menu() {
        System.out.println("\n========== AUCA_ID SCHOOL MANAGEMENT ==========");
        System.out.println("1. Register Department       11. Borrow Library Book");
        System.out.println("2. Register Student          12. Return Library Book");
        System.out.println("3. Register Teacher          13. View All Records");
        System.out.println("4. Register Administrator   14. Search Record by ID");
        System.out.println("5. Register Course           15. Update Existing Record");
        System.out.println("6. Register Classroom        16. Student Academic Summary");
        System.out.println("7. Enroll Student            17. Student Payment History");
        System.out.println("8. Record Assessment         18. View Available Books");
        System.out.println("9. Record Fee Payment        19. Demonstrate Polymorphism");
        System.out.println("10. Register Library Book     0. Exit");
    }
    private static String ask(String label) { System.out.print(label); return in.nextLine().trim(); }
    private static String required(String label) {
        while (true) { String s=ask(label); if(!s.isBlank()) return s; System.out.println("This field is required."); }
    }
    private static int number(String label,int min,int max) {
        while(true) try { int n=Integer.parseInt(ask(label)); if(n>=min&&n<=max)return n; System.out.println("Enter a number from "+min+" to "+max+"."); } catch(NumberFormatException e){System.out.println("Enter a whole number.");}
    }
    private static double decimal(String label,double min,double max) {
        while(true) try { double n=Double.parseDouble(ask(label)); if(n>=min&&n<=max)return n; System.out.println("Enter a value from "+min+" to "+max+"."); } catch(NumberFormatException e){System.out.println("Enter a valid number.");}
    }
    private static LocalDate date(String label) {
        while(true) try { return LocalDate.parse(required(label+" (YYYY-MM-DD): ")); } catch(Exception e){System.out.println("Use date format YYYY-MM-DD.");}
    }
    private static String newId(String prefix) {
        String id;
        do { id=prefix+"-"+UUID.randomUUID().toString().substring(0,8).toUpperCase(); } while(find(id)!=null);
        return id;
    }
    private static Object find(String id) {
        for(Identifiable x: allIdentifiable()) if(x.getId().equalsIgnoreCase(id)) return x;
        return null;
    }
    private static List<Identifiable> allIdentifiable() {
        List<Identifiable> a=new ArrayList<>();
        a.addAll(departments);a.addAll(students);a.addAll(teachers);a.addAll(admins);a.addAll(courses);a.addAll(rooms);a.addAll(enrollments);a.addAll(assessments);a.addAll(payments);a.addAll(books);a.addAll(loans);return a;
    }
    private static Department chooseDepartment() throws PrerequisiteMissingException {
        if(departments.isEmpty()) throw new PrerequisiteMissingException("Register a department first (menu option 1).");
        return choose("Choose a department:",departments);
    }
    private static <T> T choose(String heading,List<T> list) {
        System.out.println(heading);
        for(int i=0;i<list.size();i++)System.out.println((i+1)+". "+list.get(i));
        return list.get(number("Choose number: ",1,list.size())-1);
    }
    private static void checkUnique(String id) throws DuplicateRecordException { if(find(id)!=null)throw new DuplicateRecordException("That ID already exists."); }
    private static void registerDepartment() throws Exception {
        String name=required("Department name: "), code=required("Department code: "), office=required("Office location: "), email=required("Contact email: ");
        Department d=new Department(newId("DEP"),name,code,office,email,null);d.validate();departments.add(d);System.out.println("Department registered: "+d);
    }
    private static void registerStudent() throws Exception {
        Department d=chooseDepartment();
        String f=required("First name: "),l=required("Last name: "),email=required("Email: "),phone=required("Phone (10+ digits): ");
        LocalDate dob=date("Date of birth");
        String program=required("Program name: ");int year=number("Study year (1-8): ",1,8);String guardian=required("Guardian phone: ");
        String id=newId("STU");
        Student s=new Student(id,f,l,email,phone,dob,id,d,LocalDate.now(),"ACTIVE",email,"Not assigned",id,program,LocalDate.now(),year,guardian,ask("Scholarship? (yes/no): ").equalsIgnoreCase("yes"));
        s.validate();students.add(s);System.out.println("Student registered: "+s);
    }
    private static void registerTeacher() throws Exception {
        Department d=chooseDepartment();
        String f=required("First name: "),l=required("Last name: "),email=required("Email: "),phone=required("Phone: ");LocalDate dob=date("Date of birth");
        String spec=required("Specialization: "),office=required("Office number: "),type=required("Employment type: ");BigDecimal salary=new BigDecimal(required("Salary: "));
        String id=newId("TCH");Teacher t=new Teacher(id,f,l,email,phone,dob,id,d,LocalDate.now(),"ACTIVE",email,"Not assigned",id,spec,LocalDate.now(),salary,office,type);
        t.validate();teachers.add(t);System.out.println("Teacher registered: "+t);
    }
    private static void registerAdmin() throws Exception {
        String f=required("First name: "),l=required("Last name: "),email=required("Email: "),phone=required("Phone: ");LocalDate dob=date("Date of birth");
        String title=required("Job title: "),office=required("Assigned office: ");int level=number("Access level (1-5): ",1,5);
        String id=newId("ADM");Administrator a=new Administrator(id,f,l,email,phone,dob,id,title,office,LocalDate.now(),level,true);a.validate();admins.add(a);System.out.println("Administrator registered: "+a);
    }
    private static void registerCourse() throws Exception {
        Department d=chooseDepartment();String code=required("Course code: "),title=required("Course title: ");int credits=number("Credit hours: ",1,10);
        Teacher t=teachers.isEmpty()?null:choose("Assign teacher (optional selection):",teachers);
        Course c=new Course(newId("CRS"),code,title,credits,d,t);c.validate();courses.add(c);System.out.println("Course registered: "+c);
    }
    private static void registerClassroom() throws Exception {
        String building=required("Building: "),room=required("Room number: ");int cap=number("Capacity: ",1,500),floor=number("Floor: ",0,50);
        Classroom c=new Classroom(newId("ROM"),building,room,cap,floor,true);c.validate();rooms.add(c);System.out.println("Classroom registered: "+c);
    }
    private static Student chooseStudent() throws PrerequisiteMissingException {if(students.isEmpty())throw new PrerequisiteMissingException("Register a student first (menu option 2).");return choose("Choose a student:",students);}
    private static Course chooseCourse() throws PrerequisiteMissingException {if(courses.isEmpty())throw new PrerequisiteMissingException("Register a course first (menu option 5).");return choose("Choose a course:",courses);}
    private static void enrollStudent() throws Exception {
        Student s=chooseStudent();Course c=chooseCourse();String sem=required("Semester (e.g. 2026-S1): ");
        Enrollment e=new Enrollment(newId("ENR"),s,c,LocalDate.now(),sem,"ACTIVE");e.validate();enrollments.add(e);System.out.println("Enrollment saved: "+e);
    }
    private static Enrollment chooseEnrollment() throws PrerequisiteMissingException {if(enrollments.isEmpty())throw new PrerequisiteMissingException("Enroll a student first (menu option 7).");return choose("Choose an enrollment:",enrollments);}
    private static void recordAssessment() throws Exception {
        Enrollment e=chooseEnrollment();String type=required("Assessment type: ");double max=decimal("Maximum score: ",0.01,100000);double score=decimal("Obtained score: ",0,max);
        Assessment a=new Assessment(newId("ASM"),e,type,max,score,LocalDate.now());a.validate();assessments.add(a);System.out.println("Assessment saved: "+a);
    }
    private static void recordPayment() throws Exception {
        Student s=chooseStudent();BigDecimal amount=new BigDecimal(required("Amount: "));String method=required("Payment method: "),receipt=required("Receipt number: ");
        FeePayment p=new FeePayment(newId("PAY"),s,amount,LocalDate.now(),method,receipt);p.validate();payments.add(p);System.out.println("Payment saved: "+p);
    }
    private static void registerBook() throws Exception {
        String isbn=required("ISBN: "),title=required("Book title: "),author=required("Author: ");int copies=number("Number of copies: ",1,10000);
        LibraryBook b=new LibraryBook(newId("BOK"),isbn,title,author,copies,copies);b.validate();books.add(b);System.out.println("Book registered: "+b);
    }
    private static void borrowBook() throws Exception {
        Student s=chooseStudent();List<LibraryBook> available=books.stream().filter(b->b.getAvailableCopies()>0).toList();
        if(available.isEmpty())throw new BookUnavailableException("There are no available books.");
        LibraryBook b=choose("Choose a book to borrow:",available);b.borrow();BookLoan loan=new BookLoan(newId("LON"),s,b,LocalDate.now(),LocalDate.now().plusDays(14),null);loan.validate();loans.add(loan);System.out.println("Book borrowed. Return by "+LocalDate.now().plusDays(14)+". Loan ID: "+loan.getId());
    }
    private static void returnBook() throws Exception {
        List<BookLoan> active=loans.stream().filter(BookLoan::isActive).toList();if(active.isEmpty())throw new RecordNotFoundException("There are no books currently borrowed.");
        BookLoan l=choose("Choose the loan to return:",active);l.setReturnedDate(LocalDate.now());l.getBook().giveBack();System.out.println("Book returned successfully: "+l.getBook().getDisplayName());
    }
    private static void viewAll() {
        List<Identifiable> all=allIdentifiable();if(all.isEmpty()){System.out.println("No records have been registered yet.");return;}
        for(Identifiable x:all)System.out.println(x.getId()+" | "+x.getDisplayName()+" | Active: "+x.isActive());
    }
    private static void searchById() throws RecordNotFoundException {
        String id=required("Enter record ID: ");Object x=find(id);if(x==null)throw new RecordNotFoundException("No record found with ID "+id);
        System.out.println(x);
    }
    private static void updateRecord() throws Exception {
        String id=required("Enter record ID to update: ");Object x=find(id);
        if(x==null)throw new RecordNotFoundException("No record found with ID "+id);
        if(x instanceof LibraryBook){LibraryBook b=(LibraryBook)x;System.out.println("Current: "+b);System.out.println("This version supports stock changes through borrow/return; book identity fields are kept unchanged.");}
        else if(x instanceof Classroom){System.out.println("Classroom found: "+x+". Update is limited to availability in this starter version.");}
        else if(x instanceof Enrollment){Enrollment e=(Enrollment)x;System.out.println("Enrollment found: "+e+". Status: "+(e.isActive()?"ACTIVE":"INACTIVE"));}
        else System.out.println("Record found: "+x+". This record type has no editable fields in this version.");
    }
    private static void academicSummary() throws Exception {
        Student s=chooseStudent();System.out.println("\nACADEMIC SUMMARY: "+s.getDisplayName());
        for(Enrollment e:enrollments)if(e.getStudent()==s){System.out.println(e);for(Assessment a:assessments)System.out.println("  "+a);}
    }
    private static void paymentHistory() throws Exception {
        Student s=chooseStudent();System.out.println("\nPAYMENT HISTORY: "+s.getDisplayName());boolean found=false;
        for(FeePayment p:payments)if(p.getStudent()==s){System.out.println(p);found=true;}if(!found)System.out.println("No payments recorded.");
    }
    private static void availableBooks() {
        boolean found=false;for(LibraryBook b:books)if(b.getAvailableCopies()>0){System.out.println(b);found=true;}if(!found)System.out.println("No books are currently available.");
    }
    private static void demonstratePolymorphism() {
        List<Person> people=new ArrayList<>();people.addAll(students);people.addAll(teachers);people.addAll(admins);
        System.out.println("Person polymorphism:");
        if(people.isEmpty())System.out.println("Register a student, teacher or administrator first.");
        for(Person p:people)System.out.println(p.getDisplayName()+" -> "+p.getRoleDescription());
        System.out.println("Identifiable polymorphism:");
        for(Identifiable x:allIdentifiable())System.out.println(x.getId()+" -> "+x.getDisplayName());
    }
}
