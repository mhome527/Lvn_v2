package teach.vietnam.asia.view.places;

import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import teach.vietnam.asia.R;

import static android.view.LayoutInflater.from;

public class PlaceAdapter extends MultiTypeExpandableRecyclerViewAdapter<PlaceHeaderHolder, PlaceItemHolder> {

    public PlaceAdapter(List<PlaceGenre> groups) {
        super(groups);
    }

    @Override
    public PlaceHeaderHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext())
                .inflate(R.layout.place_item_header, parent, false);
        return new PlaceHeaderHolder(view);
    }

    @Override
    public PlaceItemHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext())
                .inflate(R.layout.place_item, parent, false);
        return new PlaceItemHolder(view);
    }

    @Override
    public void onBindChildViewHolder(PlaceItemHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        holder.bind(group);
    }

    @Override
    public void onBindGroupViewHolder(PlaceHeaderHolder holder, int flatPosition, ExpandableGroup group) {
        holder.bind(group);
    }
}
