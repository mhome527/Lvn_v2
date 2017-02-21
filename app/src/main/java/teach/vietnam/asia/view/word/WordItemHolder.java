package teach.vietnam.asia.view.word;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.utils.Utility;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class WordItemHolder extends RecyclerView.ViewHolder {

    ImageView imgSound;
    ImageView imgWord;
    TextView tvJp;
    TextView tvRomaji;

    public WordItemHolder(final View itemView) {
        super(itemView);
        imgWord = (ImageView) itemView.findViewById(R.id.imgWord);
        imgSound = (ImageView) itemView.findViewById(R.id.imgSound);
        tvJp = (TextView) itemView.findViewById(R.id.tvJp);
        tvRomaji = (TextView) itemView.findViewById(R.id.tvRomaji);
    }

    public void bind(int position, boolean isPurchased, WordEntity entity) {
        if(isPurchased || position < Constant.TRIAL)
            imgSound.setImageResource(R.drawable.ic_speaker);
        else
            imgSound.setImageResource(R.drawable.ic_lock);

        tvJp.setText(entity.getVi());
        tvRomaji.setText(entity.getO1());

//        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), "ic_back");
        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), entity.getImg());
        if (resourceId > 0) {
            imgWord.setImageResource(resourceId);
        }

    }

}
