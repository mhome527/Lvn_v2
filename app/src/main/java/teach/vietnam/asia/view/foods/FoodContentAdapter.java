package teach.vietnam.asia.view.foods;

import android.view.View;

import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.FoodEntity;
import teach.vietnam.asia.view.action.IActionList;
import teach.vietnam.asia.view.base.BaseAdapter;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class FoodContentAdapter extends BaseAdapter<FoodItemHolder> {

    private static String TAG = "FoodContentAdapter";

    private List<FoodEntity> listData;
    IActionList iActionList;
    public boolean isPurchased;

    public FoodContentAdapter(boolean isPurchased, IActionList iActionList) {
        this.iActionList = iActionList;
        this.isPurchased = isPurchased;
    }

    @Override
    public int getItemLayout() {
        return R.layout.food_item;
    }

    @Override
    public FoodItemHolder onCreateView(View view) {
        return new FoodItemHolder(view, isPurchased, iActionList);
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

    public void setData(List<FoodEntity> listData) {
        this.listData = listData;
    }


}
