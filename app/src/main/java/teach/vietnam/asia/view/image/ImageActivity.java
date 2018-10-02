package teach.vietnam.asia.view.image;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.storage.StorageReference;
import com.jsibbold.zoomage.ZoomageView;

import butterknife.BindView;
import butterknife.OnClick;
import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.R;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.utils.GlideApp;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.base.BaseActivity;

public class ImageActivity extends BaseActivity<ImageActivity> {

    @BindView(R.id.zoomView)
    ZoomageView zoomView;

    @Override
    protected int getLayout() {
        return R.layout.image_activity;
    }

    @Override
    protected void initView() {

        String link = getIntent().getStringExtra(BaseTable.COL_TITLE);
        setData(link);
    }

    @OnClick(R.id.imgClose)
    public void actionClose() {
        finish();
    }

    public void setData(String link) {
        StorageReference fileRef = BaseApplication.storageRoot.child(link);
        GlideApp.with(BaseApplication.getInstance())
                .load(fileRef)
                .placeholder(R.drawable.loading)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("glideapp", "onLoadFailed: " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.i("glideapp", "onResourceReady");

                        return false;
                    }
                })
                .into(zoomView);
    }
}
