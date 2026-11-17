package com.auca.administrator;
import java.time.LocalDate; import com.auca.abstracts.Person;
public class Administrator extends Person {
    private String staffNumber,jobTitle,assignedOffice; private LocalDate employmentDate; private int accessLevel; private boolean active;
    public Administrator(String id,String f,String l,String e,String p,LocalDate dob,String staff,String title,String office,LocalDate date,int level,boolean active){
        super(id,f,l,e,p,dob);staffNumber=staff;jobTitle=title;assignedOffice=office;employmentDate=date;accessLevel=level;this.active=active;
    }
    public String getRoleDescription(){return "Administrator";} public boolean isActive(){return active;}
    public String toString(){return getRegistrationSummary()+" | "+jobTitle;}
}
