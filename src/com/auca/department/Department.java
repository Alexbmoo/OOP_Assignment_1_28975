package com.auca.department;
import com.auca.interfaces.*; import com.auca.exceptions.ValidationException; import com.auca.teacher.Teacher;
public class Department implements Identifiable,Registrable {
    private String departmentId,departmentName,departmentCode,officeLocation,contactEmail; private Teacher headTeacher;
    public Department(String id,String name,String code,String office,String email,Teacher head){departmentId=id;departmentName=name;departmentCode=code;officeLocation=office;contactEmail=email;headTeacher=head;}
    public String getId(){return departmentId;} public String getDisplayName(){return departmentName;} public boolean isActive(){return true;}
    public void validate() throws ValidationException {if(departmentName==null||departmentName.trim().length()<2)throw new ValidationException("Department name is required.");if(contactEmail==null||!contactEmail.contains("@"))throw new ValidationException("Valid department email is required.");}
    public void printDetails(){System.out.println(getRegistrationSummary());} public String getRegistrationSummary(){return departmentId+" - "+departmentName;}
    public String getDepartmentName(){return departmentName;} public String toString(){return getRegistrationSummary()+" ["+departmentCode+"]";}
}
