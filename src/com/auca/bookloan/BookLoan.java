package com.auca.bookloan;
import java.time.LocalDate;import com.auca.interfaces.*;import com.auca.exceptions.ValidationException;import com.auca.student.Student;import com.auca.librarybook.LibraryBook;
public class BookLoan implements Identifiable,Registrable {
    private String loanId;private Student student;private LibraryBook book;private LocalDate borrowedDate,dueDate,returnedDate;
    public BookLoan(String id,Student s,LibraryBook b,LocalDate borrowed,LocalDate due,LocalDate returned){loanId=id;student=s;book=b;borrowedDate=borrowed;dueDate=due;returnedDate=returned;}
    public String getId(){return loanId;}public String getDisplayName(){return book.getDisplayName()+" borrowed by "+student.getDisplayName();}public boolean isActive(){return returnedDate==null;}
    public void validate()throws ValidationException{if(student==null||book==null||dueDate==null||borrowedDate==null)throw new ValidationException("Loan details are required.");}
    public void printDetails(){System.out.println(getRegistrationSummary());}public String getRegistrationSummary(){return loanId+" - "+getDisplayName()+" due "+dueDate;}
    public Student getStudent(){return student;}public LibraryBook getBook(){return book;}public LocalDate getReturnedDate(){return returnedDate;}public void setReturnedDate(LocalDate d){returnedDate=d;}
    public String toString(){return getRegistrationSummary()+(returnedDate==null?" [NOT RETURNED]":" [RETURNED]");}
}
