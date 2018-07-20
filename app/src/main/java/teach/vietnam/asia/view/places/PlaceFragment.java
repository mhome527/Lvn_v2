package teach.vietnam.asia.view.places;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.view.BaseFragment;
import teach.vietnam.asia.view.ICallback;
import teach.vietnam.asia.view.placedetail.PlaceDetailActivity;

public class PlaceFragment extends BaseFragment<PlaceActivity> implements IPlaceListener {
    private final String TAG = "PlaceFragment";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public int area = 1;
    PlaceAdapter adapter;

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
        activity.startActivity2(PlaceDetailActivity.class);
    }

    private void loadData() {
        activity.presenter.getData(area, new ICallback<ArrayList<PlaceGroupData>>() {
            @Override
            public void onCallback(ArrayList<PlaceGroupData> data) {
                adapter = new PlaceAdapter(data, PlaceFragment.this);
                recyclerView.setAdapter(adapter);
            }

        });
    }
}
