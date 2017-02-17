package teach.vietnam.asia.view.food;

import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.BaseActivity;
import teach.vietnam.asia.view.ICallback;

public class FoodActivity extends BaseActivity<FoodActivity> implements ViewFactory {

    private final String TAG = "LearnFoodActivity";

    @BindView(R.id.tvFood)
    private TextView tvFood;

    @BindView(R.id.gallery1)
    Gallery gallery;

    private ImageSwitcher selectedImage;

    private AudioPlayer audio;
    private List<WordEntity> lstData;
    private FoodPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.learn_food_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "======class: initView");
        presenter = new FoodPresenter(activity);
        setInitData();
    }

    @OnClick(R.id.btnSpeak)
    public void actionSpeak() {
        speakWord();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (audio != null)
            audio.stopAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View makeView() {
        ImageView iView = new ImageView(this);
        iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//        iView.setBackgroundColor(0xFFffffff);

        return iView;
    }

    private void setInitData() {
        Log.i(TAG, "setInitData");
        audio = new AudioPlayer(FoodActivity.this);
        // imgVolume.setOnClickListener(this);
        // adapter = new FruitPagerAdapter(this, mImageIds);
        // viewPager.setAdapter(adapter);
        // pagerFruit
        selectedImage = (ImageSwitcher) findViewById(R.id.selectedImage);
        selectedImage.setFactory(this);
        selectedImage.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        selectedImage.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

        gallery.setSpacing(5);
        // gallery.setAdapter(new LearnAdapter(this));

        // clicklistener for Gallery
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int resourceId;
                tvFood.setText(lstData.get(position).getVi());
                // Toast.makeText(FruitActivity.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();
                // show the selected Image
                ImageView img = (ImageView) v.findViewById(R.id.imgFruit);
                try {
                    resourceId = Integer.parseInt(img.getTag().toString());

                } catch (Exception e) {
                    Log.e(FoodActivity.class, "onItemClick get resource error:" + e.getMessage());
                    resourceId = 0;
                }
                if (resourceId > 0) {
                    selectedImage.setImageResource(resourceId);
                } else
                    Log.e(FoodActivity.class, "dont load resource");

                if (Constant.isPro)
                    speakWord();
                // selectedImage.setImageResource(mImageIds[position]);
            }
        });

        presenter.loadData(new ICallback<List<WordEntity>>() {
            @Override
            public void onCallback(List<WordEntity> data) {
                int resourceId;

                if (isFinishing()) {
                    return;
                }

                lstData = data;
                if (lstData != null && lstData.size() > 0) {
                    resourceId = Utility.getResourcesID(activity, "f_" + lstData.get(0).getImg() + "_l");
                    if (resourceId > 0) {
                        selectedImage.setImageResource(resourceId);
                    } else
                        Log.e(FoodActivity.class, "do not load resource");

                    gallery.setAdapter(new LearnFoodAdapter(FoodActivity.this, lstData, lang));
                    gallery.setSelection(2);
                    tvFood.setText(lstData.get(0).getVi());
                } else {
//                startActivity2(MainActivity.class);
                    finish();
                }
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "load err:" + err);
            }
        });
    }

    private void speakWord() {
        audio.speakWord(tvFood.getText().toString().trim());
    }
}
