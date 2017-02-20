package teach.vietnam.asia.view.practice.detail;

import java.util.List;

import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.view.BasePresenter;
import teach.vietnam.asia.view.ICallback;
import teach.vietnam.asia.view.practice.PracticeDao;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class PracticeDetailPresenter extends BasePresenter<PracticeDetailActivity> {


    public PracticeDetailPresenter(PracticeDetailActivity activity) {
        super(activity);
    }

    public void loadData(final int kind, final int level, ICallback<List<WordEntity>> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public List<WordEntity> onBackground() {
                        return PracticeDao.getListData(activity, kind, level);
                    }
                }
        );
    }

     public void loadMaxLevel(final int kind, ICallback<Integer> callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public Integer onBackground() {
                        return PracticeDao.getMaxCount(activity, kind);
                    }
                }
        );
    }



}
