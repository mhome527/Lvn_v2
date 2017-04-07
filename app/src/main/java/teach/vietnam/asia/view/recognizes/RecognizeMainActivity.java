package teach.vietnam.asia.view.recognizes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.purchase.PurchaseActivity;


/**
 * Created by huynhtd on 10/17/2016.
 */

public class RecognizeMainActivity extends PurchaseActivity<RecognizeMainActivity> {

    private static String TAG = "RecognizeMainActivity";


    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;


//    @BindView(R.id.viewpager)
//    ViewPager viewPager;

    //    WordPagerAdapter adapter;
    public boolean isPurchased = false; //  true: user has already bought product
    int currPage = 0;

    @Override
    protected int getLayout() {
        return R.layout.recognize_main_activity;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView");

        setTitleCenter(getString(R.string.title_recognize));

        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

//        adapter = new WordPagerAdapter
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                currPage = tab.getPosition();
//                viewPager.setCurrentItem(currPage);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addDrawerItems() {
        String[] osArray = {"Android", "iOS", "Windows", "OS X", "Linux"};
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RecognizeMainActivity.this, "Time for an upgrade, pos:" + position, Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
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

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    if (currPage == 0) {
//                        if (((WordFragment) adapter.getItem(0)).adapter != null)
//                            ((WordFragment) adapter.getItem(0)).adapter.setPurchased(isPurchased);
//                        if (((WordFragment) adapter.getItem(1)).adapter != null)
//                            ((WordFragment) adapter.getItem(1)).adapter.setPurchased(isPurchased);
//                    } else if (currPage == 1) {
//                        if (((WordFragment) adapter.getItem(0)).adapter != null)
//                            ((WordFragment) adapter.getItem(0)).adapter.setPurchased(isPurchased);
//                        if (((WordFragment) adapter.getItem(1)).adapter != null)
//                            ((WordFragment) adapter.getItem(1)).adapter.setPurchased(isPurchased);
//                        if (((WordFragment) adapter.getItem(2)).adapter != null)
//                            ((WordFragment) adapter.getItem(2)).adapter.setPurchased(isPurchased);
//                    } else {
//                        if (((WordFragment) adapter.getItem(2)).adapter != null)
//                            ((WordFragment) adapter.getItem(2)).adapter.setPurchased(isPurchased);
//                        if (((WordFragment) adapter.getItem(1)).adapter != null)
//                            ((WordFragment) adapter.getItem(1)).adapter.setPurchased(isPurchased);
//                    }
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
