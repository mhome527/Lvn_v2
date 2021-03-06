package teach.vietnam.asia.view.food_detail;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.entity.FoodEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.custom.RoundRectCornerImageView;
import teach.vietnam.asia.view.purchase.PurchaseActivity;

import static teach.vietnam.asia.BaseApplication.mFirebaseAnalytics;

public class FoodDetailActivity extends PurchaseActivity<FoodDetailActivity> {
    private final String TAG = "FoodDetailActivity";

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.tvVn)
    TextView tvVn;

    @BindView(R.id.tvContent)
    TextView tvContent;

    @BindView(R.id.imgFood)
    RoundRectCornerImageView imgFood;

    @BindView(R.id.imgSound)
    ImageView imgSound;

    int area_id;
    int type;
    int id;

    FoodDetailPresenter presenter;
    private AudioPlayer audio;
    FoodEntity entity;

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
        audio = new AudioPlayer(activity);

        tvContent.setMovementMethod(new ScrollingMovementMethod());

        if (isPurchased || type == 1) {
            imgSound.setImageResource(R.drawable.ic_speaker);
        } else
            imgSound.setImageResource(R.drawable.ic_lock2);

        setData();

        if (!BuildConfig.DEBUG) {
            Bundle params = new Bundle();
            String screen = "FoodDetailActivity";
            // [START custom_event]
            params.putString("Name", screen);
            params.putString("Name2", screen + "_" + lang);
            mFirebaseAnalytics.logEvent("SCREEN2", params);
        }
    }

    // ================ CLICK ================
    @OnClick(R.id.btnBack)
    public void actionBack() {
        onBackPressed();
    }

    @OnClick(R.id.imgSound)
    public void actionSpeak() {
        if (isPurchased || type == 1)
            audio.speakWord(entity.name);
        else
            purchaseItem();
    }
    // ============== END CLICK ==============

    //======================== Start Purchase =========================

    @Override
    protected void dealWithIabSetupSuccess() {
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imgSound.setImageResource(R.drawable.ic_speaker);
                }
            });


        } else {
            Log.i(TAG, "WithIabSetupSuccess item not purchase");
            isPurchased = false;
        }
    }

    @Override
    protected void dealWithIabSetupFailure() {
        Log.e(TAG, "dealWithIabSetupFailure ====================== ERROR ==================");
    }
    //========================END  Purchase =========================

    private void setData() {
//        setTitle(ot);
        presenter.loadData(area_id, type, id, new ICallback<FoodEntity>() {
            @Override
            public void onComplete(FoodEntity entity) {
                activity.entity = entity;
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
