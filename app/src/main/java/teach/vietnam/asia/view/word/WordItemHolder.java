package teach.vietnam.asia.view.word;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.base.BaseViewHolder;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class WordItemHolder extends BaseViewHolder{

    @BindView(R.id.imgWord)
    ImageView imgWord;

    @BindView(R.id.imgSound)
    ImageView imgSound;

    @BindView(R.id.tvJp)
    TextView tvJp;

    @BindView(R.id.tvRomaji)
    TextView tvRomaji;

    public WordItemHolder(final View itemView) {
        super(itemView);
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
