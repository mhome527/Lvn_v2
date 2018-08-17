package teach.vietnam.asia.view.places;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.purchase.PurchaseActivity;

public class PlaceActivity extends PurchaseActivity<PlaceActivity> {
    private final String TAG = "PlaceActivity";

    private final String STATE_PURCHASED = "STATE_PURCHASED";

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    @BindView(R.id.appBar)
    AppBarLayout appBar;

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    PlacePagerAdapter adapter;
    PlacePresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.places_activity;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
            actionBar.setDisplayShowTitleEnabled(false); // remove title

        } else
            Log.e(TAG, "initView actionBar NULL!!!!");

        toolbarTitle.setText(getString(R.string.place_vietnam_travel));

        presenter = new PlacePresenter(this);

        if (savedInstanceState != null) {
            isPurchased = savedInstanceState.getBoolean(STATE_PURCHASED);
        }

        tabLayout.addTab(tabLayout.newTab().setText(R.string.southern));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.central));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.northern));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new PlacePagerAdapter(isPurchased, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                currPage = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (!BuildConfig.DEBUG)
            FirebaseCrash.logcat(Log.INFO, TAG, "initView");
    }

    @OnClick(R.id.btnBack)
    public void actionBack() {
        onBackPressed();
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
//this method will be called before onstop
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState");
        outState.putBoolean(STATE_PURCHASED, isPurchased);


        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState");
    }

    //======================== Start Purchase =========================

    @Override
    protected void dealWithIabSetupSuccess() {
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;
            if (adapter == null)
                return;

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (adapter != null) {
                        adapter.setPurchased(isPurchased);
                        adapter.notifyDataSetChanged();
                    }
                }
            });


        } else {
            Log.i(TAG, "WithIabSetupSuccess item not purchase");
            isPurchased = false;
        }
    }

    @Override
    protected void dealWithIabSetupFailure() {
        Log.e(TAG, "dealWithIabSetupFailure ====================== ERROR ==================");
    }
    //========================END  Purchase =========================
}
