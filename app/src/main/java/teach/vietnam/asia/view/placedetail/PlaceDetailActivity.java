package teach.vietnam.asia.view.placedetail;

import butterknife.OnClick;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.BaseActivity;

public class PlaceDetailActivity extends BaseActivity<PlaceDetailActivity> {
    @Override
    protected int getLayout() {
        return R.layout.places_detail_activity;
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.btnBack)
    public void actionBack() {
        onBackPressed();
    }
}
