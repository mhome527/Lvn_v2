package teach.vietnam.asia.view.places;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.view.base.BaseChildExViewHolder;

public class PlaceItemHolder extends BaseChildExViewHolder {

    @BindView(R.id.tvVn)
    TextView tvVn;

    @BindView(R.id.tvOther)
    TextView tvOther;

    PlaceEntity entity;

    public PlaceItemHolder(View itemView, final IPlaceListener iPlaceListener) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iPlaceListener.onChildClick(entity);
            }
        });
    }

    public void bind(PlaceEntity entity) {
        this.entity = entity;
        tvVn.setText(entity.title);
        tvOther.setText(entity.ot);
    }
}
