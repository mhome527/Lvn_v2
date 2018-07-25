package teach.vietnam.asia.view.dashboard.search;

import android.view.View;

import java.util.List;

import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.base.BaseAdapterView;
import teach.vietnam.asia.view.base.BaseViewHolder;
import teach.vietnam.asia.view.action.IClickListener;

public class SearchAdapter extends BaseAdapterView<BaseViewHolder> {
    private static String TAG = "DashboardSearchAdapter";
    private static final int TYPE_ITEM_PHRASES = 1;
    private static final int TYPE_ITEM_PLACE = 2;

    private List<SearchEntity> listData;
    private IClickListener iClickListener;

    public SearchAdapter(IClickListener iClickListener) {
        this.iClickListener = iClickListener;
    }

    @Override
    protected int getHeaderLayout(int viewType) {
        return 0;
    }

    @Override
    protected int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    protected int getItemLayout(int viewType) {
        if (viewType == TYPE_ITEM_PHRASES)
            return R.layout.dashboard_search_word_item;
        else
            return R.layout.dashboard_search_place_item;
    }

    @Override
    protected BaseViewHolder getHeaderView(View view) {
        return new SearchWordItemHolder(view, iClickListener);
    }

    @Override
    protected BaseViewHolder getFooterView(View view) {
        return null;
    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    @Override
    protected BaseViewHolder getItemView(View view) {
        return new SearchPlaceItemHolder(view, iClickListener);
    }

    @Override
    protected List getListData() {
        return listData;
    }


    @Override
    public int getItemViewType(int position) {
        if (listData == null)
            return TYPE_ITEM_PHRASES;

        if (listData.get(position).kind == Constant.TYPE_DATA_PLACE)
            return TYPE_ITEM_PLACE;
        else
            return TYPE_ITEM_PHRASES;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof SearchWordItemHolder) {
            ((SearchWordItemHolder) holder).setData(listData.get(position));
        } else {
            ((SearchPlaceItemHolder) holder).setData(listData.get(position));
        }
    }

    public void setListData(List<SearchEntity> listData) {
        this.listData = listData;
    }

    public SearchEntity getItem(int pos) {
        return listData.get(pos);
    }
}
