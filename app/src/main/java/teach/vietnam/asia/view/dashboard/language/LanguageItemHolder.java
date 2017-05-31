package teach.vietnam.asia.view.dashboard.language;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.LanguageEntity;
import teach.vietnam.asia.view.BaseViewHolder;

/**
 * Created by HuynhTD on 5/26/2017.
 */

public class LanguageItemHolder extends BaseViewHolder {


    @BindView(R.id.imgCheckEn)
    ImageView imgCheckEn;

    @BindView(R.id.imgLanguage)
    ImageView imgLanguage;

    @BindView(R.id.tvLanguage)
    TextView tvLanguage;


    public LanguageItemHolder(View itemView) {
        super(itemView);
    }

    public void setData(final LanguageEntity entity, String lang, final LanguageAdapter.OnItemClickListener onItemClickListener) {
        if (entity.getLang().equals(lang))
            imgCheckEn.setBackgroundResource(R.drawable.bg_cycle_check_red);
        else
            imgCheckEn.setBackgroundResource(R.drawable.bg_cycle_un_check_red);

        tvLanguage.setText(entity.getText());
        imgLanguage.setBackgroundResource(entity.getRes());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(entity.getLang());
            }
        });

    }
}
