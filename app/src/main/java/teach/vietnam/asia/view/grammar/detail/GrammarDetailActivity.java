package teach.vietnam.asia.view.grammar.detail;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.BaseActivity;

public class GrammarDetailActivity extends BaseActivity<GrammarDetailActivity>{

    private String TAG = "GrammarDetailActivity";
    private final String PREF_PAGER_GRAMMAR = "pager_grammar";

    @BindView(R.id.pagerGrammar)
    ViewPager pagerGrammar;

    @BindView(R.id.imgLeft)
    ImageButton imgLeft;

    @BindView(R.id.imgRight)
    ImageButton imgRight;

    private GrammarDetailAdapter adapter;
    private boolean isTouchL = false;
    private boolean isTouchR = false;
    private int currPage = 0;
    private int amount = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_grammar_detail;
    }

    @Override
    protected void initView() {
        int pos;

        pos = getIntent().getIntExtra(Constant.INTENT_POSITION, 0);

        adapter = new GrammarDetailAdapter(this);
        pagerGrammar.setAdapter(adapter);
        pagerGrammar.setCurrentItem(pos);

        amount = adapter.getCount();

        currPage = pref.getIntValue(0, PREF_PAGER_GRAMMAR);
        pagerGrammar.setCurrentItem(currPage);
//        pagerGrammar.setPageMargin(-60);

        pagerGrammar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "onPageScrollStateChanged curr:" + currPage);
            }

            @Override
            public void onPageSelected(int position) {
                pref.putIntValue(position, PREF_PAGER_GRAMMAR);
                Log.i(TAG, "onPageSelected curr:" + currPage);
                currPage = position;
//                tvNumber.setText((currPage + 1) + "");
//                tvAmount.setText(amount + "");

//                pref.putIntValue(currPage, Constant.PREF_PAGE + PREF_PAGER_GRAMMAR);
                if (currPage == 0)
                    imgLeft.setVisibility(View.GONE);
                else if (currPage == amount - 1)
                    imgRight.setVisibility(View.GONE);
                else {
                    imgLeft.setVisibility(View.VISIBLE);
                    imgRight.setVisibility(View.VISIBLE);
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isTouchL = false;
                        isTouchR = false;
                    }
                }, 200);


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "onPageScrollStateChanged curr:" + currPage);
//                isTouchL = false;
//                isTouchR = false;
            }
        });

        imgLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isTouchL)
                    return false;
                Log.i(TAG, "left touch: " + isTouchL);

                isTouchL = true;

                if (currPage == 0) {
                    return false;
                }
                currPage = currPage - 1;

                if (currPage == 0)
                    imgLeft.setVisibility(View.GONE);

                imgRight.setVisibility(View.VISIBLE);
                pagerGrammar.setCurrentItem(currPage);
                return false;
            }
        });

        imgRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isTouchR)
                    return false;
                Log.i(TAG, "Right touch: " + isTouchR);
                if (currPage == amount - 1) {
                    return false;
                }

                isTouchR = true;

                currPage = currPage + 1;

                if (currPage == amount - 1)
                    imgRight.setVisibility(View.GONE);

                imgLeft.setVisibility(View.VISIBLE);
                pagerGrammar.setCurrentItem(currPage);
                return false;
            }
        });


        pref.putIntValue(currPage, Constant.PREF_PAGE);
        if (currPage == 0)
            imgLeft.setVisibility(View.GONE);
        else if (currPage == amount - 1)
            imgRight.setVisibility(View.GONE);
        else {
            imgLeft.setVisibility(View.VISIBLE);
            imgRight.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        isTouchL = false;
        isTouchR = false;
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
        pagerGrammar.setCurrentItem(currPage);
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
        pagerGrammar.setCurrentItem(currPage);
    }
}
