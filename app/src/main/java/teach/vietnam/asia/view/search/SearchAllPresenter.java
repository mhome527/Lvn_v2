package teach.vietnam.asia.view.search;

import java.util.List;

import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.view.base.BasePresenter;
import teach.vietnam.asia.view.action.ICallback;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class SearchAllPresenter extends BasePresenter<SearchAllActivity> {


    public SearchAllPresenter(SearchAllActivity activity) {
        super(activity);
    }

//    public void loadData(ICallback<List<WordEntity>> callback) {
//        loadData(callback, new ILoadData() {
//                    @Override
//                    public List<WordEntity> onBackground() {
//                        return SearchDao.getListData(activity, 11);
//                    }
//                }
//        );
//    }

 public void loadDataAll(ICallback<List<WordEntity>> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public List<WordEntity> onBackground() {
                        return SearchDao.getListDataAll(activity);
                    }
                }
        );
    }

}
