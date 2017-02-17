package teach.vietnam.asia.view.word;

import java.util.List;

import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.view.BasePresenter;
import teach.vietnam.asia.view.ICallback;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class WordPresenter extends BasePresenter<WordActivity> {


    public WordPresenter(WordActivity activity) {
        super(activity);
    }

    public void loadData(final int[] kind, ICallback<List<WordEntity>> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public List<WordEntity> onBackground() {
                        return WordDao.getListData(activity, kind);
                    }
                }
        );
    }

}
