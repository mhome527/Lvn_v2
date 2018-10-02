package teach.vietnam.asia.view.dashboard;

import android.view.View;

import teach.vietnam.asia.view.base.BaseViewHolder;

public class DashboardFooterHolder extends BaseViewHolder {

    public DashboardFooterHolder(View itemView, final IDashboardAction iDashboardAction) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iDashboardAction.onItemClick(getAdapterPosition());
            }
        });
    }
}
