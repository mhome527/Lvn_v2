package teach.vietnam.asia.view.foods;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.purchase.PurchaseActivity;


/**
 * Created by huynhtd on 10/17/2016.
 */

public class FoodActivity extends PurchaseActivity<FoodActivity> {

    private static String TAG = "FoodActivity";
    private final String STATE_PURCHASED = "STATE_PURCHASED";


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    FoodPagerAdapter adapter;
    int currPage = 0;

    @Override
    protected int getLayout() {
        return R.layout.word_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView");

//        setTitleCenter(getString(R.string.title_food));
//        toolbarTitle.setText(getString(R.string.title_food));
        toolbarTitle.setText(getString(R.string.title_food2));

        if (savedInstanceState != null) {
            isPurchased = savedInstanceState.getBoolean(STATE_PURCHASED);
        }

        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_food));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_noodles));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_snack));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new FoodPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), isPurchased);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currPage = tab.getPosition();
                viewPager.setCurrentItem(currPage);
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

//
//            adapter.isPurchased = isPurchased;
//            activity.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    adapter.notifyDataSetChanged();
//                }
//            });


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
