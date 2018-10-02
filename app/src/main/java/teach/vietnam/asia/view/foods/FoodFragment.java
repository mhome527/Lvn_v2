package teach.vietnam.asia.view.foods;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.entity.FoodEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Common;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.action.IActionList;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.base.BaseFragment;
import teach.vietnam.asia.view.food_detail.FoodDetailActivity;


/**
 * Created by HuynhTD on 01/19/2017.
 */

public class FoodFragment extends BaseFragment<FoodActivity> implements IActionList {

    private final String TAG = "FoodFragment";
    private final String FOLDER = "sound/";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    FoodContentAdapter adapter;
    FoodPresenter presenter;
    //    AudioManager audio;
    private AudioPlayer audio;
    public int kind = 1;


    List<FoodEntity> listData;

    public boolean isPurchased;

    @Override
    public int getLayout() {
        return R.layout.word_content_layout;
    }

    @Override
    public void initView(View root) {
        Log.i(TAG, "initView");
        presenter = new FoodPresenter(activity);
        adapter = new FoodContentAdapter(isPurchased, this);
        audio = new AudioPlayer(activity);

        setupView();
        loadData();
    }

    // ============= Start IActionList
    @Override
    public void actionClick(int pos, Object object) {
        if (pos == 0 && object == null) {
            activity.purchaseItem();
        } else {
            FoodEntity entity = (FoodEntity) object;
            Intent intent = new Intent(activity, FoodDetailActivity.class);
            intent.putExtra(BaseTable.COL_ID, entity.id);
            intent.putExtra(BaseTable.COL_TYPE, entity.type);
            intent.putExtra(BaseTable.COL_AREA, entity.area);
            startActivity(intent);
        }
    }
    // =========== END IActionList

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
//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, new IClickListener() {
//            @Override
//            public void actionClick(View view, int position) {
//                Log.i(TAG, "actionClick row pos:" + position);
//                activity.setTitleCenter(listData.get(position).getVi());
//                audio.speakWord(listData.get(position).name);
//                Intent intent = new Intent(activity, FoodDetailActivity.class);
//                startActivity(intent);
//
//            }
//
//            @Override
//            public void actionLongClick(View view, int position) {
//                Log.i(TAG, "actionLongClick row pos:" + position);
//            }
//        }));
    }

    public void loadData() {
        Log.i(TAG, "loadData getKind():" + kind);
        presenter.loadData(kind, new ICallback<List<FoodEntity>>() {
            @Override
            public void onComplete(List<FoodEntity> list) {
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
