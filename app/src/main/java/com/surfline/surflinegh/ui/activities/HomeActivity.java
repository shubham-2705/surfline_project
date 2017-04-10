package com.surfline.surflinegh.ui.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.surfline.R;
import com.surfline.base.ui.activity.BaseActivity;
import com.surfline.surflinegh.adapters.MenuAdapter;
import com.surfline.surflinegh.constants.AppConstants;
import com.surfline.surflinegh.controller.UserManager;
import com.surfline.surflinegh.persistence.PrefrenceConstants;
import com.surfline.surflinegh.persistence.SurflineCacheManager;
import com.surfline.surflinegh.ui.fragments.BundlesFragment;
import com.surfline.surflinegh.ui.fragments.DashBoardFragment;

public class HomeActivity extends BaseActivity implements View.OnClickListener {


    private FragmentManager fragmentManager;
    private Toolbar myToolbar;
    private boolean isEnableDrawer;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private MenuAdapter menuAdapter;
    private TextView toolbar_title,txvUserName;
    private ImageView homeORback, logout,imvfb,imvtwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragmentManager = getSupportFragmentManager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        txvUserName = (TextView) findViewById(R.id.txvUserName);
        listView = (ListView) findViewById(R.id.listView);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) myToolbar.findViewById(R.id.toolbar_title);
//        homeORback = (ImageView) myToolbar.findViewById(R.id.homeORback);
        logout = (ImageView) myToolbar.findViewById(R.id.logout);
        imvtwitter = (ImageView) findViewById(R.id.imvtwitter);
        imvfb = (ImageView)findViewById(R.id.imvfb);

        logout.setOnClickListener(this);
        imvtwitter.setOnClickListener(this);
        imvfb.setOnClickListener(this);

        if (SurflineCacheManager.getInstance(this).getBoolean(PrefrenceConstants.LOGGED_IN)) {
            logout.setImageResource(R.mipmap.logout);
            txvUserName.setText(SurflineCacheManager.getInstance(this).getString(PrefrenceConstants.FIRST_NAME)+" "+SurflineCacheManager.getInstance(this).getString(PrefrenceConstants.LAST_NAME));
        }
        else
        {
            txvUserName.setText("Guest User");
            logout.setImageResource(R.mipmap.login);
        }

        Bundle bundle = new Bundle();
        addFragment(null, new DashBoardFragment(), bundle, false);

        menuAdapter = new MenuAdapter(this);
        listView.setAdapter(menuAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                closeDrawer();
                switch (position) {
                    case AppConstants.Bundles:
//                        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        addFragment(getCurrentFragment(), new BundlesFragment(), null, true);
                        break;
                }
            }
        });



    }

    public void openUrlinBrowser(String url)
    {
        Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void setToolbar(final String title, final Fragment fragment) {


        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);
        toolbar_title.setText("");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.budgermenu);
        logout.setVisibility(View.VISIBLE);

        toolbar_title.setText(title);

        if ((fragment instanceof DashBoardFragment)) {
            setEnableDrawer(true);
            myToolbar.setNavigationIcon(R.mipmap.budgermenu);
            logout.setVisibility(View.VISIBLE);
        } else {
            myToolbar.setNavigationIcon(R.mipmap.back);
            // check for guest log in and decide log out button will be displayed or not even on dashboard
            logout.setVisibility(View.GONE);
            setEnableDrawer(false);
        }

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fragment instanceof DashBoardFragment) {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        closeDrawer();
                    } else {
                        openDrawer();
                    }
                } else {
                    closeDrawer();
                    backPressedHandler();
                }
            }
        });

    }

    private void backPressedHandler() {
        if (getBackStackCount() > 0) {
            hideKeypad();
            hideProgressDialog();
            if (!(getCurrentFragment() instanceof DashBoardFragment)) {
                fragmentManager.popBackStack();
            } else {
                this.finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.logout:
                UserManager.logoutUser(this);
                break;
            case R.id.imvfb:
                openUrlinBrowser(getResources().getString(R.string.fb_url));
                break;
            case R.id.imvtwitter:
                openUrlinBrowser(getResources().getString(R.string.twitter_url));
                break;
//            case R.id.llyDataCalculatorlayout:
//                break;
//            case R.id.imvProfile:
//                break;
//            case R.id.txvprofilename:
//                break;
//            case R.id.txvbalance:
//                break;
        }
    }

    public void addFragment(Fragment oldFragment, Fragment fragment, Bundle bundle, boolean isAddToBackStack) {


        hideProgressDialog();


        String tag = fragment.getClass().getSimpleName();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.container, fragment, tag);
        if (oldFragment != null)
            ft.hide(oldFragment);

        fragment.setRetainInstance(true);
        if (isAddToBackStack) {
            ft.addToBackStack(tag);
        }
        try {
            ft.commitAllowingStateLoss();
        } catch (Exception ex) {
            ex.printStackTrace();
            ft.commitAllowingStateLoss();
        }
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }

    public int getBackStackCount() {
        return fragmentManager.getBackStackEntryCount();
    }

//    public boolean isEnableDrawer() {
//        return isEnableDrawer;
//    }

    public void setEnableDrawer(boolean isEnableDrawer) {
        this.isEnableDrawer = isEnableDrawer;
        if (isEnableDrawer) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void clearBackStack(int firstCount) {
        if (getBackStackCount() > firstCount) {
            FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(firstCount);
            fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
