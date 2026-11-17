package com.auca.teacher;
import java.time.LocalDate; import java.math.BigDecimal; import com.auca.abstracts.AcademicMember; import com.auca.department.Department;
public class Teacher extends AcademicMember {
    private String employeeNumber,specialization,officeNumber,employmentType; private LocalDate hireDate; private BigDecimal salary;
    public Teacher(String id,String f,String l,String e,String p,LocalDate dob,String member,Department d,LocalDate join,String status,String ie,String advisor,String no,String spec,LocalDate hire,BigDecimal salary,String office,String type){
        super(id,f,l,e,p,dob,member,d,join,status,ie,advisor); employeeNumber=no;specialization=spec;hireDate=hire;this.salary=salary;officeNumber=office;employmentType=type;
    }
    public String getRoleDescription(){return "Teacher";} public String getAcademicCategory(){return "Teacher";}
    public String getEmployeeNumber(){return employeeNumber;} public String toString(){return getRegistrationSummary()+" | "+specialization;}
}
