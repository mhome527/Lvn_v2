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
    FoodFragment tab3;

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
                if (tab2 == null)
                    tab2 = new FoodFragment();
                tab2.kind = 3; // mon nuoc
                return tab2;
            default:
                if (tab3 == null)
                    tab3 = new FoodFragment();
                tab3.kind = 2; // drink
                return tab3;

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
