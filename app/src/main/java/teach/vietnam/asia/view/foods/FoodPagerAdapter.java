package teach.vietnam.asia.view.foods;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class FoodPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    FoodFragment tab1;
    FoodFragment tab2;

    public FoodPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (tab1 == null)
                    tab1 = new FoodFragment();
                tab1.kind = 1; // food
                return tab1;
            case 1:
            default:
                if (tab2 == null)
                    tab2 = new FoodFragment();
                tab2.kind = 2; // drink
                return tab2;

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
