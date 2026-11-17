package com.auca.student;
import java.time.LocalDate; import com.auca.abstracts.AcademicMember; import com.auca.department.Department;
public class Student extends AcademicMember {
    private String studentNumber, programName, guardianPhone;
    private LocalDate admissionDate; private int studyYear; private boolean scholarshipStatus;
    public Student(String id,String f,String l,String e,String p,LocalDate dob,String member,Department d,LocalDate join,String status,String ie,String advisor,String no,String program,LocalDate admission,int year,String guardian,boolean scholarship){
        super(id,f,l,e,p,dob,member,d,join,status,ie,advisor); studentNumber=no;programName=program;admissionDate=admission;studyYear=year;guardianPhone=guardian;scholarshipStatus=scholarship;
    }
    public String getRoleDescription(){return "Student";} public String getAcademicCategory(){return "Student";}
    public String getStudentNumber(){return studentNumber;} public String getProgramName(){return programName;} public int getStudyYear(){return studyYear;}
    public String toString(){return getRegistrationSummary()+" | "+programName+" | Year "+studyYear;}
}
