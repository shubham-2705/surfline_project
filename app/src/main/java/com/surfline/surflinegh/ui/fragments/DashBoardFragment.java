package com.surfline.surflinegh.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.surfline.BuildConfig;
import com.surfline.R;
import com.surfline.base.application.BaseApplication;
import com.surfline.base.listeners.UpdateGsonListener;
import com.surfline.base.network.VolleyGsonRequest;
import com.surfline.base.ui.fragment.BaseFragment;
import com.surfline.base.utils.NetworkUtil;
import com.surfline.base.utils.ToastUtil;
import com.surfline.surflinegh.constants.ApiConstants;
import com.surfline.surflinegh.models.request.GetBalanceRequest;
import com.surfline.surflinegh.models.response.BalanceResponseModel;
import com.surfline.surflinegh.persistence.PrefrenceConstants;
import com.surfline.surflinegh.persistence.ShowLog;
import com.surfline.surflinegh.persistence.SurflineCacheManager;
import com.surfline.surflinegh.ui.activities.HomeActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardFragment extends BaseFragment implements View.OnClickListener, UpdateGsonListener.onUpdateViewListener {

    private static final String TAG = DashBoardFragment.class.getSimpleName();
    private View v;
    private HomeActivity mActivity;
    private ImageView imvProfile;
    private RelativeLayout llyUserInfo;
    private LinearLayout llyMenu;
    private TextView txvprofilename, txvbalance;
    private ProgressBar progressBarBalance;
    private Gson gson;

    public DashBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (HomeActivity) getBaseActivity();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        ShowLog.d("----surf", "dashboard hidden");
        if (!hidden && getActivity() != null && getActivity() instanceof HomeActivity) {

            if (SurflineCacheManager.getInstance(getBaseActivity()).getBoolean(PrefrenceConstants.LOGGED_IN)) {
                // hit api for balance and show user name from cache
                llyUserInfo.setVisibility(View.VISIBLE);
                txvprofilename.setText(SurflineCacheManager.getInstance(getBaseActivity()).getString(PrefrenceConstants.FIRST_NAME) + " " + SurflineCacheManager.getInstance(getBaseActivity()).getString(PrefrenceConstants.LAST_NAME));
                try {
                    txvbalance.setText("");
                    hitApi(ApiConstants.REQUEST_TYPE.GET_BALANCE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // hide user info layout
                llyUserInfo.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            if (v == null) {
                v = inflater.inflate(R.layout.fragment_dashboard, container, false);
                getIds(v);
                Bundle bundle = getArguments();
//                if (bundle != null) {
//                    isMyWallet = bundle.getBoolean(AppConstants.IS_MY_WALLET);
//                    showLinkDialog = bundle.getBoolean(AppConstants.SHOW_LINK_DIALOG);
//                }

                if (getActivity() != null && getActivity() instanceof HomeActivity) {
                    ((HomeActivity) getActivity()).setToolbar(getTitle(), this);
                }

                if (SurflineCacheManager.getInstance(getBaseActivity()).getBoolean(PrefrenceConstants.LOGGED_IN)) {
                    // hit api for balance and show user name from cache
                    llyUserInfo.setVisibility(View.VISIBLE);
                    txvprofilename.setText(SurflineCacheManager.getInstance(getBaseActivity()).getString(PrefrenceConstants.FIRST_NAME) + " " + SurflineCacheManager.getInstance(getBaseActivity()).getString(PrefrenceConstants.LAST_NAME));
                    try {
                        txvbalance.setText("");
                        hitApi(ApiConstants.REQUEST_TYPE.GET_BALANCE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // hide user info layout
                    llyUserInfo.setVisibility(View.GONE);
                }


            } else {
                final ViewParent parent = v.getParent();
                if (parent instanceof android.view.ViewManager) {
                    final ViewManager viewManager = (ViewManager) parent;
                    viewManager.removeView(v);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    public void getIds(View view) {
        RelativeLayout llyBundlelayout = (android.widget.RelativeLayout) view.findViewById(R.id.llyBundlelayout);
        RelativeLayout llyDevicelayout = (RelativeLayout) view.findViewById(R.id.llyDevicelayout);
        RelativeLayout llyExtralayout = (RelativeLayout) view.findViewById(R.id.llyExtralayout);
        RelativeLayout llyDataCalculatorlayout = (RelativeLayout) view.findViewById(R.id.llyDataCalculatorlayout);

        imvProfile = (ImageView) view.findViewById(R.id.imvProfile);
        txvprofilename = (TextView) view.findViewById(R.id.txvprofilename);
        txvbalance = (TextView) view.findViewById(R.id.txvbalance);
        llyUserInfo = (RelativeLayout) view.findViewById(R.id.llyUserInfo);
        llyMenu = (LinearLayout) view.findViewById(R.id.llyMenu);
        progressBarBalance = (ProgressBar) view.findViewById(R.id.progressBarBalance);

        llyBundlelayout.setOnClickListener(this);
        llyDevicelayout.setOnClickListener(this);
        llyExtralayout.setOnClickListener(this);
        llyDataCalculatorlayout.setOnClickListener(this);
        imvProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.llyBundlelayout:
                mActivity.addFragment(mActivity.getCurrentFragment(), new BundlesFragment(), null, true);
                break;
            case R.id.llyDevicelayout:
                break;
            case R.id.llyExtralayout:
                break;
            case R.id.llyDataCalculatorlayout:
                break;
            case R.id.imvProfile:
                break;
//            case R.id.txvprofilename:
//                break;
//            case R.id.txvbalance:
//                break;
        }
    }

    private void hitApi(int reqType) throws Exception {
        if (ApiConstants.isMock) {
            switch (reqType) {
                case ApiConstants.REQUEST_TYPE.GET_BALANCE:
                    updateView(new Gson().fromJson(ApiConstants.MockResponse.GET_BALANCE, BalanceResponseModel.class), true, reqType);
                    break;


            }
            return;
        }
        if (!NetworkUtil.isNetworkAvailable(getBaseActivity())) {
            ToastUtil.showShortToast(getBaseActivity(), getResources().getString(R.string.network_error));
            return;
        }

        String loadMessage = getResources().getString(R.string.loading);
        String url = "";
        String body = "";
        Class className = null;
        switch (reqType) {
            case ApiConstants.REQUEST_TYPE.GET_BALANCE:
                url = ApiConstants.Urls.GET_BALANCE;
                body = createJsonReq(reqType);
                className = BalanceResponseModel.class;
                break;

        }

        if (!TextUtils.isEmpty(body))
            ((BaseApplication) getBaseActivity().getApplication()).getVolleyManager().addToRequestQueue(VolleyGsonRequest.doPost(url,
                    new UpdateGsonListener<>(this, reqType), className
                    , body), "" + reqType);
        progressBarBalance.setVisibility(View.VISIBLE);
    }

    private String createJsonReq(int reqType) throws Exception {
        if (gson == null)
            gson = new GsonBuilder().disableHtmlEscaping().create();
        switch (reqType) {
            case ApiConstants.REQUEST_TYPE.GET_BALANCE:
                GetBalanceRequest getBalanceRequest = new GetBalanceRequest();
                getBalanceRequest.setMsisdn(SurflineCacheManager.getInstance(getBaseActivity()).getString(PrefrenceConstants.MOBILE_NO));

                return gson.toJson(getBalanceRequest);


        }
        return "";


    }

    @Override
    public void updateView(Object responseObject, boolean isSuccess, int reqType) {

        if (BuildConfig.DEBUG) {
            Log.i(TAG, "Response String---------" + responseObject.toString());
        }

        try {
            progressBarBalance.setVisibility(View.GONE);
            if (isSuccess && responseObject != null) {
                switch (reqType) {
                    case ApiConstants.REQUEST_TYPE.GET_BALANCE:
                        if (responseObject instanceof BalanceResponseModel) {
                            BalanceResponseModel balanceResponseModel = (BalanceResponseModel) responseObject;
                            if (!TextUtils.isEmpty(balanceResponseModel.getStatusCode())) {

                                if (balanceResponseModel.getStatusCode().equalsIgnoreCase(ApiConstants.Values.ResponseCodes.SUCCESS)) {

                                    ArrayList<BalanceResponseModel.BalanceData> balanceData = balanceResponseModel.getBalanceData();
                                    txvbalance.setText(balanceData.get(0).getBalanceType() + " " + balanceData.get(0).getBalanceUnits()+ "\n"+balanceData.get(0).getBalanceExpiry());

                                } else {
                                    ToastUtil.showLongToast(getBaseActivity(), balanceResponseModel.getMessage());
                                }
                            } else {
                                ToastUtil.showLongToast(getBaseActivity(), getResources().getString(R.string.common_error_msg));
                            }
                        } else {
                            ToastUtil.showLongToast(getBaseActivity(), getResources().getString(R.string.common_error_msg));
                        }

                        break;


                }

            } else {
                ToastUtil.showLongToast(getBaseActivity(), getResources().getString(R.string.common_error_msg));
            }
        } catch (Exception e) {
            progressBarBalance.setVisibility(View.GONE);
            ToastUtil.showLongToast(getBaseActivity(), getResources().getString(R.string.common_error_msg));
            e.printStackTrace();
        }

    }

    @Override
    public String getTitle() {
        return "Menu";
    }
}
