//package teach.vietnam.asia.view.recognize.test;
//
//import android.os.Bundle;
//import android.support.v4.view.ViewPager;
//import android.view.View;
//import android.widget.ImageButton;
//
//import java.util.List;
//import java.util.Random;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import teach.vietnam.asia.Constant;
//import teach.vietnam.asia.R;
//import teach.vietnam.asia.entity.RecognizeEntity;
//import teach.vietnam.asia.sound.AudioPlayer;
//import teach.vietnam.asia.utils.Log;
//import teach.vietnam.asia.view.BaseFragment;
//import teach.vietnam.asia.view.recognize.RecognizeMainActivity;
//import teach.vietnam.asia.view.recognize.learn.LearnRecoginzeFragment;
//import teach.vietnam.asia.view.recognizes.test.RecognizeTestListAdapter;
//
//
//public class TestRecognizeFragment extends BaseFragment<RecognizeMainActivity> implements RecognizeTestListAdapter.RecognizeTest {
//    private static final String ARG_PAGER = "arg_pager";
//
//    @BindView(R.id.imgLeft)
//    ImageButton imgLeft;
//
//    @BindView(R.id.imgRight)
//    ImageButton imgRight;
//
//    @BindView(R.id.pagerRecognize)
//    ViewPager pagerRecognize;
//
//    public int currPage = 0;
//    public int arrW[];
//
//    //    private DaoMaster daoMaster;
////    private tblRecognizeDao dao;
//    private List<RecognizeEntity> dataRecognize;
//
//    private RecognizePagerTestAdapter adapterPage;
//    //    private DaoMaster daoMaster;
//    private AudioPlayer audio;
//    private int kind = 1;
//
//    private int amount = 3;
//    private int currAns = 0;
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @return A new instance of fragment LearnRecoginzeFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static TestRecognizeFragment newInstance(int param1) {
//        TestRecognizeFragment fragment = new TestRecognizeFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_PAGER, param1);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            currPage = getArguments().getInt(ARG_PAGER);
//        }
//    }
//
//    @Override
//    public int getLayout() {
//        return R.layout.fragment_test_recognize;
//    }
//
//    @Override
//    public void initView(View view) {
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        setInitData();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//    }
//
//    @OnClick(R.id.btnLearn)
//    public void actionLearn() {
////        startFragment(LearnRecoginzeFragment.class, currPage);
//        activity.onFragmentInteraction(LearnRecoginzeFragment.class, currPage);
//    }
//
//    @OnClick(R.id.imgSpeak)
//    public void actionSpeak() {
//        if (Constant.isPro || currPage < 10)
//            audio.speakWord(dataRecognize.get(arrW[currAns]).getVn());
////                    else
////                        Utility.installPremiumApp(getActivity());
//    }
//
//    @OnClick(R.id.imgLeft)
//    public void actionLeft() {
//        if (currAns <= 0) {
//            currAns = 0;
//            return;
//        }
//        currAns = currAns - 1;
//
//        if (currAns == 0)
//            imgLeft.setVisibility(View.GONE);
//
//        imgRight.setVisibility(View.VISIBLE);
//        pagerRecognize.setCurrentItem(currAns);
//        ((RecognizeMainActivity) getActivity()).hideMenu();
//    }
//
//    @OnClick(R.id.imgRight)
//    public void actionRight() {
//        if (currAns >= amount - 1) {
//            currAns = amount - 1;
//            return;
//        }
//        currAns = currAns + 1;
//
//        if (currAns == amount - 1)
//            imgRight.setVisibility(View.GONE);
//
//        imgLeft.setVisibility(View.VISIBLE);
//        pagerRecognize.setCurrentItem(currAns);
//    }
//
//
//    private void setInitData() {
//        Log.i(TestRecognizeFragment.class, "setInitData kind:" + kind);
//        audio = new AudioPlayer(getActivity());
//        pagerRecognize.setPageMargin(-50);
//        pagerRecognize.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int position) {
//                currAns = position;
////                adapterPage.notifyDataSetChanged();
//                if (currAns == 0)
//                    imgLeft.setVisibility(View.GONE);
//                else if (currAns == amount - 1)
//                    imgRight.setVisibility(View.GONE);
//                else {
//                    imgLeft.setVisibility(View.VISIBLE);
//                    imgRight.setVisibility(View.VISIBLE);
//                }
//                ((RecognizeMainActivity) getActivity()).hideMenu();
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//            }
//        });
//
//        loadData();
//        setArrData();
//
//        adapterPage = new RecognizePagerTestAdapter(getActivity(), dataRecognize, this);
//        pagerRecognize.setAdapter(adapterPage);
////        amount = adapterPage.amount;
////        setCurrentWord(currAns);
//        if (currAns == 0)
//            imgLeft.setVisibility(View.GONE);
//        else if (currAns == amount - 1)
//            imgRight.setVisibility(View.GONE);
//        else {
//            imgLeft.setVisibility(View.VISIBLE);
//            imgRight.setVisibility(View.VISIBLE);
//        }
//
//    }
//
//    private void setArrData() {
//        arrW = new int[amount];
//        for (int i = 0; i < amount; i++) {
//            arrW[i] = i;
//        }
//
//        for (int i = 0; i < amount - 1; i++) {
//
//            Random ran = new Random();
//            Random ran2 = new Random();
//            int value = ran.nextInt(amount);
//            int value2 = ran2.nextInt(amount);
//            int tmp = arrW[value];
//            arrW[value] = arrW[value2];
//            arrW[value2] = tmp;
//        }
////        ULog.i(TestRecognizeFragment.class, "arr:" + arrW[0] + "," + arrW[1] + "," + arrW[2] + "," + arrW[3]);
//    }
//
////    public void setCurrentWord(int currAns) {
////        currWord = dataRecognize.get(arrW[currAns]).getVn();
////        ULog.i(RecognizePagerTestAdapter.class, "setCurrentWord page:" + currAns + "; word:" + currWord + " a0:" + arrW[0] + "; aN:" + arrW[currAns]);
////    }
//
//    private void loadData() {
//        dataRecognize = activity.presenter.loadData(currPage + 1);
//        amount = dataRecognize.size();
//
////        activity.presenter.loadData(currPage + 1, new ICallback<List<RecognizeEntity>>() {
////            @Override
////            public void onCallback(List<RecognizeEntity> data) {
////                dataRecognize = data;
////                amount = data.size();
////            }
////
////            @Override
////            public void onFail(String err) {
////
////            }
////        });
//
//    }
//
//    @Override
//    public String getCurrWord() {
//        return dataRecognize.get(arrW[currAns]).getVn();
//    }
//
//}
