package com.auca.interfaces;
import com.auca.exceptions.ValidationException;
public interface Registrable {
    int MIN_NAME_LENGTH = 2, MAX_NAME_LENGTH = 60, MAX_EMAIL_LENGTH = 100;
    int MIN_PHONE_LENGTH = 10, MAX_PHONE_LENGTH = 15;
    String DATE_PATTERN = "yyyy-MM-dd";
    void validate() throws ValidationException;
    void printDetails();
    String getRegistrationSummary();
}
