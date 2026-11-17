package com.auca.abstracts;
import java.time.LocalDate;
import com.auca.interfaces.*;
import com.auca.exceptions.ValidationException;
public abstract class Person implements Identifiable, Registrable {
    protected String personId, firstName, lastName, email, phoneNumber;
    protected LocalDate dateOfBirth;
    public Person(String id,String first,String last,String email,String phone,LocalDate dob) {
        this.personId=id; this.firstName=first; this.lastName=last; this.email=email; this.phoneNumber=phone; this.dateOfBirth=dob;
    }
    public abstract String getRoleDescription();
    public String getId(){return personId;}
    public String getDisplayName(){return firstName+" "+lastName;}
    public boolean isActive(){return true;}
    public void validate() throws ValidationException {
        if(blank(personId)||blank(firstName)||blank(lastName)) throw new ValidationException("ID, first name and last name are required.");
        if(firstName.length()<2||lastName.length()<2) throw new ValidationException("Names must contain at least 2 characters.");
        if(email==null||!email.matches("^[^@\\\\s]+@[^@\\\\s]+\\\\.[^@\\\\s]+$")) throw new ValidationException("Enter a valid email address.");
        if(phoneNumber==null||phoneNumber.replaceAll("\\\\D","").length()<10) throw new ValidationException("Phone must contain at least 10 digits.");
        if(dateOfBirth==null||dateOfBirth.isAfter(LocalDate.now())) throw new ValidationException("Enter a valid date of birth.");
    }
    protected boolean blank(String s){return s==null||s.trim().isEmpty();}
    public void printDetails(){System.out.println(getRegistrationSummary()+" | "+email+" | "+phoneNumber);}
    public String getRegistrationSummary(){return personId+" - "+getDisplayName()+" ("+getRoleDescription()+")";}
    public String getFirstName(){return firstName;} public String getLastName(){return lastName;}
    public String getEmail(){return email;} public String getPhoneNumber(){return phoneNumber;}
    public LocalDate getDateOfBirth(){return dateOfBirth;}
}
