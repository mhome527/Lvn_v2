package teach.vietnam.asia.view.recognize.learn;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Prefs;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.BaseFragment;
import teach.vietnam.asia.view.recognize.RecognizeMainActivity;
import teach.vietnam.asia.view.recognize.RecognizePagerAdapter;
import teach.vietnam.asia.view.recognize.test.TestRecognizeFragment;

public class LearnRecoginzeFragment extends BaseFragment<RecognizeMainActivity> {
    private static final String ARG_AMOUNT = "arg_pager";
    private static final String ARG_CURRPAGE = "arg_currpage";

    @BindView(R.id.pagerRecognize)
    ViewPager pagerRecognize;

    @BindView(R.id.imgLeft)
    ImageButton imgLeft;

    @BindView(R.id.imgRight)
    ImageButton imgRight;

    @BindView(R.id.tvSearch)
    TextView tvSearch;

    @BindView(R.id.tvNumber)
    TextView tvNumber;

    @BindView(R.id.tvAmount)
    TextView tvAmount;

    private int amount = 0;
    private RecognizePagerAdapter adapterPage;
    //    private DaoMaster daoMaster;
//    private AudioPlayer audio;
    private int currPage = 0;
    //    private ProgressDialog progress;
    private Prefs pref;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param amount   Parameter 1.
     * @param currPage Parameter 2.
     * @return A new instance of fragment LearnRecoginzeFragment.
     */
    public static LearnRecoginzeFragment newInstance(int amount, int currPage) {
        LearnRecoginzeFragment fragment = new LearnRecoginzeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_AMOUNT, amount);
        args.putInt(ARG_CURRPAGE, currPage);
        fragment.setArguments(args);
        return fragment;
    }


    public LearnRecoginzeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            amount = getArguments().getInt(ARG_AMOUNT);
            currPage = getArguments().getInt(ARG_CURRPAGE);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_learn_recognize;
    }

    @Override
    public void initView(View view) {
        setInitData();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.imgMenu)
    public void actionMenu() {
        ((RecognizeMainActivity) getActivity()).showMenu();
    }

    @OnClick(R.id.btnTest)
    public void actionTest() {
//        startFragment(TestRecognizeFragment.class, currPage);
        activity.onFragmentInteraction(TestRecognizeFragment.class, currPage);
    }

    @OnClick(R.id.imgLeft)
    public void actionLeft() {
        if (currPage == 0) {
            return;
        }
        currPage = currPage - 1;

        if (currPage == 0)
            imgLeft.setVisibility(View.GONE);

        imgRight.setVisibility(View.VISIBLE);
        pagerRecognize.setCurrentItem(currPage);

        ((RecognizeMainActivity) getActivity()).hideMenu();
    }

    @OnClick(R.id.imgRight)
    public void actionRight() {
        if (currPage == amount - 1) {
            return;
        }
        currPage = currPage + 1;

        if (currPage == amount - 1)
            imgRight.setVisibility(View.GONE);

        imgLeft.setVisibility(View.VISIBLE);
        pagerRecognize.setCurrentItem(currPage);
    }

    @OnClick(R.id.llRecognize)
    public void actionRecognize() {
        Utility.promptSpeechInput(activity, Constant.REQ_CODE_SPEECH_INPUT);
    }

    @OnClick(R.id.imgRecording)
    public void actionRecording() {
        Utility.promptSpeechInput(activity, Constant.REQ_CODE_SPEECH_INPUT);
    }

    private void setInitData() {

        Log.i("fragmentLearnRec", "setInitData...");

        if (pref == null)
            pref = new Prefs(getActivity());
        pagerRecognize.setPageMargin(-60);
        pagerRecognize.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currPage = position;
                tvNumber.setText((currPage + 1) + "");
                tvAmount.setText(amount + "");

                pref.putIntValue(currPage, Constant.PREF_PAGE);
                if (currPage == 0)
                    imgLeft.setVisibility(View.GONE);
                else if (currPage == amount - 1)
                    imgRight.setVisibility(View.GONE);
                else {
                    imgLeft.setVisibility(View.VISIBLE);
                    imgRight.setVisibility(View.VISIBLE);
                }
                ((RecognizeMainActivity) getActivity()).hideMenu();
//                ULog.i(RecognizeActivity.class, "PageSelected Word:" + adapterPage.lstData.get(currPage).getVi());
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        adapterPage = new RecognizePagerAdapter(activity, amount);
        pagerRecognize.setAdapter(adapterPage);
        adapterPage.notifyDataSetChanged();

        pagerRecognize.setCurrentItem(currPage);
        if (currPage == 0)
            imgLeft.setVisibility(View.GONE);
        else if (currPage == amount - 1)
            imgRight.setVisibility(View.GONE);
        else {
            imgLeft.setVisibility(View.VISIBLE);
            imgRight.setVisibility(View.VISIBLE);
        }
    }

    public void setCurrentPage(int currPage) {
        this.currPage = currPage;
        pagerRecognize.setCurrentItem(currPage);
    }

    public void setTextVoid(String text) {
        tvSearch.setText(text);
    }

}
