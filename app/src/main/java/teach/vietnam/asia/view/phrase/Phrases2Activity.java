package teach.vietnam.asia.view.phrase;

import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.sound.IAudioPlayer;
import teach.vietnam.asia.utils.Common;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.BaseActivity;
import teach.vietnam.asia.view.ICallback;
import teach.vietnam.asia.view.IClickListener;

/**
 * Created by HuynhTD on 5/11/2017.
 */

public class Phrases2Activity extends BaseActivity<Phrases2Activity> implements IClickListener {

    private final String TAG = "Phrases2Activity";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ckbSpeed)
    CheckBox ckbSpeed;

    @BindView(R.id.tvHint)
    TextView tvHint;

    private AudioPlayer audio;
    private Phrases2Presenter presenter;
    private List<WordEntity> lstData;
    private PhrasesAdapter2 adapter;
    public boolean isSlowly = false;


    @Override
    protected int getLayout() {
        return R.layout.phrases_layout;
    }

    @Override
    protected void initView() {
        presenter = new Phrases2Presenter(activity);
        setTitle(getString(R.string.title_button_phrase));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(true); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(true); // remove the icon
            actionBar.setDisplayShowTitleEnabled(true); // remove title
        }

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setSupportActionBar(mToolbar);


        Common.setupRecyclerView(activity, recyclerView, this);

        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_phrases, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.ckbSpeed)
    public void actionCkbSpeed() {
        if (ckbSpeed.isChecked()) {
            isSlowly = true;
        } else {
            isSlowly = false;
        }
    }


    /// =============== IClickListener
    @Override
    public void actionClick(View view, int position) {
        String word = adapter.getItem(position).getVi();
        presenter.speakWord(word, new IAudioPlayer() {
            @Override
            public void showWord(String word, boolean visible) {
                Log.i(TAG, "word: " + word + "; visible: " + visible);
                if (activity == null || activity.isFinishing())
                    return;

                tvHint.setText(word);
                if (visible)
                    tvHint.setVisibility(View.VISIBLE);
                else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tvHint.setVisibility(View.GONE);
                        }
                    }, 1000);
                }
            }
        });
    }

    @Override
    public void actionLongClick(View view, int position) {

    }

    ///==== END IClickListener


    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (adapter != null)
                    adapter.getFilter().filter(newText);
                return true;
            }
        });
    }


    private void initData() {
        audio = new AudioPlayer(activity);

        presenter.loadData(new ICallback<List<WordEntity>>() {
            @Override
            public void onCallback(List<WordEntity> data) {
                if (isFinishing()) {
                    return;
                }
                lstData = data;
                if (lstData != null && lstData.size() > 0) {
                    Log.i(Phrases2Activity.class, "load data size:" + lstData.size());
                    adapter = new PhrasesAdapter2(lstData);
                    recyclerView.setAdapter(adapter);

                } else {
                    Log.e(Phrases2Activity.class, "Load data Error");
                    finish();
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

}
