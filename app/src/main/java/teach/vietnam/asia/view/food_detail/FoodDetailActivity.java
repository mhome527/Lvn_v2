package teach.vietnam.asia.view.food_detail;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.entity.FoodEntity;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.base.BaseActivity;
import teach.vietnam.asia.view.custom.RoundRectCornerImageView;

public class FoodDetailActivity extends BaseActivity<FoodDetailActivity> {
    private final String TAG = "FoodDetailActivity";

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.tvVn)
    TextView tvVn;

    @BindView(R.id.tvContent)
    TextView tvContent;

    @BindView(R.id.imgFood)
    RoundRectCornerImageView imgFood;

    int area_id;
    int type;
    int id;

    FoodDetailPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.food_detail_activity;
    }

    @Override
    protected void initView() {

        area_id = getIntent().getIntExtra(BaseTable.COL_AREA, 0);
        type = getIntent().getIntExtra(BaseTable.COL_TYPE, 0);
        id = getIntent().getIntExtra(BaseTable.COL_ID, 0);

        presenter = new FoodDetailPresenter(this);
        tvContent.setMovementMethod(new ScrollingMovementMethod());

        setData();
    }

    // ================ CLICK ================
    @OnClick(R.id.btnBack)
    public void actionBack() {
        onBackPressed();
    }

    // ============== END CLICK ==============

    private void setData() {
//        setTitle(ot);
        presenter.loadData(area_id, type, id, new ICallback<FoodEntity>() {
            @Override
            public void onCallback(FoodEntity entity) {
                toolbarTitle.setText(entity.ot);
                tvVn.setText(entity.name);
//        tvOt.setText(entity.ot);
                tvContent.setText(entity.content);

                int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), entity.image);
                if (resourceId > 0) {
                    imgFood.setImageResource(resourceId);
                }
            }
        });


    }
}
