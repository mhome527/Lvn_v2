package teach.vietnam.asia.view.word;

import android.view.View;

import java.util.List;

import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.view.base.BaseActivity;
import teach.vietnam.asia.view.base.BaseAdapter;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class WordContentAdapter extends BaseAdapter<WordItemHolder> {

    private static String TAG = "WordContentAdapter";

    private List<WordEntity> listData;
    private boolean isPurchased = false;
    String lang;

    public WordContentAdapter(BaseActivity activity) {
        lang = activity.lang;
    }

    @Override
    public int getItemLayout() {
        if (lang.equals(Constant.AR))
            return R.layout.word_item_ar;
        else
            return R.layout.word_item;
    }

    @Override
    public WordItemHolder onCreateView(View view) {
        return new WordItemHolder(view);
    }

    @Override
    public void onBindViewHolder(WordItemHolder holder, int position) {
        holder.bind(position, isPurchased, listData.get(position));
    }

    @Override
    public int getItemCount() {
        if (listData == null)
            return 0;
        return listData.size();
    }

    public void setData(List<WordEntity> listData) {
        this.listData = listData;
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
        this.notifyDataSetChanged();
    }


}
