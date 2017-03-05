package teach.vietnam.asia.view.foods;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.BaseActivity;


/**
 * Created by huynhtd on 10/17/2016.
 */

public class FoodActivity extends BaseActivity<FoodActivity> {

    private static String TAG = "FoodActivity";


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    FoodPagerAdapter adapter;
    int currPage = 0;

    @Override
    protected int getLayout() {
        return R.layout.word_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView");

        setTitleCenter(getString(R.string.title_word));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_food2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_drink2));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new FoodPagerAdapter
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
    }


}
