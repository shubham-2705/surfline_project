package com.surfline.surflinegh.models.request;

/**
 * Created by shubhamlamba on 29/03/17.
 */

public class SignUpCheckRequestModel {

    private String msisdn;
    private String dateOfBirth;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
}
