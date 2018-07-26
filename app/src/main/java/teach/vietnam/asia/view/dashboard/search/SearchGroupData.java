package teach.vietnam.asia.view.dashboard.search;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;


public class SearchGroupData extends ExpandableGroup<SearchEntity> {

    public int type;
    public int group;

    public SearchGroupData(int group, int type, String title, List<SearchEntity> items) {
        super(title, items);
        this.group = group;
        this.type = type;
    }
}
