package teach.vietnam.asia.view.dashboard.search;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.base.BaseChildExViewHolder;

public class SearchPlaceItemHolder extends BaseChildExViewHolder {

    @BindView(R.id.tvVn)
    TextView tvVn;

    @BindView(R.id.tvOther)
    TextView tvOther;

    @BindView(R.id.imgSpeak)
    ImageView imgSpeak;

    private SearchEntity entity;

    public SearchPlaceItemHolder(View itemView, final IActionSearch iActionSearch) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iActionSearch.onSearchClick(entity);
            }
        });
    }

    public void bind(SearchEntity entity) {
        this.entity = entity;
        tvVn.setText(entity.vn);
        tvOther.setText(entity.ot);
    }
}
