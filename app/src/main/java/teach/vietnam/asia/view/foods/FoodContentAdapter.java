package teach.vietnam.asia.view.foods;

import android.view.View;

import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.WordEntity;
import teach.vietnam.asia.view.base.BaseAdapter;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class FoodContentAdapter extends BaseAdapter<FoodItemHolder> {

    private static String TAG = "FoodContentAdapter";

    private List<WordEntity> listData;

    @Override
    public int getItemLayout() {
        return R.layout.food_item;
    }

    @Override
    public FoodItemHolder onCreateView(View view) {
        return new FoodItemHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodItemHolder holder, int position) {
        holder.bind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        if (listData == null)
            return 0;
        return listData.size();
    }

    public void setData(List<WordEntity> listData) {
        this.listData = listData;
    }


}
