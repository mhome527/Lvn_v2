package teach.vietnam.asia.view.phrase;

import java.util.List;

import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.view.BasePresenter;
import teach.vietnam.asia.view.ICallback;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class PhrasesPresenter extends BasePresenter<PhrasesActivity> {


    public PhrasesPresenter(PhrasesActivity activity) {
        super(activity);
    }

    public void loadData(ICallback<List<WordEntity>> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public List<WordEntity> onBackground() {
                        return PhrasesDao.getListData(activity, 11);
                    }
                }
        );
    }

}
