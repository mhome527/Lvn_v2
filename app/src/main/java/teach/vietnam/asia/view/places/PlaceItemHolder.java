package teach.vietnam.asia.view.places;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.sound.AudioPlayer;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.base.BaseChildExViewHolder;

public class PlaceItemHolder extends BaseChildExViewHolder {

    @BindView(R.id.imgPlace)
    ImageView imgPlace;

    @BindView(R.id.tvVn)
    TextView tvVn;

    @BindView(R.id.tvOther)
    TextView tvOther;

    @BindView(R.id.imgSound)
    ImageView imgSound;

    PlaceEntity entity;
    private AudioPlayer audio;
    boolean isPurchased;
    IPlaceListener iPlaceListener;

    public PlaceItemHolder(View itemView, boolean isPurchased, final IPlaceListener iPlaceListener) {
        super(itemView);
        audio = new AudioPlayer(BaseApplication.getInstance());
        this.iPlaceListener = iPlaceListener;
        this.isPurchased = isPurchased;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getAdapterPosition() <= Constant.TRIAL)
                    entity.favorite = 1;

                iPlaceListener.onChildClick(entity);
            }
        });
    }

    @OnClick(R.id.imgSound)
    public void actionSpeak() {
        if (isPurchased || getAdapterPosition() <= Constant.TRIAL)
            audio.speakWord(entity.vn);
        else
            iPlaceListener.onChildClick(null);


    }

    public void bind(PlaceEntity entity) {
        this.entity = entity;
        tvVn.setText(entity.vn);
        tvOther.setText(entity.ot1);

        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), entity.image);
        if (resourceId < 0) {
            resourceId = Utility.getResourcesID(BaseApplication.getInstance(), "place_cho_ben_thanh");
        }
        imgPlace.setImageResource(resourceId);

        if (isPurchased || getAdapterPosition() <= Constant.TRIAL) {
            imgSound.setImageResource(R.drawable.ic_speaker);
        } else
            imgSound.setImageResource(R.drawable.ic_lock2);

//        if (isPurchased) {
//            imgSound.setImageResource(R.drawable.ic_speaker);
//        } else
//            imgSound.setImageResource(R.drawable.ic_lock);
    }
}
