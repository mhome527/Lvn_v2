package teach.vietnam.asia.view.word;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;

import butterknife.BindView;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.purchase.PurchaseActivity;


/**
 * Created by huynhtd on 10/17/2016.
 */

public class WordActivity extends PurchaseActivity<WordActivity> {

    private static String TAG = "WordActivity";

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    WordPagerAdapter adapter;

    int currPage = 0;

    @Override
    protected int getLayout() {
        return R.layout.word_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView");

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
            actionBar.setDisplayShowTitleEnabled(false); // remove title

        } else
            Log.e(TAG, "initView actionBar NULL!!!!");


        setTitleCenter(getString(R.string.title_word));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_animal));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_fruit));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_other));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new WordPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
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

    // ================= Purchase ====================
    @Override
    protected void dealWithIabSetupSuccess() {
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (currPage == 0) {
                        if (((WordFragment) adapter.getItem(0)).adapter != null)
                            ((WordFragment) adapter.getItem(0)).adapter.setPurchased(isPurchased);
                        if (((WordFragment) adapter.getItem(1)).adapter != null)
                            ((WordFragment) adapter.getItem(1)).adapter.setPurchased(isPurchased);
                    } else if (currPage == 1) {
                        if (((WordFragment) adapter.getItem(0)).adapter != null)
                            ((WordFragment) adapter.getItem(0)).adapter.setPurchased(isPurchased);
                        if (((WordFragment) adapter.getItem(1)).adapter != null)
                            ((WordFragment) adapter.getItem(1)).adapter.setPurchased(isPurchased);
                        if (((WordFragment) adapter.getItem(2)).adapter != null)
                            ((WordFragment) adapter.getItem(2)).adapter.setPurchased(isPurchased);
                    } else {
                        if (((WordFragment) adapter.getItem(2)).adapter != null)
                            ((WordFragment) adapter.getItem(2)).adapter.setPurchased(isPurchased);
                        if (((WordFragment) adapter.getItem(1)).adapter != null)
                            ((WordFragment) adapter.getItem(1)).adapter.setPurchased(isPurchased);
                    }
                }
            });

            /// Test only
//            clearPurchaseTest();

        } else {
            Log.i(TAG, "WithIabSetupSuccess item not purchase");
            isPurchased = false;
        }
    }

    @Override
    protected void dealWithIabSetupFailure() {
        Log.i(TAG, "dealWithIabSetupFailure...");
    }
    // ================ Purchase ===========

}
