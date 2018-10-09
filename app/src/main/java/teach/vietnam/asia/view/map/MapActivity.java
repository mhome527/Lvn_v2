package teach.vietnam.asia.view.map;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.PlaceDetailTable;
import teach.vietnam.asia.db.table.PlaceTitleLanguageTable;
import teach.vietnam.asia.view.base.BaseActivity;

import static teach.vietnam.asia.BaseApplication.mFirebaseAnalytics;

public class MapActivity extends BaseActivity<MapActivity> implements OnMapReadyCallback {


    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    SupportMapFragment mapFragment;

    private GoogleMap mMap;
    private String title;
    private String ot;
    private double latitude;
    private double longitude;

    @Override
    protected int getLayout() {
        return R.layout.map_activity;
    }

    @Override
    protected void initView() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);

//        mapFragment.getMapAsync(this);
//        intent.putExtra(PlaceDetailTable.COL_TITLE, entity.ot);
//        intent.putExtra(PlaceDetailTable.COL_LATITUDE, entity.latitude);
//        intent.putExtra(PlaceDetailTable.COL_LONGITUDE, entity.longitude);
        title = getIntent().getStringExtra(PlaceDetailTable.COL_TITLE);
        ot = getIntent().getStringExtra(PlaceTitleLanguageTable.COL_OT1);
        latitude = getIntent().getDoubleExtra(PlaceDetailTable.COL_LATITUDE, 0);
        longitude = getIntent().getDoubleExtra(PlaceDetailTable.COL_LONGITUDE, 0);

        toolbarTitle.setText(ot);
        mapFragment.getMapAsync(this);

        if (!BuildConfig.DEBUG) {
            Bundle params = new Bundle();
            String screen = "MapActivity";
            // [START custom_event]
            params.putString("Name", screen);
            params.putString("Name2", screen + "_" + lang);
            mFirebaseAnalytics.logEvent("SCREEN2", params);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(ot).snippet(title));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 13));

    }

    // ================ CLICK ================
    @OnClick(R.id.btnBack)
    public void actionBack() {
        onBackPressed();
    }
}
