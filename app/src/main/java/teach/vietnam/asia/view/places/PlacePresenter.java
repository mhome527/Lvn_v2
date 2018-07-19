package teach.vietnam.asia.view.places;

import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.view.BasePresenter;
import teach.vietnam.asia.view.ICallback;

public class PlacePresenter extends BasePresenter<PlaceActivity> {

    PlaceDao dao;

    public PlacePresenter(PlaceActivity activity) {
        super(activity);
        dao = new PlaceDao(activity);
    }

    public void getData(final int pos, ICallback<ArrayList<PlaceGroupData>> iCallback) {
        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                ArrayList<PlaceGroupData> groups = new ArrayList<>();
                PlaceGroupData group;
                List<PlaceEntity> items;

                List<PlaceEntity> titleGroups = dao.getPlaces(pos);
                if (titleGroups == null || titleGroups.size() == 0)
                    return groups;

                for (PlaceEntity entity : titleGroups) {
                    items = dao.getPlaceDetail(entity.group, entity.type);
                    group = new PlaceGroupData(entity.group, entity.type,  entity.title, items);
                    groups.add(group);
                }

                return groups;
            }
        });
    }

}
