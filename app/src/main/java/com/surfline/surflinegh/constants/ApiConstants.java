package com.surfline.surflinegh.constants;


public interface ApiConstants {

    boolean isMock = false;


    interface Urls {
        String BASE_URL = "http://41.242.136.91:8090/SurflineAppServer/"; // QA
//        String BASE_URL_DEV = "http://103.253.37.61/"; // DEV

//        String BASE_URL = BASE_URL_DEV;


        String GET_BALANCE = BASE_URL + "get/user/balance";
        String SIGN_IN = BASE_URL + "user/login";
        String SIGN_UP_STEP1 = BASE_URL + "user/register/check";
        String SIGN_UP_STEP2 = BASE_URL + "user/register/data";



    }

    interface REQUEST_TYPE
    {
        int GET_BALANCE=0;
        int SIGN_IN=1;
        int SIGN_UP_STEP1=2;
        int SIGN_UP_STEP2=3;
    }



    interface Values {

        interface ResponseCodes {

            String SUCCESS="2000";
            String FAILURE="4004";
            String SERVER_ERROR="5000";

        }

        interface UserType {//based on response code
//            String NEW_USER = "1";
//            String ALREADY_EXISTING_USER = "0";
        }

    }

    interface MockResponse {
        String GET_BALANCE = "{\"statusCode\": \"2000\",\"message\": null,\"balanceData\": [{\"balanceType\": \"Current Balance\",\"balanceUnits\": \"100.34\",\"balanceExpiry\": \"mar 21,2017 15:00:00\"}]}";
        String SIGN_IN = "{\"msisdn\": \"123456\",\"contactId\": \"123\",\"dateOfBirth\": \"2017-01-01\",\"firstName\": \"Gary\",\"lastName\": \"J. Roder\",\"emailId\": \"garyjroder@gmail.com\",\"statusCode\": \"2000\",\"message\": \"Verfied User\"}";
        String SIGN_UP_STEP1 = "{\"msisdn\": \"123456\",\"contactId\": \"123\",\"dateOfBirth\": \"2017-01-01\",\"firstName\": \"Gary\",\"lastName\": \"J. Roder\",\"emailId\": \"garyjroder@gmail.com\",\"statusCode\": \"2000\",\"message\": \"Verfied User\"}";
        String SIGN_UP_STEP2 = "{\"statusCode\": \"2000\",\"message\": null,\"variable\": null}";
    }
}
