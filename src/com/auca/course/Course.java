package com.auca.course;
import com.auca.interfaces.*;import com.auca.exceptions.ValidationException;import com.auca.department.Department;import com.auca.teacher.Teacher;
public class Course implements Identifiable,Registrable {
    private String courseId,courseCode,courseTitle;private int creditHours;private Department department;private Teacher assignedTeacher;
    public Course(String id,String code,String title,int credits,Department d,Teacher t){courseId=id;courseCode=code;courseTitle=title;creditHours=credits;department=d;assignedTeacher=t;}
    public String getId(){return courseId;}public String getDisplayName(){return courseTitle;}public boolean isActive(){return true;}
    public void validate()throws ValidationException{if(courseTitle==null||courseTitle.trim().isEmpty()||creditHours<1)throw new ValidationException("Course title and positive credit hours are required.");}
    public void printDetails(){System.out.println(getRegistrationSummary());}public String getRegistrationSummary(){return courseId+" - "+courseCode+" "+courseTitle;}
    public String getCourseTitle(){return courseTitle;}public String toString(){return getRegistrationSummary();}
}
