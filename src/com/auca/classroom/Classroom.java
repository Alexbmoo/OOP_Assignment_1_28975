package com.auca.classroom;
import com.auca.interfaces.*;import com.auca.exceptions.ValidationException;
public class Classroom implements Identifiable,Registrable {
    private String classroomId,buildingName,roomNumber;private int capacity,floorNumber;private boolean available;
    public Classroom(String id,String building,String room,int capacity,int floor,boolean available){classroomId=id;buildingName=building;roomNumber=room;this.capacity=capacity;floorNumber=floor;this.available=available;}
    public String getId(){return classroomId;}public String getDisplayName(){return buildingName+" "+roomNumber;}public boolean isActive(){return available;}
    public void validate()throws ValidationException{if(capacity<1||floorNumber<0)throw new ValidationException("Capacity must be positive and floor cannot be negative.");}
    public void printDetails(){System.out.println(getRegistrationSummary());}public String getRegistrationSummary(){return classroomId+" - "+getDisplayName();}
    public String toString(){return getRegistrationSummary()+" (capacity "+capacity+")";}
}
