package com.surfline.surflinegh.ui.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.surfline.BuildConfig;
import com.surfline.R;
import com.surfline.base.application.BaseApplication;
import com.surfline.base.listeners.UpdateGsonListener;
import com.surfline.base.network.VolleyGsonRequest;
import com.surfline.base.ui.activity.BaseActivity;
import com.surfline.base.utils.NetworkUtil;
import com.surfline.base.utils.ToastUtil;
import com.surfline.surflinegh.constants.ApiConstants;
import com.surfline.surflinegh.models.request.SignUpCheckRequestModel;
import com.surfline.surflinegh.models.request.SignUpRegisterDataRequest;
import com.surfline.surflinegh.models.response.BaseResponseModel;
import com.surfline.surflinegh.models.response.LoginResponseModel;
import com.surfline.surflinegh.persistence.PrefrenceConstants;
import com.surfline.surflinegh.persistence.SurflineCacheManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class SignupActivity extends BaseActivity implements UpdateGsonListener.onUpdateViewListener,View.OnClickListener {

    private EditText edtmob, edtdob, edtUsername, edtPassword,edtAns1,edtAns2,edtEmail;
    private TextView txvname;
    private Spinner spnQues1,spnQues2;
    private LinearLayout llyMoreInfo,llyInfo;
    private Calendar myCalendar = Calendar.getInstance();
    private static final String TAG = LoginActivity.class.getSimpleName();
    private Gson gson;
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String signup_firstname="",signup_lastname="",signup_email="",signup_contactid="";
    private String[] questionList1 = {};
    private String[] questionList2 = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        llyMoreInfo = (LinearLayout) findViewById(R.id.llyMoreInfo);
        llyInfo = (LinearLayout) findViewById(R.id.llyInfo);
        edtmob = (EditText) findViewById(R.id.edtmob);
        edtdob = (EditText) findViewById(R.id.edtdob);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
//        edtcontactId = (EditText) findViewById(R.id.edtcontactId);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        txvname = (TextView) findViewById(R.id.txvname);
        edtAns1 = (EditText) findViewById(R.id.edtAns1);
        edtAns2 = (EditText) findViewById(R.id.edtAns2);
        spnQues1 = (Spinner) findViewById(R.id.spnQues1);
        spnQues2 = (Spinner) findViewById(R.id.spnQues2);

        questionList1=getResources().getStringArray(R.array.selectBox1);
        questionList2=getResources().getStringArray(R.array.selectBox2);
        edtdob.setOnClickListener(this);
        setSpinner1();
        setSpinner2();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(llyMoreInfo.getVisibility()==View.GONE) {
                    if (validateForm(ApiConstants.REQUEST_TYPE.SIGN_UP_STEP1)) {
                        try {
                            hitApi(ApiConstants.REQUEST_TYPE.SIGN_UP_STEP1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    if (validateForm(ApiConstants.REQUEST_TYPE.SIGN_UP_STEP2)) {
                        try {
                            hitApi(ApiConstants.REQUEST_TYPE.SIGN_UP_STEP2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
//                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);

            }
        });
    }

    public void setSpinner1() {
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text_item, questionList1) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.black_color));
//                    tv.setTypeface(null, Typeface.BOLD);
                } else {
                    tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dialog_item);
        spnQues1.setAdapter(spinnerArrayAdapter);

//        spnQues1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                edtAns1.requestFocus();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }
    public void setSpinner2() {

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text_item, questionList2) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(getResources().getColor(R.color.black_color));
//                    tv.setTypeface(null, Typeface.BOLD);

                } else {
                    tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dialog_item);
        spnQues2.setAdapter(spinnerArrayAdapter);
