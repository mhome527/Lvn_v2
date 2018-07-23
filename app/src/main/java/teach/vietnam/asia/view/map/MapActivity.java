package teach.vietnam.asia.view.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import teach.vietnam.asia.R;
import teach.vietnam.asia.view.BaseActivity;

public class MapActivity extends BaseActivity<MapActivity> {

//    @BindView(R.id.mapFragment)
    SupportMapFragment mapFragment;

    private GoogleMap mMap;

    @Override
    protected int getLayout() {
        return R.layout.map_activity;
    }

    @Override
    protected void initView() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
//        mapFragment.getMapAsync(this);

    }
}
