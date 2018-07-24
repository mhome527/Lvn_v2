package teach.vietnam.asia.view.placedetail;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.db.table.PlaceDetailTable;
import teach.vietnam.asia.db.table.PlaceTitleLanguageTable;
import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.BaseActivity;
import teach.vietnam.asia.view.custom.RoundRectCornerImageView;
import teach.vietnam.asia.view.map.MapActivity;

public class PlaceDetailActivity extends BaseActivity<PlaceDetailActivity> {
    private final String TAG = "PlaceDetailActivity";

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.tvVn)
    TextView tvVn;

//    @BindView(R.id.tvOt)
//    TextView tvOt;

    @BindView(R.id.tvAddress)
    TextView tvAddress;

    @BindView(R.id.tvContent)
    TextView tvContent;

    @BindView(R.id.imgPlace)
    RoundRectCornerImageView imgPlace;

    int area_id;
    int type;
    int id;

    PlaceEntity entity;
    PlaceDetailPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.places_detail_activity;
    }

    @Override
    protected void initView() {
        area_id = getIntent().getIntExtra(BaseTable.COL_AREA, 0);
        type = getIntent().getIntExtra(BaseTable.COL_TYPE, 0);
        id = getIntent().getIntExtra(BaseTable.COL_ID, 0);

        presenter = new PlaceDetailPresenter(activity);

        tvContent.setMovementMethod(new ScrollingMovementMethod());

        getData();
    }

    // ================ CLICK ================
    @OnClick(R.id.btnBack)
    public void actionBack() {
        onBackPressed();
    }

    @OnClick(R.id.llLocation)
    public void actionLocation() {
        Intent intent = new Intent(activity, MapActivity.class);
        intent.putExtra(PlaceDetailTable.COL_TITLE, entity.title);
        intent.putExtra(PlaceTitleLanguageTable.COL_OT1, entity.ot);
        intent.putExtra(PlaceDetailTable.COL_LATITUDE, entity.latitude);
        intent.putExtra(PlaceDetailTable.COL_LONGITUDE, entity.longitude);
        startActivity(intent);
    }
    // ============== END CLICK ==============

    private void getData() {
        entity = presenter.getData(area_id, type, id);
        setTitle(entity.ot);

        toolbarTitle.setText(entity.ot);
        tvVn.setText(entity.title);
//        tvOt.setText(entity.ot);
        tvAddress.setText(entity.address);
        tvContent.setText(entity.content);

        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), entity.image);
        if (resourceId > 0) {
            imgPlace.setImageResource(resourceId);
        }

    }
}
