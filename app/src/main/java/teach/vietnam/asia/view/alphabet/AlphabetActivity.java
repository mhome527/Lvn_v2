package teach.vietnam.asia.view.alphabet;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.util.List;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.TblAlphabetEx;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.view.BaseActivity;
import teach.vietnam.asia.view.ICallback;

public class AlphabetActivity extends BaseActivity<AlphabetActivity> {

    @BindView(R.id.gridWords)
    GridView gridWords;

    private AlphabetAdapter adapter;
    private AudioPlayer audio;
    private List<TblAlphabetEx> lstData;

    AlphabetPresenter presenter;
    // private boolean male = true;

    @Override
    protected int getLayout() {
        return R.layout.alphabet_layout;
    }

    @Override
    protected void initView() {
        initData();
    }

    private void initData() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(true); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(true); // remove the icon
            actionBar.setDisplayShowTitleEnabled(true); // remove title
//            toolbarTitle.setText(getString(R.string.title_alphabet));
            actionBar.setTitle(getString(R.string.title_alphabet));
        }

        presenter = new AlphabetPresenter(this);
        audio = new AudioPlayer(AlphabetActivity.this);
        gridWords.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                String strAudio = lstData.get(position).getSound();
                audio.speakWord(strAudio);
            }
        });

        presenter.loadData(new ICallback<List<TblAlphabetEx>>() {
            @Override
            public void onCallback(List<TblAlphabetEx> data) {
                lstData = data;
                adapter = new AlphabetAdapter(AlphabetActivity.this, lstData);
                gridWords.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (audio != null)
            audio.stopAll();
    }

    // ///

}
