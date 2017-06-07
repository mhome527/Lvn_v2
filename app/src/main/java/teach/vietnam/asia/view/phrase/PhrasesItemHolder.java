package teach.vietnam.asia.view.phrase;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.view.BaseViewHolder;

/**
 * Created by HuynhTD on 5/10/2017.
 */

public class PhrasesItemHolder extends BaseViewHolder {
    @BindView(R.id.tvViet)
    TextView tvViet;

    @BindView(R.id.tvOther)
    TextView tvOther;

    @BindView(R.id.imgSpeak)
    ImageView imgSpeak;

    public PhrasesItemHolder(View itemView) {
        super(itemView);
    }

    public void setData(WordEntity item, boolean isSpeak) {

        tvViet.setText(Html.fromHtml(item.getVi()));
        tvOther.setText(Html.fromHtml(item.getO1()));
        if (isSpeak || item.getNum() < Constant.TRIAL) {
            imgSpeak.setImageResource(R.drawable.ic_speaker);
        } else
            imgSpeak.setImageResource(R.drawable.ic_lock);

    }
}
