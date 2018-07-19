package teach.vietnam.asia.view.places;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.view.base.BaseHeaderExViewHolder;


public class PlaceHeaderHolder extends BaseHeaderExViewHolder {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.imgIcon)
    ImageView imgIcon;

    public PlaceHeaderHolder(View itemView) {
        super(itemView);
    }

    @Override
    public ImageView getIconArrow() {
        return imgIcon;
    }

    public void bind(PlaceGroupData data) {
        tvTitle.setText(data.getTitle());
    }
}
