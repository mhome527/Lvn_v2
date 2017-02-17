package teach.vietnam.asia.view.color;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.utils.Utility;

public class ColorAdapter extends BaseAdapter {

    private Context context;
    private String[] colorName;
    private String[] colorValue;
    private LayoutInflater layoutInflater;

    public ColorAdapter(Context context, String[] colorName, String[] colorValue) {
        this.context = context;
        this.colorName = colorName;
        this.colorValue = colorValue;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return colorValue.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    // public View getView(int pos, View convertView, ViewGroup arg2) {

    // if (convertView == null) {
    // convertView = layoutInflater.inflate(R.layout.taxi_item, null);
    // }
    // Override this method according to your need
    @SuppressLint({"InflateParams", "DefaultLocale"})
    public View getView(int index, View convertView, ViewGroup viewGroup) {
        int resourceId;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.color_item, null);
        }
        try {

            TextView tvColorName = (TextView) convertView.findViewById(R.id.tvColorName);
            ImageView imgColor = (ImageView) convertView.findViewById(R.id.imgColor);

            tvColorName.setText(colorName[index]);
            // tvColorValue.setBackgroundColor(colorValue[index]);
            resourceId = Utility.getResourcesID(context, colorValue[index]);
            if (resourceId > 0) {
                imgColor.setImageResource(resourceId);
            } else
                Log.i(ColorAdapter.class, "getView color not found: " + colorValue[index]);

        } catch (Exception e) {
            Log.e(ColorAdapter.class, "number error:" + e.getMessage());
        }
        return convertView;
    }
}