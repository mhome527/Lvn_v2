package teach.vietnam.asia.view.placeimage;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.view.action.IClickListener;
import teach.vietnam.asia.view.base.BaseActivity;
import teach.vietnam.asia.view.image.ImageActivity;

public class PlaceImageActivity extends BaseActivity<PlaceImageActivity> implements IClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tvTitle1)
    TextView tvTitle1;

    PlaceImageAdapter adapter;
    PlaceImagePresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.place_image_activity;
    }

    @Override
    protected void initView() {

        presenter = new PlaceImagePresenter(activity);

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        String title = getIntent().getStringExtra(BaseTable.COL_TITLE);
        String links = getIntent().getStringExtra(BaseTable.COL_SEARCH_TEXT);
        int area_id = getIntent().getIntExtra(BaseTable.COL_AREA, 0);
        int type = getIntent().getIntExtra(BaseTable.COL_TYPE, 0);
        int id = getIntent().getIntExtra(BaseTable.COL_ID, 0);

        tvTitle1.setText(title);
        adapter = new PlaceImageAdapter(this, presenter.getLinks(area_id, type, id, links));
//        String[] strs = new String[]{"place_1_1_dinh_doc_lap.png", "place_1_1_dinh_doc_lap.png"};
//        adapter.setImages();
        recyclerView.setAdapter(adapter);

//        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnBack)
    public void actionBack() {
        onBackPressed();
    }

    @Override
    public void actionClick(View view, int position) {
        Intent i = new Intent(activity, ImageActivity.class);
        i.putExtra(BaseTable.COL_TITLE, adapter.getItem(position));
        startActivity(i);
    }
}
