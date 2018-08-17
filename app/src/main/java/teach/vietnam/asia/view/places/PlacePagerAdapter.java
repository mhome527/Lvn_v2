package teach.vietnam.asia.view.places;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class PlacePagerAdapter extends FragmentStatePagerAdapter {
    final int mNumOfTabs = 3;
    private boolean isPurchased;

    PlaceFragment placeFragment1;
    PlaceFragment placeFragment2;
    PlaceFragment placeFragment3;

    public PlacePagerAdapter(boolean isPurchased, FragmentManager fm) {
        super(fm);
        this.isPurchased = isPurchased;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                placeFragment1 = new PlaceFragment();
                placeFragment1.area = 1;
                placeFragment1.isPurchased = isPurchased;
                return placeFragment1;
            case 1:
                placeFragment2 = new PlaceFragment();
                placeFragment2.area = 2;
                placeFragment2.isPurchased = isPurchased;
                return placeFragment2;
            case 2:
                placeFragment3 = new PlaceFragment();
                placeFragment3.area = 3;
                placeFragment3.isPurchased = isPurchased;
                return placeFragment3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
        if (placeFragment1 != null) {
            placeFragment1.isPurchased = isPurchased;
            if (placeFragment1.adapter != null)
                placeFragment1.adapter.notifyDataSetChanged();
        }

        if (placeFragment2 != null) {
            placeFragment2.isPurchased = isPurchased;
            if (placeFragment2.adapter != null)
                placeFragment2.adapter.notifyDataSetChanged();
        }
        if (placeFragment3 != null) {
            placeFragment3.isPurchased = isPurchased;
            if (placeFragment3.adapter != null)
                placeFragment3.adapter.notifyDataSetChanged();
        }
    }
}
