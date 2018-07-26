package teach.vietnam.asia.view.foods;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.FoodEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.action.IActionList;
import teach.vietnam.asia.view.base.BaseViewHolder;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class FoodItemHolder extends BaseViewHolder {

    private final String TAG = "FoodItemHolder";

    @BindView(R.id.imgFood)
    ImageView imgFood;

    @BindView(R.id.tvVn)
    TextView tvVn;

    @BindView(R.id.tvOther)
    TextView tvOther;


    private AudioPlayer audio;
    FoodEntity entity;

    public FoodItemHolder(final View itemView, final IActionList iActionList) {
        super(itemView);
        audio = new AudioPlayer(BaseApplication.getInstance());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iActionList.actionClick(getAdapterPosition(), entity);
            }
        });
    }

    public void bind(FoodEntity entity) {
//        imgSound.setImageResource(R.drawable.ic_speaker);
        this.entity = entity;
        tvVn.setText(entity.name);
        tvOther.setText(entity.ot);

        String strImage = entity.image;
        Log.i(TAG, "filename:" + strImage);
        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), strImage);
        if (resourceId > 0) {
            imgFood.setImageResource(resourceId);
        }

    }

    @OnClick(R.id.imgSound)
    public void actionSpeak() {
        audio.speakWord(entity.name);
    }

}
