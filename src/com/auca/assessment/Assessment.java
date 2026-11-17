package com.auca.assessment;
import java.time.LocalDate;import com.auca.interfaces.*;import com.auca.exceptions.ValidationException;import com.auca.enrollment.Enrollment;
public class Assessment implements Identifiable,Registrable {
    private String assessmentId,assessmentType;private Enrollment enrollment;private double maximumScore,obtainedScore;private LocalDate assessmentDate;
    public Assessment(String id,Enrollment e,String type,double max,double score,LocalDate date){assessmentId=id;enrollment=e;assessmentType=type;maximumScore=max;obtainedScore=score;assessmentDate=date;}
    public String getId(){return assessmentId;}public String getDisplayName(){return assessmentType;}public boolean isActive(){return true;}
    public void validate()throws ValidationException{if(enrollment==null||maximumScore<=0||obtainedScore<0||obtainedScore>maximumScore)throw new ValidationException("Assessment scores are invalid.");}
    public void printDetails(){System.out.println(getRegistrationSummary());}public String getRegistrationSummary(){return assessmentId+" - "+assessmentType+": "+obtainedScore+"/"+maximumScore;}
    public String toString(){return getRegistrationSummary();}
}
