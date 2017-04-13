package com.surfline.surflinegh.models.response;

import java.util.ArrayList;

/**
 * Created by shubhamlamba on 23/03/17.
 */

public class LoginResponseModel extends BaseResponseModel {



    private ArrayList<String> msisdn;
    private String  contactId;
    private String dateOfBirth;
    private String  firstName;
    private String  lastName;
    private String  emailId;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<String> getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(ArrayList<String> msisdn) {
        this.msisdn = msisdn;
    }
}
