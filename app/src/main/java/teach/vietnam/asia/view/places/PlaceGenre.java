package teach.vietnam.asia.view.places;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class PlaceGenre extends ExpandableGroup<PlaceEntity> {


    //    protected PlaceGenre(Parcel in) {
//        super(in);
//    }

    public PlaceGenre(String title, List<PlaceEntity> items) {
        super(title, items);
    }


}
