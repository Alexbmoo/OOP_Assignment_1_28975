package com.auca.enrollment;
import java.time.LocalDate;import com.auca.interfaces.*;import com.auca.exceptions.ValidationException;import com.auca.student.Student;import com.auca.course.Course;
public class Enrollment implements Identifiable,Registrable {
    private String enrollmentId,semester,enrollmentStatus;private Student student;private Course course;private LocalDate enrollmentDate;
    public Enrollment(String id,Student s,Course c,LocalDate date,String sem,String status){enrollmentId=id;student=s;course=c;enrollmentDate=date;semester=sem;enrollmentStatus=status;}
    public String getId(){return enrollmentId;}public String getDisplayName(){return student.getDisplayName()+" / "+course.getCourseTitle();}public boolean isActive(){return "ACTIVE".equals(enrollmentStatus);}
    public void validate()throws ValidationException{if(student==null||course==null||semester==null||semester.isBlank())throw new ValidationException("Student, course and semester are required.");}
    public void printDetails(){System.out.println(getRegistrationSummary());}public String getRegistrationSummary(){return enrollmentId+" - "+getDisplayName()+" ("+semester+")";}
    public Student getStudent(){return student;}public String toString(){return getRegistrationSummary();}
}
