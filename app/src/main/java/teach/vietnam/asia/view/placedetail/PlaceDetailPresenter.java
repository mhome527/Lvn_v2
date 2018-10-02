package teach.vietnam.asia.view.placedetail;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import java.nio.charset.StandardCharsets;

import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.db.dao.PlaceDao;
import teach.vietnam.asia.entity.PlaceEntity;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.action.ICallback;
import teach.vietnam.asia.view.base.BasePresenter;

public class PlaceDetailPresenter extends BasePresenter<PlaceDetailActivity> {
    private final String TAG = "PlaceDetailPresenter";

    PlaceDetailDao dao;
    PlaceDao placeDao;

    public PlaceDetailPresenter(PlaceDetailActivity activity) {
        super(activity);
        dao = new PlaceDetailDao(activity);
        placeDao = new PlaceDao(activity);

    }

    public PlaceEntity getData(int area, int type, int id) {
        return dao.getPlaceDetail(area, type, id);
    }

    public void downloadLink(final PlaceEntity entity, final ICallback iDownload) {
        final long ONE_MEGABYTE = 1024 * 1024;
//        String path = String.format("place/%1$s/%1$s/%1$s/%1$s.txt", entity.area, entity.type, entity.id, entity.id);
        String path = String.format(Constant.FIREBASE_PLACE_LINK, entity.area, entity.type, entity.id);
        StorageReference fileRef = BaseApplication.storageRoot.child(path);
        fileRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                String links = new String(bytes, StandardCharsets.UTF_8);
                Log.i(TAG, "downloadLink: \n" + links);
                if (links != null && !links.trim().equals("")) {
                    entity.imgLinks = links;

                    if (placeDao != null)
                        placeDao.updateData(entity);

                    if (activity != null && !activity.isFinishing())
                        iDownload.onComplete(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any error
                Log.e(TAG, "downloadLink ERROR: \n" + exception.getMessage());

            }
        });
    }
}
