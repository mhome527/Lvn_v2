package teach.vietnam.asia.view.dashboard.search;

import java.util.List;

import teach.vietnam.asia.view.base.BasePresenter;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.dashboard.DashboardActivity;

public class SearchPresenter extends BasePresenter<DashboardActivity> {

    SearchDao dao;

    public SearchPresenter(DashboardActivity activity) {
        super(activity);
        dao = new SearchDao(activity);
    }

    public void getData(final String keySearch, ICallback<List<SearchEntity>> iCallback) {
        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getData(keySearch);
            }
        });
    }

}
