package teach.vietnam.asia.view.foods;

import java.util.List;

import teach.vietnam.asia.entity.FoodEntity;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.base.BasePresenter;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class FoodPresenter extends BasePresenter<FoodActivity> {


    public FoodPresenter(FoodActivity activity) {
        super(activity);
    }

    public void loadData(final int kind, ICallback<List<FoodEntity>> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public List<FoodEntity> onBackground() {
                        return FoodDao.getListData(activity, kind);
                    }
                }
        );
    }

}
