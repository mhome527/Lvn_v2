package teach.vietnam.asia.view.places;

import android.content.Context;
import android.database.Cursor;

import teach.vietnam.asia.db.dao.BaseDao;

public class PlaceDao extends BaseDao<PlaceEntity> {
    public PlaceDao(Context context) {
        super(context);
    }

    @Override
    protected PlaceEntity fetch(Cursor cursor) {
        return null;
    }
}
