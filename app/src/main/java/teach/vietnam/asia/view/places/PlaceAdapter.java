package teach.vietnam.asia.view.places;

import android.view.View;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.view.base.BaseExAdapterView;

public class PlaceAdapter extends BaseExAdapterView<PlaceHeaderHolder, PlaceItemHolder> {

    private IPlaceListener iPlaceListener;
    List<PlaceGroupData> groups;

    public PlaceAdapter(List<PlaceGroupData> groups, IPlaceListener iPlaceListener) {
        super(groups);
        this.iPlaceListener = iPlaceListener;
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
    protected PlaceHeaderHolder getHeaderView(View view) {
        return new PlaceHeaderHolder(view);
    }

    @Override
    protected PlaceItemHolder getItemView(View view) {
        return new PlaceItemHolder(view, iPlaceListener);
    }

    @Override
    protected void onBindHeaderHolder(PlaceHeaderHolder holder, int flatPosition, ExpandableGroup group) {
        holder.tvTitle.setText(group.getTitle());
    }

    @Override
    protected void onBindItemHolder(PlaceItemHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        PlaceEntity entity = ((PlaceGroupData) group).getItems().get(childIndex);
        holder.bind(entity);
    }


}
