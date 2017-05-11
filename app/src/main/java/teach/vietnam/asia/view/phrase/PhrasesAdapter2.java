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
import teach.vietnam.asia.view.BaseAdapterView;

/**
 * Created by HuynhTD on 5/10/2017.
 */

public class PhrasesAdapter2 extends BaseAdapterView<PhrasesItemHolder> implements Filterable {
    private List<WordEntity> listData;
    private List<WordEntity> mFilteredList;

    public PhrasesAdapter2(List<WordEntity> listData) {
        this.listData = listData;
        mFilteredList = listData;
    }

    @Override
    protected List getListData() {
//        return listData;
        return mFilteredList;
    }

    @Override
    protected int getHeaderLayout() {
        return 0;
    }

    @Override
    protected int getFooterLayout() {
        return 0;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.phrases_item;
    }

    @Override
    protected PhrasesItemHolder getHeaderView(View view) {
        return null;
    }

    @Override
    protected PhrasesItemHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected PhrasesItemHolder getItemView(View view) {
        return new PhrasesItemHolder(view);
    }


    @Override
    public void onBindViewHolder(PhrasesItemHolder holder, int position) {
        holder.setData(this.mFilteredList.get(position), true); //// FIXME: 5/11/2017 check param 2
    }

    public void setData(List<WordEntity> listData) {
        this.listData = listData;
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

    public WordEntity getItem(int pos) {
        return (WordEntity) getListData().get(pos);
    }
}
