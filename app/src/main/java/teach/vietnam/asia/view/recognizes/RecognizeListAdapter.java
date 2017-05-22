package teach.vietnam.asia.view.recognizes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.RecognizeEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;

public class RecognizeListAdapter extends BaseAdapter {

    private final String TAG = "RecognizeListAdapter";

    private Context context;
    private List<RecognizeEntity> listData;
    private LayoutInflater layoutInflater;
    private AudioPlayer audio;
    private int currPage;
    boolean isPurchased;

    public RecognizeListAdapter(Context context, List<RecognizeEntity> listData, int currPage, boolean isPurchased) {
        this.context = context;
        this.listData = listData;
        this.currPage = currPage;
        this.isPurchased = isPurchased;
        try {
            layoutInflater = LayoutInflater.from(context);
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
            holder.imgLock = (ImageView) view.findViewById(R.id.imgLock);

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

        if (isPurchased || currPage < 10)
            holder.imgLock.setVisibility(View.GONE);
        else
            holder.imgLock.setVisibility(View.VISIBLE);

        holder.btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                audio.speakWord(listData.get(position).getVn());
                if (isPurchased || currPage < 10)
                    audio.speakWord(entity.getVn());
//                else
//                    Utility.installPremiumApp(context);
            }
        });
        return view;
    }

    public class ViewHolder {
        TextView tvWord;
        ImageView imgLock;
        TextView tvEx;
        Button btnSpeak;
    }

}