package teach.vietnam.asia.view.placeimage;

import android.view.View;

import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.view.action.IClickListener;
import teach.vietnam.asia.view.base.BaseAdapterView;
import teach.vietnam.asia.view.base.BaseViewHolder;


public class PlaceImageAdapter extends BaseAdapterView<BaseViewHolder> {

    private List<String> images;
    IClickListener iClickListener;

    public PlaceImageAdapter(IClickListener iClickListener, List images) {
        this.iClickListener = iClickListener;
        this.images = images;
    }

    @Override
    protected int getItemLayout(int viewType) {
        if (viewType == TYPE_ITEM)
            return R.layout.place_image_item;
        else
            return -1;

    }

    @Override
    protected BaseViewHolder getItemView(View view, int viewType) {
        if (viewType == TYPE_ITEM)
            return new PlaceImageHolder(view, iClickListener);
        else
            return new BaseViewHolder(view);
    }


    @Override
    protected List getListData() {
        return images;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof PlaceImageHolder)
            ((PlaceImageHolder) holder).bind(images.get(position));
    }

    public String getItem(int pos) {
        return images.get(pos);
    }
//    public void setImages(String[] images) {
//        this.images = images;
//    }
}
