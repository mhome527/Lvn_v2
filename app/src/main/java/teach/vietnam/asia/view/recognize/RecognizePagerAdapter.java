//package teach.vietnam.asia.view.recognize;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//
//import java.util.List;
//
//import teach.vietnam.asia.Constant;
//import teach.vietnam.asia.R;
//import teach.vietnam.asia.entity.RecognizeEntity;
//import teach.vietnam.asia.view.BaseActivity;
//
//
//public class RecognizePagerAdapter extends PagerAdapter {
//
//    private final String TAG = "RecognizePagerAdapter";
//    private RecognizeMainActivity activity;
//    //    public ArrayList<PracticeDetailEntity> lstExceriese;
//    private int num;
//    private String lang;
//    ListView lstRecognize;
////    RecognizePresenter presenter;
//
//    public RecognizePagerAdapter(RecognizeMainActivity activity, int num) {
//
////        lstExceriese = new ArrayList<>();
//        this.num = num;
//        this.activity = activity;
////        lang  = activity.getString(R.string.language);
//        lang = BaseActivity.pref.getStringValue("en", Constant.EN);
//
//    }
//
//    @Override
//    public int getCount() {
//        return num;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == ((View) object);
//    }
//
//    @SuppressLint("InflateParams")
//    public Object instantiateItem(ViewGroup collection, final int position) {
//        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View view = inflater.inflate(R.layout.recognize_list, null);
//
//        lstRecognize = (ListView) view.findViewById(R.id.lstRecognize);
//
//        view.setTag(position);
//        ((ViewPager) collection).addView(view, 0);
//        List<RecognizeEntity> lstData = activity.presenter.loadData(position + 1);
//        RecognizeListAdapter adapter = new RecognizeListAdapter(activity, lstData, position + 1);
//        lstRecognize.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//
////        new LoadData(lstRecognize, position).execute();
////        activity.presenter.loadData(position + 1, new ICallback<List<RecognizeEntity>>() {
////            @Override
////            public void onCallback(final List<RecognizeEntity> data) {
////                activity.runOnUiThread(new Runnable() {
////                    @Override
////            public void run() {
////                Log.i(TAG, "loadData page size:" + data.size());
////                RecognizeListAdapter adapter = new RecognizeListAdapter(activity, data, position + 1);
////                lstRecognize.setAdapter(adapter);
////                adapter.notifyDataSetChanged();
////            }
////        });
////    }
////
////            @Override
////            public void onFail(String err) {
////                Log.e(TAG, "onFail error:" + err);
////            }
////        });
//
//
//        return view;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        ((ViewPager) container).removeView((View) object);
//    }
//
//}
