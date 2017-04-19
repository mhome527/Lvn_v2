package teach.vietnam.asia.view.recognizes.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.PracticeDetailEntity;
import teach.vietnam.asia.utils.Log;

public class RecognizeTestPagerAdapter extends PagerAdapter {

    private final String TAG = "RecognizeTestPagerAdapter";

    private RecognizeTestListAdapter.RecognizeTest recognizeTest;

    private RecognizeTestActivity activity;
    private int num;
    public ArrayList<PracticeDetailEntity> lstExceriese;
    private List dataRecognize;

    public RecognizeTestPagerAdapter(RecognizeTestActivity activity, List dataRecognize, RecognizeTestListAdapter.RecognizeTest recognizeTest) {
        lstExceriese = new ArrayList<>();
        this.activity = activity;
        this.dataRecognize = dataRecognize;
        this.recognizeTest = recognizeTest;
    }


    @Override
    public int getCount() {
        if (dataRecognize == null)
            return 0;
        return dataRecognize.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @SuppressLint("InflateParams")
    public Object instantiateItem(ViewGroup collection, final int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.recognize_test_list, null);

        ListView lstRecognize = (ListView) view.findViewById(R.id.lstRecognize);

        if (dataRecognize != null || dataRecognize.size() > 0) {
            RecognizeTestListAdapter adapter = new RecognizeTestListAdapter(activity, dataRecognize, recognizeTest);
            lstRecognize.setAdapter(adapter);
        } else {
            Log.e(TAG, "load data recognize error pos:" + position);
        }
        view.setTag(position);
        ((ViewPager) collection).addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

}
