package com.auca.abstracts;
import java.time.LocalDate;
import com.auca.department.Department;
public abstract class AcademicMember extends Person {
    protected String memberNumber, academicStatus, institutionalEmail, advisorName;
    protected Department department; protected LocalDate joinDate;
    public AcademicMember(String id,String f,String l,String e,String p,LocalDate dob,String member,Department dept,LocalDate join,String status,String instEmail,String advisor){
        super(id,f,l,e,p,dob); memberNumber=member; department=dept; joinDate=join; academicStatus=status; institutionalEmail=instEmail; advisorName=advisor;
    }
    public abstract String getAcademicCategory();
    @Override public boolean isActive(){return "ACTIVE".equalsIgnoreCase(academicStatus);}
    public String getMemberNumber(){return memberNumber;} public Department getDepartment(){return department;}
}
