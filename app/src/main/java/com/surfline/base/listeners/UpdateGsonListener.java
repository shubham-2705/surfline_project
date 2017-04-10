package com.surfline.base.listeners;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.surfline.base.network.VolleyExceptionUtil;
import com.surfline.base.ui.activity.BaseActivity;


public class UpdateGsonListener<T> implements Listener<T>, ErrorListener {

    private int reqType;
    private onUpdateViewListener onUpdateViewListener;
//    private Activity mActivity;

    public UpdateGsonListener( onUpdateViewListener onUpdateView, int reqType) {
        this.reqType = reqType;
        this.onUpdateViewListener = onUpdateView;
//        mActivity = activity;
    }

    public interface onUpdateViewListener {
        void updateView(Object responseObject, boolean isSuccess, int reqType);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        onUpdateViewListener.updateView(VolleyExceptionUtil.getErrorMessage(error), false, reqType);
    }

    @Override
    public void onResponse(final Object responseModel) {
        try {
            onUpdateViewListener.updateView(responseModel, true, reqType);
        } catch (Exception ex) {
            ex.printStackTrace();
            onUpdateViewListener.updateView(responseModel, false, reqType);
        }

    }

}