package teach.vietnam.asia.view.dashboard.search;

import android.view.View;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.view.base.BaseExAdapterView;

public class SearchAdapter extends BaseExAdapterView<SearchHeaderHolder, SearchPlaceItemHolder> {

    private IActionSearch iActionSearch;
    List<SearchGroupData> groups;

    public SearchAdapter(List<SearchGroupData> groups, IActionSearch iActionSearch) {
        super(groups);
        this.iActionSearch = iActionSearch;
        this.groups = groups;
    }

    @Override
    protected int getHeaderLayout() {
        return R.layout.place_item_header;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.place_item;
    }

    @Override
    protected SearchHeaderHolder getHeaderView(View view) {
        return new SearchHeaderHolder(view);
    }

    @Override
    protected SearchPlaceItemHolder getItemView(View view) {
        return new SearchPlaceItemHolder(view, iActionSearch);
    }

    @Override
    protected void onBindHeaderHolder(SearchHeaderHolder holder, int flatPosition, ExpandableGroup group) {
        holder.tvTitle.setText(group.getTitle());
    }

    @Override
    protected void onBindItemHolder(SearchPlaceItemHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        SearchEntity entity = ((SearchGroupData) group).getItems().get(childIndex);
        holder.bind(entity);
    }

    public void setListData(List<SearchGroupData> groups) {
        this.groups = groups;
    }
}
