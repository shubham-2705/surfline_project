package com.surfline.base.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.surfline.base.listeners.IEvent;
import com.surfline.base.ui.activity.BaseActivity;
import com.surfline.surflinegh.persistence.ShowLog;
import com.surfline.surflinegh.ui.activities.HomeActivity;


public abstract class BaseFragment extends Fragment implements IEvent {

    private BaseActivity baseActivity;

    public BaseActivity getBaseActivity() {
        if (baseActivity == null) {
            baseActivity = (BaseActivity) getActivity();
        }
        return baseActivity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
//        try {
//            baseActivity.initAutoLogService();
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }

        getTitle();
    }

//    @Override
//    public void onStart() {
//        // TODO Auto-generated method stub
//        super.onStart();
//        baseActivity.checkForAutoLogout();
//
//    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        baseActivity.checkForAutoLogout();
//    }

    @Override
    public void onAttach(Context context) {
        // TODO Auto-generated method stub
        super.onAttach(context);
        this.baseActivity = (BaseActivity) context;
    }

    @Override
    public void onEvent(int id, Object object) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if ( !hidden && getActivity() != null && getActivity() instanceof HomeActivity) {
            ShowLog.d("----surf","base fragment hidden");
            ((HomeActivity) getActivity()).setToolbar(getTitle(), this);
        }

    }

    public abstract String getTitle();
}
