package teach.vietnam.asia.view.recognizes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.RecognizeEntity;

public class RecognizePagerAdapter extends PagerAdapter {

    private final String TAG = "RecognizePagerAdapter";
    private RecognizeMainActivity activity;
    private int num;
    ListView lstRecognize;
    boolean isPurchased = false;
//    RecognizePresenter presenter;

    public RecognizePagerAdapter(RecognizeMainActivity activity, int num) {
        this.num = num;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return num;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @SuppressLint("InflateParams")
    public Object instantiateItem(ViewGroup collection, final int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.recognize_list, null);

        lstRecognize = (ListView) view.findViewById(R.id.lstRecognize);

        view.setTag(position);
        ((ViewPager) collection).addView(view, 0);
        List<RecognizeEntity> lstData = activity.presenter.loadData(position + 1);
        RecognizeListAdapter adapter = new RecognizeListAdapter(activity, lstData, position + 1, isPurchased);
        lstRecognize.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
        this.notifyDataSetChanged();
    }

}