//        spnQues2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                edtAns2.requestFocus();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }

    private void hitApi(int reqType) throws Exception {
        if (ApiConstants.isMock) {
            switch (reqType) {
                case ApiConstants.REQUEST_TYPE.SIGN_UP_STEP1:
                    updateView(new Gson().fromJson(ApiConstants.MockResponse.SIGN_UP_STEP1, LoginResponseModel.class), true, reqType);
                    break;
                case ApiConstants.REQUEST_TYPE.SIGN_UP_STEP2:
                    updateView(new Gson().fromJson(ApiConstants.MockResponse.SIGN_UP_STEP2, BaseResponseModel.class), true, reqType);
                    break;


            }
            return;
        }
        if (!NetworkUtil.isNetworkAvailable(this)) {
            ToastUtil.showShortToast(this, getResources().getString(R.string.network_error));
            return;
        }

        String loadMessage = getResources().getString(R.string.loading);
        String url = "";
        String body = "";
        Class className = null;
        switch (reqType) {
            case ApiConstants.REQUEST_TYPE.SIGN_UP_STEP1:
                url = ApiConstants.Urls.SIGN_UP_STEP1;
                body = createJsonReq(reqType);
                className = LoginResponseModel.class;
                break;
            case ApiConstants.REQUEST_TYPE.SIGN_UP_STEP2:
                url = ApiConstants.Urls.SIGN_UP_STEP2;
                body = createJsonReq(reqType);
                className = BaseResponseModel.class;
                break;

        }

        if (!TextUtils.isEmpty(body))
            ((BaseApplication) getApplication()).getVolleyManager().addToRequestQueue(VolleyGsonRequest.doPost(url,
                    new UpdateGsonListener<>(this, reqType), className
                    , body), "" + reqType);
        showProgressdialog(loadMessage);
    }

    private String createJsonReq(int reqType) throws Exception {
        if (gson == null)
            gson = new GsonBuilder().disableHtmlEscaping().create();
        switch (reqType) {
            case ApiConstants.REQUEST_TYPE.SIGN_UP_STEP1:
                SignUpCheckRequestModel signUpCheckRequestModel = new SignUpCheckRequestModel();
                signUpCheckRequestModel.setMsisdn(edtmob.getText().toString().trim());
                signUpCheckRequestModel.setDateOfBirth(edtdob.getText().toString().trim());

                return gson.toJson(signUpCheckRequestModel);
            case ApiConstants.REQUEST_TYPE.SIGN_UP_STEP2:
                SignUpRegisterDataRequest signUpRegisterDataRequest = new SignUpRegisterDataRequest();
                signUpRegisterDataRequest.setUserName(edtUsername.getText().toString().trim());
                signUpRegisterDataRequest.setPassword(edtPassword.getText().toString().trim());
                signUpRegisterDataRequest.setContactId(signup_contactid);
                signUpRegisterDataRequest.setFirstName(signup_firstname);
                signUpRegisterDataRequest.setLastName(signup_lastname);
                signUpRegisterDataRequest.setEmailId(signup_email);
                signUpRegisterDataRequest.setQues1(""+getResources().getStringArray(R.array.selectBox1_IDs)[spnQues1.getSelectedItemPosition()]);
                signUpRegisterDataRequest.setQues2(""+getResources().getStringArray(R.array.selectBox2_IDs)[spnQues2.getSelectedItemPosition()]);
                signUpRegisterDataRequest.setAns1(edtAns1.getText().toString().trim());
                signUpRegisterDataRequest.setAns2(edtAns2.getText().toString().trim());

                return gson.toJson(signUpRegisterDataRequest);


        }
        return "";


    }

    @Override
    public void updateView(Object responseObject, boolean isSuccess, int reqType) {

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "Response String---------" + responseObject.toString());
        }

        try {
            hideProgressDialog();
            if (isSuccess && responseObject != null) {
                switch (reqType) {
                    case ApiConstants.REQUEST_TYPE.SIGN_UP_STEP1:
                        if (responseObject instanceof LoginResponseModel) {
                            LoginResponseModel loginResponseModel = (LoginResponseModel) responseObject;
                            if (!TextUtils.isEmpty(loginResponseModel.getStatusCode())) {

                                if (loginResponseModel.getStatusCode().equalsIgnoreCase(ApiConstants.Values.ResponseCodes.SUCCESS)) {

//                                    SurflineCacheManager.getInstance(SignupActivity.this).setBoolean(PrefrenceConstants.LOGGED_IN, true);
                                    llyMoreInfo.setVisibility(View.VISIBLE);
                                    llyInfo.setVisibility(View.GONE);
                                    edtmob.setEnabled(false);
                                    edtdob.setEnabled(false);
                                    edtUsername.requestFocus();
                                    signup_email=loginResponseModel.getEmailId();
                                    signup_firstname=loginResponseModel.getFirstName();
                                    signup_lastname=loginResponseModel.getLastName();
                                    signup_contactid=loginResponseModel.getContactId();
                                    edtUsername.requestFocus();
                                    txvname.setText(loginResponseModel.getFirstName()+" "+loginResponseModel.getLastName());
                                    edtEmail.setText(loginResponseModel.getEmailId());

//                                    hitApi(ApiConstants.REQUEST_TYPE.SIGN_UP_STEP2);

                                } else {
                                    ToastUtil.showLongToast(SignupActivity.this, loginResponseModel.getMessage());
                                }
                            } else {
                                ToastUtil.showLongToast(SignupActivity.this, getResources().getString(R.string.common_error_msg));
                            }
                        } else {
                            ToastUtil.showLongToast(SignupActivity.this, getResources().getString(R.string.common_error_msg));
                        }

                        break;

                    case ApiConstants.REQUEST_TYPE.SIGN_UP_STEP2:
                        if (responseObject instanceof LoginResponseModel) {
                            LoginResponseModel loginResponseModel = (LoginResponseModel) responseObject;
                            if (!TextUtils.isEmpty(loginResponseModel.getStatusCode())) {

                                if (loginResponseModel.getStatusCode().equalsIgnoreCase(ApiConstants.Values.ResponseCodes.SUCCESS)) {

                                    SurflineCacheManager.getInstance(SignupActivity.this).setBoolean(PrefrenceConstants.LOGGED_IN, true);

                                    SurflineCacheManager.getInstance(SignupActivity.this).setString(PrefrenceConstants.EMAIL, loginResponseModel.getEmailId());
                                    SurflineCacheManager.getInstance(SignupActivity.this).setArrayList(PrefrenceConstants.MOBILE_NO, loginResponseModel.getMsisdn());
                                    SurflineCacheManager.getInstance(SignupActivity.this).setString(PrefrenceConstants.FIRST_NAME, loginResponseModel.getFirstName());
                                    SurflineCacheManager.getInstance(SignupActivity.this).setString(PrefrenceConstants.LAST_NAME, loginResponseModel.getLastName());
                                    SurflineCacheManager.getInstance(SignupActivity.this).setString(PrefrenceConstants.CONTACT_ID, loginResponseModel.getContactId());

                                    ToastUtil.showLongToast(this,"Registration Completed Successfully");
                                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    ToastUtil.showLongToast(SignupActivity.this, loginResponseModel.getMessage());
                                }
                            } else {
                                ToastUtil.showLongToast(SignupActivity.this, getResources().getString(R.string.common_error_msg));
                            }
                        } else {
                            ToastUtil.showLongToast(SignupActivity.this, getResources().getString(R.string.common_error_msg));
                        }

                        break;


                }

            } else {
                ToastUtil.showLongToast(SignupActivity.this, getResources().getString(R.string.common_error_msg));
            }
        } catch (Exception e) {
            hideProgressDialog();
            ToastUtil.showLongToast(SignupActivity.this, getResources().getString(R.string.common_error_msg));
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edtdob:
                hideKeypad();
                new DatePickerDialog(this,R.style.DialogTheme,  date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;

        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDOB();
        }
    };

    private void updateDOB() {

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        edtdob.setText(sdf.format(myCalendar.getTime()));
    }


    private boolean validateForm(int reqType) {
        switch (reqType){
            case ApiConstants.REQUEST_TYPE.SIGN_UP_STEP1:
                if (edtmob.getText().toString().trim().length() == 0 || edtmob.getText().toString().trim().length()<10) {
                    ToastUtil.showLongToast(this, "Please enter valid Mobile Number");
                    return false;
                }
                if (edtdob.getText().toString().trim().length() == 0) {
                    ToastUtil.showLongToast(this, "Please enter Date of birth");
                    return false;
                }
                break;
            case ApiConstants.REQUEST_TYPE.SIGN_UP_STEP2:

                if (edtEmail.getText().toString().trim().length() == 0 || !Pattern.matches(EMAIL_REGEX, edtEmail.getText().toString().trim())) {
                    ToastUtil.showLongToast(this, "Please enter valid Email Id");
                    return false;
                }

                if (edtUsername.getText().toString().trim().length() == 0 || edtUsername.getText().toString().trim().length()<5) {
                    ToastUtil.showLongToast(this, "Please enter valid Username");
                    return false;
                }

                if (edtPassword.getText().toString().trim().length() == 0 || edtPassword.getText().toString().trim().length()<5) {
                    ToastUtil.showLongToast(this, "Please enter valid Password");
                    return false;
                }
                if (edtAns1.getText().toString().trim().length() == 0) {
                    ToastUtil.showLongToast(this, "Please enter Answer for Question 1");
                    return false;
                }
                if (edtAns2.getText().toString().trim().length() == 0) {
                    ToastUtil.showLongToast(this, "Please enter Answer for Question 2");
                    return false;
                }
                if (spnQues1.getSelectedItemPosition() == 0) {
                    ToastUtil.showLongToast(this, "Please choose Question 1");
                    return false;
                }
                if (spnQues2.getSelectedItemPosition() == 0) {
                    ToastUtil.showLongToast(this, "Please choose Question 2");
                    return false;
                }
                break;
        }
        return true;
    }
}
