package teach.vietnam.asia.view.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.DashboardEntity;

/**
 * Created by Administrator on 10/17/2016.
 */

public class DashboardAdapter extends BaseAdapter {

    private static String TAG = "DashboardAdapter";

    Context context;
    List<DashboardEntity> listData;
    LayoutInflater layoutinflater;

    public DashboardAdapter(Context context, List<DashboardEntity> listData) {
        this.context = context;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;

        if (convertView == null) {
            holderView = new HolderView();
            convertView = layoutinflater.inflate(R.layout.dashboard_item, parent, false);
            holderView.imgItem = (ImageView) convertView.findViewById(R.id.imgItem);
            holderView.tv = (TextView) convertView.findViewById(R.id.tvContent);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }

        DashboardEntity entity = listData.get(position);
        holderView.imgItem.setImageResource(entity.img);
        holderView.tv.setText(entity.text);
        return convertView;
    }

    public void setData(List<DashboardEntity> listData) {
        this.listData = listData;
    }

    static class HolderView {
        ImageView imgItem;
        TextView tv;
    }
}
