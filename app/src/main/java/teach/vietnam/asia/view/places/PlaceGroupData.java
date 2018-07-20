package teach.vietnam.asia.view.places;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import teach.vietnam.asia.entity.PlaceEntity;


public class PlaceGroupData extends ExpandableGroup<PlaceEntity> {

    public int type;
    public int group;

    public PlaceGroupData(int group, int type, String title, List<PlaceEntity> items) {
        super(title, items);
        this.group = group;
        this.type = type;
    }
}
