package teach.vietnam.asia.view.color;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.BaseActivity;

public class ColorActivity extends BaseActivity<ColorActivity> {

    @BindView(R.id.gridColor)
    GridView gridColor;

    private String[] colorName;
    private String[] colorValue;

    private AudioPlayer audio;

    @Override
    protected int getLayout() {
        return R.layout.color_layout;
    }

    @Override
    protected void initView() {
        initData();
    }

    private void initData() {

        colorName = getResources().getStringArray(R.array.color_name);
        colorValue = getResources().getStringArray(R.array.color_value);


        gridColor.setAdapter(new ColorAdapter(this, colorName, colorValue));
        gridColor.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int postion, long arg3) {
                Log.i(ColorActivity.class, "onItemClick");
                audio.speakWord(colorName[postion]);
            }
        });

        audio = new AudioPlayer(ColorActivity.this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (audio != null)
            audio.stopAll();
    }
}
