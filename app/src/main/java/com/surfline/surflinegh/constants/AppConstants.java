package com.surfline.surflinegh.constants;


public interface AppConstants {

    int SPLASH_TIMEOUT = 3000;

    int BACK_EVENT_ID = 0;
    int FILTER_EVENT_ID = 1;
    int CITY_SEARCH_EVENT_ID = 2;
    int STORE_SEARCH_EVENT_ID = 3;
    int EMPTY_VIEW_EVENT_ID = 4;
    int CLEAR_FORM_EVENT_ID = 5;
    int SHARE_OFFER_EVENT_ID = 6;
    int SAVE_OFFER_EVENT_ID = 7;
//    int TRANSACTION_HISTORY_EVENT_ID = 1;
//    int TRANSACTION_FILTER_EVENT_ID = 2;


//    String CUSTOM_FONT = "fonts/Lato-Regular.ttf";
//    String DEFAULT_FONT = "SERIF";

    int NONE = 0;
    int LOAD = 1;
    int LINK = 2;

    int FILTER_PRICE_MATCH = 0;
    int FILTER_OFFERS = 1;
    int FILTER_OTHERS = 2;


//    Bundle Constants

    String SCREEN_TITLE = "tile";
    String URL = "url";
    String URL1 = "url1";
    String URL2 = "url2";
    String FROM_SIGNUP_SCREEN = "from_sign_up_screen";
    String WALLET_DETAIL = "wallet_detail";
    String OTP = "otp";
    String IS_LINKED = "is_linked";
    String CARD_TYPE = "card_type";
    String CARD_NUMBER = "card_number";
    String WALLET_ID = "wallet_ID";
    String REFRESH_LIST = "refresh_list";
    String HISTORY_DETAIL = "history_detail";
    String HISTORY_LIST = "history_list";
    String WALLET_NAME = "walletName";
    String POST_DATA = "post_data";
    String BANNER_LIST = "banner_list";
    String SCALE = "scale";
    String POSITION = "pos";
    String IS_MY_WALLET = "my_Wallet";
    String SHOW_LINK_DIALOG = "show_link_dialog";
    String LINK_LOAD = "link_load";
    String LOADED_AMOUT = "loaded_amount";
    String DO_NOT_SHOW_TITLE = "don't show title";
    String PROFILE_DATA = "profile_data";
    String FROM_MY_ACCOUNT = "from_my_account";
    String FROM_WEB_VIEW = "from_web_view";
//    String FROM_PRICE_MATCH = "from_price_match";
    String CITY = "city";
    String STORE = "store";
    String STORE_CODE = "store_code";
    String DATE = "date";
    String TRANSACTION_NO = "trans_no";
    String TILL_NO = "till_number";
    String IS_MANUAL_CHECK = "is_manual_check";
    String CLEAR_STACK = "clear_stack";
    String TRANSACTION_ID = "transaction_id";
    String SHOW_PRICE_MATCH = "show_price_match";
    //    String TRANS_FROM_PRICE_MATCH = "from_price_match";
    String VIDEO_POSITION = "video_position";

    //Dialog Constants

    int VERIFY_NUMBER_DIALOG = 0;
    int VERIFYING_OTP_DIALOG = 1;
    int VERIFY_OTP_DIALOG = 2;
    int ENTER_OTP_DIALOG = 3;
    int DELINK_DIALOG = 4;
    int DELINK_STATUS_DIALOG = 5;
    int TRANSACTION_HISTORY_FILTER_DIALOG = 6;

    int DOB_DIALOG = 7;
    int ENTER_PASSWORD_DIALOG = 8;
    int LOGOUT_USER_DIALOG = 9;
    int CALL_DIALOG = 10;
    int DATE_DIALOG = 11;
    int WALLET_LIST_DIALOG = 12;
    int PRICE_MATCH_NOW_DIALOG = 13;
    int PRICE_MATCH_DATE_DIALOG = 14;

    // update profile
    int CAPTURE_IMAGE_REQ_CODE = 4;
    int SELECT_IMAGE_REQ_CODE = 5;
    int MY_PERMISSIONS_REQUEST_CAMERA = 6;
    int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 7;
    int MY_PERMISSIONS_REQUEST_CALL = 8;
    int MY_PERMISSIONS_REQUEST_SMS = 9;

    //SMS Constants
    String ACTION_LOGIN_OTP = ".login_otp";
    String ACTION_FORGOT_OTP = ".forgot_otp";
    String ACTION_SIGNUP_OTP = ".signup_otp";
    String ACTION_CARD_LINK_OTP = ".card_link_otp";
    String ACTION_CARD_DELINK_OTP = ".card_de_link";
    //
    int SMS_TYPE_LOGIN = 1;
    int SMS_TYPE_FORGOT = 2;
    int SMS_TYPE_SIGNUP = 3;
    int SMS_TYPE_CARD_LINK = 4;
    int SMS_TYPE_CARD_DE_LINK = 5;

    String DEBITED = "debit";
    String REDEMPTION = "redemption";
    String CREDITED = "credit";
    String FAILURE = "failure";
    //    String ADDED = "Load Money";
    String ADDED = "Added";
    String REWARDED = "Rewarded";
    String PM_REWARDED = "PMRewarded";
    String PAID = "Paid";
    String POS = "POS";
    String BIG_BAZAAR = "BIG BAZAAR";
    String EZONE = "E ZONE";
    String HOME_TOWN = "HOME TOWN";
//    String FOOD_BAZAAR = "FOOD BAZAAR";
//    String FBB = "FBB";
    int REFRESH_LIST_HIT_API = 0;
    int REFRESH_LIST_UPDATE_LIST = 1;


    //Toolbar Change

//    int DEFAULT = 0;
//    int HISTORY = 1;
//    int FILTER = 2;
//    int CROSS = 3;
//    int USER = 4;


    //Menu

    int Store_locator = 0;
    int Retailer = 1;
    int Bundles = 2;
    int Devices = 3;
    int Company = 4;





}
