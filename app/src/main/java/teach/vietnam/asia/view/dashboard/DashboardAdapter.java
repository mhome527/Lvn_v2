package teach.vietnam.asia.view.dashboard;

import android.view.View;

import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.DashboardEntity;
import teach.vietnam.asia.view.BaseAdapterView;
import teach.vietnam.asia.view.BaseViewHolder;

public class DashboardAdapter extends BaseAdapterView<BaseViewHolder> {

    List<DashboardEntity> items;
    IDashboardAction iDashboardAction;

    public DashboardAdapter(List<DashboardEntity> items, IDashboardAction iDashboardAction) {
        this.items = items;
        this.iDashboardAction = iDashboardAction;
    }

    @Override
    protected int getHeaderLayout() {
        return R.layout.dashboard_header_item;
    }

    @Override
    protected int getFooterLayout() {
        return R.layout.dashboard_footer_item;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.dashboard_item;
    }

    @Override
    protected BaseViewHolder getHeaderView(View view) {
        return new DashboardHeaderHolder(view, iDashboardAction);
    }

    @Override
    protected BaseViewHolder getFooterView(View view) {
        return new DashboardFooterHolder(view);
    }

    @Override
    protected BaseViewHolder getItemView(View view) {
        return new DashboardItemHolder(view, iDashboardAction);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof DashboardItemHolder)
            ((DashboardItemHolder) holder).bind(items.get(position - 1));
    }
}
