package teach.vietnam.asia.view.recognize;

import java.util.List;

import teach.vietnam.asia.entity.RecognizeEntity;
import teach.vietnam.asia.view.BasePresenter;
import teach.vietnam.asia.view.ICallback;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class RecognizePresenter extends BasePresenter<RecognizeMainActivity> {


    public RecognizePresenter(RecognizeMainActivity activity) {
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
