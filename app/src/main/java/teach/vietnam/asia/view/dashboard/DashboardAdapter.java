package teach.vietnam.asia.view.dashboard;

import android.view.View;

import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.DashboardEntity;
import teach.vietnam.asia.view.base.BaseAdapterView;
import teach.vietnam.asia.view.base.BaseViewHolder;

public class DashboardAdapter extends BaseAdapterView<BaseViewHolder> {

    List<DashboardEntity> items;
    IDashboardAction iDashboardAction;

    public DashboardAdapter(List<DashboardEntity> items, IDashboardAction iDashboardAction) {
        this.items = items;
        this.iDashboardAction = iDashboardAction;
    }

    @Override
    protected int getHeaderLayout(int viewType) {
        return R.layout.dashboard_header_item;
    }

    @Override
    protected int getFooterLayout(int viewType) {
        return R.layout.dashboard_footer_item;
    }

    @Override
    protected int getItemLayout(int viewType) {
        return R.layout.dashboard_item;
    }

    @Override
    protected int getSize() {
        if (items == null)
            return 0;
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 1)
            return TYPE_HEADER;
        else if (position == getSize() - 1 && getFooterLayout(TYPE_FOOTER) > 0)
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }

    @Override
    protected BaseViewHolder getHeaderView(View view) {
        return new DashboardHeaderHolder(view, iDashboardAction);
    }

    @Override
    protected BaseViewHolder getFooterView(View view) {
        return new DashboardFooterHolder(view, iDashboardAction);
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
        if (holder instanceof DashboardHeaderHolder)
            ((DashboardHeaderHolder) holder).bind(items.get(position));
        else if (holder instanceof DashboardItemHolder)
            ((DashboardItemHolder) holder).bind(items.get(position));
    }
}
