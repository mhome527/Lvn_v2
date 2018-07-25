package teach.vietnam.asia.view.alphabet;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.base.BaseViewHolder;

/**
 * Created by Administrator on 6/5/2017.
 */

public class AlphabetHolder extends BaseViewHolder {

    @BindView(R.id.tvAlphabet)
    TextView tvAlphabet;

    @BindView(R.id.tvAlphabetRight)
    TextView tvAlphabetRight;


    public AlphabetHolder(View itemView) {
        super(itemView);
    }
}
