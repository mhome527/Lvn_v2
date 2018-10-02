package teach.vietnam.asia.view.placeimage;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.storage.StorageReference;

import butterknife.BindView;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.GlideApp;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.action.IClickListener;
import teach.vietnam.asia.view.base.BaseViewHolder;

public class PlaceImageHolder extends BaseViewHolder {

    @BindView(R.id.imgView)
    ImageView imgView;

    public PlaceImageHolder(View itemView, final IClickListener iClickListener) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickListener.actionClick(view, getAdapterPosition());
            }
        });
    }

    public void bind(String link) {
//        StorageReference fileRef = BaseApplication.storageRoot.child(link);
//        String path = String.format(Constant.FIREBASE_PLACE_IMG, )
//        StorageReference fileRef = BaseApplication.storageRoot.child("place/1/1/1/place_1_1_dinh_doc_lap.png");
        StorageReference fileRef = BaseApplication.storageRoot.child(link);
        GlideApp.with(BaseApplication.getInstance())
                .load(fileRef)
                .placeholder(R.drawable.loading)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        tvLoadStatus.setText("Error loading image: " + e.getMessage());
                        Log.e("glideapp", "onLoadFailed: " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        tvLoadStatus.setText("Image loaded in onCreate");
                        Log.i("glideapp", "onResourceReady");

                        return false;
                    }
                })
                .into(imgView);
//                        .placeholder(R.drawable.loading)

    }
}
