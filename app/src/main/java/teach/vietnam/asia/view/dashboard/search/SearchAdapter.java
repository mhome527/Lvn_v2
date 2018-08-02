package teach.vietnam.asia.view.dashboard.search;

import android.view.View;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.base.BaseChildExViewHolder;
import teach.vietnam.asia.view.base.BaseExAdapterView;

public class SearchAdapter extends BaseExAdapterView<SearchHeaderHolder, BaseChildExViewHolder> {

    private final int VIEW_TYPE_CHILD_1 = 11;
    private final int VIEW_TYPE_CHILD_2 = 12;

    private IActionSearch iActionSearch;
    List<SearchGroupData> groups;

    public SearchAdapter(List<SearchGroupData> groups, IActionSearch iActionSearch) {
        super(groups);
        this.iActionSearch = iActionSearch;
        this.groups = groups;
    }

    @Override
    protected int getHeaderLayout() {
        return R.layout.dashboard_search_item_header;
    }

    @Override
    protected int getItemLayout(int viewType) {
        if (viewType == VIEW_TYPE_CHILD_1)
            return R.layout.dashboard_search_item_1;
        else
            return R.layout.dashboard_search_item_2;
    }

    @Override
    protected SearchHeaderHolder getHeaderView(View view) {
        return new SearchHeaderHolder(view, iActionSearch);
    }

    @Override
    protected BaseChildExViewHolder getItemView(View view, int viewType) {
        if (viewType == VIEW_TYPE_CHILD_1)
            return new SearchItem1Holder(view, iActionSearch);
        else
            return new SearchItem2Holder(view, iActionSearch);
    }

    @Override
    protected void onBindHeaderHolder(SearchHeaderHolder holder, int flatPosition, ExpandableGroup group) {
//        holder.tvTitle.setText(group.getTitle());
        holder.bind(flatPosition, group.getTitle());
    }

    @Override
    protected void onBindItemHolder(BaseChildExViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        SearchEntity entity = ((SearchGroupData) group).getItems().get(childIndex);
        int viewType = getItemViewType(flatPosition);
        switch (viewType) {
            case VIEW_TYPE_CHILD_1: // kind place and food
                ((SearchItem1Holder) holder).bind(entity);
                break;
            case VIEW_TYPE_CHILD_2: // kind phrase
            default:
                ((SearchItem2Holder) holder).bind(entity);
                break;
        }
//        holder.bind(entity);
    }

    @Override
    public int getChildViewType(int position, ExpandableGroup group, int childIndex) {
        SearchEntity entity = ((SearchGroupData) group).getItems().get(childIndex);
        if (entity.kind == Constant.SEARCH_DATA_PHRASES)
            return VIEW_TYPE_CHILD_2; //phrase
        else
            return VIEW_TYPE_CHILD_1; // place, food
    }

    @Override
    public boolean isChild(int viewType) {
        return viewType == VIEW_TYPE_CHILD_1 || viewType == VIEW_TYPE_CHILD_2;
    }

    public void setListData(List<SearchGroupData> groups) {
        this.groups = groups;
    }
}
