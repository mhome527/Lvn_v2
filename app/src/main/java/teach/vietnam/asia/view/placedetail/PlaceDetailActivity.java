package teach.vietnam.asia.view.placedetail;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.view.BaseActivity;

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

    int area_id;
    int type;
    int id;

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

    @OnClick(R.id.btnBack)
    public void actionBack() {
        onBackPressed();
    }

    private void getData() {
        PlaceEntity entity = presenter.getData(area_id, type, id);
        setTitle(entity.ot);

        toolbarTitle.setText(entity.ot);
        tvVn.setText(entity.title);
//        tvOt.setText(entity.ot);
        tvAddress.setText(entity.address);
        tvContent.setText(entity.content);
    }
}
