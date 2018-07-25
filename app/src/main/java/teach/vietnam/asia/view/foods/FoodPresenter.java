package teach.vietnam.asia.view.foods;

import java.util.List;

import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.view.base.BasePresenter;
import teach.vietnam.asia.view.action.ICallback;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class FoodPresenter extends BasePresenter<FoodActivity> {


    public FoodPresenter(FoodActivity activity) {
        super(activity);
    }

    public void loadData(final int kind, ICallback<List<WordEntity>> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public List<WordEntity> onBackground() {
                        return FoodDao.getListData(activity, kind);
                    }
                }
        );
    }

}
