package teach.vietnam.asia.view.recognize;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.RecognizeEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.BaseActivity;

@SuppressLint("DefaultLocale")
public class RecognizeListAdapter extends BaseAdapter {

    private final String TAG = "RecognizeListAdapter";

    private Context context;
    private List<RecognizeEntity> listData;
    private LayoutInflater layoutInflater;
    private String lang = "";
    private AudioPlayer audio;
    private int currPage;

    public RecognizeListAdapter(Context context, List<RecognizeEntity> listData, int currPage) {
        this.context = context;
        this.listData = listData;
        this.currPage = currPage;
        try {
            layoutInflater = LayoutInflater.from(context);
//            lang = context.getString(R.string.language);
            lang = BaseActivity.pref.getStringValue("en", Constant.EN);
            audio = new AudioPlayer(context);

        } catch (Exception e) {
            Log.e(TAG, "RecognizeListAdapter Error: " + e.getMessage());
        }

    }

    // public LearnAdapter(Context context) {
    // mContext = context;
    // }

    public int getCount() {
        return listData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        String ex, ot;
        if (view == null) {
            holder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.recognize_item, null);
            holder.tvWord = (TextView) view.findViewById(R.id.tvWord);
            holder.tvEx = (TextView) view.findViewById(R.id.tvEx);
//            holder.tvOther = (TextView) view.findViewById(R.id.tvOther);

            holder.btnSpeak = (Button) view.findViewById(R.id.btnSpeak);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final RecognizeEntity entity = listData.get(position);
        ex = entity.getEx();
        if (ex != null && !ex.equals("")) {
            holder.tvEx.setText(ex);
        }
        holder.tvWord.setText(entity.getVn());

        ot = entity.getOt();
        if (ot != null && !ot.equals(""))
            holder.tvEx.setText(ex + ": " + ot);

        holder.btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                audio.speakWord(listData.get(position).getVn());
                if (Constant.isPro || currPage < 10)
                    audio.speakWord(entity.getVn());
//                else
//                    Utility.installPremiumApp(context);
            }
        });
        return view;
    }

    public class ViewHolder {
        TextView tvWord;
//        TextView tvOther;
        TextView tvEx;
        Button btnSpeak;
    }

}