package teach.vietnam.asia.view.recognizes.test;

import java.util.List;

import teach.vietnam.asia.entity.RecognizeEntity;
import teach.vietnam.asia.view.base.BasePresenter;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.recognizes.RecognizeDao;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class RecognizeTestPresenter extends BasePresenter<RecognizeTestActivity> {


    public RecognizeTestPresenter(RecognizeTestActivity activity) {
        super(activity);
    }

    public void loadData(final int kind, ICallback<List<RecognizeEntity>> callback) {
        this.loadData(callback, new ILoadData() {
                    @Override
                    public List<RecognizeEntity> onBackground() {
                        return RecognizeDao.getListData(activity, kind);
                    }
                }
        );
    }

    public List<RecognizeEntity> loadData(int kind){
        return RecognizeDao.getListData(activity, kind);
    }

    public void loadGroup( ICallback<List<String>> callback) {
        this.loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                return RecognizeDao.getGroupData(activity);
            }
        });
    }
}
