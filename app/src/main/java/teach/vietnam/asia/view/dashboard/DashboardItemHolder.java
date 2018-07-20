package teach.vietnam.asia.view.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.DashboardEntity;
import teach.vietnam.asia.view.BaseViewHolder;

public class DashboardItemHolder extends BaseViewHolder {

    @BindView(R.id.tvContent)
    TextView tvContent;

    @BindView(R.id.imgItem)
    ImageView imgItem;


    public DashboardItemHolder(View itemView, final IDashboardAction iDashboardAction) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iDashboardAction.onItemClick(getAdapterPosition());
            }
        });
    }

    public void bind(DashboardEntity entity) {
        imgItem.setImageResource(entity.img);
        tvContent.setText(entity.text);
    }
}
