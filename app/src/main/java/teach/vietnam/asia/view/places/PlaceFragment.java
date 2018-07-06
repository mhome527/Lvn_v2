package teach.vietnam.asia.view.places;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.BaseFragment;

public class PlaceFragment extends BaseFragment<PlaceActivity> {
    private final String TAG = "PlaceFragment";

    @BindView(R.id.textView)
    TextView textView;

    public int group = 1;

    @Override
    public int getLayout() {
        return R.layout.places_fragment;
    }

    @Override
    public void initView(View root) {
        textView.setText("group: " + group);
    }
}
