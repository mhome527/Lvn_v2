//package teach.vietnam.asia.view.dashboard.search;
//
//import android.view.View;
//
//import java.util.List;
//
//import teach.vietnam.asia.Constant;
//import teach.vietnam.asia.R;
//import teach.vietnam.asia.view.BaseAdapterView;
//import teach.vietnam.asia.view.BaseViewHolder;
//import teach.vietnam.asia.view.IClickListener;
//
//public class SearchAdapter extends BaseAdapterView<BaseViewHolder> {
//    private static String TAG = "DashboardSearchAdapter";
//
//    private List<SearchEntity> listData;
//    private IClickListener iClickListener;
//    public boolean isHistory = true;
//
//    public SearchAdapter(IClickListener iClickListener) {
//        this.iClickListener = iClickListener;
//    }
//
//    @Override
//    protected int getHeaderLayout() {
//        if (isHistory)
//            return 0;
//        else
//            return R.layout.search_header_item;
//    }
//
//    @Override
//    protected int getFooterLayout() {
//        return 0;
//    }
//
//    @Override
//    protected int getItemLayout() {
//        if (isHistory)
//            return R.layout.search_history_item;
//        else
//            return R.layout.search_item;
//
//    }
//
//    @Override
//    protected BaseViewHolder getHeaderView(View view) {
//        return new SearchWordItemHolder(view, iClickListener);
//    }
//
//    @Override
//    protected BaseViewHolder getFooterView(View view) {
//        return null;
//    }
//
//    @Override
//    public int getItemCount() {
//        return getSize();
//    }
//
//    @Override
//    protected BaseViewHolder getItemView(View view) {
//
//        return new SearchPlaceItemHolder(view, iClickListener);
//    }
//
//    @Override
//    protected List getListData() {
//        return null;
//    }
//
//    @Override
//    protected int getSize() {
//        if (listData == null)
//            return 0;
//        return listData.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (listData == null || isHistory)
//            return TYPE_ITEM;
//
//        if (listData.get(position).kind == Constant.KIND_TITLE)
//            return TYPE_HEADER;
//        else
//            return TYPE_ITEM;
//    }
//
//    @Override
//    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        if (holder instanceof SearchWordItemHolder) {
//            SearchWordItemHolder itemHolder = (SearchWordItemHolder) holder;
//            itemHolder.setData(listData.get(position));
//        } else if (holder instanceof SearchHistoryItemHolder) {
//            SearchHistoryItemHolder itemHolder = (SearchHistoryItemHolder) holder;
//            itemHolder.setData(listData.get(position));
//        } else {
//            SearchPlaceItemHolder itemHolder = (SearchPlaceItemHolder) holder;
//            itemHolder.setData(listData.get(position));
//        }
//    }
//
//    public void setListData(List<SearchEntity> listData, boolean isHistory) {
//        this.listData = listData;
//        this.isHistory = isHistory;
//    }
//
//    public SearchEntity getItem(int pos) {
//        return listData.get(pos);
//    }
//}
