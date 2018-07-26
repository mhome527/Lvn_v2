package teach.vietnam.asia.view.food_detail;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.FoodDetailTable;
import teach.vietnam.asia.db.table.FoodTable;
import teach.vietnam.asia.utils.Utility;
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

    String name;
    String ot;
    String strImage;
    String content;

    @Override
    protected int getLayout() {
        return R.layout.food_detail_activity;
    }

    @Override
    protected void initView() {
        name = getIntent().getStringExtra(FoodTable.COL_NAME);
        strImage = getIntent().getStringExtra(FoodTable.COL_IMAGE);
        ot = getIntent().getStringExtra(FoodDetailTable.COL_OT);
        content = getIntent().getStringExtra(FoodDetailTable.COL_CONTENT);


//        presenter = new PlaceDetailPresenter(activity);

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

        toolbarTitle.setText(ot);
        tvVn.setText(name);
//        tvOt.setText(entity.ot);
        tvContent.setText(content);

        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), strImage);
        if (resourceId > 0) {
            imgFood.setImageResource(resourceId);
        }

    }
}
