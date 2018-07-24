package teach.vietnam.asia.view.dashboard.search;

import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.view.BasePresenter;
import teach.vietnam.asia.view.ICallback;
import teach.vietnam.asia.view.dashboard.DashboardActivity;
import teach.vietnam.asia.view.places.PlaceDao;
import teach.vietnam.asia.view.places.PlaceGroupData;

public class SearchPresenter extends BasePresenter<DashboardActivity> {

    PlaceDao dao;

    public SearchPresenter(DashboardActivity activity) {
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
