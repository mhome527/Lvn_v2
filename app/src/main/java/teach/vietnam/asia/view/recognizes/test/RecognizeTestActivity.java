package teach.vietnam.asia.view.recognizes.test;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.RecognizeEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.ICallback;
import teach.vietnam.asia.view.purchase.PurchaseActivity;
import teach.vietnam.asia.view.recognizes.MenuRecognizeAdapter;


/**
 * Created by huynhtd on 10/17/2016.
 */

public class RecognizeTestActivity extends PurchaseActivity<RecognizeTestActivity> implements RecognizeTestListAdapter.RecognizeTest {

    private static String TAG = "RecognizeTestActivity";

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


//    private ArrayList<String> lstData;

//    private List<RecognizeEntity> dataRecognize;

    //    private int amount = 0;
    public RecognizeTestPresenter presenter;


    //    WordPagerAdapter adapter;
    public boolean isPurchased = false; //  true: user has already bought product
//    int currPage = 0;
//    private RecognizePagerAdapter adapterPage;
////////

    public int currPage = 0;
    public int arrW[];

    //    private DaoMaster daoMaster;
//    private tblRecognizeDao dao;
    private List<RecognizeEntity> dataRecognize;

    private RecognizeTestPagerAdapter adapterPage;
    //    private DaoMaster daoMaster;
    private AudioPlayer audio;
    private int kind = 1;

    private int amount = 3;
    private int currAns = 0;
    private ArrayList<String> lstData;


    ///////

    @Override
    protected int getLayout() {
        return R.layout.fragment_test_recognize;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView");

        setTitleCenter(getString(R.string.title_recognize));

        mActivityTitle = getTitle().toString();

        setupDrawer();
        setupViewPager();
        setInitData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_recognize, menu);
//        return true;
//    }

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

//        if (id == R.id.menuTest) {
//            Log.i(TAG, "menu test click....");
//            return true;
//        }

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

    //==========
    @Override
    public String getCurrWord() {
        return dataRecognize.get(arrW[currAns]).getVn();
    }

    ///////////
    private void setInitData() {
        lstData = new ArrayList<>();
        presenter = new RecognizeTestPresenter(activity);

        String initData = pref.getStringValue("", Constant.JSON_RECOGNIZE_NAME);
        Log.i(RecognizeTestActivity.class, "setInit: " + initData);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RecognizeTestActivity.this, "Time for an upgrade, pos:" + position, Toast.LENGTH_SHORT).show();
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
                    MenuRecognizeAdapter adapter = new MenuRecognizeAdapter(RecognizeTestActivity.this, lstData);
                    mDrawerList.setAdapter(adapter);

                    amount = num;

                    adapterPage = new RecognizeTestPagerAdapter(activity, dataRecognize, activity);
                    pagerRecognize.setAdapter(adapterPage);
                    adapterPage.notifyDataSetChanged();

                    pagerRecognize.setCurrentItem(currPage);
                } else {
                    Log.i(TAG, "Load data error!!!, num= 0");
                }
            }

            @Override
            public void onFail(String err) {

            }
        });

        ///=====================================

        audio = new AudioPlayer(activity);

        dataRecognize = presenter.loadData(currPage + 1);
        amount = dataRecognize.size();
        Log.i(TAG, "setInitData kind:" + kind + "; size:" + dataRecognize.size());

        setArrData();

        adapterPage = new RecognizeTestPagerAdapter(activity, dataRecognize, this);
        pagerRecognize.setAdapter(adapterPage);
//        amount = adapterPage.amount;
//        setCurrentWord(currAns);
        if (currAns == 0)
            imgLeft.setVisibility(View.GONE);
        else if (currAns == amount - 1)
            imgRight.setVisibility(View.GONE);
        else {
            imgLeft.setVisibility(View.VISIBLE);
            imgRight.setVisibility(View.VISIBLE);
        }

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

    private void setArrData() {
        arrW = new int[amount];
        for (int i = 0; i < amount; i++) {
            arrW[i] = i;
        }

        for (int i = 0; i < amount - 1; i++) {

            Random ran = new Random();
            Random ran2 = new Random();
            int value = ran.nextInt(amount);
            int value2 = ran2.nextInt(amount);
            int tmp = arrW[value];
            arrW[value] = arrW[value2];
            arrW[value2] = tmp;
        }
//        ULog.i(TestRecognizeFragment.class, "arr:" + arrW[0] + "," + arrW[1] + "," + arrW[2] + "," + arrW[3]);
    }


    // ================= Purchase ====================
    @Override
    protected void dealWithIabSetupSuccess() {
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;


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
