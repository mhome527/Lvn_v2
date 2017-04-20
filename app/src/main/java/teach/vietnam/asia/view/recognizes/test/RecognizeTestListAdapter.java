package teach.vietnam.asia.view.recognizes.test;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.RecognizeEntity;
import teach.vietnam.asia.utils.Log;

public class RecognizeTestListAdapter extends BaseAdapter {

    private final String TAG = "RecognizeTestListAdapter";
    //    private Context context;
    private List<RecognizeEntity> listData;
    private LayoutInflater layoutInflater;
//    private String lang = "";
//    private String ans = "";

    public interface RecognizeTest {
        String getCurrWord();
    }

    private RecognizeTest recognizeTest;

    public RecognizeTestListAdapter(RecognizeTestActivity activity, int kind, RecognizeTest recognizeTest) {
//        lang = BaseActivity.pref.getStringValue("en", Constant.EN);
        this.listData = activity.presenter.loadData(kind + 1);

//        this.listData = cloneData(listData);
        this.recognizeTest = recognizeTest;

        try {
            RandData();
            layoutInflater = LayoutInflater.from(activity);

        } catch (Exception e) {
            Log.e(TAG, "SearchAllAdapter Error: " + e.getMessage());
        }

    }

//    private List cloneData(List<RecognizeEntity> listData) {
//        List<RecognizeEntity> lstTmp = new ArrayList();
//
//        for (RecognizeEntity entry : listData) {
//            lstTmp.add(entry);
//        }
//        return lstTmp;
//    }

    public int getCount() {
        if (listData == null)
            return 0;
        return listData.size();
    }

    public RecognizeEntity getItem(int position) {
        return listData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.recognize_test_item, null);
            holder.tvWord = (TextView) view.findViewById(R.id.tvWord);
            holder.tvEx = (TextView) view.findViewById(R.id.tvEx);
            holder.tvOther = (TextView) view.findViewById(R.id.tvOther);

            holder.imgCheck = (ImageView) view.findViewById(R.id.imgCheck);
            holder.rlWord = (RelativeLayout) view.findViewById(R.id.rlWord);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        RecognizeEntity entity = listData.get(position);
        holder.rlWord.setTag(R.id.tvWord, entity.getVn());
        holder.rlWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag;
                tag = (String) holder.rlWord.getTag(R.id.tvWord);
                Log.i(TAG, "Word compare: " + tag + "; currWord:" + recognizeTest.getCurrWord());
                if (tag.equals(recognizeTest.getCurrWord())) {
                    holder.imgCheck.setBackgroundResource(R.drawable.o);
                    holder.tvWord.setTypeface(null, Typeface.BOLD);
                } else
                    holder.imgCheck.setBackgroundResource(R.drawable.x);
            }
        });

        holder.imgCheck.setBackgroundResource(R.drawable.ck_cycle);
        holder.tvOther.setText(entity.getOt());
        holder.tvWord.setText(entity.getVn());
        holder.tvEx.setText(entity.getEx());
        return view;
    }


    public class ViewHolder {
        TextView tvWord;
        TextView tvOther;
        TextView tvEx;
        ImageView imgCheck;
        RelativeLayout rlWord;
    }

    private void RandData() {
        int number1;
        int number2;
        int size = listData.size();

        RecognizeEntity entryTmp;
        Random ran;
        for (int i = 0; i < size; i++) {
            ran = new Random();
            number1 = ran.nextInt(size - 1);

            ran = new Random();
            number2 = ran.nextInt(size - 1);

            entryTmp = listData.get(number2);
            listData.set(number2, listData.get(number1));
            listData.set(number1, entryTmp);

        }
    }

}