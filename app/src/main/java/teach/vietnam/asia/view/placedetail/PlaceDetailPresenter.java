package teach.vietnam.asia.view.placedetail;

import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.view.BasePresenter;

public class PlaceDetailPresenter extends BasePresenter<PlaceDetailActivity> {

    PlaceDetailDao dao;

    public PlaceDetailPresenter(PlaceDetailActivity activity) {
        super(activity);
        dao = new PlaceDetailDao(activity);
    }

    public PlaceEntity getData(int area, int type, int id) {
        return dao.getPlaceDetail(area, type, id);
    }
}
