package teach.vietnam.asia.view.places;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.view.base.BaseFragment;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.placedetail.PlaceDetailActivity;

public class PlaceFragment extends BaseFragment<PlaceActivity> implements IPlaceListener {
    private final String TAG = "PlaceFragment";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public int area = 1;
    PlaceAdapter adapter;
    public boolean isPurchased;

    @Override
    public int getLayout() {
        return R.layout.places_fragment;
    }

    @Override
    public void initView(View root) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        loadData();
    }

    @Override
    public void onChildClick(PlaceEntity entity) {
//        activity.startActivity2(PlaceDetailActivity.class);
        if (entity != null) {
            Intent intent = new Intent(activity, PlaceDetailActivity.class);
            intent.putExtra(BaseTable.COL_ID, entity.id);
            intent.putExtra(BaseTable.COL_TYPE, entity.type);
            intent.putExtra(BaseTable.COL_AREA, entity.area);
            intent.putExtra(Constant.TRIAL_APP, entity.favorite);
            startActivity(intent);
        } else {
            activity.purchaseItem();
        }
    }

    private void loadData() {
        activity.presenter.getData(area, new ICallback<ArrayList<PlaceGroupData>>() {
            @Override
            public void onComplete(ArrayList<PlaceGroupData> data) {
                adapter = new PlaceAdapter(isPurchased, data, PlaceFragment.this);
                recyclerView.setAdapter(adapter);

                if (data != null && data.size() > 0)
                    adapter.toggleGroup(data.size() - 1);
            }

        });
    }
}
