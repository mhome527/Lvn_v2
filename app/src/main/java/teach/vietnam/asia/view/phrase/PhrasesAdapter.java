package teach.vietnam.asia.view.phrase;

import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.utils.Common;
import teach.vietnam.asia.view.base.BaseAdapterView;
import teach.vietnam.asia.view.base.BaseViewHolder;

/**
 * Created by HuynhTD on 5/10/2017.
 */

public class PhrasesAdapter extends BaseAdapterView<BaseViewHolder> implements Filterable {
    private List<WordEntity> listData;
    private List<WordEntity> mFilteredList;

    boolean isPurchased = false;

    public PhrasesAdapter(List<WordEntity> listData) {
        this.listData = listData;
        mFilteredList = listData;
    }

    @Override
    protected List getListData() {
        return mFilteredList;
    }

    @Override
    protected int getItemLayout(int viewType) {
        if (viewType == TYPE_ITEM)
            return R.layout.phrases_item;
        else
            return -1;

    }

    @Override
    protected BaseViewHolder getItemView(View view, int viewType) {
         if (viewType == TYPE_ITEM)
            return new PhrasesItemHolder(view);
        else
            return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof PhrasesItemHolder)
            ((PhrasesItemHolder) holder).setData(this.mFilteredList.get(position), isPurchased);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String wordVN, word1;
                String charText = charSequence.toString();

                if (charText.isEmpty()) {
                    mFilteredList = listData;
                } else {

                    List<WordEntity> filteredList = new ArrayList<>();
                    charText = charText.toLowerCase(Locale.getDefault()).trim();

                    for (WordEntity vi : listData) {

                        wordVN = android.text.Html.fromHtml(vi.getVi()).toString().toLowerCase();
                        wordVN = Common.stripAccents(wordVN);
                        if (wordVN.contains(charText))
                            filteredList.add(vi);
                        else {
                            word1 = android.text.Html.fromHtml(vi.getO1()).toString().toLowerCase();
                            if (!word1.equals("") && (word1.contains(charText))) {
                                filteredList.add(vi);
                            }
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (List<WordEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setData(List<WordEntity> listData) {
        this.listData = listData;
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

    public WordEntity getItem(int pos) {
        return (WordEntity) getListData().get(pos);
    }
}
