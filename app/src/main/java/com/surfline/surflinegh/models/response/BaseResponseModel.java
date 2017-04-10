package com.surfline.surflinegh.models.response;

/**
 * Created by shubhamlamba on 21/03/17.
 */

public class BaseResponseModel {

    private String statusCode;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }



}
