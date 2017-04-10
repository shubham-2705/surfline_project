package com.surfline.surflinegh.models.request;

/**
 * Created by shubhamlamba on 23/03/17.
 */

public class LoginRequestModel {

    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
