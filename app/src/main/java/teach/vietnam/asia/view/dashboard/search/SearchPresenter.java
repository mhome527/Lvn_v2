package teach.vietnam.asia.view.dashboard.search;

import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.base.BasePresenter;
import teach.vietnam.asia.view.dashboard.DashboardActivity;

public class SearchPresenter extends BasePresenter<DashboardActivity> {

    SearchDao dao;

    public SearchPresenter(DashboardActivity activity) {
        super(activity);
        dao = new SearchDao(activity);
    }

    public void getData(final String keySearch, ICallback<List<SearchGroupData>> iCallback) {
        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                ArrayList<SearchGroupData> groups = new ArrayList<>();
                SearchGroupData group;
                List<SearchEntity> items;

                //Foods
                items = dao.getFood(keySearch);
                if (items != null && items.size() > 0) {
                    group = new SearchGroupData(1, 1, activity.getString(R.string.title_food), items);
                    groups.add(group);
                }

                //Phrases
                items = dao.getPhrases(keySearch);
                if (items != null && items.size() > 0) {
                    group = new SearchGroupData(2, 1, activity.getString(R.string.title_phrase), items);
                    groups.add(group);
                }

                // Places
                items = dao.getPlace(keySearch);
                if (items != null && items.size() > 0) {
                    group = new SearchGroupData(3, 1, activity.getString(R.string.title_place), items);
                    groups.add(group);
                }

                return groups;
            }
        });
    }

}
