package teach.vietnam.asia.view.dashboard.search;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.base.BaseHeaderExViewHolder;


public class SearchHeaderHolder extends BaseHeaderExViewHolder {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.imgIcon)
    ImageView imgIcon;

    public SearchHeaderHolder(View itemView) {
        super(itemView);
    }

    @Override
    public ImageView getIconArrow() {
        return imgIcon;
    }

    public void bind(SearchGroupData data) {
        tvTitle.setText(data.getTitle());
    }
}
