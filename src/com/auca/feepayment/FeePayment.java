package com.auca.feepayment;
import java.time.LocalDate;import java.math.BigDecimal;import com.auca.interfaces.*;import com.auca.exceptions.ValidationException;import com.auca.student.Student;
public class FeePayment implements Identifiable,Registrable {
    private String paymentId,paymentMethod,receiptNumber;private Student student;private BigDecimal amount;private LocalDate paymentDate;
    public FeePayment(String id,Student s,BigDecimal amount,LocalDate date,String method,String receipt){paymentId=id;student=s;this.amount=amount;paymentDate=date;paymentMethod=method;receiptNumber=receipt;}
    public String getId(){return paymentId;}public String getDisplayName(){return "Payment by "+student.getDisplayName();}public boolean isActive(){return true;}
    public void validate()throws ValidationException{if(student==null||amount==null||amount.signum()<=0)throw new ValidationException("Student and positive payment amount are required.");}
    public void printDetails(){System.out.println(getRegistrationSummary());}public String getRegistrationSummary(){return paymentId+" - "+student.getDisplayName()+" paid "+amount;}
    public Student getStudent(){return student;}public String toString(){return getRegistrationSummary();}
}
