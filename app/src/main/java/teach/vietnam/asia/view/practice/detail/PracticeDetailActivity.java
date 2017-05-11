package teach.vietnam.asia.view.practice.detail;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.BaseActivity;
import teach.vietnam.asia.view.ICallback;
import teach.vietnam.asia.view.practice.PracticePagerAdapter;

public class PracticeDetailActivity extends BaseActivity<PracticeDetailActivity> {

    private final String TAG = "PracticeDetailActivity";

    @BindView(R.id.tvAns)
    public TextView tvAns;

    @BindView(R.id.pagerExceriese)
    ViewPager pagerExceriese;


    @BindView(R.id.gridPage)
    GridView gridPage;

    private PracticePagerAdapter adapterPage;
    private PracticeFooterAdapter adapterFooter;
    //    private tblVietENDao dao;

    //    private ProgressDialog progressDialog;
    private AudioPlayer audio;
    private int currPage = 0;
    private int kind = 1;
    private int level = 1;
    private int max_level = 1;
    //    private String lang;
    private PracticeDetailPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.practice_detail_layout;
    }

    @Override
    protected void initView() {
        tvAns.setVisibility(View.INVISIBLE);
        presenter = new PracticeDetailPresenter(activity);


    }

    @OnClick(R.id.imgSpeak)
    public void actionSpeak() {
        if (Constant.isPro)
            audio.speakWord(adapterPage.lstData.get(currPage).getVi());
//        else
//            Utility.installPremiumApp(PracticeDetailActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (audio != null)
            audio.stopAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setInitData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (progressDialog != null && progressDialog.isShowing())
//            progressDialog.dismiss();
    }


    private void setInitData() {
        kind = getIntent().getIntExtra(Constant.INTENT_KIND, 1);

        Log.i(PracticeDetailActivity.class, "setInitData kind:" + kind);
        audio = new AudioPlayer(PracticeDetailActivity.this);
        pagerExceriese.setPageMargin(-80);
        pagerExceriese.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currPage = position;
//                ULog.i(PracticeDetailActivity.class, "PageSelected Word:" + adapterPage.lstData.get(currPage).getVi());

                if (Constant.isPro) {
                    audio.speakWord(adapterPage.lstData.get(currPage).getVi());
                    tvAns.setVisibility(View.INVISIBLE);
                } else {
                    tvAns.setText(adapterPage.lstData.get(currPage).getVi());
                    tvAns.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        gridPage.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // viewPager.setCurrentItem(position, true);
                if (adapterFooter.currentPage == position)
                    return;
                currPage = 0;
                adapterFooter.currentPage = position;
                adapterFooter.notifyDataSetChanged();
                level = position + 1;
                tvAns.setText("");
                loadData();
            }
        });

        loadData();

        presenter.loadMaxLevel(kind, new ICallback<Integer>() {
            @Override
            public void onCallback(Integer data) {
                if (data != null)
                    max_level = data;
                if (max_level > 0) {
                    adapterFooter = new PracticeFooterAdapter(PracticeDetailActivity.this, max_level);
                    gridPage.setAdapter(adapterFooter);
                    gridPage.setLayoutParams(new LinearLayout.LayoutParams(120 * max_level, LinearLayout.LayoutParams.WRAP_CONTENT));
                    gridPage.setNumColumns(max_level);
                } else {
                    finish();
                }

            }

            @Override
            public void onFail(String err) {

            }
        });

    }

    private void loadData() {
        Log.i(TAG, "loadData kind:" + kind + "; level:" + level);
        presenter.loadData(kind, level, new ICallback<List<WordEntity>>() {
            @Override
            public void onCallback(List<WordEntity> data) {
//                if (!isFinishing()) {
//                    progressDialog.dismiss();
//                }
                if (data != null && data.size() > 0) {
                    adapterPage = new PracticePagerAdapter(activity, data, lang);
                    pagerExceriese.setAdapter(adapterPage);
                    if (!Constant.isPro)
                        tvAns.setText(adapterPage.lstData.get(0).getVi());
                }
            }

            @Override
            public void onFail(String err) {

            }
        });

    }

}
