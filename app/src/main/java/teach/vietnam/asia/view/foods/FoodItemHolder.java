package teach.vietnam.asia.view.foods;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Utility;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class FoodItemHolder extends RecyclerView.ViewHolder {

    private final String TAG = "FoodItemHolder";
    ImageView imgSound;
    ImageView imgWord;
    TextView tvJp;
    TextView tvRomaji;

    public FoodItemHolder(final View itemView) {
        super(itemView);
        imgWord = (ImageView) itemView.findViewById(R.id.imgWord);
        imgSound = (ImageView) itemView.findViewById(R.id.imgSound);
        tvJp = (TextView) itemView.findViewById(R.id.tvJp);
        tvRomaji = (TextView) itemView.findViewById(R.id.tvRomaji);
    }

    public void bind(WordEntity entity) {
        imgSound.setImageResource(R.drawable.ic_speaker);

        tvJp.setText(entity.getVi());
        tvRomaji.setText(entity.getO1());

        String strImage = "f_" + entity.getImg();
        Log.i(TAG, "filename:" + strImage);
        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), strImage);
        if (resourceId > 0) {
            imgWord.setImageResource(resourceId);
        }

    }

}
