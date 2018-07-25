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

import com.google.firebase.crash.FirebaseCrash;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.sound.IAudioPlayer;
import teach.vietnam.asia.utils.Common;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.action.IClickListener;
import teach.vietnam.asia.view.purchase.PurchaseActivity;

/**
 * Created by HuynhTD on 5/11/2017.
 */

public class PhrasesActivity extends PurchaseActivity<PhrasesActivity> implements IClickListener {

    private final String TAG = "PhrasesActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ckbSpeed)
    CheckBox ckbSpeed;

    @BindView(R.id.tvHint)
    TextView tvHint;

    private AudioPlayer audio;
    private PhrasesPresenter presenter;
    private List<WordEntity> lstData;
    private PhrasesAdapter adapter;
    public boolean isSlowly = false;

    @Override
    protected int getLayout() {
        return R.layout.phrases_layout;
    }

    @Override
    protected void initView() {
        presenter = new PhrasesPresenter(activity);
        setTitle(getString(R.string.title_button_phrase));
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(true); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(true); // remove the icon
            actionBar.setDisplayShowTitleEnabled(true); // remove title
        }

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        setSupportActionBar(mToolbar);


        Common.setupRecyclerView(activity, recyclerView, this);

        initData();
        if (!BuildConfig.DEBUG)
        FirebaseCrash.logcat(Log.INFO, TAG, "initView");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {        if (!BuildConfig.DEBUG)
        FirebaseCrash.logcat(Log.INFO, TAG, "searchMenu");

        getMenuInflater().inflate(R.menu.menu_phrases, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        if (isPurchased || adapter.getItem(position).getNum() < Constant.TRIAL) {
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
        } else {
            //////////
            Log.i(TAG, "===> buy!!!");
            purchaseItem();
        }


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
                    Log.i(PhrasesActivity.class, "load data size:" + lstData.size());
                    adapter = new PhrasesAdapter(lstData);
                    adapter.setPurchased(isPurchased);
                    recyclerView.setAdapter(adapter);

                } else {
                    Log.e(PhrasesActivity.class, "Load data Error");
                    finish();
                }
            }

        });
    }

    //==================================== Purchase =========================
    @Override
    protected void dealWithIabSetupSuccess() {
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;
            if(adapter == null)
                return;

            adapter.setPurchased(isPurchased);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

            /// Test only
//            if (BuildConfig.DEBUG)
//                clearPurchaseTest();

        } else {
            Log.i(TAG, "WithIabSetupSuccess item not purchase");
            isPurchased = false;
        }
    }

    @Override
    protected void dealWithIabSetupFailure() {
        Log.e(TAG, "dealWithIabSetupFailure ====================== ERROR ==================");
    }

    //================================= END Purchase =========================

}
