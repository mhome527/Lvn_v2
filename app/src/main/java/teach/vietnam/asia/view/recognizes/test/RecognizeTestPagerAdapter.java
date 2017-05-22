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

public class RecognizeTestPagerAdapter extends PagerAdapter {

    private final String TAG = "RecognizeTestPagerAdapter";

    private RecognizeTestListAdapter.RecognizeTest recognizeTest;

    private RecognizeTestActivity activity;
    private int totalPage;
    List<RecognizeTestListAdapter> adapters;

    public RecognizeTestPagerAdapter(RecognizeTestActivity activity, int totalPage, RecognizeTestListAdapter.RecognizeTest recognizeTest) {
//        lstExceriese = new ArrayList<>();
        this.activity = activity;
//        this.dataRecognize = dataRecognize;
//        this.recognizeTest = recognizeTest;
        this.totalPage = totalPage;
        adapters = new ArrayList<>();

        for(int i = 0; i < totalPage; i++){
            RecognizeTestListAdapter adapter = new RecognizeTestListAdapter(activity, i, recognizeTest);
            adapters.add(adapter);
        }
    }


    @Override
    public int getCount() {
        return totalPage;
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

//            RecognizeTestListAdapter adapter = new RecognizeTestListAdapter(activity, position, recognizeTest);
//            lstRecognize.setAdapter(adapter);
            lstRecognize.setAdapter(adapters.get(position));

        view.setTag(position);
        ((ViewPager) collection).addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

//    public void setData(List dataRecognize) {
//        this.dataRecognize = dataRecognize;
//    }

}
