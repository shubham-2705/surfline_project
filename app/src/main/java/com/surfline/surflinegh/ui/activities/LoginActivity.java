package com.surfline.surflinegh.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.surfline.surflinegh.models.request.LoginRequestModel;
import com.surfline.surflinegh.models.response.LoginResponseModel;
import com.surfline.surflinegh.persistence.PrefrenceConstants;
import com.surfline.surflinegh.persistence.SurflineCacheManager;

public class LoginActivity extends BaseActivity implements UpdateGsonListener.onUpdateViewListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText edtEmail, edtPassword;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        TextView txvSignup = (TextView) findViewById(R.id.txvSignup);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateForm()) {

                    try {
                        hitApi(ApiConstants.REQUEST_TYPE.SIGN_IN);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        txvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);

            }
        });
    }


    private boolean validateForm() {
        if (edtEmail.getText().toString().trim().length() == 0) {
            ToastUtil.showLongToast(this, "Please enter valid Username/Email");
            return false;
        }
        if (edtPassword.getText().toString().trim().length() == 0) {
            ToastUtil.showLongToast(this, "Please enter Password");
            return false;
        }
        return true;
    }

    private void hitApi(int reqType) throws Exception {
        if (ApiConstants.isMock) {
            switch (reqType) {
                case ApiConstants.REQUEST_TYPE.SIGN_IN:
                    updateView(new Gson().fromJson(ApiConstants.MockResponse.SIGN_IN, LoginResponseModel.class), true, reqType);
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
            case ApiConstants.REQUEST_TYPE.SIGN_IN:
                url = ApiConstants.Urls.SIGN_IN;
                body = createJsonReq(reqType);
                className = LoginResponseModel.class;
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
            case ApiConstants.REQUEST_TYPE.SIGN_IN:
                LoginRequestModel loginRequestModel = new LoginRequestModel();
                loginRequestModel.setUserName(edtEmail.getText().toString().trim());
                loginRequestModel.setPassword(edtPassword.getText().toString().trim());

                return gson.toJson(loginRequestModel);


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
                    case ApiConstants.REQUEST_TYPE.SIGN_IN:
                        if (responseObject instanceof LoginResponseModel) {
                            LoginResponseModel loginResponseModel = (LoginResponseModel) responseObject;
                            if (!TextUtils.isEmpty(loginResponseModel.getStatusCode())) {

                                if (loginResponseModel.getStatusCode().equalsIgnoreCase(ApiConstants.Values.ResponseCodes.SUCCESS)) {

                                    SurflineCacheManager.getInstance(LoginActivity.this).setBoolean(PrefrenceConstants.LOGGED_IN, true);

                                    SurflineCacheManager.getInstance(LoginActivity.this).setString(PrefrenceConstants.EMAIL, loginResponseModel.getEmailId());
                                    SurflineCacheManager.getInstance(LoginActivity.this).setString(PrefrenceConstants.MOBILE_NO, loginResponseModel.getMsisdn());
                                    SurflineCacheManager.getInstance(LoginActivity.this).setString(PrefrenceConstants.FIRST_NAME, loginResponseModel.getFirstName());
                                    SurflineCacheManager.getInstance(LoginActivity.this).setString(PrefrenceConstants.LAST_NAME, loginResponseModel.getLastName());
                                    SurflineCacheManager.getInstance(LoginActivity.this).setString(PrefrenceConstants.CONTACT_ID, loginResponseModel.getContactId());

                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    ToastUtil.showLongToast(LoginActivity.this, loginResponseModel.getMessage());
                                }
                            } else {
                                ToastUtil.showLongToast(LoginActivity.this, getResources().getString(R.string.common_error_msg));
                            }
                        } else {
                            ToastUtil.showLongToast(LoginActivity.this, getResources().getString(R.string.common_error_msg));
                        }

                        break;


                }

            } else {
                ToastUtil.showLongToast(LoginActivity.this, getResources().getString(R.string.common_error_msg));
            }
        } catch (Exception e) {
            hideProgressDialog();
            ToastUtil.showLongToast(LoginActivity.this, getResources().getString(R.string.common_error_msg));
            e.printStackTrace();
        }

    }
}



