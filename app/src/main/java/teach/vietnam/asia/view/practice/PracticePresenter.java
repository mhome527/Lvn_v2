//package teach.vietnam.asia.view.practice;
//
//import java.util.List;
//
//import teach.vietnam.asia.entity.WordEntity;
//import teach.vietnam.asia.view.BasePresenter;
//import teach.vietnam.asia.view.ICallback;
//import teach.vietnam.asia.view.phrase.PhrasesActivity;
//
///**
// * Created by HuynhTD on 01/19/2017.
// */
//
//public class PracticePresenter extends BasePresenter<PhrasesActivity> {
//
//
//    public PracticePresenter(PhrasesActivity activity) {
//        super(activity);
//    }
//
//    public void loadData(int kind, int level, ICallback<List<WordEntity>> callback) {
//        loadData(callback, new ILoadData() {
//                    @Override
//                    public List<WordEntity> onBackground() {
//                        return PracticeDao.getListData(activity, 11, 0);
//                    }
//                }
//        );
//    }
//
//}
