package teach.vietnam.asia.view.recognizes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.ICallback;
import teach.vietnam.asia.view.purchase.PurchaseActivity;
import teach.vietnam.asia.view.recognizes.test.RecognizeTestActivity;


/**
 * Created by huynhtd on 10/17/2016.
 */

public class RecognizeMainActivity extends PurchaseActivity<RecognizeMainActivity> {

    private static String TAG = "RecognizeMainActivity";

    @BindView(R.id.navList)
    ListView mDrawerList;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.pagerRecognize)
    ViewPager pagerRecognize;

    @BindView(R.id.imgLeft)
    ImageButton imgLeft;

    @BindView(R.id.imgRight)
    ImageButton imgRight;


    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;


    private ArrayList<String> lstData;
    private int amount = 0;
    public RecognizePresenter presenter;


    //    WordPagerAdapter adapter;

    int currPage = 0;
    private RecognizePagerAdapter adapterPage;


    @Override
    protected int getLayout() {
        return R.layout.recognize_main_activity;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView");        if (!BuildConfig.DEBUG)
        FirebaseCrash.logcat(Log.INFO, TAG, "initView");

        setTitleCenter(getString(R.string.title_recognize));

        mActivityTitle = getTitle().toString();

        setupDrawer();
        setupViewPager();
        setInitData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        if (!BuildConfig.DEBUG)
        FirebaseCrash.logcat(Log.INFO, TAG, "initView");
    }

    ////////////


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recognize, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.menuTest) {
            Log.i(TAG, "menu test click....");
            Intent intent = new Intent(activity, RecognizeTestActivity.class);
            startActivity(intent);
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.imgLeft)
    public void actionLeft() {
        if (currPage == 0) {
            return;
        }
        currPage = currPage - 1;

        if (currPage == 0)
            imgLeft.setVisibility(View.GONE);

        imgRight.setVisibility(View.VISIBLE);
        pagerRecognize.setCurrentItem(currPage);

//        ((RecognizeMainActivity) getActivity()).hideMenu();
    }

    @OnClick(R.id.imgRight)
    public void actionRight() {
        if (currPage == amount - 1) {
            return;
        }
        currPage = currPage + 1;

        if (currPage == amount - 1)
            imgRight.setVisibility(View.GONE);

        imgLeft.setVisibility(View.VISIBLE);
        pagerRecognize.setCurrentItem(currPage);
    }

    @OnClick(R.id.btnBack)
    public void actionBack() {
        finish();
    }

    ///////////
    private void setInitData() {
        lstData = new ArrayList<>();
        presenter = new RecognizePresenter(activity);

        String initData = pref.getStringValue("", Constant.JSON_RECOGNIZE_NAME);
        Log.i(RecognizeMainActivity.class, "setInit: " + initData);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(RecognizeMainActivity.this, "Time for an upgrade, pos:" + position, Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
                currPage = position;
                pagerRecognize.setCurrentItem(currPage);
            }
        });

        presenter.loadGroup(new ICallback<List<String>>() {
            @Override
            public void onCallback(List<String> data) {
                int num = data.size();
                if (num > 0) {
                    Log.i(TAG, "loadGroup size:" + num);

                    lstData.addAll(data);
                    MenuRecognizeAdapter adapter = new MenuRecognizeAdapter(RecognizeMainActivity.this, lstData);
                    mDrawerList.setAdapter(adapter);

                    amount = num;

                    adapterPage = new RecognizePagerAdapter(activity, amount);
                    adapterPage.setPurchased(isPurchased);

                    pagerRecognize.setAdapter(adapterPage);
                    adapterPage.notifyDataSetChanged();

                    pagerRecognize.setCurrentItem(currPage);
                } else {
                    Log.i(TAG, "Load data error!!!, num= 0");
                }
            }


        });

        ///

//        if (currPage == 0)
//            imgLeft.setVisibility(View.GONE);
//        else if (currPage == amount - 1)
//            imgRight.setVisibility(View.GONE);
//        else {
//            imgLeft.setVisibility(View.VISIBLE);
//            imgRight.setVisibility(View.VISIBLE);
//        }

    }

    ////
    private void setupViewPager() {

        pagerRecognize.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currPage = position;

                pref.putIntValue(currPage, Constant.PREF_PAGE);
                if (currPage == 0)
                    imgLeft.setVisibility(View.GONE);
                else if (currPage == amount - 1)
                    imgRight.setVisibility(View.GONE);
                else {
                    imgLeft.setVisibility(View.VISIBLE);
                    imgRight.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    // ================= Purchase ====================
    @Override
    protected void dealWithIabSetupSuccess() {
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;
            if(adapterPage == null)
                return;

            adapterPage.setPurchased(isPurchased);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapterPage.notifyDataSetChanged();
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
