package teach.vietnam.asia.view.places;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.google.firebase.crash.FirebaseCrash;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.BaseActivity;

public class PlaceActivity extends BaseActivity<PlaceActivity> {
    private final String TAG = "PlaceActivity";

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    PlacePagerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.places_activity;
    }

    @Override
    protected void initView() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.southern));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.central));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.northern));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new PlacePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                currPage = tab.getPosition();
//                viewPager.setCurrentItem(currPage);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FirebaseCrash.logcat(Log.INFO, TAG, "initView");
    }
}
