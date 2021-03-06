package teach.vietnam.asia.view.word;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Common;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.base.BaseFragment;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.action.IClickListener;
import teach.vietnam.asia.view.custom.RecyclerTouchListener;


/**
 * Created by HuynhTD on 01/19/2017.
 */

public class WordFragment extends BaseFragment<WordActivity> {

    private final String TAG = "WordFragment";
    public Constant.TYPE_WORD typeWord = Constant.TYPE_WORD.ANIMAL;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    WordContentAdapter adapter;
    WordPresenter presenter;
    //    AudioManager audio;
    private AudioPlayer audio;


    List<WordEntity> listData;

    @Override
    public int getLayout() {
        return R.layout.word_content_layout;
    }

    @Override
    public void initView(View root) {
        Log.i(TAG, "initView");
        presenter = new WordPresenter(activity);
        adapter = new WordContentAdapter(activity);
        audio = new AudioPlayer(activity);

        setupView();
        loadData();
    }

    public void setupView() {
        GridLayoutManager lLayout;
        if (Common.isTablet(activity))
            lLayout = new GridLayoutManager(activity, 3);
        else
            lLayout = new GridLayoutManager(activity, 2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setPurchased(activity.isPurchased);
//        int spacingInPixels = Utility.dpToPx(2);
//        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        //Add event
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, new IClickListener() {
            @Override
            public void actionClick(View view, int position) {
                Log.i(TAG, "actionClick row pos:" + position);
                activity.setTitleCenter(listData.get(position).getVi());

                if (activity.isPurchased || position < Constant.TRIAL) {
                    audio.speakWord(listData.get(position).getVi());
                } else {
                    Log.i(TAG, "===> buy!!!");
                    activity.purchaseItem();
                }
            }

        }));
    }

    public void loadData() {
        Log.i(TAG, "loadData getKind():" + getKind());
        presenter.loadData(getKind(), new ICallback<List<WordEntity>>() {
            @Override
            public void onComplete(List<WordEntity> list) {
                listData = list;
                adapter.setData(listData);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private int[] getKind() {
        switch (typeWord) {
            case ANIMAL:
                return new int[]{4};

            case FRUIT:
                return new int[]{1, 2};

            case OTHER:
                return new int[]{5, 12};

            default:
                return new int[]{1};
        }
    }
}
