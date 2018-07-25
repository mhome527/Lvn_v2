package teach.vietnam.asia.view.dashboard.search;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.base.BaseViewHolder;
import teach.vietnam.asia.view.action.IClickListener;

public class SearchWordItemHolder extends BaseViewHolder {

    @BindView(R.id.tvVn)
    TextView tvVn;

    @BindView(R.id.tvOther)
    TextView tvOther;

    @BindView(R.id.imgSpeak)
    ImageView imgSpeak;

    private SearchEntity entity;

    public SearchWordItemHolder(View itemView, final IClickListener iClickListener) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickListener.actionClick(view, getAdapterPosition());
            }
        });
    }

    public void setData(SearchEntity entity) {
        this.entity = entity;
        tvVn.setText(entity.vn);
        tvOther.setText(entity.ot);
    }
}
