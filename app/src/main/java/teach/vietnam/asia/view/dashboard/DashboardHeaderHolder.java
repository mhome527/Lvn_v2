package teach.vietnam.asia.view.dashboard;

import android.view.View;

import teach.vietnam.asia.view.BaseViewHolder;

public class DashboardHeaderHolder extends BaseViewHolder {

    public DashboardHeaderHolder(View itemView, final IDashboardAction iDashboardAction) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iDashboardAction.onItemClick(getAdapterPosition());
            }
        });
    }
}
