package teach.vietnam.asia.view.dashboard.search;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Utility;
import teach.vietnam.asia.view.base.BaseChildExViewHolder;

public class SearchItem1Holder extends BaseChildExViewHolder {

    @BindView(R.id.tvVn)
    TextView tvVn;

    @BindView(R.id.tvOther)
    TextView tvOther;

    @BindView(R.id.imgPlace)
    ImageView imgPlace;

    private SearchEntity entity;

    public SearchItem1Holder(View itemView, final IActionSearch iActionSearch) {
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

        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), entity.image);
        if (resourceId > 0) {
            imgPlace.setImageResource(resourceId);
        }
    }
}
