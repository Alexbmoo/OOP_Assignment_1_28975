package com.auca.librarybook;
import com.auca.interfaces.*;import com.auca.exceptions.ValidationException;
public class LibraryBook implements Identifiable,Registrable {
    private String bookId,isbn,title,author;private int totalCopies,availableCopies;
    public LibraryBook(String id,String isbn,String title,String author,int total,int available){bookId=id;this.isbn=isbn;this.title=title;this.author=author;totalCopies=total;availableCopies=available;}
    public String getId(){return bookId;}public String getDisplayName(){return title;}public boolean isActive(){return availableCopies>0;}
    public void validate()throws ValidationException{if(title==null||title.isBlank()||totalCopies<1||availableCopies<0||availableCopies>totalCopies)throw new ValidationException("Book details or copy counts are invalid.");}
    public void printDetails(){System.out.println(getRegistrationSummary());}public String getRegistrationSummary(){return bookId+" - "+title+" by "+author;}
    public int getAvailableCopies(){return availableCopies;}public void borrow(){if(availableCopies>0)availableCopies--;}public void giveBack(){if(availableCopies<totalCopies)availableCopies++;}
    public String toString(){return getRegistrationSummary()+" (available "+availableCopies+"/"+totalCopies+")";}
}
