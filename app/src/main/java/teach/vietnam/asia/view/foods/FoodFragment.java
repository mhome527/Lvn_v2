package teach.vietnam.asia.view.foods;

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
import teach.vietnam.asia.view.BaseFragment;
import teach.vietnam.asia.view.ICallback;
import teach.vietnam.asia.view.IClickListener;
import teach.vietnam.asia.view.RecyclerTouchListener;


/**
 * Created by HuynhTD on 01/19/2017.
 */

public class FoodFragment extends BaseFragment<FoodActivity> {

    private final String TAG = "FoodFragment";
    private final String FOLDER = "sound/";
    //    private View root;
    public Constant.TYPE_WORD typeWord = Constant.TYPE_WORD.ANIMAL;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    FoodContentAdapter adapter;
    FoodPresenter presenter;
    //    AudioManager audio;
    private AudioPlayer audio;
    public int kind = 3;


    List<WordEntity> listData;

    @Override
    public int getLayout() {
        return R.layout.word_content_layout;
    }

    @Override
    public void initView(View root) {
        Log.i(TAG, "initView");
        presenter = new FoodPresenter(activity);
        adapter = new FoodContentAdapter();
        audio = new AudioPlayer(activity);

        setupView();
        loadData();
    }

    public void setupView() {
        GridLayoutManager lLayout;
        if (Common.isTablet(activity))
            lLayout = new GridLayoutManager(activity, 2);
        else
            lLayout = new GridLayoutManager(activity, 1);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //Add event
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, new IClickListener() {
            @Override
            public void actionClick(View view, int position) {
                Log.i(TAG, "actionClick row pos:" + position);
                activity.setTitleCenter(listData.get(position).getVi());

                audio.speakWord(listData.get(position).getVi());

            }

            @Override
            public void actionLongClick(View view, int position) {
                Log.i(TAG, "actionLongClick row pos:" + position);
            }
        }));
    }

    public void loadData() {
        Log.i(TAG, "loadData getKind():" + kind);
        presenter.loadData(kind, new ICallback<List<WordEntity>>() {
            @Override
            public void onCallback(List<WordEntity> list) {
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

}
