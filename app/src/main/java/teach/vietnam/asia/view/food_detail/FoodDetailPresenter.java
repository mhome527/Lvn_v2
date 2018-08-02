package teach.vietnam.asia.view.food_detail;

import teach.vietnam.asia.entity.FoodEntity;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.base.BasePresenter;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class FoodDetailPresenter extends BasePresenter<FoodDetailActivity> {


    public FoodDetailPresenter(FoodDetailActivity activity) {
        super(activity);
    }

    public void loadData(final int area, final int type, final int id, ICallback<FoodEntity> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public FoodEntity onBackground() {
                        return FoodDetailDao.getData(activity, area, type, id);
                    }
                }
        );
    }

}
