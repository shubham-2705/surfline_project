package com.surfline.base.ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    public void setToolbar(int toolbarColor, Toolbar myToolbar, String title) {
//        myToolbar.setBackgroundColor(ContextCompat.getColor(this, toolbarColor));
//
//        myToolbar.setTitle("");
//        setSupportActionBar(myToolbar);
//
//        TextView toolbar_title = (TextView) myToolbar.findViewById(R.id.toolbar_title);
//        if (TextUtils.isEmpty(title)) {
//            toolbar_title.setBackgroundResource(R.drawable.oxigen_wallet);
//        } else {
//            toolbar_title.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
//        }
//        toolbar_title.setText(title);



        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        if (BuildConfig.DEBUG && this instanceof EnterMobileActivity) {
//            toolbar_title.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    showLinkDialog();
//                    return false;
//                }
//            });
//        }
    }

//    public void setToolbar(Toolbar myToolbar, String title) {
//        setToolbar(R.color.white_color, myToolbar, title);
//    }

    public void showKeypad() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public void hideDialogKeypad() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void hideKeypad() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void showProgressdialog(String message) {
        showProgressdialog(message, false);
    }

    public void showProgressdialog(String message, boolean isCancelable) {
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(isCancelable);
//        progressDialog.setProgress(0);
        progressDialog.show();
    }

    public void hideProgressDialog() {
//        removeProgressDialog(false);
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }
}
