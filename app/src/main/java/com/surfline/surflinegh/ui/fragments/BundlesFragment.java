package com.surfline.surflinegh.ui.fragments;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.surfline.R;
import com.surfline.base.ui.fragment.BaseFragment;
import com.surfline.surflinegh.ui.activities.HomeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class BundlesFragment extends BaseFragment implements View.OnClickListener,Animation.AnimationListener {

    private View v;
    private HomeActivity mActivity;
    private View layout1,layout2,layout3,layout4,layout5;
    private Animation slide_down1,slide_down2,slide_down3,slide_down4,slide_down5,slide_up1,slide_up2,slide_up3,slide_up4,slide_up5;
    private ImageView imvArrow,imvArrow2,imvArrow3,imvArrow4,imvArrow5;

    public BundlesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (HomeActivity) getBaseActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            if (v == null) {
                v = inflater.inflate(R.layout.fragment_bundle_layout, container, false);
                getIds(v);
                Bundle bundle = getArguments();
//                if (bundle != null) {
//                    isMyWallet = bundle.getBoolean(AppConstants.IS_MY_WALLET);
//                    showLinkDialog = bundle.getBoolean(AppConstants.SHOW_LINK_DIALOG);
//                }

                if ( getActivity() != null && getActivity() instanceof HomeActivity) {
                    ((HomeActivity) getActivity()).setToolbar(getTitle(), this);
                }

            } else {
                final ViewParent parent = v.getParent();
                if (parent instanceof ViewManager) {
                    final ViewManager viewManager = (ViewManager) parent;
                    viewManager.removeView(v);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    public void  getIds(View view) {
        RelativeLayout llyBundlelayout1 = (RelativeLayout) view.findViewById(R.id.llyBundlelayout1);
        RelativeLayout llyBundlelayout2 = (RelativeLayout) view.findViewById(R.id.llyBundlelayout2);
        RelativeLayout llyBundlelayout3 = (RelativeLayout) view.findViewById(R.id.llyBundlelayout3);
        RelativeLayout llyBundlelayout4 = (RelativeLayout) view.findViewById(R.id.llyBundlelayout4);
        RelativeLayout llyBundlelayout5 = (RelativeLayout) view.findViewById(R.id.llyBundlelayout5);
        layout1 = (View) view.findViewById(R.id.layout1);
        layout2 = (View) view.findViewById(R.id.layout2);
        layout3 = (View) view.findViewById(R.id.layout3);
        layout4 = (View) view.findViewById(R.id.layout4);
        layout5 = (View) view.findViewById(R.id.layout5);
        imvArrow = (ImageView) view.findViewById(R.id.imvArrow);
        imvArrow2 = (ImageView) view.findViewById(R.id.imvArrow2);
        imvArrow3 = (ImageView) view.findViewById(R.id.imvArrow3);
        imvArrow4 = (ImageView) view.findViewById(R.id.imvArrow4);
        imvArrow5 = (ImageView) view.findViewById(R.id.imvArrow5);

        slide_down1 = AnimationUtils.loadAnimation(getBaseActivity().getApplicationContext(), R.anim.slide_down);
        slide_down2 = AnimationUtils.loadAnimation(getBaseActivity().getApplicationContext(), R.anim.slide_down);
        slide_down3 = AnimationUtils.loadAnimation(getBaseActivity().getApplicationContext(), R.anim.slide_down);
        slide_down4 = AnimationUtils.loadAnimation(getBaseActivity().getApplicationContext(), R.anim.slide_down);
        slide_down5 = AnimationUtils.loadAnimation(getBaseActivity().getApplicationContext(), R.anim.slide_down);
        slide_up1 = AnimationUtils.loadAnimation(getBaseActivity().getApplicationContext(), R.anim.slide_up);
        slide_up2 = AnimationUtils.loadAnimation(getBaseActivity().getApplicationContext(), R.anim.slide_up);
        slide_up3 = AnimationUtils.loadAnimation(getBaseActivity().getApplicationContext(), R.anim.slide_up);
        slide_up4 = AnimationUtils.loadAnimation(getBaseActivity().getApplicationContext(), R.anim.slide_up);
        slide_up5 = AnimationUtils.loadAnimation(getBaseActivity().getApplicationContext(), R.anim.slide_up);


        llyBundlelayout1.setOnClickListener(this);
        llyBundlelayout2.setOnClickListener(this);
        llyBundlelayout3.setOnClickListener(this);
        llyBundlelayout4.setOnClickListener(this);
        llyBundlelayout5.setOnClickListener(this);
        slide_down1.setAnimationListener(this);
        slide_down2.setAnimationListener(this);
        slide_down3.setAnimationListener(this);
        slide_down4.setAnimationListener(this);
        slide_down5.setAnimationListener(this);
        slide_up1.setAnimationListener(this);
        slide_up2.setAnimationListener(this);
        slide_up3.setAnimationListener(this);
        slide_up4.setAnimationListener(this);
        slide_up5.setAnimationListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.llyBundlelayout1:

                if(layout1.getVisibility()==View.GONE) {
                    showDropDown(layout1,slide_down1);
                    startImageAnimation(imvArrow);
                }
                else
                {
                    hideDropDown(layout1,slide_up1);
                    stopImageAnimation(imvArrow);
                }
                break;
            case R.id.llyBundlelayout2:

                if(layout2.getVisibility()==View.GONE) {
                    showDropDown(layout2,slide_down2);
                    startImageAnimation(imvArrow2);
                }
                else
                {
                    hideDropDown(layout2,slide_up2);
                    stopImageAnimation(imvArrow2);
                }
                break;
            case R.id.llyBundlelayout3:

                if(layout3.getVisibility()==View.GONE) {
                    showDropDown(layout3,slide_down3);
                    startImageAnimation(imvArrow3);
                }
                else
                {
                    hideDropDown(layout3,slide_up3);
                    stopImageAnimation(imvArrow3);
                }
                break;
            case R.id.llyBundlelayout4:

                if(layout4.getVisibility()==View.GONE) {
                    showDropDown(layout4,slide_down4);
                    startImageAnimation(imvArrow4);
                }
                else
                {
                    hideDropDown(layout4,slide_up4);
                    stopImageAnimation(imvArrow4);
                }
                break;
            case R.id.llyBundlelayout5:

                if(layout5.getVisibility()==View.GONE) {
                    showDropDown(layout5,slide_down5);
                    startImageAnimation(imvArrow5);
                }
                else
                {
                    hideDropDown(layout5,slide_up5);
                    stopImageAnimation(imvArrow5);
                }
                break;
        }
    }

    public void startImageAnimation(ImageView imvArrow)
    {
        ObjectAnimator imageViewObjectAnimator = ObjectAnimator.ofFloat(imvArrow,
                "rotation", 0f, 90f);
        imageViewObjectAnimator.setDuration(100); // miliseconds
        imageViewObjectAnimator.start();
    }

    public void stopImageAnimation(ImageView imvArrow)
    {
        ObjectAnimator imageViewObjectAnimator = ObjectAnimator.ofFloat(imvArrow,
                "rotation", 90f, 0f);
        imageViewObjectAnimator.setDuration(100); // miliseconds
        imageViewObjectAnimator.start();
    }

    public void hideDropDown(final View view,Animation animation)
    {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.GONE);
            }
        }, 750);
        view.startAnimation(animation);

    }

    public void showDropDown(final View view,Animation animation)
    {
        view.setVisibility(View.VISIBLE);
        view.startAnimation(animation);
    }



    @Override
    public String getTitle() {
        return "Bundles";
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
